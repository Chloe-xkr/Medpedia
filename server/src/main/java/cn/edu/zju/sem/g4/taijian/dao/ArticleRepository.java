package cn.edu.zju.sem.g4.taijian.dao;

import cn.edu.zju.sem.g4.taijian.entity.Article;
import java.util.List;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ArticleRepository extends JpaRepository<Article, Long> {

  List<Article> findByTitle(String title);

  List<Article> findByDepartmentId(Long id);

  List<Article> findByType(String type);

  List<Article> findByTextLike(Pageable pageable, String text);

}
