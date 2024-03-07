package server.drawwang.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum CustomErrorCode {

    //==400==//
    THREAD_KING_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "쓰레드 왕의 게시물을 찾을 수 없습니다."),
    THREAD_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "쓰레드를 찾을 수 없습니다."),
    BOARD_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "게시물을 찾을 수 없습니다."),
    IMAGE_NOT_FOUND_ERROR(HttpStatus.NOT_FOUND, "이미지를 찾을 수 없습니다."),

    //==500==//
    FILE_PROCESSING_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일을 처리할 수 없습니다."),
    FILE_CREATE_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "파일 경로를 생성할 수 없습니다.");

    private final HttpStatus statusCode;
    private final String statusMessage;
}
