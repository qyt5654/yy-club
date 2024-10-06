package com.jingdianyy.wx.controller;

import com.jingdianyy.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.*;

@RestController
@Slf4j
public class CallBackController {

    private static final String token = "dasdasda";

    @RequestMapping("/test")
    public String test(){
        return "hello world";
    }

    /**
     * 回调消息校验
     * @return
     */
    @GetMapping("/callback")
    public String callback(@RequestParam("signature") String signature,
                           @RequestParam("timestamp") String timestamp,
                           @RequestParam("nonce") String nonce,
                           @RequestParam("echostr") String echostr){
        log.info("get验签请求参数：signature:{}，timestamp:{}，nonce:{}，echostr:{}",
                signature, timestamp, nonce, echostr);
        String shaStr = SHA1.getSHA1(token, timestamp, nonce, "");
        log.info("shaStr:{}", shaStr);
        if(signature.equals(shaStr)){
            return echostr;
        }
        return "unknown";
    }

    @PostMapping(value = "/callback", produces = "application/xml;charset=UTF-8")
    public String callback(
            @RequestBody String requestBody,
            @RequestParam("signature") String signature,
            @RequestParam("timestamp") String timestamp,
            @RequestParam("nonce") String nonce,
            @RequestParam(value = "msg_signature", required = false) String msgSignature){
        log.info("接收到微信的请求：requestBody:{}，signature:{}，timestamp:{}，nonce:{}，msgSignature:{}",
                requestBody, signature, timestamp, nonce, msgSignature);

        return "unknown";
    }

}