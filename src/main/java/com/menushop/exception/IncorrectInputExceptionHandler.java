package com.menushop.exception;

import com.menushop.controller.DishController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.servlet.http.HttpServletRequest;


@ControllerAdvice
public class IncorrectInputExceptionHandler {

    @Autowired
    private DishController dishController;

    @ExceptionHandler(Exception.class)
    public String notDish(Exception e) {
        return "redirect:/";
    }

    @ExceptionHandler(NumberFormatException.class)
    public String notCorrectInput(Exception e, Model model, HttpServletRequest httpServletRequest, @PageableDefault(value = 12) Pageable pageable) {
        if (httpServletRequest.getRequestURL().indexOf("author") != -1)
            return "redirect:/author";
        if (httpServletRequest.getRequestURL().indexOf("category") != -1)
            return "redirect:/category";
        if (httpServletRequest.getRequestURL().indexOf("user") != -1)
            return "redirect:/user";
        if (httpServletRequest.getRequestURL().indexOf("book") + 4 != httpServletRequest.getRequestURL().length())
            return "redirect:/book";
        model.addAttribute("priceError", "");
        return dishController.dishList(model, pageable);
    }
}
