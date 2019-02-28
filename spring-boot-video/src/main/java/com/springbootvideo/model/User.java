package com.springbootvideo.model;

import lombok.Data;
import java.util.Date;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 13:07
 * @Description:
 */
@Data
public class User {

    private Integer id;
    private String username;
    private String email;
    private String password;
    private Integer status;
    private String salt;
    private Integer isVip;
    private Date vipExpirTime;
    private String ip;
    private String vipType;
    private Date created;

    private String icon;

    private String title;

}
