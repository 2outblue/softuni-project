package com.ngfrt.appmain.web;

import com.ngfrt.appmain.service.exception.EventNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.ModelAndView;

@ControllerAdvice
public class GlobalExceptionHandler {


    @ExceptionHandler(EventNotFoundException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ModelAndView handleEventNotFound(EventNotFoundException ex, Model model) {
        model.addAttribute("message", ex.getMessage());

        return new ModelAndView("custom-error");
    }
}
