package com.bulain;

import com.bulain.mail.MailService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.mail.javamail.MimeMessagePreparator;

import javax.mail.internet.MimeMessage;

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

        mailService.send(new MimeMessagePreparator(){
            @Override
            public void prepare(MimeMessage mime) throws Exception {
                MimeMessageHelper message = new MimeMessageHelper(mime, true, "UTF-8");
                message.setTo("bulain@163.com");
                message.setSubject("Test email from A_P_TMS@support.envision-energy.com");
                message.setText("send from A_P_TMS@support.envision-energy.com to you");
            }
        });

        log.info("send mail - end");

    }

}
