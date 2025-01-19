package ru.shift.filefilterapp.exception.handler;

import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class GlobalExceptionHandler {

  @AfterThrowing(pointcut = "execution(* ru.shift.filefilterapp..*(..))", throwing = "ex")
  public void handleException(Exception ex) {
    System.err.println("== Произошла ошибка ==\n" + ex.getMessage());
    System.exit(1);
  }
}
