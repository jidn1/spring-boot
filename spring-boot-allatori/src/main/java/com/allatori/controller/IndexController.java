package com.allatori.controller;

import com.allatori.model.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/20 10:42
 * @Description:
 */
@Controller
public class IndexController {

    @RequestMapping("/index")
    public String index(HttpServletRequest request) {
        try {
            User user = new User();
            user.setAddress("北京市");
            user.setName("tom");
            user.setAge(19);
            request.setAttribute("user",user);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

}
