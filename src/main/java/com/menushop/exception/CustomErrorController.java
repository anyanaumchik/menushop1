package com.menushop.exception;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;

@Controller
public class CustomErrorController implements ErrorController {

    @GetMapping("/error")
    public String renderErrorPage(HttpServletResponse resp) {
        int status = resp.getStatus();
        switch (status) {
            case 404: {
                return "notFound";
            }
            case 403: {
                return "redirect:/login";
            }
            case 500: {
                return "redirect:/";
            }
        }
        return "redirect:/";
    }

    @Override
    public String getErrorPath() {
        return "/error";
    }
}