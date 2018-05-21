package com.bulain.activiti.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.activiti.model.OrderMaster;
import com.bulain.activiti.pojo.OrderSearch;
import com.bulain.common.dataset.DataSet;
import com.bulain.common.test.DaoTestCase;

@DataSet(file = "test-data/init_order_masters.xml")
public class OrderMasterMapperTest extends DaoTestCase {
    @Autowired
    private OrderMasterMapper orderMasterMapper;

    @Test
    public void testSelectByName() {
        OrderMaster orderMaster = orderMasterMapper.selectByName("name_102");

        assertNotNull(orderMaster);

        assertEquals("name_102", orderMaster.getName());
        assertEquals("note_102", orderMaster.getNote());
    }

    @Test
    public void testFind() {
        OrderSearch search = new OrderSearch();
        search.setName("name_page");
        search.setEffDateFrom(null);
        search.setEffDateTo(null);
        List<OrderMaster> find = orderMasterMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        OrderSearch search = new OrderSearch();
        search.setName("name_page");
        search.setEffDateFrom(null);
        search.setEffDateTo(null);
        Long count = orderMasterMapper.count(search);
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
        List<OrderMaster> page = orderMasterMapper.page(search);
        assertEquals(3, page.size());
    }

    public void setOrderMasterMapper(OrderMasterMapper orderMasterMapper) {
        this.orderMasterMapper = orderMasterMapper;
    }

}
