package cn.edu.zju.sem.g4.taijian.entity;

import java.sql.Timestamp;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long post_id;


  @NotNull(message = "article_id is mandatory.")
  private Long article_id;


  @NotNull(message = "user_id is mandatory.")
  private Long user_id;
  private String post_text;
  private Timestamp publish_time;
  private String title;

  public Post() {
  }

  public Post(Long post_id, Long article_id, Long user_id, String post_text, Timestamp publish_time) {
    this.post_id = post_id;
    this.article_id = article_id;
    this.user_id = user_id;
    this.post_text = post_text;
    this.publish_time = publish_time;
  }

  public Post(Long article_id, Long user_id, String post_text, Timestamp publish_time) {
    this.article_id = article_id;
    this.user_id = user_id;
    this.post_text = post_text;
    this.publish_time = publish_time;
  }

  public Long getPost_id() {
    return post_id;
  }

  public void setPost_id(Long post_id) {
    this.post_id = post_id;
  }

  public Long getArticle_id() {
    return article_id;
  }

  public void setArticle_id(Long article_id) {
    this.article_id = article_id;
  }

  public Long getUser_id() {
    return user_id;
  }

  public void setUser_id(Long user_id) {
    this.user_id = user_id;
  }

  public String getPost_text() {
    return post_text;
  }

  public void setPost_text(String post_text) {
    this.post_text = post_text;
  }

  public Timestamp getPublish_time() {
    return publish_time;
  }

  public void setPublish_time(Timestamp publish_time) {
    this.publish_time = publish_time;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  @Override
  public String toString() {
    return "post{" +
        "post_id=" + post_id +
        ", article_id=" + article_id +
        ", user_id=" + user_id +
        ", post_text='" + post_text + '\'' +
        ", publish_time=" + publish_time +
        '}';
  }
}
