package com.bookshop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PageNotFoundExceptionHandler {

    @ExceptionHandler(PageNotFoundException.class)
    public String notBook(Exception e) {
        return "notFound";
    }


}
