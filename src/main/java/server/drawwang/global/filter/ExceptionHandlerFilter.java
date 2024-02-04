package server.drawwang.global.filter;

import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.MediaType;
import org.springframework.web.filter.OncePerRequestFilter;
import server.drawwang.global.exception.CustomErrorResponse;
import server.drawwang.global.exception.CustomException;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

@Slf4j
public class ExceptionHandlerFilter extends OncePerRequestFilter {

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        try {
            filterChain.doFilter(request, response);
        }catch (CustomException e){
            setErrorResponse(response, e);
        }
    }

    private void setErrorResponse(
            HttpServletResponse response,
            CustomException customException
    ){
        ObjectMapper objectMapper = new ObjectMapper();
        response.setStatus(customException.getCustomErrorCode().getStatusCode().value());
        response.setContentType(MediaType.APPLICATION_JSON_VALUE);
        response.setCharacterEncoding("UTF-8");

        CustomErrorResponse customErrorResponse = CustomErrorResponse.builder()
                .status(customException.getCustomErrorCode())
                .statusMessage(customException.getDetailMessage())
                .build();
        try{
            response.getWriter().write(objectMapper.writeValueAsString(customErrorResponse));
        }catch (IOException e){
            e.printStackTrace();
        }
    }
}
