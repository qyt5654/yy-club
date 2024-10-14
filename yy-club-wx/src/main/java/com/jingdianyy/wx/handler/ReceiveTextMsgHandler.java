package com.jingdianyy.wx.handler;

import com.jingdianyy.wx.redis.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.TimeUnit;

@Component
@Slf4j
public class ReceiveTextMsgHandler implements WxChatMsgHandler{

    private static final String KEY_WORD = "验证码";

    private static final String LOGIN_PREFIX = "loginCode";

    @Resource
    private RedisUtil redisUtil;

    @Override
    public WxChatMsgTypeEnum getMsgType() {
        return WxChatMsgTypeEnum.TEXT_MSG;
    }

    @Override
    public String dealmsg(Map<String, String> msgMap) {
        log.info("接收到文本消息！");
        String content = msgMap.get("Content");
        if(!KEY_WORD.equals(content)){
            return "";
        }
        String fromUserName = msgMap.get("FromUserName");
        String toUserName = msgMap.get("ToUserName");

        Random random = new Random();
        int num = 999/*random.nextInt(1000)*/;
        String numKey = redisUtil.buildKey(LOGIN_PREFIX, String.valueOf(num));
        redisUtil.setNx(numKey, fromUserName, 5L, TimeUnit.MINUTES);
        String subscribeMsg = "您当前的验证码是：" + num + "！5分钟内有效";
        String repcontent = "<xml>\n" +
                "  <ToUserName><![CDATA["+toUserName+"]]></ToUserName>\n" +
                "  <FromUserName><![CDATA["+fromUserName+"]]></FromUserName>\n" +
                "  <CreateTime>12345678</CreateTime>\n" +
                "  <MsgType><![CDATA[text]]></MsgType>\n" +
                "  <Content><![CDATA["+subscribeMsg+"]]></Content>\n" +
                "</xml>";
        return repcontent;
    }
}
