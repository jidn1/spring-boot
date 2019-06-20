package com.mq.model;

import lombok.Data;
import lombok.ToString;

import java.io.Serializable;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/20 15:34
 * @Description:
 */
@Data
@ToString
public class User implements Serializable {

    private String name;

    private String pass;
}
