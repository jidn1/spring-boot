package com.notes.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 10:27
 * @Description:
 */
@Data
public class JsonResult implements Serializable {

    private Boolean success = false;
    private String msg = "";
    private Object obj = null;
    private String code = "";

}
