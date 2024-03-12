package cn.edu.zju.sem.g4.taijian.dao;

import cn.edu.zju.sem.g4.taijian.entity.SearchHistory;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SearchHistoryRepository extends JpaRepository<SearchHistory, Long> {

  List<SearchHistory> findByUserId(Long userId);
}
