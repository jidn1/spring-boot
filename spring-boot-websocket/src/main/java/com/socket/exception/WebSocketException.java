package com.socket.exception;

/**
 * @Copyright © 北京互融时代软件有限公司
 * @Author: Jidn
 * @Date: 2019/7/31 10:37
 * @Description: webSocket 异常
 */
public class WebSocketException extends RuntimeException {

    private Code code;

    private String message;

    public WebSocketException () {
    }

    public WebSocketException (String message) {
        super(message);
        this.message = message;
    }

    public WebSocketException (Throwable cause) {
        super(cause);
    }

    public WebSocketException (String message, Throwable cause) {
        super(message, cause);
        this.message = message;
    }

    public WebSocketException (Throwable cause, Code code) {
        super(cause);
        this.code = code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    public enum Code {
        /**
         *
         */
        OTHER_ERROR(1051, "其他异常"),
        /**
         *
         */
        FIRST_PUSH(1052, "首次推送"),
        /**
         *
         */
        REQ_PUSH(1053, "连接异常"),

        /**
         *
         */
        SEND_MSG(1054, "发送消息异常"),

        /**
         *
         */
        SEND_ERROR(1055, "消息处理"),
        ;

        Code(int code, String distribution) {
            this.code = code;
            this.distribution = distribution;
        }


        private int code;

        private String distribution;
    }
}
