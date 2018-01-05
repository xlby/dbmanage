package com.fifedu.util.common;

import com.alibaba.fastjson.JSONObject;
import com.qcloud.cos.COSClient;
import com.qcloud.cos.meta.InsertOnly;
import com.qcloud.cos.request.UploadFileRequest;

import java.io.File;
import java.io.InputStream;

/**
 * Created by super on 2016/8/1.
 * 腾讯云COS 参数工具类
 */
public class CosQCloudUtils{

    static private int appId;
    static private String secretId;
    static private String secretKey;
    static private String bucketName;

    static {
        try {
            appId = Integer.parseInt(GlobalConfig.getConfig("cos.appId"));
            secretId = GlobalConfig.getConfig("cos.secretId");
            secretKey = GlobalConfig.getConfig("cos.secretKey");
            bucketName = GlobalConfig.getConfig("cos.bucketName");
        } catch (Exception e) {
            System.out.println(">>>>>init cos config failed, 初始化腾讯云COS参数失败");
            throw e;
        }
    }


    public static JSONObject upload(String cosFilePath, String filePath, boolean overWrite) {
        COSClient client = new COSClient(appId, secretId, secretKey);
        try {
            UploadFileRequest uploadFileRequest = new UploadFileRequest(bucketName, cosFilePath, filePath);
            if (overWrite){
                uploadFileRequest.setInsertOnly(InsertOnly.OVER_WRITE);
            }else {
                uploadFileRequest.setInsertOnly(InsertOnly.NO_OVER_WRITE);
            }
            String result = client.uploadFile(uploadFileRequest);
            System.out.println(result);
            return (JSONObject) JSONObject.parse(result);
        }finally {
            client.shutdown();
        }
    }

}
