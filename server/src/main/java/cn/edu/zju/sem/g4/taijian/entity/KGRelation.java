package cn.edu.zju.sem.g4.taijian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class KGRelation {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long id;

  @Column
  private Long articleId1;

  @Column
  private Long articleId2;

  public KGRelation(){

  }

  public KGRelation(Long a1, Long a2){
    this.articleId1 = a1;
    this.articleId2 = a2;
  }
}
