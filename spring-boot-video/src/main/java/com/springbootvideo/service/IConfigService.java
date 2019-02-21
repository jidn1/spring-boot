package com.springbootvideo.service;

import com.springbootvideo.model.Config;

import java.util.List;

/**
 * @Copyright © 北京互融时代软件有限公司
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
