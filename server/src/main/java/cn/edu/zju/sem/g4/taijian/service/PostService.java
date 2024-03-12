package cn.edu.zju.sem.g4.taijian.service;

import cn.edu.zju.sem.g4.taijian.dao.PostRepository;
import cn.edu.zju.sem.g4.taijian.entity.AppUser;
import cn.edu.zju.sem.g4.taijian.entity.Post;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import org.springframework.stereotype.Service;

@Service
public class PostService {

  private final PostRepository postRepository;
  private final AppUserService appUserService;
  private final ReplyPostService replyPostService;

  public PostService(PostRepository postRepository, AppUserService appUserService,
      ReplyPostService replyPostService) {
    this.postRepository = postRepository;
    this.appUserService = appUserService;
    this.replyPostService = replyPostService;
  }

  public List getAllByArticle(Long article_id) {
    List<Post> posts1 = postRepository.findPostByArticleId(article_id);
    List result = new ArrayList<>();
    for (int i = 0; i < posts1.size(); i++) {
      Post post = posts1.get(i);
      Map map = new LinkedHashMap();
      Map user = new LinkedHashMap();
      List reply_post = replyPostService.getAllByPostId(post.getPost_id());
      AppUser appUser = appUserService.getById(post.getUser_id());
      user.put("id", appUser.getId());
      user.put("name", appUser.getUserName());
      map.put("id", post.getPost_id());
      map.put("title", post.getTitle());
      map.put("text", post.getPost_text());
      map.put("creator", user);
      map.put("created", post.getPublish_time());
      map.put("replies", reply_post);
      result.add(map);
    }
    return result;
  }

  public Map getPostById(Long post_id) {
    Optional<Post> posts = postRepository.findById(post_id);
    if (!posts.isPresent()) {
      throw new IllegalStateException("error post id");
    }
    Post post = posts.get();
    Map map1 = new LinkedHashMap();
    Map user1 = new LinkedHashMap();
    List reply_post = replyPostService.getAllByPostId(post_id);
    AppUser appUser = appUserService.getById(post.getUser_id());
    user1.put("id", appUser.getId());
    user1.put("name", appUser.getUserName());
    map1.put("id", post.getPost_id());
    map1.put("title", post.getTitle());
    map1.put("text", post.getPost_text());
    map1.put("creator", user1);
    map1.put("created", post.getPublish_time());
    map1.put("replies", reply_post);
    return map1;
  }

  public Post create(Post newPost) {
    return postRepository.save(newPost);
  }
}
