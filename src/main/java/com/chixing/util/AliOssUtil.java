package com.chixing.util;

import com.aliyun.oss.ClientException;
import com.aliyun.oss.OSS;
import com.aliyun.oss.OSSClientBuilder;
import com.aliyun.oss.OSSException;
import com.aliyun.oss.model.PutObjectRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/12 16:33
 */
@Slf4j
public class AliOssUtil {
    // Endpoint以华东1（杭州）为例，其它Region请按实际情况填写。
    static String endpoint = "oss-cn-nanjing.aliyuncs.com";
    // 阿里云账号AccessKey拥有所有API的访问权限，风险很高。强烈建议您创建并使用RAM用户进行API访问或日常运维，请登录RAM控制台创建RAM用户。
    static String accessKeyId = "LTAI5tG7nJQaHNGWeSvwmYn9";
    static String accessKeySecret = "cgXNd3ACgoXXJeu94TviYo66nzerHd";
    // 填写Bucket名称，例如examplebucket。
    static String bucketName = "zhangxu-1023";
    public static String sendImg(MultipartFile file,String myDirectoryName) {
        // 填写本地文件的完整路径，例如D:\\localpath\\examplefile.txt。
        String localDate = LocalDate.now().toString();
        // 创建OSSClient实例。
        String objectName = myDirectoryName + "/"+localDate + "/" + UUID.randomUUID().toString().replace("-","") + file.getOriginalFilename();

        OSS ossClient = new OSSClientBuilder().build(endpoint, accessKeyId, accessKeySecret);
        URL url = null;
        try {
            // 创建PutObjectRequest对象。
            PutObjectRequest putObjectRequest = new PutObjectRequest(bucketName, objectName, transferToFile(file));
            // 设置签名URL过期时间，单位为毫秒。
            Date expiration = new Date(System.currentTimeMillis() + 360000 * 1000);
            // 生成以GET方法访问的签名URL，访客可以直接通过浏览器访问相关内容。
            ossClient.putObject(putObjectRequest);
            url = ossClient.generatePresignedUrl(bucketName, objectName, expiration);
            log.info(url.toString());
            return url.toString().split("\\?")[0];
        } catch (OSSException oe) {
            System.out.println("Caught an OSSException, which means your request made it to OSS, "
                    + "but was rejected with an error response for some reason.");
            System.out.println("Error Message:" + oe.getErrorMessage());
            System.out.println("Error Code:" + oe.getErrorCode());
            System.out.println("Request ID:" + oe.getRequestId());
            System.out.println("Host ID:" + oe.getHostId());
        } catch (ClientException ce) {
            System.out.println("Caught an ClientException, which means the client encountered "
                    + "a serious internal problem while trying to communicate with OSS, "
                    + "such as not being able to access the network.");
            System.out.println("Error Message:" + ce.getMessage());
            return null;
        } finally {
            if (ossClient != null) {
                ossClient.shutdown();
            }
        }
        return null;
    }

    /**
     * 此方法是将前端文件MultipartFile 对象转换为 File对象的方法
     * @param multipartFile 前端文件对象
     * @return File对象
     */
    private static File transferToFile(MultipartFile multipartFile)  {
        String originalFilename = multipartFile.getOriginalFilename();
        String prefix = originalFilename.split("\\.")[0];
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));
        File file = null;
        try {
            file = File.createTempFile(prefix, suffix);
            multipartFile.transferTo(file);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return file;
    }

}
