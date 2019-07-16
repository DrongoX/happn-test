package com.drongox.happntest;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
@ResponseBody
public class GlobalExceptionHandler
{
  @ExceptionHandler(IllegalArgumentException.class)
  public ResponseEntity handleIllegalArgumentException(IllegalArgumentException e)
  {
    return ResponseEntity.badRequest()
                         .body(e.getMessage());
  }
}
