package com.springbootvideo.web;

import com.alibaba.fastjson.JSONObject;
import com.github.pagehelper.PageInfo;
import com.springbootvideo.common.service.RedisService;
import com.springbootvideo.common.constant.VideoConstant;
import com.springbootvideo.model.Movie;
import com.springbootvideo.model.MovieCon;
import com.springbootvideo.service.IMovieService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/18 14:40
 * @Description:
 */
@Api(tags ="通用访问拦截匹配")
@Controller
public class IndexController {

    @Resource
    private RedisService redisService;

    @Resource
    private IMovieService movieService;

    @GetMapping("index.html")
    public String index(HttpServletRequest request) {
        List<Movie> mLists = new ArrayList<Movie>();
        try {
            String topMovie = redisService.get(VideoConstant.TOP_HOME);
            List<Movie> movieList = JSONObject.parseArray(topMovie, Movie.class);
            request.setAttribute("mLists",movieList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/player/{mid}.html")
    public String player(HttpServletRequest request,@PathVariable("mid") Integer mid) {
        return  "content/player";
    }

    @ApiOperation("电影库")
    @GetMapping("/library.html")
    public String library(HttpServletRequest request,@ApiParam(name = "limit", value = "页数", required = false)
                        @RequestParam(name = "limit", required = false, defaultValue = "8")int limit){
        return this.libraryPgae(request, 1, limit);
    }

    @ApiOperation("movie电影库-分页")
    @GetMapping("/library/page/{p}.html")
    public String libraryPgae(HttpServletRequest request,@PathVariable("p")int p,
                            @RequestParam(value = "limit", required = false, defaultValue = "8")int limit){
        p = p < 0 || p > VideoConstant.MAX_PAGE ? 1 : p;
        MovieCon movieCon = new MovieCon();
        PageInfo<Movie> movieList = movieService.findPageBySql(movieCon, p, limit);
        request.setAttribute("movieList", movieList);//文章列表
        return "content/videoLibrary";
    }


    @RequestMapping("/getPlayer")
    @ResponseBody
    public Object getPlayerUrl(HttpServletRequest request){
        Map<String,String> map = new HashMap<String,String>();
        String mid = request.getParameter("mid");
        String id = mid.replaceAll(".html","");
        String config = redisService.get(VideoConstant.VIDEO_CONFIG);
        JSONObject parseObject = JSONObject.parseObject(config);
        Object analysisurl = parseObject.get("analysisurl");
        String playerUrl = redisService.hget(VideoConstant.MOVIE_LIBRARY, id);
        String url = analysisurl+playerUrl;
        System.out.println(url);
        map.put("URL",url);
        return map;
    }

}
