package com.bulain.activiti.dao;

import static org.junit.Assert.assertEquals;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.activiti.model.OrderHis;
import com.bulain.activiti.pojo.OrderSearch;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.DaoTestCase;

@DataSet(file = "test-data/init_order_hiss.xml")
public class OrderHisMapperTest extends DaoTestCase {
    @Autowired
    private OrderHisMapper orderHisMapper;

    @Test
    public void testFind() {
        OrderSearch search = new OrderSearch();
        search.setName("name_page");
        search.setEffDateFrom(null);
        search.setEffDateTo(null);
        List<OrderHis> find = orderHisMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        OrderSearch search = new OrderSearch();
        search.setName("name_page");
        search.setEffDateFrom(null);
        search.setEffDateTo(null);
        Long count = orderHisMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        OrderSearch search = new OrderSearch();
        search.setName("name_page");
        search.setEffDateFrom(null);
        search.setEffDateTo(null);
        search.setLow(0);
        search.setHigh(20);
        List<OrderHis> page = orderHisMapper.page(search);
        assertEquals(3, page.size());
    }

    public void setOrderHisMapper(OrderHisMapper orderHisMapper) {
        this.orderHisMapper = orderHisMapper;
    }

}
