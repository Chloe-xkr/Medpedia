package cn.edu.zju.sem.g4.taijian.dao;

import cn.edu.zju.sem.g4.taijian.entity.KGRelation;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface KGRelationRepository extends JpaRepository<KGRelation, Long> {

  List<KGRelation> findByArticleId1(Long id);
}
