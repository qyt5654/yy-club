package com.jingdianyy.wx.controller;

import com.jingdianyy.wx.handler.WxChatMsgFactory;
import com.jingdianyy.wx.handler.WxChatMsgHandler;
import com.jingdianyy.wx.utils.MessageUtil;
import com.jingdianyy.wx.utils.SHA1;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Objects;

@RestController
@Slf4j
public class CallBackController {

    private static final String token = "dasdasda";

    @Resource
    private WxChatMsgFactory wxChatMsgFactory;

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
        Map<String, String> msgMap = MessageUtil.parseXml(requestBody);
        String msgType = msgMap.get("MsgType");
        String event = msgMap.get("Event") == null ? "" : msgMap.get("Event");
        log.info("msgType:{}, event:{}", msgType, event);

        StringBuilder sb = new StringBuilder();
        sb.append(msgType);
        if(!StringUtils.isEmpty(event)){
            sb.append(".");
            sb.append(event);
        }

        String magTypeKey = sb.toString();
        WxChatMsgHandler wxChatMsgHandler = wxChatMsgFactory.getWxChatMsgHandler(magTypeKey);
        if(Objects.isNull(wxChatMsgHandler)){
            return "unknown";
        }
        String dealmsg = wxChatMsgHandler.dealmsg(msgMap);
        log.info("dealmsg:{}", dealmsg);
        return dealmsg;
    }
//        String msg = "<xml>\n" +
//                "  <ToUserName><![CDATA["+toUserName+"]]></ToUserName>\n" +
//                "  <FromUserName><![CDATA["+fromUserName+"]]></FromUserName>\n" +
//                "  <CreateTime>12345678</CreateTime>\n" +
//                "  <MsgType><![CDATA[text]]></MsgType>\n" +
//                "  <Content><![CDATA[你好,yyppcc]]></Content>\n" +
//                "</xml>";
}