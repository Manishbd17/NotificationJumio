package com.jumio.notificationSystem.exception;

public class CustomException extends RuntimeException{

        private int errorCode;
        private String errorMessage;

        public CustomException(int errorCode, String errorMessage) {
            this.errorCode = errorCode;
            this.errorMessage = errorMessage;
        }

        public int getErrorCode() {
            return errorCode;
        }

        public String getErrorMessage() {
            return errorMessage;
        }
}
