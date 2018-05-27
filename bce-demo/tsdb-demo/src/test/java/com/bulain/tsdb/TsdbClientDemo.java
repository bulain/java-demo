package com.bulain.tsdb;

import java.util.Arrays;
import java.util.List;

import org.joda.time.DateTime;
import org.junit.Before;
import org.junit.Test;

import com.baidubce.BceClientConfiguration;
import com.baidubce.auth.DefaultBceCredentials;
import com.baidubce.services.tsdb.TsdbClient;
import com.baidubce.services.tsdb.TsdbConstants;
import com.baidubce.services.tsdb.model.Aggregator;
import com.baidubce.services.tsdb.model.Datapoint;
import com.baidubce.services.tsdb.model.Filters;
import com.baidubce.services.tsdb.model.GetFieldsResponse;
import com.baidubce.services.tsdb.model.GetMetricsResponse;
import com.baidubce.services.tsdb.model.GetTagsResponse;
import com.baidubce.services.tsdb.model.GroupBy;
import com.baidubce.services.tsdb.model.Query;
import com.baidubce.services.tsdb.model.QueryDatapointsResponse;
import com.baidubce.util.JsonUtils;
import com.fasterxml.jackson.core.JsonProcessingException;

public class TsdbClientDemo {

    private TsdbClient tsdbClient;

    @Before
    public void setUp() {
        String ACCESS_KEY_ID = "";
        String SECRET_ACCESS_KEY = "";
        String ENDPOINT = "tsdbdemo.tsdb.iot.gz.baidubce.com";

        // 创建配置
        BceClientConfiguration config = new BceClientConfiguration().withCredentials(
                new DefaultBceCredentials(ACCESS_KEY_ID, SECRET_ACCESS_KEY)).withEndpoint(ENDPOINT);

        // 初始化一个TsdbClient
        tsdbClient = new TsdbClient(config);
    }

    @Test
    public void testMetric() throws JsonProcessingException {
        // 获取Metric
        GetMetricsResponse response = tsdbClient.getMetrics();

        // 打印结果
        System.out.println(JsonUtils.toJsonPrettyString(response));
    }

    @Test
    public void testTag() throws JsonProcessingException {
        String METRIC = "wind"; // 设置需要获取tag的metric

        // 获取标签
        GetTagsResponse response = tsdbClient.getTags(METRIC);

        // 打印结果
        System.out.println(JsonUtils.toJsonPrettyString(response));
    }

    @Test
    public void testField() throws JsonProcessingException {
        String METRIC = "wind";

        // 获取Field
        GetFieldsResponse response = tsdbClient.getFields(METRIC);

        // 打印结果
        System.out.println(JsonUtils.toJsonPrettyString(response));

    }

    @Test
    public void testQuery() throws JsonProcessingException {
        String METRIC = "wind";

        DateTime start = new DateTime(2015, 1, 1, 0, 0);
        DateTime end = new DateTime(2016, 1, 1, 0, 0);

        // 构造查询对象
        List<Query> queries = Arrays.asList(new Query()
                // 创建Query对象
                .withMetric(METRIC)
                // 设置metric
                .withFilters(new Filters() // 创建Filters对象
                        //.withRelativeStart("2 seconds ago")) // 设置相对的开始时间，这里设置为2秒前
                        .withAbsoluteStart(start.getMillis()).withAbsoluteEnd(end.getMillis()))
                .addGroupBy(new GroupBy() // 创建GroupBy对象
                        .withName(TsdbConstants.GROUP_BY_NAME_TIME) // 设置分组方式为Time
                        .withTimeRangeSize("60 minutes") // 设置目标时长
                        .withGroupCount(1)) // 设置分组个数
                .withLimit(100) // 设置返回数据点数目限制
                .addAggregator(new Aggregator() // 创建Aggregator对象
                        .withName(TsdbConstants.AGGREGATOR_NAME_SUM) // 设置聚合函数为Sum
                        .withSampling("60 minutes"))); // 设置采样

        // 查询数据
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);
        System.out.println(JsonUtils.toJsonPrettyString(response));
    }

    @Test
    public void testWrite() {
        String METRIC = "cpu_idle"; // metric
        String TAG_KEY = "server"; // 标签名称
        String TAG_VALUE = "server1"; // 标签值
        String FIELD = "temperature"; // 域

        // 创建数据点
        {
            List<Datapoint> datapoints = Arrays.asList(new Datapoint().withMetric(METRIC) // 设置Metric
                    .withField(FIELD) // 设置数据点域，可选，不填使用默认域名 value
                    .addTag(TAG_KEY, TAG_VALUE) // 设置Tag
                    .addDoubleValue(System.currentTimeMillis(), 0.1)); // 添加一个数据点
            tsdbClient.writeDatapoints(datapoints);
        }

        {
            Datapoint datapoint = new Datapoint().withMetric(METRIC) // 设置Metric
                    .withField(FIELD) // 设置数据点域，可选，不填使用默认域名 value
                    .addTag(TAG_KEY, TAG_VALUE) // 设置Tag
                    .addDoubleValue(System.currentTimeMillis(), 0.1) // 添加一个数据点
                    .addDoubleValue(System.currentTimeMillis() + 1, 0.2); // 再添加一个数据点
            tsdbClient.writeDatapoints(Arrays.asList(new Datapoint[]{datapoint}));
        }

        // 添加Double类型数据点
        {
            Datapoint datapoint = new Datapoint().withMetric(METRIC) // 设置Metric
                    .withField(FIELD) // 设置数据点域，可选，不填使用默认域名 value
                    .addTag(TAG_KEY, TAG_VALUE) // 设置Tag
                    .addDoubleValue(System.currentTimeMillis(), 0.1); // 添加Double类型数据点
            tsdbClient.writeDatapoints(Arrays.asList(new Datapoint[]{datapoint}));
        }

        // 添加Long类型数据点
        {
            Datapoint datapoint = new Datapoint().withMetric(METRIC) // 设置Metric
                    .withField("LField").addTag(TAG_KEY, TAG_VALUE) // 设置Tag
                    .addLongValue(System.currentTimeMillis(), 10L); // 添加Long类型数据点
            tsdbClient.writeDatapoints(Arrays.asList(new Datapoint[]{datapoint}));
        }

        // 添加String类型数据点
        {
            Datapoint datapoint = new Datapoint().withMetric(METRIC) // 设置Metric
                    .withField("SField").addTag(TAG_KEY, TAG_VALUE) // 设置Tag
                    .addStringValue(System.currentTimeMillis(), "string"); // 添加String类型数据点
            tsdbClient.writeDatapoints(Arrays.asList(new Datapoint[]{datapoint}));
        }

        // 添加byte[]类型数据点
        {
            Datapoint datapoint = new Datapoint().withMetric(METRIC) // 设置Metric
                    .withField("BField").addTag(TAG_KEY, TAG_VALUE) // 设置Tag
                    .addBytesValue(System.currentTimeMillis(), new byte[]{0x01, 0x02}); // 添加byte[]类型数据点
            tsdbClient.writeDatapoints(Arrays.asList(new Datapoint[]{datapoint}));
        }
    }

    @Test
    public void testQueryCpuIdle() throws JsonProcessingException {

        String METRIC = "cpu_idle";
        String FIELD = "temperature"; // 域
        
        DateTime start = new DateTime(2015, 1, 1, 0, 0);

        // 构造查询对象
        List<Query> queries = Arrays.asList(new Query()
                .withMetric(METRIC)
                .withField(FIELD)
                .withFilters(new Filters()
                        .withAbsoluteStart(start.getMillis()))
                .withLimit(100));
               

        // 查询数据
        QueryDatapointsResponse response = tsdbClient.queryDatapoints(queries);
        System.out.println(JsonUtils.toJsonPrettyString(response));
    
    }
}
