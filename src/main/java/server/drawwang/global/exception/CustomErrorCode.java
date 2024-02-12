package server.drawwang.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CustomErrorCode {

    //==400==//
    THREAD_KING_ALREADY_EXISTS(HttpStatus.BAD_REQUEST, "쓰레드의 왕이 이미 지정되어 있습니다."),
    THREAD_NOT_EXPIRED(HttpStatus.BAD_REQUEST, "쓰레드가 만료되지 않았습니다."),
    THREAD_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "쓰레드를 찾을 수 없습니다."),
    BOARD_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다.");

    private final HttpStatus statusCode;
    private final String statusMessage;
}
