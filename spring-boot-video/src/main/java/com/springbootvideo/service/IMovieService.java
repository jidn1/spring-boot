package com.springbootvideo.service;

import com.github.pagehelper.PageInfo;
import com.springbootvideo.common.model.Email;
import com.springbootvideo.model.Movie;
import com.springbootvideo.model.MovieCon;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Map;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/21 10:22
 * @Description:
 */
public interface IMovieService {

    /**
     * 电影列表查询
     * @param m
     * @param pageNum
     * @param pageSize
     * @return
     */
    PageInfo<Movie> findPageBySql(MovieCon m, int pageNum, int pageSize);

    /**
     * 首页展示12部电影
     * @return
     */
    void initTopHome();

    /**
     *
     * @param mid
     * @return
     */
    Movie findById(Integer mid);

    /**
     * 初始化电影
     */
    void initMovieAll(Map<String,String> map);


    void initOssUrl();

}
