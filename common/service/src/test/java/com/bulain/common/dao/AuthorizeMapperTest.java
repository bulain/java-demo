package com.bulain.common.dao;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import com.bulain.common.dataset.DataSet;
import com.bulain.common.page.Page;
import com.bulain.common.test.ServiceTestCase;
import com.bulain.common.model.Authorize;
import com.bulain.common.pojo.AuthorizeSearch;

@DataSet(file = "test-data/init_authorizes.xml")
public class AuthorizeMapperTest extends ServiceTestCase {
    @Autowired
    private AuthorizeMapper authorizeMapper;

    @Test
    public void testDeleteByPrimaryKey() {
        int deleteByPrimaryKey = authorizeMapper.deleteByPrimaryKey(Long.valueOf(101));
        assertEquals(1, deleteByPrimaryKey);
    }

    @Test
    public void testInsert() {
        Authorize record = new Authorize();
        record.setController("controller");
        record.setAction("action");
        record.setPermission("permission");
        int insert = authorizeMapper.insert(record);
        assertEquals(1, insert);
    }

    @Test
    public void testInsertSelective() {
        Authorize record = new Authorize();
        record.setController("controller");
        record.setAction("action");
        record.setPermission("permission");
        int insert = authorizeMapper.insertSelective(record);
        assertEquals(1, insert);
    }

    @Test
    public void testSelectByPrimaryKey() {
        Authorize selectByPrimaryKey = authorizeMapper.selectByPrimaryKey(Long.valueOf(102));
        assertNotNull(selectByPrimaryKey);

        assertEquals("controller_102", selectByPrimaryKey.getController());
        assertEquals("action_102", selectByPrimaryKey.getAction());
        assertEquals("permission_102", selectByPrimaryKey.getPermission());
    }

    @Test
    public void testUpdateByPrimaryKeySelective() {
        Authorize record = new Authorize();
        record.setId(Long.valueOf(103));
        record.setController("controller-updated");
        record.setAction("action-updated");
        record.setPermission("permission-updated");
        int updateByPrimaryKeySelective = authorizeMapper.updateByPrimaryKeySelective(record);
        assertEquals(1, updateByPrimaryKeySelective);
    }

    @Test
    public void testUpdateByPrimaryKey() {
        Authorize record = new Authorize();
        record.setId(Long.valueOf(104));
        record.setController("controller-updated");
        record.setAction("action-updated");
        record.setPermission("permission-updated");
        int updateByPrimaryKey = authorizeMapper.updateByPrimaryKey(record);
        assertEquals(1, updateByPrimaryKey);
    }

    @Test
    public void testFind() {
        AuthorizeSearch search = new AuthorizeSearch();
        search.setController("controller_page");
        search.setAction("action_page");
        List<Authorize> find = authorizeMapper.find(search);
        assertEquals(3, find.size());
    }

    @Test
    public void testCount() {
        AuthorizeSearch search = new AuthorizeSearch();
        search.setController("controller_page");
        search.setAction("action_page");
        Long count = authorizeMapper.count(search);
        assertEquals(Long.valueOf(3), count);
    }

    @Test
    public void testPage() {
        AuthorizeSearch search = new AuthorizeSearch();
        search.setController("controller_page");
        search.setAction("action_page");
        Page page = new Page();
        page.setCount(10);
        search.setHigh(page.getHigh());
        search.setLow(page.getLow());
        List<Authorize> listAuthorize = authorizeMapper.page(search);
        assertEquals(3, listAuthorize.size());
    }

}
