package cn.edu.zju.sem.g4.taijian.controller;

import cn.edu.zju.sem.g4.taijian.service.ArticleNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ArticleNotFoundAdvice {

  @ExceptionHandler(ArticleNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  String articleNotFoundHandler(ArticleNotFoundException ex) {
    return ex.getMessage();
  }
}
