package com.springbootvideo.dao;

import com.springbootvideo.model.Config;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/21 13:03
 * @Description:
 */
@Mapper
public interface ConfigDao {


    List<Config> findByCondition(Config c);


}
