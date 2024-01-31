package server.drawwang.global.exception;

import lombok.Getter;

@Getter
public class CustomException extends RuntimeException{
    private CustomErrorCode customErrorCode;
    private String detailMessage;

    public CustomException(CustomErrorCode customErrorCode) {
        super(customErrorCode.getStatusMessage());
        this.customErrorCode = customErrorCode;
        this.detailMessage = customErrorCode.getStatusMessage();
    }
}
