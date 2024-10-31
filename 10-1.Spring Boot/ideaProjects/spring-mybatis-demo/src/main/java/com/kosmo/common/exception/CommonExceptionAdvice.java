package com.kosmo.common.exception;

import com.kosmo.user.exception.NoMemberException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.sql.SQLException;

// 예외 처리를 하는 메서드를 모아두자
@ControllerAdvice
@Slf4j
public class CommonExceptionAdvice {

    @ExceptionHandler(NoMemberException.class)
    public String exceptionHandler(Exception ex, Model m){
        m.addAttribute("msg", ex.getMessage());
        m.addAttribute("loc", "javascript:history.back()");

        return "message";
    }

    @ExceptionHandler({SQLException.class, NumberFormatException.class})
    public String exceptionHandler2(Exception ex, Model m){
        log.error(ex.getMessage());

        m.addAttribute("msg", ex.getMessage());
        return "error";
    }
}
