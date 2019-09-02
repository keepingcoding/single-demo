package com.example.demo.web.controller;

import com.example.demo.web.bean.FormData;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author zzs
 * @date 2019/9/3 0:01
 */
@Controller
public class VueController {

    @PostMapping("/uploadFile")
    public void uploadFile(MultipartFile[] multipartFiles) {
        for (MultipartFile file : multipartFiles) {
            System.err.println(file.getOriginalFilename());
        }
    }

    @PostMapping("/uploadFileAndData")
    public void uploadFileWithFormData(FormData formData) {

    }
}
