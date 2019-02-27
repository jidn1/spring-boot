package com.springbootvideo.common.util;

import com.springbootvideo.common.constant.VideoConstant;
import com.springbootvideo.common.model.Email;
import com.springbootvideo.common.queue.ConsumeMailQueue;
import com.springbootvideo.common.queue.MailQueue;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.Resource;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 16:51
 * @Description:
 */
public class EmailUtil {


    public static Email createEmail(String em,String userName){
        Email email = new Email();
        email.setContent(VideoConstant.EMAIL_SEND_CONTENT);
        email.setSubject(VideoConstant.EMAIL_SUBJECT);
        email.setUserName(userName);
        email.setEmail(em);
        return email;
    }

    public static void sendEmail(String userName,String email) {
        Email e = createEmail(email,userName);
        try {
            MailQueue.getMailQueue().produce(e);
        } catch (InterruptedException e1) {
            e1.printStackTrace();
        }
    }

}
