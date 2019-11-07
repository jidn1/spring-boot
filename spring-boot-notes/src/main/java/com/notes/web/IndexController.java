package com.notes.web;

import com.alibaba.fastjson.JSONObject;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/11/7 10:19
 * @Description:
 */

@Api(tags = "合伙人购买大厅 黄一鸣")
@Controller
@RequestMapping("/")
public class IndexController {

    @ApiOperation(value = "首页 index", httpMethod = "GET", response = HashMap.class, notes = "首页 index")
    @GetMapping("index")
    public String index(HttpServletRequest request) {

        return "index";
    }
}
