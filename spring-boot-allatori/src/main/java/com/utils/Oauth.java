package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/26 16:13
 * @Description:
 */
public class Oauth {

    static SimpleDateFormat sdf = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss");

    public static void success(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("**********************************************").append("\n");
        stringBuilder.append("**************WWW.HURONGSOFT.COM**************").append("\n");
        stringBuilder.append("【success=true】【Successful authorization【"+sdf.format(new Date())+"】】").append("\n");
        stringBuilder.append("【success=true】【Successful authorization【"+sdf.format(new Date())+"】】").append("\n");
        stringBuilder.append("********©2009-2019 All Rights Reserved********").append("\n");
        stringBuilder.append("**********************************************").append("\n");

        System.out.println(""+stringBuilder.toString());
    }


    public static void fail(){
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("**********************************************").append("\n");
        stringBuilder.append("**************WWW.HURONGSOFT.COM**************").append("\n");
        stringBuilder.append("【success=false】【Failure authorization【"+sdf.format(new Date())+"】】").append("\n");
        stringBuilder.append("【success=false】【Failure authorization【"+sdf.format(new Date())+"】】").append("\n");
        stringBuilder.append("********©2009-2019 All Rights Reserved********").append("\n");
        stringBuilder.append("**********************************************").append("\n");

        System.out.println(""+stringBuilder.toString());
    }


    public static boolean ifPass(String oldOauthKey,String newOauthKey){
        if(oldOauthKey.equals(newOauthKey)){
            return true;
        }
        return false;
    }
}
