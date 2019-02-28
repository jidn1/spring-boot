package com.springbootvideo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootvideo.common.constant.VideoConstant;
import com.springbootvideo.common.service.RedisService;
import com.springbootvideo.common.util.FileUtil;
import com.springbootvideo.common.util.OssUtil;
import com.springbootvideo.dao.MovieDao;
import com.springbootvideo.model.Movie;
import com.springbootvideo.model.MovieCon;
import com.springbootvideo.service.IMovieService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/21 10:27
 * @Description:
 */
@Service("movieService")
public class MovieServiceImpl implements IMovieService {

    private final static Logger logger = Logger.getLogger(MovieServiceImpl.class);

    @Resource
    private MovieDao movieDao;

    @Resource
    private FileUtil fileUtil;
    @Resource
    private OssUtil ossUtil;

    @Resource
    private RedisService redisService;

    @Override
    @CacheEvict(value="movieCaches",key = "'findPageBySql'")
    public PageInfo<Movie> findPageBySql(MovieCon m, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> pageBySql = movieDao.findPageBySql(m);
        pageBySql.forEach( movie -> {
            movie.setOssPictureUrl(ossUtil.getUrl(movie.getMoviceLocalUrl(),true));
        });
        PageInfo<Movie> pageInfo = new PageInfo<>(pageBySql);
        return pageInfo;
    }

    @Override
    public void initTopHome() {
       try {
           List<Movie> topHome = movieDao.findTopHome();
           topHome.forEach( movie -> {
               movie.setOssPictureUrl(ossUtil.getUrl(movie.getMoviceLocalUrl(),true));
           });
           String homeStr = JSON.toJSONString(topHome);
           redisService.save(VideoConstant.TOP_HOME,homeStr);
           logger.info("初始化首页电影信息"+VideoConstant.MOVIE_LIBRARY);
       } catch (Exception e){
           e.printStackTrace();
       }
    }

    @Override
    public Movie findById(Integer mid) {
        return null;
    }

    @Override
    public void initMovieAll(Map<String,String> map) {
        try {
            List<Movie> movieByCon = movieDao.findMovieByCon(map);
            String homeStr = JSON.toJSONString(movieByCon);
            movieByCon.forEach(m -> {
                redisService.hset(VideoConstant.MOVIE_LIBRARY,m.getId().toString(),m.getMovicePlayerUrl());
            });
            logger.info("初始化电影播放信息"+VideoConstant.MOVIE_LIBRARY);
        } catch (Exception e){
            e.printStackTrace();
        }
    }

    @Override
    public void initOssUrl() {
        try {
            List<Movie> movies = movieDao.findMovieByCon(null);
            movies.forEach( m -> {
                if(StringUtils.isEmpty(m.getMoviceLocalUrl())){
                    String url = fileUtil.downFileFromHttp(m.getMovicePictureUrl());
                    m.setMoviceLocalUrl(url);
                    movieDao.update(m);
                }
            });
            logger.info("初始化电影海报");
        } catch (Exception e){
            e.printStackTrace();
        }
    }
}
