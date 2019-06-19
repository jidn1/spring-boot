package com.translate.baidu.api;

import com.translate.common.util.FileUtil;
import com.translate.common.util.PropertiesUtils;
import org.springframework.util.StringUtils;
import com.baidu.aip.speech.AipSpeech;
import com.baidu.aip.speech.TtsResponse;
import org.json.JSONObject;
import java.util.HashMap;


/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/19 13:37
 * @Description: AipSpeech是语音识别的Java客户端
 */
public class SpeechApi {


    /**
     * 语音合成
     * @param str
     * @param appId
     * @param apiKey
     * @param secretKey
     * @return
     */
    public static String synthesis(String str,String appId,String apiKey,String secretKey) {
        AipSpeech client = new AipSpeech(appId, apiKey, secretKey);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");//语速，取值0-9，默认为5中语速      非必选
        options.put("pit", "6");//音调，取值0-9，默认为5中语调      非必选
        options.put("per", "4");//发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女 非必选
        TtsResponse res = client.synthesis(str, "zh", 1, options);
        JSONObject result = res.getResult();
        if (!StringUtils.isEmpty(result)) {
            System.out.printf("error：" + result.toString());
            return result.toString();
        }
        String filePath = PropertiesUtils.APP.getProperty("WRITE_FILE_SYSTEM");
        byte[] data = res.getData();
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(res.getResult()));
        if (data != null) {
            try {
                return  FileUtil.writeBytesToOssVideo(data,filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }


    public static String synthesis(String str) {
        String APP_ID = PropertiesUtils.APP.getProperty("baiduSpeechApi");
        String API_KEY = PropertiesUtils.APP.getProperty("baiduSpeechApiKey");
        String SECRET_KEY = PropertiesUtils.APP.getProperty("baiduSpeechApiSecretKey");


        AipSpeech client = new AipSpeech(APP_ID, API_KEY, SECRET_KEY);
        client.setConnectionTimeoutInMillis(2000);
        client.setSocketTimeoutInMillis(60000);
        HashMap<String, Object> options = new HashMap<String, Object>();
        options.put("spd", "5");//语速，取值0-9，默认为5中语速      非必选
        options.put("pit", "6");//音调，取值0-9，默认为5中语调      非必选
        options.put("per", "4");//发音人选择, 0为女声，1为男声，3为情感合成-度逍遥，4为情感合成-度丫丫，默认为普通女 非必选
        TtsResponse res = client.synthesis(str, "zh", 1, options);
        JSONObject result = res.getResult();
        if (!StringUtils.isEmpty(result)) {
            System.out.printf("error：" + result.toString());
            return result.toString();
        }
        String filePath = PropertiesUtils.APP.getProperty("WRITE_FILE_SYSTEM");
        byte[] data = res.getData();
        System.out.println(com.alibaba.fastjson.JSONObject.toJSONString(res.getResult()));
        if (data != null) {
            try {
                return  FileUtil.writeBytesToOssVideo(data,filePath);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return null;
    }

}
