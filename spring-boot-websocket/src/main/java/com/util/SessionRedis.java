package com.util;

import lombok.Data;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/30 17:43
 * @Description: 伪session redis数据
 */
@Data
public class SessionRedis {

    private String marginToken;

    private String userId;

    private String msg;
}
