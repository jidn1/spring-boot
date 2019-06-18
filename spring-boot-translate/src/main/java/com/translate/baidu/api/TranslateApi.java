package com.translate.baidu.api;

import com.translate.common.util.PropertiesUtils;
import com.translate.common.util.SignUtil;
import com.translate.common.util.TranslateUtil;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/18 16:01
 * @Description:
 */
public class TranslateApi {

    private static final String TRANS_API_HOST = PropertiesUtils.APP.getProperty("translate_baidu");

    private static final String FORM = "auto";

    private String appid;
    private String securityKey;

    public TranslateApi() {
        this.appid = PropertiesUtils.APP.getProperty("baiduTranApi");
        this.securityKey = PropertiesUtils.APP.getProperty("baiduTranSecurityKey");
    }


    public TranslateApi(String appid, String securityKey) {
        this.appid = appid;
        this.securityKey = securityKey;
    }

    public String getTransResult(String str,String to) {
        if("zh_CN".equals(to) || StringUtils.isEmpty(TRANS_API_HOST)){
            return str;
        }
        Map<String, String> params = buildParams(str, FORM,getLanguage(to));
        return getResult(TRANS_API_HOST, params);
    }


    public String getResult(String host, Map<String, String> params){
        String  transResult = "";
        String s = TranslateUtil.get(host, params);
        if(s.contains("error_code")){
            System.out.println("=========百度翻译结果信息:"+s);
            return null;
        }
        char [] content_result_temp = s.toCharArray();
        for(int i = content_result_temp.length-5;;i--) {
            if(content_result_temp[i] == '"') {
                break;
            }
            transResult = content_result_temp[i] + transResult;
        }
        return transResult;
    }

    private String getLanguage(String language){
        String lan = "";
        switch (language){
            case "zh_CN":
                lan = "zh";
                break;
            case "en":
                lan = "en";
                break;
            case "kor":
                lan = "kor";
                break;
            case "zh_TW":
                lan = "cht";
                break;
            case "fr":
                lan = "fra";
                break;
            case "es":
                lan = "spa";
                break;
            case "jp":
                lan = "jp";
                break;
            default:
                lan = "en";
                break;
        }
        return lan;
    }

    private Map<String, String> buildParams(String query, String from,String to) {
        Map<String, String> params = new HashMap<String, String>();
        params.put("q", query);
        params.put("from", from);
        params.put("to", to);
        params.put("appid", appid);
        String salt = String.valueOf(System.currentTimeMillis());
        params.put("salt", salt);
        String src = appid + query + salt + securityKey;
        params.put("sign", SignUtil.md5(src));
        return params;
    }

}
