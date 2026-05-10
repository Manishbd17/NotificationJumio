package com.jumio.notificationSystem.exception;

public enum ExceptionCode implements ErrorCode {

    NOTIFICATION_NOT_SENT(notSent, "Notification not sent"),
    INVALID_REQUEST(invalidInput, "Bad Request");

    private final int code;
    private final String message;

    ExceptionCode(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

}
