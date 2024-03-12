package cn.edu.zju.sem.g4.taijian.service;

import cn.edu.zju.sem.g4.taijian.entity.Article;
import java.util.List;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
class KGServiceTest {
  @Autowired
  private KGService kgService;

  @Test
  public void test(){
    List<Article> articles = kgService.findKGRelations(2L);
    System.out.println("测试函数");
    for(Article a: articles){
      System.out.println(a.toString());
    }
  }
}