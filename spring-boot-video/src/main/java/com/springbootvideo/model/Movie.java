package com.springbootvideo.model;

import lombok.Data;

import java.util.Date;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/19 13:06
 * @Description: 电影
 */

@Data
public class Movie {

    private Integer id;

    private String moviceName;//电影名称

    private String movicePictureUrl;//电影海报

    private String movicePlayerUrl;//电影播放

    private  String moviceLocalUrl;//本地海报路径

    private Date moviceReleaseTime;//上映时间

    private String moviceType;//电影类型

    private String country;//国别

    private String language;//语言

    private String director;//导演

    private String mainCharacter;//主要演员

    private Integer duration;//电影时长

    private Date created;

    private String ossPictureUrl;


}
