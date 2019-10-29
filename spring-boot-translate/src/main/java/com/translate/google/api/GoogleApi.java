package com.translate.google.api;

import com.alibaba.fastjson.JSONArray;
import com.translate.common.constant.TranslateConstant;
import com.translate.common.util.PropertiesUtils;
import com.translate.google.model.Browser;
import com.translate.google.util.RegularUtil;
import com.translate.google.util.StringUtils;
import com.translate.web.model.JsonResult;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.net.URLEncoder;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/12 16:19
 * @Description:
 */
public class GoogleApi {

    private static final String PATH =  PropertiesUtils.APP.getProperty("google.tkPath");

    private static final String BROWSER_URL = PropertiesUtils.APP.getProperty("google.browserUrl");

    private static final String TRANSLATE_URL = PropertiesUtils.APP.getProperty("google.translateUrl");

    static ScriptEngine engine = null;

    private Browser browser = null;

    static {
        ScriptEngineManager maneger = new ScriptEngineManager();
        engine = maneger.getEngineByName("javascript");
        FileInputStream fileInputStream = null;
        Reader scriptReader = null;

        try {
            scriptReader = new InputStreamReader(GoogleApi.class.getResourceAsStream(PATH), TranslateConstant.UTF8);
            engine.eval(scriptReader);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fileInputStream != null) {
                try {
                    fileInputStream.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (scriptReader != null) {
                try {
                    scriptReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public GoogleApi() {
        this.browser = new Browser();
    }

    public GoogleApi(String ip, Integer port) {
        this.browser = new Browser();
        this.browser.setProxy(ip, port);
    }

    public String getTKK() throws Exception {
        browser.setUrl(BROWSER_URL);

        try {
            String result = browser.executeGet();
            if (StringUtils.isNotBlank(result)) {
                if (result.indexOf("tkk") > -1) {
                    String matchString = RegularUtil.findMatchString(result, "tkk:.*?',");
                    String tkk = matchString.substring(5, matchString.length() - 2);
                    return tkk;
                }
            }
        } catch (Exception e) {
            throw new RuntimeException("获取 tkk 出错");
        }

        return null;
    }

    public static String getTK(String word, String tkk) {
        String result = null;

        try {
            if (engine instanceof Invocable) {
                Invocable invocable = (Invocable) engine;
                result = (String) invocable.invokeFunction("tk", new Object[]{word, tkk});
            }
        } catch (Exception e) {
            throw new RuntimeException("获取 tk 出错");
        }

        return result;
    }

    public JsonResult translate(String word, String from, String to) throws Exception {
        if (StringUtils.isBlank(word)) {
            return new JsonResult().setSuccess(false).setCode("1006").setMsg("原文为空");
        }

        String tkk = getTKK();

        if (StringUtils.isBlank(tkk)) {
            //throw new RuntimeException("无法获取 tkk");
            System.out.println("无法获取 tkk");
            return new JsonResult().setSuccess(false).setCode("1007").setMsg("无法获取tkk");
        }

        String tk = getTK(word, tkk);

        try {
            word = URLEncoder.encode(word, TranslateConstant.UTF8);
        } catch (Exception e) {
            e.printStackTrace();
        }

        StringBuffer buffer = new StringBuffer(TRANSLATE_URL);

        if (StringUtils.isBlank(from)) {
            from = "auto";
        }

        buffer.append("&sl=" + from);
        buffer.append("&tl=" + to);
        buffer.append("&hl=zh-CN&dt=at&dt=bd&dt=ex&dt=ld&dt=md&dt=qca&dt=rw&dt=rm&dt=ss&dt=t&ie=UTF-8&oe=UTF-8&source=btn&kc=0");
        buffer.append("&tk=" + tk);
        buffer.append("&q=" + word);
        browser.setUrl(buffer.toString());

        try {
            String result = browser.executeGet();
            JSONArray array = (JSONArray) JSONArray.parse(result);
            JSONArray rArray = array.getJSONArray(0);
            StringBuffer rBuffer = new StringBuffer();
            for (int i = 0; i < rArray.size(); i++) {
                String r = rArray.getJSONArray(i).getString(0);
                if (StringUtils.isNotBlank(r)) {
                    rBuffer.append(r);
                }
            }

            return new JsonResult().setSuccess(true).setCode("0").setMsg(rBuffer.toString());
        } catch (Exception e) {
            //throw new RuntimeException("结果集解析出错");
            System.out.println("结果集解析出错");
            return new JsonResult().setSuccess(false).setCode("1009").setMsg("异常");
        }
    }

    /**
     * 自动检测源语言
     *
     * @param word 要翻译的词
     * @param to   翻译的目标语言, 参考谷歌接口
     * @return
     * @throws Exception
     */
    public JsonResult translate(String word, String to) throws Exception {
        return translate(word, null, getLanguage(to));
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
                lan = "ko";
                break;
            case "zh_TW":
            case "cht":
                lan = "zh_TW";
                break;
            case "fr":
            case "fra":
                lan = "fr";
                break;
            case "es":
            case "spa":
                lan = "es";
                break;
            case "jp":
                lan = "ja";
                break;
            default:
                lan = "en";
                break;
        }
        return lan;
    }

}
