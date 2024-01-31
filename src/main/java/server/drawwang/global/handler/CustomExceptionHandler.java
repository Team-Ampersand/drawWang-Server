package server.drawwang.global.handler;

import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import server.drawwang.global.exception.CustomErrorResponse;
import server.drawwang.global.exception.CustomException;

@Slf4j
@RestControllerAdvice
public class CustomExceptionHandler {

    @ExceptionHandler(CustomException.class)
    public ResponseEntity<CustomErrorResponse> handlerException(
            CustomException e,
            HttpServletRequest request
    ) {
        log.error("errorCode: {}, url: {}, message: {}",
                e.getCustomErrorCode(), request.getRequestURI(), e.getDetailMessage());

        CustomErrorResponse response = CustomErrorResponse.builder()
                .status(e.getCustomErrorCode())
                .statusMessage(e.getDetailMessage())
                .build();

        return new ResponseEntity<>(response, e.getCustomErrorCode().getStatusCode());
    }
}
