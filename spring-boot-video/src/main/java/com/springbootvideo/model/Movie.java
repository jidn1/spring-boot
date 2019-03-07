package com.springbootvideo.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/19 13:06
 * @Description: 电影
 */

@Data
@Table(name = "fh_movie")
public class Movie {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "moviceName", length = 20)
    private String moviceName;//电影名称

    @Column(name = "movicePictureUrl", length = 100)
    private String movicePictureUrl;//电影海报

    @Column(name = "movicePlayerUrl", length = 100)
    private String movicePlayerUrl;//电影播放

    @Column(name = "moviceLocalUrl", length = 100)
    private  String moviceLocalUrl;//本地海报路径

    @Column(name = "moviceReleaseTime")
    private Date moviceReleaseTime;//上映时间

    @Column(name = "moviceType", length = 20)
    private String moviceType;//电影类型

    @Column(name = "country", length = 10)
    private String country;//国别

    @Column(name = "language", length = 10)
    private String language;//语言

    @Column(name = "director", length = 20)
    private String director;//导演

    @Column(name = "mainCharacter", length = 50)
    private String mainCharacter;//主要演员

    @Column(name = "duration", length = 11)
    private Integer duration;//电影时长

    @Column(name = "created")
    private Date created;

    @Transient
    private String ossPictureUrl;


}
