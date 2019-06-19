package com.translate.web.controller;

import com.translate.baidu.api.TranslateApi;
import io.swagger.annotations.*;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/18 15:56
 * @Description:
 */
@Api(tags = "首页")
@RestController
@RequestMapping("/")
public class IndexController {

    @ApiOperation(value = "首页翻译", httpMethod = "POST", notes = "首页翻译")
    @PostMapping("/translate")
    @ResponseBody
    public Map<String,Object> translate(
            @ApiParam(name = "from", value = "原文", required = true) @RequestParam("from") String from,
            @ApiParam(name = "auto", value = "翻译语言", required = true) @RequestParam("auto") String auto,
            HttpServletRequest request) {
        Map<String,Object> apiJsonResult = new HashMap<String, Object>();
        TranslateApi api = new TranslateApi();
        try {
            Map<String,Object> result = new HashMap<String, Object>();
            result.put("from",from);
            result.put("auto",auto);

            String transResult = api.getTransResult(from,auto);

            result.put("to",transResult);

            apiJsonResult.put("success","true");
            apiJsonResult.put("code","0");
            apiJsonResult.put("msg",result);
        } catch (Exception e){
            e.printStackTrace();
        }
        return apiJsonResult;
    }


    @ApiOperation(value = "首页", httpMethod = "POST", notes = "首页")
    @RequestMapping("/index")
    public String index() {
        return "swagger-ui";
    }

}
