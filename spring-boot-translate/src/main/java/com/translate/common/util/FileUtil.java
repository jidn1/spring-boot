package com.translate.common.util;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.util.UUID;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/6/19 13:40
 * @Description:
 */
public class FileUtil {

    public static String writeBytesToOssVideo(byte[] bfile, String filePath) {
        String fileName =  UUID.randomUUID()+".mp3";
        try {
            //上传流
            InputStream ossStream = new ByteArrayInputStream(bfile);
            OssUtil.upload(ossStream, filePath+"/"+fileName,true);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return filePath+"/"+fileName;
    }

}
