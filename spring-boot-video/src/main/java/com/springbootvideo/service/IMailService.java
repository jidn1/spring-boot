package com.springbootvideo.service;

import com.springbootvideo.common.model.Email;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 9:43
 * @Description:
 */
public interface IMailService {

    /**
     * 发送纯文本
     * @param mail
     * @throws Exception
     */
    void send(Email mail) throws Exception;

    /**
     * 队列
     * @param mail
     * @throws Exception
     */
    void sendQueue(Email mail) throws Exception;

    void sendEmail(String to);
}
