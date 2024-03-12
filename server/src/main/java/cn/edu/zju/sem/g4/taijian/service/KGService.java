package cn.edu.zju.sem.g4.taijian.service;

import cn.edu.zju.sem.g4.taijian.dao.ArticleRepository;
import cn.edu.zju.sem.g4.taijian.dao.KGRelationRepository;
import cn.edu.zju.sem.g4.taijian.entity.Article;
import cn.edu.zju.sem.g4.taijian.entity.KGRelation;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KGService {
  private final KGRelationRepository kgRelationRepository;
  private final ArticleRepository articleRepository;

  public void initKG(){
    System.out.println("知识图谱数据初始化");
    List<Article> articles = articleRepository.findAll();
    for(Article item: articles){
      int drugNum = 0, diseaseNum = 0;
      for(Article item2: articles){
        if(!Objects.equals(item.getId(), item2.getId())){
          if(item2.getText().contains(item.getTitle())){
            if(Objects.equals(item2.getType(), "drug") && drugNum <= 5){
              kgRelationRepository.save(new KGRelation(item.getId(), item2.getId()));
              drugNum++;
            }
            else if(Objects.equals(item2.getType(), "disease") && diseaseNum <= 5)
            {
              kgRelationRepository.save(new KGRelation(item.getId(), item2.getId()));
              diseaseNum++;
            }
            else if(drugNum > 5 && diseaseNum > 5)
              break;
          }
        }
      }
    }
  }

  public List<Article> findKGRelations(Long id){
    List<Article> ret = new ArrayList<>();
    List<KGRelation> relations = kgRelationRepository.findByArticleId1(id);
    for(KGRelation relation: relations){
      Optional<Article> tem = articleRepository.findById(relation.getArticleId2());
      tem.ifPresent(ret::add);
    }
    return ret;
  }
}
