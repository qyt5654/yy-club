package com.jingdianyy.wx.handler;


import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
@Slf4j
public class SubscribeMsgHandler implements WxChatMsgHandler{
    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.SUBSCRIBE;
    }

    @Override
    public String dealmsg(Map<String, String> msgMap) {
        log.info("触发用户关注事件！");
        String fromUserName = msgMap.get("FromUserName");
        String toUserName = msgMap.get("ToUserName");
        String subscribeMsg = "感谢你的关注";
        String content = "<xml>\n" +
                "  <ToUserName><![CDATA["+toUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+fromUserName+"]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+subscribeMsg+"]]></Content>\n" +
                "</xml>";
        return content;
    }
}
