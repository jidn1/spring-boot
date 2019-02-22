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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

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

//            Movie m1 = new Movie();
//            m1.setId(1);
//            m1.setMoviceName("那个静默的阳光午后");
//            m1.setDirector("范玮琪");
//            m1.setMovicePictureUrl("images/cont/slider_img1.jpg");
//            m1.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");
//
//            Movie m2 = new Movie();
//            m2.setId(2);
//            m2.setMoviceName("流浪地球");
//            m2.setDirector("吴京");
//            m2.setMovicePictureUrl("images/cont/slider_img2.jpg");
//            m2.setMovicePlayerUrl("http://www.html5videoplayer.net/videos/madagascar3.mp4");
//
//            Movie m3 = new Movie();
//            m3.setId(3);
//            m3.setMoviceName("疯狂的外星人");
//            m3.setDirector("黄渤");
//            m3.setMovicePictureUrl("images/cont/slider_img3.jpg");
//            m3.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");
//
//            Movie m4 = new Movie();
//            m4.setId(4);
//            m4.setMoviceName("新喜剧之王");
//            m4.setDirector("周星驰");
//            m4.setMovicePictureUrl("images/cont/slider_img4.jpg");
//            m4.setMovicePlayerUrl("http://www.html5videoplayer.net/videos/madagascar3.mp4");
//
//            mLists.add(m1);
//            mLists.add(m2);
//            mLists.add(m3);
//            mLists.add(m4);

            request.setAttribute("mLists",movieList);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "index";
    }

    @GetMapping("/player/{mid}.html")
    public String player(HttpServletRequest request,@PathVariable("mid") Integer mid) {
        try {
            String videoUrl = "";
            switch (mid){
                case 1:
                    videoUrl = "http://vjs.zencdn.net/v/oceans.mp4";
                    break;
                case 2:
                    videoUrl = "http://www.html5videoplayer.net/videos/madagascar3.mp4";
                    break;
                case 3:
                    videoUrl = "http://vjs.zencdn.net/v/oceans.mp4";
                    break;
                case 4:
                    videoUrl = "http://www.html5videoplayer.net/videos/madagascar3.mp4";
                    break;
            }
            request.setAttribute("videoUrl",videoUrl);
        } catch (Exception e){
            e.printStackTrace();
        }
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
        movieCon.setLanguage("中文");
        PageInfo<Movie> movieList = movieService.findPageBySql(movieCon, p, limit);
        request.setAttribute("movieList", movieList);//文章列表

        return "content/videoLibrary";
    }


//    @GetMapping("{module}/{url}.htm")
//    public String page(@PathVariable("module") String module,@PathVariable("url") String url) {
//        return module + "/" + url;
//    }

}
