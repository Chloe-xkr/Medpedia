package cn.edu.zju.sem.g4.taijian.entity;

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
public class HotList {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private String title;

  @Column
  private Long searchCounter;

  public HotList(){}

  public HotList(String title){
    this.title = title;
    this.searchCounter = 1L;
  }
}
