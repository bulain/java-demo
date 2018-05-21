package com.bulain.mybatis.demo.dao;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.GenericXmlApplicationContext;

import com.bulain.mybatis.demo.model.Blog;

public class MybatisPtDemo {

    public static void main(String[] args) throws InterruptedException {
        ApplicationContext applicationContext = new GenericXmlApplicationContext(
                "classpath*:spring/applicationContext-*.xml");
        BlogMapper blogMapper = applicationContext.getBean(BlogMapper.class);

        ExecutorService executorService = Executors.newFixedThreadPool(32);
        CountDownLatch before = new CountDownLatch(6);
        {
            executorService.submit(new PtCallable("insert", blogMapper, 1, before));
            executorService.submit(new PtCallable("insertSelective", blogMapper, 1, before));
            executorService.submit(new PtCallable("updateByPrimaryKey", blogMapper, 1, before));
            executorService.submit(new PtCallable("updateByPrimaryKeySelective", blogMapper, 1, before));
            executorService.submit(new PtCallable("selectByPrimaryKey", blogMapper, 1, before));
            executorService.submit(new PtCallable("deleteByPrimaryKey", blogMapper, 1, before));
        }
        before.await();

        int nThreads = 32;
        int times = 100;
        CountDownLatch latch = new CountDownLatch(nThreads * 1);
        for (int i = 0; i < nThreads; i++) {
            //executorService.submit(new PtCallable("insert", blogMapper, times, latch));
            //executorService.submit(new PtCallable("insertSelective", blogMapper, times, latch));
            //executorService.submit(new PtCallable("updateByPrimaryKey", blogMapper, times, latch));
            //executorService.submit(new PtCallable("updateByPrimaryKeySelective", blogMapper, times, latch));
            //executorService.submit(new PtCallable("selectByPrimaryKey", blogMapper, times, latch));
            //executorService.submit(new PtCallable("deleteByPrimaryKey", blogMapper, times, latch));
            executorService.submit(new PtCallable("bulkInsert", blogMapper, times, latch));
        }

        latch.await();
        executorService.shutdown();

    }

}

class PtCallable implements Callable<Integer> {

    private Logger logger = LoggerFactory.getLogger(getClass());

    private CountDownLatch latch;
    private BlogMapper blogMapper;
    private int times;
    private String cmd;

    public PtCallable(String cmd, BlogMapper blogMapper, int times, CountDownLatch latch) {
        this.cmd = cmd;
        this.blogMapper = blogMapper;
        this.times = times;
        this.latch = latch;
    }

    @Override
    public Integer call() throws Exception {

        logger.info("{} {}-start", Thread.currentThread(), cmd);

        String threadName = Thread.currentThread().getName();
        Date startAt = new Date();

        try {

            List<Blog> list = new ArrayList<Blog>();
            for (int i = 0; i < times; i++) {
                if ("insert".equals(cmd)) {
                    Blog blog = new Blog();
                    blog.setTitle(threadName);
                    blog.setDescr("descr-" + i);
                    blog.setCreatedVia("Thread");
                    blog.setActiveFlag("Y");
                    blog.setCreatedAt(new Date());
                    blog.setCreatedBy("PT");
                    blogMapper.insert(blog);
                } else if ("insertSelective".equals(cmd)) {
                    Blog blog = new Blog();
                    blog.setTitle(threadName);
                    blog.setDescr("descr-" + i);
                    blog.setCreatedVia("Thread");
                    blog.setActiveFlag("Y");
                    blog.setCreatedAt(new Date());
                    blog.setCreatedBy("PT");
                    blogMapper.insertSelective(blog);
                } else if ("updateByPrimaryKey".equals(cmd)) {
                    Blog blog = new Blog();
                    blog.setId(Long.valueOf(i));
                    blog.setTitle(threadName);
                    blog.setDescr("descr-" + i);
                    blog.setCreatedVia("Thread");
                    blog.setActiveFlag("Y");
                    blog.setUpdatedAt(new Date());
                    blog.setUpdatedBy("PT");
                    blogMapper.updateByPrimaryKey(blog);
                } else if ("updateByPrimaryKeySelective".equals(cmd)) {
                    Blog blog = new Blog();
                    blog.setId(Long.valueOf(i));
                    blog.setTitle(threadName);
                    blog.setDescr("descr-" + i);
                    blog.setCreatedVia("Thread");
                    blog.setActiveFlag("Y");
                    blog.setUpdatedAt(new Date());
                    blog.setUpdatedBy("PT");
                    blogMapper.updateByPrimaryKeySelective(blog);
                } else if ("selectByPrimaryKey".equals(cmd)) {
                    blogMapper.selectByPrimaryKey(Long.valueOf(i));
                } else if ("deleteByPrimaryKey".equals(cmd)) {
                    blogMapper.deleteByPrimaryKey(Long.valueOf(i));
                } else if ("bulkInsert".equals(cmd)) {
                    Blog blog = new Blog();
                    blog.setTitle(threadName);
                    blog.setDescr("descr-" + i);
                    blog.setCreatedVia("Thread");
                    blog.setActiveFlag("Y");
                    blog.setCreatedAt(new Date());
                    blog.setCreatedBy("PT");
                    list.add(blog);

                    if (i > 0 && i % 1000 == 0) {
                        blogMapper.bulkInsert(list);
                        list.clear();
                    }
                }
            }

            if ("bulkInsert".equals(cmd)) {
                blogMapper.bulkInsert(list);
            }
        } catch (Exception e) {
            logger.error("call()-", e);
        }

        Date endAt = new Date();
        double during = (endAt.getTime() - startAt.getTime()) / 1000d;
        logger.info("{} {}-end: {}s", new Object[]{Thread.currentThread(), cmd, during});

        latch.countDown();

        return 0;
    }

}
