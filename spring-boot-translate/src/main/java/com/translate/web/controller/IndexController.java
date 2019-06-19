package com.translate.web.controller;

import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import javax.servlet.http.HttpServletRequest;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/18 15:56
 * @Description:
 */
@Api(tags = "首页")
@Controller
public class IndexController {

    @ApiOperation(value = "首页", httpMethod = "POST", notes = "首页")
    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        return "index";
    }

}
