package com.springbootvideo.service.impl;

import com.alibaba.fastjson.JSON;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.springbootvideo.common.constant.VideoConstant;
import com.springbootvideo.common.service.RedisService;
import com.springbootvideo.dao.MovieDao;
import com.springbootvideo.model.Movie;
import com.springbootvideo.model.MovieCon;
import com.springbootvideo.service.IMovieService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    private RedisService redisService;

    @Override
    @CacheEvict(value="movieCaches",key = "'findPageBySql'")
    public PageInfo<Movie> findPageBySql(MovieCon m, int pageNum, int pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<Movie> pageBySql = movieDao.findPageBySql(m);
        PageInfo<Movie> pageInfo = new PageInfo<>(pageBySql);
        return pageInfo;
    }

    @Override
    public void initTopHome() {
       try {
           List<Movie> topHome = movieDao.findTopHome();
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
}
