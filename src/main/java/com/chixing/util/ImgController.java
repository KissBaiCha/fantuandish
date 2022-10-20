package com.chixing.util;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author Xu Zhang
 * @version 1.0
 * @date 2022/10/10 19:11
 */
@Controller
public class ImgController {
    @RequestMapping("img")
    public String hello(){
        return "Img";
    }
    @ResponseBody
    @PostMapping("upload")
    public String sendFile( MultipartFile myfile){
        // images/log  ---- user ----- customer ------ food ---- shop ----
        return AliOssUtil.sendImg(myfile,"images");
    }
}
