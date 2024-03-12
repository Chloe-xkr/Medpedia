package cn.edu.zju.sem.g4.taijian.dao;

import cn.edu.zju.sem.g4.taijian.entity.Reply_Post;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface ReplyPostRepository extends JpaRepository<Reply_Post, Long> {

  @Query("SELECT s FROM Reply_Post s WHERE s.main_post_id=?1")
  List findReplyPostByPost(Long postId);
}
