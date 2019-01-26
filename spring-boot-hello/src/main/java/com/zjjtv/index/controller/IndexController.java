package com.zjjtv.index.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/1/26 10:51
 * @Description:
 */
@RestController
public class IndexController {

    @RequestMapping("/index")
    public String index(){

        return "hello";
    }
}
