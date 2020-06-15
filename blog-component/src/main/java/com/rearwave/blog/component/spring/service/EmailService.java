package com.rearwave.blog.component.spring.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

/**
 * @author sunyi
 */
@Component
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String from;

    private final static String NAME_ALIAS = "后浪博客系统<%s>";

    /**
     * 发送普通文本邮件
     * @param to 发给谁
     * @param subject 标题
     * @param text 内容
     */
    public void sendTextMail(String to,String subject,String text){

        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(String.format(NAME_ALIAS,from));
        message.setText(text);
        message.setSubject(subject);
        message.setTo(to);
        mailSender.send(message);
    }

    public void sendHtmlMail(String to,String subject,String html){
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(message,true);
            helper.setFrom(String.format(NAME_ALIAS,from));
/**
            helper.setFrom(String.format(NAME_ALIAS,"noreply@rearwave.com"));
*/
            helper.setSubject(subject);
            helper.setTo(to);
            helper.setText(html,true);
            mailSender.send(message);
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
