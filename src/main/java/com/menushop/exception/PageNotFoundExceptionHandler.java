package com.menushop.exception;

import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class PageNotFoundExceptionHandler {

    @ExceptionHandler(PageNotFoundException.class)
    public String notDish(Exception e) {
        return "notFound";
    }


}
