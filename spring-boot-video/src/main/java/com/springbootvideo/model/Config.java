package com.springbootvideo.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/21 13:02
 * @Description:
 */
@Data
@Table(name = "fh_config")
public class Config {

    @Id
    @GeneratedValue
    private Long id;  //

    @Column(name = "configkey", length = 20)
    private String configkey;  //

    @Column(name = "configname", length = 20)
    private String configname;  //

    @Column(name = "configdescription", length = 20)
    private String configdescription;  //

    @Column(name = "datatype", length = 2)
    private Integer datatype;  //

    @Column(name = "value", length = 50)
    private String value;  //

    @Column(name = "typekey", length = 50)
    private String typekey;  //

    @Column(name = "typename", length = 50)
    private String typename;  //

    @Column(name = "description", length = 50)
    private String description;  //
}
