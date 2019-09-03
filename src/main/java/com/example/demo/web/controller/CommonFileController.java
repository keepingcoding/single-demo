package com.example.demo.web.controller;

import com.example.demo.web.bean.FormData;
import com.google.common.io.Files;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestPart;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author zzs
 * @date 2019/9/3 0:01
 */
@Controller
public class CommonFileController {

    /**
     * 1.@RequestPart这个注解用在multipart/form-data表单提交请求的方法上。
     * 2.支持的请求方法的方式MultipartFile，属于Spring的MultipartResolver类。这个请求是通过http协议传输的。
     * 3.@RequestParam也同样支持multipart/form-data请求。
     * 4.他们最大的不同是，当请求方法的请求参数类型不再是String类型的时候。
     * 5.@RequestParam适用于name-valueString类型的请求域，@RequestPart适用于复杂的请求域（像JSON，XML）。
     *
     * @param multipartFile
     * @return
     */
    @PostMapping("/uploadFile")
    @ResponseBody
    public String uploadFile(@RequestParam("file") MultipartFile multipartFile) {
        if (multipartFile == null) {
            return "null";
        }

        copyFile(multipartFile);
        return "success";
    }

    @PostMapping("/uploadFileAndData")
    public void uploadFileWithFormData(FormData formData) {

    }

    /**
     * 保存文件
     *
     * @param file
     * @return 保存的文件名
     */
    private String copyFile(MultipartFile file) {
        String originalFilename = file.getOriginalFilename();
        String suffix = originalFilename.substring(originalFilename.lastIndexOf("."));

        String s = UUID.randomUUID().toString();
        //s = s.replaceAll("-", "");
        String newName = s + suffix;
        String parentPath = getParentPath();

        File dest = new File(parentPath, newName);
        try {
            Files.createParentDirs(dest);
            file.transferTo(dest);
            return newName;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    private String getParentPath() {
        String parentPath = "";
        //默认存储classpath目录，可修改为可配置
        if (StringUtils.isBlank(baseStoreDir)) {
            String rootUri = ClassUtils.getDefaultClassLoader().getResource("").getPath();
            parentPath = rootUri + File.separator + "upload" + File.separator + "base";
        } else {
            parentPath = baseStoreDir;
        }
        return parentPath;
    }

    @Value("${com.example.demo.baseStoreDir:''}")
    private String baseStoreDir;
}
