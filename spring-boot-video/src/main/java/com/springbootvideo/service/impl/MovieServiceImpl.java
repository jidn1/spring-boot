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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/2/21 10:27
 * @Description:
 */
@Service("movieService")
public class MovieServiceImpl implements IMovieService {

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
        List<Movie> topHome = movieDao.findTopHome();
        String homeStr = JSON.toJSONString(topHome);
        redisService.save(VideoConstant.TOP_HOME,homeStr);

    }
}
