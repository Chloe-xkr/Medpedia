package cn.edu.zju.sem.g4.taijian.service;

import cn.edu.zju.sem.g4.taijian.dao.ArticleRepository;
import cn.edu.zju.sem.g4.taijian.dao.HotListReository;
import cn.edu.zju.sem.g4.taijian.entity.Article;
import cn.edu.zju.sem.g4.taijian.entity.HotList;
import java.util.List;

import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

@Service
public class ArticleService {

  @Autowired
  private HotListReository hotListReository;

  private final ArticleRepository repository;

  public ArticleService(ArticleRepository repository) {
    this.repository = repository;
  }

  /**
   * Gets all articles.
   *
   * @return list of all articles
   */
  public List<Article> getAll() {
    return repository.findAll();
  }

  /**
   * Gets an article by id.
   *
   * @param id id of an article
   * @return the article
   */
  public Article getById(Long id) {
    return repository.findById(id)
        .orElseThrow(() -> new ArticleNotFoundException(id));
  }

  public List<Article> getByDepartmentId(Long id) {
    return repository.findByDepartmentId(id);
  }


  public Article create(Article newArticle) {
    return repository.save(newArticle);
  }

  /**
   * Updates an article.
   *
   * @param id         id of article to be updated
   * @param newArticle the article that contains the new content
   * @return the updated article
   */
  public Article updateById(Long id, Article newArticle) {
    System.out.println(newArticle.getTitle());
    return repository.findById(id)
        .map(article -> {
          article.setType(newArticle.getType());
          article.setText(newArticle.getText());
          article.setTitle(newArticle.getTitle());
          return repository.save(article);
        }).orElseThrow(() -> new ArticleNotFoundException(id));
  }

  /**
   * Deletes an article.
   *
   * @param id id of article to be deleted
   */
  public void deleteById(Long id) {
    repository.deleteById(id);
  }

  public List<Article> findByTextLike(String text) {
    PageRequest pr = PageRequest.of(0,20);
    return repository.findByTextLike(pr, text);
  }


  public void addHotList(String title){
    //判断是否存在完整title
    List<Article> articles = repository.findByTitle(title);
    if(articles.size() != 0){
      System.out.println("进来了");
      HotList hotList = hotListReository.findByTitle(title);
      if(hotList != null){
        hotListReository.increaseCounter(title);
      }
      else{
        HotList newHotList = new HotList(title);
        hotListReository.save(newHotList);
      }
    }
  }

  public List<HotList> getHotList() {
    return hotListReository.findAll(Sort.by(Sort.Direction.DESC,"searchCounter"));
  }
}