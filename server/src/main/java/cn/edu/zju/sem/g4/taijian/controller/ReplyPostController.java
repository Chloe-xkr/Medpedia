package cn.edu.zju.sem.g4.taijian.controller;

import cn.edu.zju.sem.g4.taijian.entity.Reply_Post;
import cn.edu.zju.sem.g4.taijian.service.PostService;
import cn.edu.zju.sem.g4.taijian.service.ReplyPostService;
import java.util.List;
import java.util.Map;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ReplyPostController {

  private final ReplyPostService replyPostService;
  private final PostService postService;

  public ReplyPostController(ReplyPostService replyPostService, PostService postService) {
    this.replyPostService = replyPostService;
    this.postService = postService;
  }

  @GetMapping("/posts/reply/{postId}")
  List all(@PathVariable Long postId) {
    return replyPostService.getAllByPostId(postId);
  }

  @PostMapping("/posts/reply")
  Map newOne(@Valid @RequestBody Reply_Post reply_post) {

    Long main_post_id = replyPostService.create(reply_post).getMain_post_id();
    return postService.getPostById(main_post_id);
  }
}
