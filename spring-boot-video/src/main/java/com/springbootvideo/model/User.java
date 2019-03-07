package com.springbootvideo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/26 13:07
 * @Description:
 */
@Data
@Table(name = "fh_user")
public class User {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "username", length = 20)
    private String username;

    @Column(name = "email", length = 20)
    private String email;

    @Column(name = "password", length = 50)
    private String password;

    @Column(name = "status", length = 2)
    private Integer status;

    @Column(name = "salt", length = 50)
    private String salt;

    @Column(name = "isVip", length = 2)
    private Integer isVip;

    @Column(name = "vipExpirTime")
    private Date vipExpirTime;

    @Column(name = "ip", length = 20)
    private String ip;

    @Column(name = "vipType", length = 20)
    private String vipType;

    @Column(name = "created")
    private Date created;

    @Transient
    private String icon;

    @Transient
    private String title;

}
