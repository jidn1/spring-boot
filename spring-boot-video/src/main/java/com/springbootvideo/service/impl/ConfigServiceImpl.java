package com.springbootvideo.service.impl;

import com.alibaba.fastjson.JSON;
import com.springbootvideo.common.service.RedisService;
import com.springbootvideo.common.util.VideoCache;
import com.springbootvideo.common.constant.VideoConstant;
import com.springbootvideo.dao.ConfigDao;
import com.springbootvideo.model.Config;
import com.springbootvideo.service.IConfigService;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/2/21 13:12
 * @Description:
 */
@Service("configService")
public class ConfigServiceImpl implements IConfigService {

    private final static Logger logger = Logger.getLogger(ConfigServiceImpl.class);

    @Resource
    private ConfigDao configDao;
    @Resource
    private RedisService redisService;

    @Override
    public List<Config> findByCondition(Config c) {
        return configDao.findByCondition(c);
    }

    @Override
    public void initCache() {
        Map<String, String> configMap = new HashMap<String, String>();
        try {
            List<Config> configList = configDao.findByCondition(null);
            for (Config app : configList) {
                if (null != app.getConfigkey() && !"".equals(app.getConfigkey())) {
                    configMap.put(app.getConfigkey(), app.getValue());
                }
            }

            String configStr = JSON.toJSONString(configMap);
            redisService.save(VideoConstant.VIDEO_CONFIG,configStr);
            logger.info("初始化系统配置信息缓存"+VideoConstant.VIDEO_CONFIG);
            cache_appconfig(configList);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 缓存部分值装载到 VideoCache 中
     */
    public void cache_appconfig(List<Config> list){
        for (Config app : list) {
            VideoCache.cache_language.put(app.getConfigkey(),app.getValue());
        }
    }
}
