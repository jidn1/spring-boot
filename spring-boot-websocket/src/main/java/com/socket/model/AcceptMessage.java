package com.socket.model;

import lombok.Data;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/31 10:48
 * @Description: socket消息接收对象
 */
@Data
public class AcceptMessage {

    private String userId;

    private String msg ;
}
