package com.springbootvideo.common.model;

import lombok.Data;

import java.io.Serializable;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/25 16:57
 * @Description:
 */
@Data
public class JsonResult implements Serializable {
    private Boolean success = false;
    private String msg = "";
    private Object obj = null;
    private String code = "";

}
