package cn.edu.zju.sem.g4.taijian.dao;

import cn.edu.zju.sem.g4.taijian.entity.Post;
import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface PostRepository extends JpaRepository<Post, Long> {
  @Query("SELECT s FROM Post s WHERE s.article_id=?1")
  List<Post> findPostByArticleId(Long article_id);
}
