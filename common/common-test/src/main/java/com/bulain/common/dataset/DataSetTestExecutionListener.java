package com.bulain.common.dataset;

import java.lang.reflect.Method;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;

import com.bulain.common.db.Import;

public class DataSetTestExecutionListener extends AbstractTestExecutionListener {
    private static final Logger logger = LoggerFactory.getLogger(DataSetTestExecutionListener.class);
    
    public void beforeTestClass(TestContext testContext) {
        SeedDataSet seedDataSet = getSeedDataSet(testContext);
        if (seedDataSet != null) {
            String file = seedDataSet.file();
            String setUp = seedDataSet.setUp();
            executeOperation(testContext, file, setUp);
        }
    }

    public void beforeTestMethod(TestContext testContext) {
        DataSet dataSet = getDataSet(testContext);
        if (dataSet != null) {
            String file = dataSet.file();
            String setUp = dataSet.setUp();
            executeOperation(testContext, file, setUp);
        }
    }

    public void afterTestMethod(TestContext testContext) {
        DataSet dataSet = getDataSet(testContext);
        if (dataSet != null) {
            String file = dataSet.file();
            String tearDown = dataSet.tearDown();
            executeOperation(testContext, file, tearDown);
        }
    }

    public void afterTestClass(TestContext testContext) {
        SeedDataSet seedDataSet = getSeedDataSet(testContext);
        if (seedDataSet != null) {
            String file = seedDataSet.file();
            String tearDown = seedDataSet.tearDown();
            executeOperation(testContext, file, tearDown);
        }
    }

    private SeedDataSet getSeedDataSet(TestContext testContext) {
        Class<?> testClass = testContext.getTestClass();
        SeedDataSet seedDataSet = AnnotationUtils.findAnnotation(testClass, SeedDataSet.class);
        return seedDataSet;
    }

    private DataSet getDataSet(TestContext testContext) {
        Method method = testContext.getTestMethod();
        DataSet dataSet = AnnotationUtils.getAnnotation(method, DataSet.class);

        if (dataSet == null) {
            Class<?> testClass = testContext.getTestClass();
            dataSet = AnnotationUtils.findAnnotation(testClass, DataSet.class);
        }

        return dataSet;
    }

    private void executeOperation(TestContext testContext, String file, String operation) {
        ApplicationContext applicationContext = testContext.getApplicationContext();
        Import importSrv = applicationContext.getBean(Import.class);
        if (file == null || file.length() == 0) {
            file = getDataSetFile(testContext);
        }
        
        logger.info("import file: {}, operation: {}", file, operation);
        
        importSrv.importFlatXml(file, operation);
    }

    private String getDataSetFile(TestContext testContext) {
        Class<?> testClass = testContext.getTestClass();
        String className = testClass.getName();
        String file = className.replace('.', '/');
        return file;
    }
}
