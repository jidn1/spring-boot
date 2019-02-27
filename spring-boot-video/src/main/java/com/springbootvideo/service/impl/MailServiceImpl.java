package com.springbootvideo.service.impl;

import com.springbootvideo.common.queue.ConsumeMailQueue;
import com.springbootvideo.common.util.EmailUtil;
import org.springframework.stereotype.Service;
import com.springbootvideo.common.model.Email;
import com.springbootvideo.common.queue.MailQueue;
import com.springbootvideo.service.IMailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;

import javax.annotation.Resource;

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
    @Resource
    ConsumeMailQueue consumeMailQueue;

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
    public void sendQueue(Email mail) throws Exception {
        MailQueue.getMailQueue().produce(mail);
    }

    @Override
    public void sendEmail(String to) {
        EmailUtil.sendEmail(USER_NAME,to);
    }
}
