package cn.edu.zju.sem.g4.taijian.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Department {
  @Id
  @GeneratedValue
  private Long id;

  @Column
  @NotBlank(message = "Name is mandatory.")
  private String name;

  @Column
  @NotBlank(message = "Level is mandatory.")
  private Integer level;

  @Column
  private String belong;
}
