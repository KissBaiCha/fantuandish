package com.chixing.controller;

import com.chixing.util.AesUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class CustomweMessageController {
    @GetMapping("/jiamiTest")
    public String jiami(){
        String data = "zhangxu1023.";
        String decrypt;
        try {
            String encrypt = AesUtil.encrypt(data, AesUtil.getKey());
            log.info("加密后的数据={}",encrypt);
            decrypt = AesUtil.decrypt(encrypt, AesUtil.getKey());
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return decrypt;
    }
}
