package com.bulain.jasypt.ctrl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.sql.DataSource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/jndi")
public class JndiCtrl {

    @Autowired
    @Qualifier("dataSource1")
    private DataSource dataSource1;
    @Autowired
    @Qualifier("dataSource2")
    private DataSource dataSource2;

    @ResponseBody
    @RequestMapping(value = "tomcat", method = {RequestMethod.GET, RequestMethod.POST})
    public String tomcat() throws SQLException {

        Connection conn = dataSource1.getConnection();
        PreparedStatement ps = conn.prepareStatement("select 'tomcat'");
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        String str = resultSet.getString(1);

        return str;
    }
    
    @ResponseBody
    @RequestMapping(value = "hikari", method = {RequestMethod.GET, RequestMethod.POST})
    public String hikari() throws SQLException {

        Connection conn = dataSource2.getConnection();
        PreparedStatement ps = conn.prepareStatement("select 'hikari'");
        ResultSet resultSet = ps.executeQuery();
        resultSet.next();
        String str = resultSet.getString(1);

        return str;
    }

}
