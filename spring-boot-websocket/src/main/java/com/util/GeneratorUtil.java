package com.util;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/31 10:28
 * @Description: 唯一id生成器
 */
public class GeneratorUtil {

    /**
     * webSocket 唯一标识生成
     */
    public static class MarginToken {
        /**
         * 当前时间+1s
         * 1s内递增的次数
         */
        private static Long currentTime = 0L;
        private static Integer count = 0;

        public static synchronized String getCount() {
            long ctime = System.currentTimeMillis();
            if (currentTime < ctime) {
                currentTime = ctime + 1000;
                count = 0;
            } else {
                count++;
            }
            String num = String.format("%03d", count);
            SimpleDateFormat format = new SimpleDateFormat("yyMMddHHmmss");
            String date = format.format(new Date());
            return "L" + num + date;
        }
    }
}
