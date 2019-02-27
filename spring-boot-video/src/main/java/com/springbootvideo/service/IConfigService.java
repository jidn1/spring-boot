package com.springbootvideo.service;

import com.springbootvideo.model.Config;

import java.util.List;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/2/21 13:11
 * @Description:
 */
public interface IConfigService {

    /**
     * 基础配置查询
     * @param c
     * @return
     */
    List<Config> findByCondition(Config c);

    void initCache();
}
