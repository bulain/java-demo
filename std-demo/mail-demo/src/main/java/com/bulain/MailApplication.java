package com.bulain;

import com.bulain.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.SimpleMailMessage;

@Slf4j
@SpringBootApplication
@ImportResource({"classpath:spring/applicationContext-mail.xml", "classpath:spring/propertyConfigurer.xml"})
public class MailApplication implements CommandLineRunner {

    @Autowired
    private MailService mailService;

    public static void main(String[] args) {
        SpringApplication.run(MailApplication.class, args);
    }

    @Override
    public void run(String... args) throws Exception {

        log.info("send mail - start");

        SimpleMailMessage mail = new SimpleMailMessage();
        mail.setTo("bulain@163.com");
        mail.setSubject("Test email from A_P_TMS@support.envision-energy.com");
        mail.setText("send from A_P_TMS@support.envision-energy.com to you");
        mailService.send(mail);

        log.info("send mail - end");

    }

}
