package com.springbootvideo.service.impl;

import com.springbootvideo.common.queue.ConsumeMailQueue;
import com.springbootvideo.common.util.EmailUtil;
import freemarker.template.Configuration;
import freemarker.template.Template;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import com.springbootvideo.common.model.Email;
import com.springbootvideo.common.queue.MailQueue;
import com.springbootvideo.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.ui.freemarker.FreeMarkerTemplateUtils;

import javax.annotation.Resource;
import javax.mail.internet.MimeMessage;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 9:45
 * @Description:
 */
@Service("mailService")
public class MailServiceImpl implements IMailService {
    private static final Logger logger = LoggerFactory.getLogger(MailServiceImpl.class);

    @Resource
    private JavaMailSender mailSender;//执行者
    @Autowired
    public Configuration configuration;//freemarker


    @Value("${spring.mail.username}")
    public String USER_NAME;//发送者
    @Value("${server.path}")
    public String PATH;

    @Override
    public void send(Email mail) throws Exception {
        logger.info("发送邮件：{}",mail.getContent());
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(mail.getUserName());
        message.setTo(mail.getEmail());
        message.setSubject(mail.getSubject());
        message.setText(mail.getContent());
        mailSender.send(message);
    }

    @Override
    public void sendHtml(Email mail) throws Exception {
        System.out.println("进入发送html");
        MimeMessage message = mailSender.createMimeMessage();
        MimeMessageHelper helper = new MimeMessageHelper(message, true);
        helper.setFrom(mail.getUserName(),mail.getSign());
        helper.setTo(mail.getEmail());
        helper.setSubject(mail.getSubject());
        Map<String, Object> model = new HashMap<String, Object>();
        model.put("mail", mail);
        model.put("path", PATH);
        Template template = configuration.getTemplate(mail.getTemplate()+".flt");
        String text = FreeMarkerTemplateUtils.processTemplateIntoString(
                template, model);
        helper.setText(text, true);
        mailSender.send(message);
    }

    @Override
    public void sendQueue(Email mail) throws Exception {
        MailQueue.getMailQueue().produce(mail);
    }

    @Override
    public void sendEmail(String to) {
        EmailUtil.sendEmail(USER_NAME,to);
    }
}
