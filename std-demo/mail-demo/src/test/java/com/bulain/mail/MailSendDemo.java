package com.bulain.mail;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.annotation.ExcelProperty;
import com.alibaba.excel.read.listener.PageReadListener;
import com.alibaba.fastjson.JSON;
import lombok.Data;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ClassPathResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;

import java.io.InputStream;

@Slf4j
@Disabled
@ExtendWith(SpringExtension.class)
@TestExecutionListeners(value = {DependencyInjectionTestExecutionListener.class})
@ContextConfiguration(locations = {"classpath*:spring/applicationContext-*.xml",
        "classpath:spring/propertyConfigurer-test.xml"})
public class MailSendDemo {

    @Autowired
    private MailService mailService;

    @SneakyThrows
    @Test
    void testRead() {
        ClassPathResource resource = new ClassPathResource("demo.xlsx");
        try (InputStream is = resource.getInputStream()) {
            EasyExcel.read(is, Account.class, new PageReadListener<Account>(dataList -> {
                for (Account acc : dataList) {
                    log.info("读取到一条数据{}", JSON.toJSONString(acc));

                    StringBuilder text = new StringBuilder();
                    text.append("Hi, ").append(acc.getFullname()).append("\n");
                    text.append("").append("\n");
                    text.append("您的XXX（正式环境）账号已开通，账号信息如下：").append("\n");
                    text.append("网址：https://www.xxx.com/").append("\n");
                    text.append("姓名：").append(acc.getFullname()).append("\n");
                    text.append("账号：").append(acc.getUsername()).append("\n");
                    text.append("密码：").append(acc.getPassword()).append("\n");

                    SimpleMailMessage message = new SimpleMailMessage();
                    message.setTo(acc.getMail());
                    message.setSubject("XXX（正式环境）账号已开通");
                    message.setText(text.toString());
                    mailService.send(message);

                }
            })).sheet().doRead();
        }
    }

    @Data
    public static class Account {

        @ExcelProperty("邮箱")
        private String mail;
        @ExcelProperty("姓名")
        private String fullname;
        @ExcelProperty("登录名")
        private String username;
        @ExcelProperty("密码")
        private String password;

    }

}

