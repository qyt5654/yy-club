package com.jingdianyy.wx.handler;

import java.util.Map;

public interface WxChatMsgHandler {

    WxChatMsgTypeEnum getMsgType();

    String dealmsg(Map<String, String> msgMap);

}
