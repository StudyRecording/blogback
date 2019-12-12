package com.hu.blogback.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 文件上传
 */
public class FileUploadUtil {

    // 图片保存的相对路径
    public static final String IMAGE_PATH =  "/imagePath/";

    /**
     * 单个文件上传
     * @param uploadFile 上传的文件
     * @param realPath   保存在本地路径中的映射部分
     * @param relativePath 相对路径
     * @param request
     * @return 成功：网络访问路径，失败：null
     */
    public static String imageUpload(MultipartFile uploadFile,
                                     String realPath,
                                     String relativePath,
                                     HttpServletRequest request) {
        //String realPath = request.getSession().getServletContext().getRealPath("/uploadFile/");
        //System.out.println("------------------realPath: " + realPath);
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy/MM/dd");
        String format = sdf.format(new Date());
        File folder = new File(realPath + relativePath+ format);
        if (!folder.isDirectory()) {
            folder.mkdirs();
        }

        String oldName = uploadFile.getOriginalFilename();
        String newName = UUID.randomUUID().toString() +
                oldName.substring(oldName.lastIndexOf("."), oldName.length());

        try {
            uploadFile.transferTo(new File(folder, newName));
            String filePath = request.getScheme() + "://" + request.getServerName() +
                    ":" +request.getServerPort() + "/uploadFile" + relativePath + format + "/"+ newName;
            //String filePath = "/uploadFile/imageFile/" + format + "/"+ newName;
            return filePath;
        } catch (IOException e) {
            e.printStackTrace();
        }

        return "null";
    }
}
