package cn.edu.zju.sem.g4.taijian.service;

public class ArticleNotFoundException extends RuntimeException {

  ArticleNotFoundException(Long id) {
    super("Article #" + id + " cannot be found.");
  }
}
