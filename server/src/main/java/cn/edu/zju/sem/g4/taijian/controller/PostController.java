package cn.edu.zju.sem.g4.taijian.controller;

import cn.edu.zju.sem.g4.taijian.entity.Post;
import cn.edu.zju.sem.g4.taijian.service.PostService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class PostController {

  private final PostService postService;

  public PostController(PostService postService) {
    this.postService = postService;
  }

  @GetMapping("/posts/{article_id}")
  List all(@PathVariable Long article_id) {
    return postService.getAllByArticle(article_id);
  }

  @GetMapping("/posts/one/{post_id}")
  Map one(@PathVariable Long post_id) {
    return postService.getPostById(post_id);
  }

  @PostMapping("/posts")
  List newOne(@Valid @RequestBody Post newPost) {
    Long article_id = postService.create(newPost).getArticle_id();
    return postService.getAllByArticle(article_id);
  }

//  @PostMapping("/entries")
//  Article newOne(@Valid @RequestBody Article newArticle) {
//    return service.create(newArticle);
//  }

}
