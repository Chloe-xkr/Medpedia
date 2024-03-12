package cn.edu.zju.sem.g4.taijian.controller;

import cn.edu.zju.sem.g4.taijian.entity.SearchHistory;
import cn.edu.zju.sem.g4.taijian.entity.View;
import cn.edu.zju.sem.g4.taijian.service.KGService;
import cn.edu.zju.sem.g4.taijian.service.SearchHistoryService;
import com.fasterxml.jackson.annotation.JsonView;
import java.util.List;
import javax.validation.Valid;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SearchHistoryController {
  private final SearchHistoryService service;

  public SearchHistoryController(SearchHistoryService service) {
    this.service = service;
  }

  @JsonView(View.Summary.class)
  @GetMapping("/getSearchHistory/{userid}")
  public List<SearchHistory> getSearchHistory(@PathVariable Long userid){
    return service.getSearchHistory(userid);
  }

  @PostMapping("/addSearchHistory")
  public void addSearchHistory(@Valid @RequestBody SearchHistory searchHistory){
    service.addSearchHistory(searchHistory.getUserId(), searchHistory.getTitle());
  }
}
