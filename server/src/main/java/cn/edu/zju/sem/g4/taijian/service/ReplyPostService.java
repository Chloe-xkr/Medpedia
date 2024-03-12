package cn.edu.zju.sem.g4.taijian.service;

import cn.edu.zju.sem.g4.taijian.dao.ReplyPostRepository;
import cn.edu.zju.sem.g4.taijian.entity.AppUser;
import cn.edu.zju.sem.g4.taijian.entity.Post;
import cn.edu.zju.sem.g4.taijian.entity.Reply_Post;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

@Service
public class ReplyPostService {
  private final ReplyPostRepository replyPostRepository;
  private final  AppUserService appUserService;

  public ReplyPostService(ReplyPostRepository replyPostRepository,
      AppUserService appUserService) {
    this.replyPostRepository = replyPostRepository;
    this.appUserService = appUserService;
  }

  public List getAllByPostId(Long postId) {
    List<Reply_Post> reply_posts=replyPostRepository.findReplyPostByPost(postId);
    List result=new ArrayList<>();
    for(int i=0;i<reply_posts.size();i++)
    {
      Reply_Post reply_post=reply_posts.get(i);
      Map map=new LinkedHashMap();
      Map user=new LinkedHashMap();
      AppUser appUser=appUserService.getById(reply_post.getUser_id());
      user.put("id",appUser.getId());
      user.put("name",appUser.getUserName());
      map.put("id",reply_post.getReply_post_id());
      map.put("text",reply_post.getPost_text());
      map.put("creator",user);
      map.put("created",reply_post.getReply_time());
      result.add(map);
    }
    return  result;
  }

  public Reply_Post create(Reply_Post reply_post) {
    return replyPostRepository.save(reply_post);
  }
}
