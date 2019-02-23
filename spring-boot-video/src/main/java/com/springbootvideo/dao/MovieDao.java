package com.springbootvideo.dao;

import com.springbootvideo.model.Movie;
import com.springbootvideo.model.MovieCon;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/2/21 10:15
 * @Description:
 */
@Mapper
public interface MovieDao {

    /**
     * 分页查询
     * @param m
     * @return
     */
    List<Movie> findPageBySql(MovieCon m);

    /**
     * 首页展示12部电影
     * @return
     */
    List<Movie> findTopHome();


    List<Movie> findMovieByCon(Map<String,String> map);
}
