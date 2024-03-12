package cn.edu.zju.sem.g4.taijian.entity;

import com.fasterxml.jackson.annotation.JsonView;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class SearchHistory {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @JsonView(View.Summary.class)
  private Long id;

  @Column
  private Long userId;

  @Column
  @JsonView(View.Summary.class)
  private String title;

  public SearchHistory(){}

  public SearchHistory(Long userId, String title) {
    this.userId = userId;
    this.title = title;
  }
}
