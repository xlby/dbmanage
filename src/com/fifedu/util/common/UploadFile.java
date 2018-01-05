package com.fifedu.util.common;


import org.apache.commons.lang3.time.DateFormatUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.UUID;
/**
 * 上传文件工具类
 * @author liu10
 *
 */
public class UploadFile {
    private static Logger logger = LoggerFactory.getLogger(UploadFile.class);
/**
 * 上传文件至云端
 * @param file 本地文件
 * @param ext 排除文件类型
 * @return
 */
    public static String localUpload(MultipartFile file, String ext) {
        String inputFormat = file.getOriginalFilename().split("\\.")[1];
        String dateFolder = DateFormatUtils.format(new Date(), "yyyyMMdd");
        if (ext.indexOf(inputFormat.toLowerCase()) < 0) {
            logger.warn("上传文件格式不正确。。[{}]", ext);
            return "-1";
        }
        String path = "tmp_dir" + "/" + dateFolder;
        String randomFileName = UUID.randomUUID().toString();
        String newFileName = randomFileName + "." + inputFormat.toLowerCase();
        File targetFile = new File(path, newFileName);
        if (!targetFile.exists()) {
            logger.info("创建文件路径。。。[{}]", targetFile.getPath());
            targetFile.mkdirs();
        }
        try {
            String cosFolder = "/staff/" + dateFolder + "/" + newFileName;
            file.transferTo(targetFile);
            CosQCloudUtils.upload(cosFolder, targetFile.getPath(), true);
            targetFile.delete();
//            String filePath = "/" + dateFolder + "/" + newFileName;
            return cosFolder;
        } catch (Exception e) {
            logger.error("上传文件发生异常，", e);
            return "";
        }
    }
}
