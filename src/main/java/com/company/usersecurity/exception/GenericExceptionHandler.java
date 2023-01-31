package com.company.usersecurity.exception;

import com.company.usersecurity.service.TranslationRepoService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.time.OffsetDateTime;

import static org.springframework.http.HttpHeaders.ACCEPT_LANGUAGE;

@RestControllerAdvice
@Slf4j
@RequiredArgsConstructor
public class GenericExceptionHandler extends ResponseEntityExceptionHandler {

    private final TranslationRepoService translationRepoService;

    @ExceptionHandler(GenericException.class)
    public ResponseEntity<ErrorResponseDto> handleGenericException(GenericException ex, WebRequest request)  {
        ex.printStackTrace();
        var path=((ServletWebRequest) request).getRequest().getRequestURL().toString();
        String lang=request.getHeader(ACCEPT_LANGUAGE);
        return createErrorResponse(ex,path,lang);
    }



    private ResponseEntity<ErrorResponseDto> createErrorResponse(GenericException ex, String path, String lang) {

        ErrorResponseDto  build= ErrorResponseDto.builder()
                .status(ex.getStatus())
                .code(ex.getCode())
                .path(path)
                .timeStamp(OffsetDateTime.now())
                .message(translationRepoService.findByKey(ex.getMessage(),lang,ex.getArguments()))
                .details(translationRepoService.findByKey(ex.getMessage().concat("_DETAIL"),lang,ex.getArguments()))
                .build();
        return ResponseEntity.status(ex.getStatus()).body(build);
    }


}

