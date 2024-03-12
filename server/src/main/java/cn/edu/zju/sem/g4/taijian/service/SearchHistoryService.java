package cn.edu.zju.sem.g4.taijian.service;

import cn.edu.zju.sem.g4.taijian.dao.SearchHistoryRepository;
import cn.edu.zju.sem.g4.taijian.entity.SearchHistory;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SearchHistoryService {
  @Autowired
  SearchHistoryRepository searchHistoryRepository;

  public List<SearchHistory> getSearchHistory(Long userId){
    return searchHistoryRepository.findByUserId(userId);
  }

  public void addSearchHistory(Long userId, String title){
    SearchHistory searchHistory = new SearchHistory(userId, title);
    searchHistoryRepository.save(searchHistory);
  }
}
