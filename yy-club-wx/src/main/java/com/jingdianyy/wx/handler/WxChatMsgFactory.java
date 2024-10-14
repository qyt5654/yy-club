package com.jingdianyy.wx.handler;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Component
public class WxChatMsgFactory implements InitializingBean {

    @Resource
    private List<WxChatMsgHandler> wxChatMsgHandlerList;

    private Map<WxChatMsgTypeEnum, WxChatMsgHandler> wxChatMsgHandlerMap = new HashMap<>();

    public WxChatMsgHandler getWxChatMsgHandler(String msgType) {
        WxChatMsgTypeEnum msgTypeEnum = WxChatMsgTypeEnum.getByMsgType(msgType);
        return wxChatMsgHandlerMap.get(msgTypeEnum);
    }


    @Override
    public void afterPropertiesSet(){
        for (WxChatMsgHandler wxChatMsgHandler : wxChatMsgHandlerList) {
            wxChatMsgHandlerMap.put(wxChatMsgHandler.getMsgType(), wxChatMsgHandler);
        }
    }
}
