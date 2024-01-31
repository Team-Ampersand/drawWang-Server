package server.drawwang.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CustomErrorCode {

    //==400==//
    THREAD_NOT_FOUND_ERROR(HttpStatus.BAD_REQUEST, "쓰레드를 찾을 수 없습니다.");

    private final HttpStatus statusCode;
    private final String statusMessage;
}
