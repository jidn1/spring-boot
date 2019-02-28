package com.springbootvideo.common.util;

import com.aliyun.oss.OSSClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Date;

/**
 * @Copyright © 正经吉
 * @Author: Jidn
 * @Date: 2019/1/16 13:13
 * @Description: 阿里云Oss
 */
@Component
@PropertySource("classpath:application.yml")
public class OssUtil {

    @Value("${app.oss.endpoint}")
    private  String END_POINT;

    @Value("${app.oss.accessKeyId}")
    private  String ACCESS_KEYID;

    @Value("${app.oss.accessKeySecret}")
    private  String ACCESS_KEY_SECRET;

    @Value("${app.oss.publicBucketName}")
    private  String PUBLIC_BUCKET_NAME;

    @Value("${app.oss.privateBucketName}")
    private  String PRIVATE_BUCKET_NAME;

    /**
     * 获得文件夹访问路径
     * @param objectName 文件路径
     * @param isPrivate 是否为私有
     * @return
     */
    public String getUrl(String objectName,boolean isPrivate) {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId =  ACCESS_KEYID;
        String accessKeySecret =  ACCESS_KEY_SECRET;
        String bucketName;
        if(isPrivate) {
            bucketName = PRIVATE_BUCKET_NAME;
        }else{
            bucketName = PUBLIC_BUCKET_NAME;
        }
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 设置URL过期时间为1小时。
        Date expiration = new Date(System.currentTimeMillis() + 3600 * 1000);
        // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
        URL url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
        // 关闭OSSClient。
        ossClient.shutdown();
        return url.toString();
    }

    /**
     * 上传文件
     * @param inputStream
     * @param objectName
     */
    public  void upload(InputStream inputStream,String objectName,boolean isPrivate)  {
        // Endpoint以杭州为例，其它Region请按实际情况填写。
        String endpoint = END_POINT;
        // 云账号AccessKey有所有API访问权限，建议遵循阿里云安全最佳实践，创建并使用RAM子账号进行API访问或日常运维，请登录 https://ram.console.aliyun.com 创建。
        String accessKeyId =  ACCESS_KEYID;
        String accessKeySecret =  ACCESS_KEY_SECRET;
        String bucketName;
        if(isPrivate) {
            bucketName = PRIVATE_BUCKET_NAME;
        }else{
            bucketName = PUBLIC_BUCKET_NAME;
        }
        // 创建OSSClient实例。
        OSSClient ossClient = new OSSClient(endpoint, accessKeyId, accessKeySecret);
        // 上传文件流。
        ossClient.putObject(bucketName, objectName, inputStream);
        // 关闭OSSClient。
        ossClient.shutdown();
    }


    public static ByteArrayOutputStream cloneInputStream(InputStream input) {
        try {
            ByteArrayOutputStream baos = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int len;
            while ((len = input.read(buffer)) > -1) {
                baos.write(buffer, 0, len);
            }
            baos.flush();

            return baos;
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }


}
