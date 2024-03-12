package cn.edu.zju.sem.g4.taijian.controller;

import cn.edu.zju.sem.g4.taijian.entity.Article;
import cn.edu.zju.sem.g4.taijian.entity.HotList;
import cn.edu.zju.sem.g4.taijian.entity.View;
import cn.edu.zju.sem.g4.taijian.service.ArticleService;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.validation.Valid;

import org.springframework.data.domain.PageRequest;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ArticleController {

  private final ArticleService service;

  public ArticleController(ArticleService service) {
    this.service = service;
  }

  // Aggregate root
  @JsonView(View.Summary.class)  // only render the attributes with the same annotation
  @GetMapping("/entries")
  List<Article> all() {
    return service.getAll();
  }

  @PostMapping("/entries")
  Article newOne(@Valid @RequestBody Article newArticle) {
    return service.create(newArticle);
  }

  // Single item
  @GetMapping("/entries/{id}")
  Article one(@PathVariable Long id) {
    return service.getById(id);
  }

  @PutMapping("/entries/{id}")
  public Article updateArticle(@PathVariable Long id, @Valid @RequestBody Article newArticle) {
    return service.updateById(id, newArticle);
  }

  @GetMapping("/entries/findEntriesLike")
  public List<Article> findByNameLike(String text){
    System.out.println(text);
    //    for(Article article:articles){
//      article.setText(article.getText().replace(text,"<font color='red‘><b>"+text+"</b></font>"));
//      article.setTitle(article.getTitle().replace(text,"<font color='red‘><b>"+text+"</b></font>"));
//    }
    service.addHotList(text);
    return service.findByTextLike("%"+text+"%");
  }
//  Article update(@PathVariable Long id, @Valid @RequestBody Article newArticle) {
//    return service.updateById(id, newArticle);
//  }

//  @PutMapping("/entries/{id}")
//  Article updatetitle(@PathVariable Long id, @Valid @RequestBody Article newArticle) {
//    return service.updateById(id, newArticle);
//  }

  @DeleteMapping("/entries/{id}")
  void delete(@PathVariable Long id) {
    service.deleteById(id);
  }

  @GetMapping("/getHotList")
  List<HotList> getHotList(){
    return service.getHotList();
  }
}
