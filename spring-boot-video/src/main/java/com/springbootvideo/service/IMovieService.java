package com.springbootvideo.service;

import com.github.pagehelper.PageInfo;
import com.springbootvideo.model.Movie;
import com.springbootvideo.model.MovieCon;

import java.util.List;

/**
 * @Copyright © 北京互融时代软件有限公司
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
}
