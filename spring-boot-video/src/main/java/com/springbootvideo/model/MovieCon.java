package com.springbootvideo.model;

import lombok.Data;

import java.util.Date;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/2/21 10:41
 * @Description:
 */
@Data
public class MovieCon {

    private Integer id;

    private String moviceName;//电影名称

    private Date moviceReleaseTime;//上映时间

    private String moviceType;//电影类型

    private String country;//国别

    private String language;//语言

    private String director;//导演

    private String mainCharacter;//主要演员


}
