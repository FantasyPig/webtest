package com.alex.webtest.exception;

import com.alex.webtest.result.CodeMsg;
import com.alex.webtest.result.Result;
import org.springframework.validation.BindException;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler {

    @ExceptionHandler(value = Exception.class)
    public Result<String> exceptionHandler(Exception e){
        e.printStackTrace();
        if(e instanceof GlobalException){
            GlobalException exception = (GlobalException) e;
            return Result.error(exception.getCm());
        } else if(e instanceof BindException){
            BindException exception = (BindException) e;
            List<ObjectError> errors = exception.getAllErrors();
            ObjectError error = errors.get(0);
            String msg = error.getDefaultMessage();
            return Result.error(CodeMsg.BIND_ERROR.fillArgs(error));
        } else{
            return Result.error(CodeMsg.SERVER_ERROR);
        }
    }
}
