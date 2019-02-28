package com.springbootvideo.common.util;

import com.alibaba.fastjson.JSON;
import org.apache.http.*;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.protocol.BasicHttpContext;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.*;
import java.util.UUID;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/2/28 10:23
 * @Description: 文件处理类
 */
@Component
public class FileUtil {

    @Resource
    private  OssUtil ossUtil;

    /**
     * 本地上传文件至oss
     * @param files
     * @return
     */
    public  String[] upload(@RequestParam("file") MultipartFile[] files) {
        String[] pathImg = new String[3];
        try {
            if (files != null && files.length > 0) {
                for (int i = 0; i < files.length; i++) {
                    MultipartFile file = files[i];
                    if(file !=null){
                        // 获取文件名
                        String filename = file.getOriginalFilename();
                        if (filename != null && filename.length() > 0) {
                            String newFileName = UUID.randomUUID() + filename.substring(filename.lastIndexOf("."));
                            ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
                            //上传流
                            InputStream ossStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray());
                            byteArrayOutputStream.close();

                            ossUtil.upload(ossStream, newFileName,true);
                            pathImg[i] =  newFileName;
                        }
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return pathImg;
    }

    /**
     * 下载http 文件
     * @param url
     * @return
     */
    public  String downFileFromHttp(String url){
        try {
            HttpGet httpGet = new HttpGet(url);
            RequestConfig requestConfig = RequestConfig.custom().setSocketTimeout(100000).setConnectTimeout(100000).build();
            httpGet.setConfig(requestConfig);
            //发起请求，获取响应信息 创建httpClient
            CloseableHttpClient httpClient = HttpClients.createDefault();
            CloseableHttpResponse response = null;

            File file = null;
            //发起请求，获取响应信息
            response = httpClient.execute(httpGet, new BasicHttpContext());
//            System.out.println("HttpStatus.SC_OK:"+ HttpStatus.SC_OK);
//            System.out.println("response.getStatusLine().getStatusCode():"+response.getStatusLine().getStatusCode());
//            System.out.println("http-header:"+ JSON.toJSONString( response.getAllHeaders() ));
//            System.out.println("http-filename:"+getFileName(response) );

            if(HttpStatus.SC_OK==response.getStatusLine().getStatusCode()){
                //6.取得请求内容
                HttpEntity entity = response.getEntity();

                if (entity != null) {
                    //这里可以得到文件的类型 如image/jpg /zip /tiff 等等 但是发现并不是十分有效，有时明明后缀是.rar但是取到的是null，这点特别说明
                    System.out.println(entity.getContentType());
                    //可以判断是否是文件数据流
                    System.out.println(entity.isStreaming());


                    //6.1.1获取文件名，拼接文件路径
//                    String fileName="D://test.jpg";
//                    file = new File(fileName);
//                    //6.1.2根据文件路径获取输出流
//                    FileOutputStream output = new FileOutputStream(file);
//
//                    InputStream input = entity.getContent();
//
//                    //6.3 将数据写入文件：将输入流中的数据写入到输出流
//                    byte b[] = new byte[1024];
//                    int j = 0;
//                    while( (j = input.read(b))!=-1){
//                        output.write(b,0,j);
//                    }
//                    output.flush();
//                    output.close();

                    String newFileName = UUID.randomUUID() + ".jpg";
                    InputStream input = entity.getContent();
                    //上传流
                    ossUtil.upload(input, "video/"+newFileName, true);
                  //  byte[] bytes = readInputStream(input);
                    return "video/"+newFileName;
                }
                if (entity != null) {
                    entity.consumeContent();
                }
            }
        } catch (Exception  e){
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 获取文件名
     * @param response
     * @return
     */
    public  String getFileName(HttpResponse response) {
        Header contentHeader = response.getFirstHeader("Content-Disposition");
        String filename = null;
        if (contentHeader != null) {
            HeaderElement[] values = contentHeader.getElements();
            if (values.length == 1) {
                NameValuePair param = values[0].getParameterByName("filename");
                if (param != null) {
                    try {
                        filename = param.getValue();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        return filename;
    }

    /**
     * 转换成 byte
     * @param inStream
     * @return
     * @throws Exception
     */
    public  byte[] readInputStream(InputStream inStream) throws Exception {
        ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        //创建一个Buffer字符串
        byte[] buffer = new byte[1024 * 4];
        //每次读取的字符串长度，如果为-1，代表全部读取完毕
        int len;
        //使用一个输入流从buffer里把数据读取出来
        while ((len = inStream.read(buffer)) != -1) {
            //用输出流往buffer里写入数据，中间参数代表从哪个位置开始读，len代表读取的长度
            outStream.write(buffer, 0, len);
        }
        //关闭输入流
        inStream.close();
        //把outStream里的数据写入内存
        return outStream.toByteArray();
    }

}
