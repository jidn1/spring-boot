package com.springbootvideo.web;

import com.springbootvideo.common.service.RedisService;
import com.springbootvideo.model.Movie;
import io.swagger.annotations.Api;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

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

    @GetMapping("index.html")
    public String index(HttpServletRequest request) {
        List<Movie> mLists = new ArrayList<Movie>();
        try {
            Movie m1 = new Movie();
            m1.setId(1);
            m1.setMoviceName("那个静默的阳光午后");
            m1.setDirector("范玮琪");
            m1.setMovicePictureUrl("images/cont/slider_img1.jpg");
            m1.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m2 = new Movie();
            m2.setId(2);
            m2.setMoviceName("流浪地球");
            m2.setDirector("吴京");
            m2.setMovicePictureUrl("images/cont/slider_img2.jpg");
            m2.setMovicePlayerUrl("http://www.html5videoplayer.net/videos/madagascar3.mp4");

            Movie m3 = new Movie();
            m3.setId(3);
            m3.setMoviceName("疯狂的外星人");
            m3.setDirector("黄渤");
            m3.setMovicePictureUrl("images/cont/slider_img3.jpg");
            m3.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m4 = new Movie();
            m4.setId(4);
            m4.setMoviceName("新喜剧之王");
            m4.setDirector("周星驰");
            m4.setMovicePictureUrl("images/cont/slider_img4.jpg");
            m4.setMovicePlayerUrl("http://www.html5videoplayer.net/videos/madagascar3.mp4");

            mLists.add(m1);
            mLists.add(m2);
            mLists.add(m3);
            mLists.add(m4);

            request.setAttribute("mLists",mLists);
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

    @GetMapping("/library.html")
    public String library(HttpServletRequest request) {
        List<Movie> mLists = new ArrayList<Movie>();
        try {
            Movie m1 = new Movie();
            m1.setId(1);
            m1.setDuration(126);
            m1.setMoviceName("Let Me Love You");
            m1.setDirector("刘惜君");
            m1.setMovicePictureUrl("images/cont/mv_img1.jpg");
            m1.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m2 = new Movie();
            m2.setId(2);
            m2.setDuration(126);
            m2.setMoviceName("Let Me Love You");
            m2.setDirector("刘惜君");
            m2.setMovicePictureUrl("images/cont/mv_img2.jpg");
            m2.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m3 = new Movie();
            m3.setId(3);
            m3.setDuration(126);
            m3.setMoviceName("Let Me Love You");
            m3.setDirector("刘惜君");
            m3.setMovicePictureUrl("images/cont/mv_img3.jpg");
            m3.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m4 = new Movie();
            m4.setId(4);
            m4.setDuration(126);
            m4.setMoviceName("Let Me Love You");
            m4.setDirector("刘惜君");
            m4.setMovicePictureUrl("images/cont/mv_img4.jpg");
            m4.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m5 = new Movie();
            m5.setId(5);
            m5.setDuration(126);
            m5.setMoviceName("Let Me Love You");
            m5.setDirector("刘惜君");
            m5.setMovicePictureUrl("images/cont/mv_img5.jpg");
            m5.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m6 = new Movie();
            m6.setId(6);
            m6.setDuration(126);
            m6.setMoviceName("Let Me Love You");
            m6.setDirector("刘惜君");
            m6.setMovicePictureUrl("images/cont/mv_img6.jpg");
            m6.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m7 = new Movie();
            m7.setId(7);
            m7.setDuration(126);
            m7.setMoviceName("Let Me Love You");
            m7.setDirector("刘惜君");
            m7.setMovicePictureUrl("images/cont/mv_img7.jpg");
            m7.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            Movie m8 = new Movie();
            m8.setId(8);
            m8.setDuration(126);
            m8.setMoviceName("Let Me Love You");
            m8.setDirector("刘惜君");
            m8.setMovicePictureUrl("images/cont/mv_img8.jpg");
            m8.setMovicePlayerUrl("http://vjs.zencdn.net/v/oceans.mp4");

            mLists.add(m1);
            mLists.add(m2);
            mLists.add(m3);
//            mLists.add(m4);
//            mLists.add(m5);
//            mLists.add(m6);
//            mLists.add(m7);
//            mLists.add(m8);

            request.setAttribute("mLists",mLists);
        } catch (Exception e){
            e.printStackTrace();
        }
        return "content/videoLibrary";
    }

    /**
     * 页面跳转
     * @param module
     * @param url
     * @return
     */
    @GetMapping("{url}.htm")
    public String page(@PathVariable("url") String url) {
        return  url;
    }


    @GetMapping("{module}/{url}.htm")
    public String page(@PathVariable("module") String module,@PathVariable("url") String url) {
        return module + "/" + url;
    }

}
