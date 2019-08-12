package com.translate.google.model;

import com.translate.google.util.HttpClientUtil;
import lombok.Data;
import java.net.InetSocketAddress;
import java.net.Proxy;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/12 16:19
 * @Description:
 */
@Data
public class Browser {

    public Proxy proxy;
    public String url;

    public void setProxy(String ip, Integer port) {
        this.proxy = new Proxy(Proxy.Type.HTTP, new InetSocketAddress(ip, port.intValue()));
    }

    public String executeGet()
            throws Exception {
        String result;
        if (this.proxy != null)
            result = HttpClientUtil.doGetWithProxy(this.url, this.proxy);
        else {
            result = HttpClientUtil.doGet(this.url);
        }

        return result;
    }
}
