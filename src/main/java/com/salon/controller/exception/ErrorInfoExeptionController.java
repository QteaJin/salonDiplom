package com.salon.controller.exception;

import com.salon.service.exception.ExceptionCustom;
import com.salon.service.exception.ErrorInfoExeption;
import com.salon.utility.Const;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ErrorInfoExeptionController extends ResponseEntityExceptionHandler {

    @ExceptionHandler(ErrorInfoExeption.class)
    public ResponseEntity<ExceptionCustom> resourceNotFound(ErrorInfoExeption ex) {
        ExceptionCustom exceptionCustom = new ExceptionCustom();
        exceptionCustom.setCode(Const.Exception.Code.NOT_FOUND);
        exceptionCustom.setType(ex.getType());
        exceptionCustom.setMessege(ex.getMessage());

        return new ResponseEntity<ExceptionCustom>(exceptionCustom, HttpStatus.NOT_FOUND);
    }

}
