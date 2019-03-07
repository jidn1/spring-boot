package com.springbootvideo.common.model;

import lombok.Data;

import java.io.Serializable;
import java.util.HashMap;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 9:42
 * @Description:
 */
@Data
public class Email implements Serializable {

    private static final long serialVersionUID = 1L;
    //必填参数
    private String email;//接收方邮件
    private String subject;//主题
    private String content;//邮件内容
    private String sign;
    private String userName;//发送人
    //选填
    private String template;//模板
    private HashMap<String, String> kvMap;// 自定义参数

    public Email() {
        super();
    }

    public Email(String email, String subject, String content,String userName, String template,
                 HashMap<String, String> kvMap) {
        super();
        this.email = email;
        this.subject = subject;
        this.content = content;
        this.userName = userName;
        this.template = template;
        this.kvMap = kvMap;
    }

}
