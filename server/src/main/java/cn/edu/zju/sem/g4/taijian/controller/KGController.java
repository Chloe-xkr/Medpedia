package cn.edu.zju.sem.g4.taijian.controller;

import cn.edu.zju.sem.g4.taijian.entity.Article;
import cn.edu.zju.sem.g4.taijian.entity.View;
import cn.edu.zju.sem.g4.taijian.service.ArticleService;
import cn.edu.zju.sem.g4.taijian.service.KGService;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class KGController {
  private final KGService service;

  public KGController(KGService service) {
    this.service = service;
  }

  @GetMapping("/kg/kginit_notabletoexcuted")
  public void initKG(){
    System.out.println("知识图谱数据初始化");
    service.initKG();
  }

  @JsonView(View.Summary.class)
  @GetMapping("/kg/{id}")
  public List<Article> findKGRelation(@PathVariable Long id){
    return service.findKGRelations(id);
  }
}
