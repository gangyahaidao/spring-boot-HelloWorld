package com.qingpu.demo.controller;

import com.qingpu.demo.EncryptUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

@RestController
@RequestMapping("/weixin")
public class HelloWorldController {

    @RequestMapping("/server_identify")
    public String serverIdentify(@RequestParam("signature") String signature,
                                 @RequestParam("timestamp") String timestamp,
                                 @RequestParam("nonce") String nonce,
                                 @RequestParam("echostr") String echostr) {
        System.out.println(signature + ", " + timestamp + ", " + nonce + ", " + echostr);
        String token = "qingpu-g58-mall";
        String[] params = new String[] {token, timestamp, nonce};
        Arrays.sort(params);// 按照api要求进行字典序排序
        StringBuilder signStrBuilder = new StringBuilder();
        for(int i = 0; i < params.length; i++) {
            signStrBuilder.append(params[i]);
        }
        if(signature.equals(EncryptUtils.getSha1(signStrBuilder.toString()))) { // 如果签名校验正确
            return echostr;
        } else {
            System.out.println("签名校验失败");
            return null;
        }
    }
}
