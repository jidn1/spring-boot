package com.notes.study.websocketClient;

import lombok.extern.slf4j.Slf4j;
import okhttp3.*;
import okio.ByteString;
import org.apache.commons.lang.StringUtils;

import java.util.concurrent.*;

@Slf4j
public class OkHttpSocketClient {


    public void createBroadcastWs() {
        OkHttpClient mOkHttpClient = new OkHttpClient.Builder()
                .readTimeout(30000, TimeUnit.SECONDS)//设置读取超时时间
                .writeTimeout(30000, TimeUnit.SECONDS)//设置写的超时时间
                .connectTimeout(30, TimeUnit.SECONDS)//设置连接超时时间
                .build();


        Request request = new Request.Builder().url(getWebsocketUrl()).build();

        mOkHttpClient.newWebSocket(request, new EchoWebSocketListener());
//        mOkHttpClient.dispatcher().executorService().shutdown();
    }

    private String getWebsocketUrl() {
        return  "wss://csocketapi.bitgetapi.com/ws/v1";
    }



    private class EchoWebSocketListener extends WebSocketListener {

        @Override
        public void onOpen(WebSocket webSocket, Response response) {
            super.onOpen(webSocket, response);
            System.out.println("连接成功" + webSocket.queueSize() + "....");
            webSocket.send("{\"op\": \"subscribe\", \"args\": [\"swap/ticker:btcusd\"]}");
        }

        @Override
        public void onMessage(WebSocket webSocket, ByteString bytes) {
            super.onMessage(webSocket, bytes);
            try {
                String content = ZipUtil.uncompress(bytes.toByteArray());
                if (StringUtils.isBlank(content)) return;


                if (content.contains("ping")) {
//                    SubMessage subMessage = JSON.parseObject(content, SubMessage.class);
//                    if ("ping".equalsIgnoreCase(subMessage.getEvent())) {
//                        webSocket.send("{\"event\":\"pong\",\"ts\":" + subMessage.getTs() + "}");
//                        return;
//                    }
                }
                webSocket.send("{'event':'ping'}");
                if (!content.contains("pong")) {
                    log.info("receive  content {} ", content);
                }

            } catch (Exception e) {
                log.error("Content decompression exception", e);
            }
        }

        @Override
        public void onMessage(WebSocket webSocket, String text) {
            super.onMessage(webSocket, text);
            log.info("receive {}", text);

        }

        @Override
        public void onClosed(WebSocket webSocket, int code, String reason) {
            super.onClosed(webSocket, code, reason);
            log.info("closed:" + reason);
        }

        @Override
        public void onClosing(WebSocket webSocket, int code, String reason) {
            super.onClosing(webSocket, code, reason);
            log.info("closing:" + reason);
        }

        @Override
        public void onFailure(WebSocket webSocket, Throwable t, Response response) {
            super.onFailure(webSocket, t, response);
            log.info("failure:" + t.getMessage());
        }
    }


}