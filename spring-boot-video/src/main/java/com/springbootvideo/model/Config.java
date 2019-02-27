package com.springbootvideo.model;

import lombok.Data;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/21 13:02
 * @Description:
 */
@Data
public class Config {

    private Long id;  //

    private String configkey;  //

    private String configname;  //

    private String configdescription;  //

    private Integer datatype;  //

    private String value;  //

    private String typekey;  //

    private String typename;  //

    private String description;  //
}
