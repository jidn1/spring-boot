package com.springbootvideo.model;

import lombok.Data;

/**
 * @Copyright © 北京互融时代软件有限公司
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
