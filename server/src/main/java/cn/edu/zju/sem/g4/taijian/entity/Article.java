package cn.edu.zju.sem.g4.taijian.entity;

import com.fasterxml.jackson.annotation.JsonView;
import java.util.Objects;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Article {

  @Id
//  @GeneratedValue
  @JsonView(View.Summary.class)
  private Long id;

  @JsonView(View.Summary.class)
  @NotBlank(message = "Title is mandatory.")
  private String title;

  @JsonView(View.Summary.class)
  @NotBlank(message = "Type is mandatory.")
  @Pattern(regexp = "(drug|disease)", message = "Type is either drug or disease.")
  private String type; // drug or disease

  @JsonView(View.Summary.class)
  private Long departmentId;

  @Lob  // Large object
  @NotBlank(message = "Text is mandatory.")
  private String text;

  public Article() {
  }

  public Article(String title, String type, String text) {
    this.title = title;
    this.type = type;
    this.text = text;
  }

  public Article(String title, String type, String text, Long departmentId) {
    this.title = title;
    this.type = type;
    this.text = text;
    this.departmentId = departmentId;
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (!(o instanceof Article another)) {
      return false;
    }
    return Objects.equals(this.id, another.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(this.id);
  }

  @Override
  public String toString() {
    return "Article{" + "id=" + this.id + ", title='" + this.title + '\'' + ", type='" + this.type + '\'' + '}';
  }
}
