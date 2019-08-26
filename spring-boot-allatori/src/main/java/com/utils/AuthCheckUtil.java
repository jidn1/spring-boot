package com.utils;


/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/26 11:43
 * @Description:
 */
public class AuthCheckUtil {

    public static String checkAuth() {
        try {
            String oauthIp = PropertiesUtils.APP.getProperty("app.oauthIp");
            String oauthKey = PropertiesUtils.APP.getProperty("app.oauthKey");

            String localMac = MacUtil.getLocalMac(oauthIp);
            String oauthKeyNew = Md5Encrypt.md5(localMac + "," + oauthIp);

            boolean ifPass = Oauth.ifPass(oauthKey, oauthKeyNew);

            if(ifPass){
                Oauth.success();
            }

            if(!ifPass){
                Oauth.fail();
                System.exit(-1);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
