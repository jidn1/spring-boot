package com.zjjtv.utils.oss;

import com.microsoft.azure.storage.CloudStorageAccount;
import com.microsoft.azure.storage.OperationContext;
import com.microsoft.azure.storage.blob.*;
import com.zjjtv.utils.properties.PropertiesUtils;

import java.io.*;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/8/21 14:48
 * @Description: 微软上传云存储
 */
public class AzureUtil {

    public static void main(String[] args) throws FileNotFoundException {
        InputStream inputStream = new FileInputStream(new File("/Users/hurongyun/Documents/picture/banner2.jpg"));
        //测试
        upload(inputStream,"/hryfilefront/banner2.jpg");
    }

    /**
     * 上传文件
     * @param inputStream  文件流
     * @param objectName   图片相对路径和名称
     */
    public static void upload(InputStream inputStream, String objectName )  {
        String storageConnectionString = PropertiesUtils.APP.getProperty("app.azure.storageConnectionString");
//        String azureUrl = "https://xunq.blob.core.windows.net/hry-exchange-public";
        //String storageConnectionString ="DefaultEndpointsProtocol=https;AccountName=xunq;AccountKey=c0QMEMG+8iw9QgK1J1EZ4qIfXMC7EsmG79RqJrqKKmDUJoNX6cT70Fp0ipo2R3yFBCqBU1WusMOkTDV4Dgt7WA==;EndpointSuffix=core.windows.net";

        CloudStorageAccount storageAccount;
        CloudBlobClient blobClient = null;
        CloudBlobContainer container=null;
        try {
            storageAccount = CloudStorageAccount.parse(storageConnectionString);
            blobClient = storageAccount.createCloudBlobClient();
            container = blobClient.getContainerReference(PropertiesUtils.APP.getProperty("app.oss.publicBucketName"));

            System.out.println("Creating container: " + container.getName());
            container.createIfNotExists(BlobContainerPublicAccessType.CONTAINER, new BlobRequestOptions(), new OperationContext());

            CloudBlockBlob blob = container.getBlockBlobReference(objectName);

            blob.upload(inputStream, inputStream.available());
            //图片路径
            System.out.println(blob.getUri());

        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * 复制流
     * @param input
     * @return
     */
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
