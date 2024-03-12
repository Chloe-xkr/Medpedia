package cn.edu.zju.sem.g4.taijian.entity;


import java.sql.Timestamp;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity
@Getter
@Setter
public class Reply_Post {
  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  private Long reply_post_id;

  @NotNull(message = "article_id is mandatory.")
  private Long main_post_id;

  @NotNull(message = "article_id is mandatory.")
  private Long user_id;
  private String post_text;
  private Timestamp reply_time;

  public Reply_Post() {
  }

  public Reply_Post(Long reply_post_id, Long main_post_id, Long user_id, String post_text,
      Timestamp reply_time) {
    this.reply_post_id = reply_post_id;
    this.main_post_id = main_post_id;
    this.user_id = user_id;
    this.post_text = post_text;
    this.reply_time = reply_time;
  }

  public Reply_Post(Long main_post_id, Long user_id, String post_text, Timestamp reply_time) {
    this.main_post_id = main_post_id;
    this.user_id = user_id;
    this.post_text = post_text;
    this.reply_time = reply_time;
  }

  public Long getReply_post_id() {
    return reply_post_id;
  }

  public void setReply_post_id(Long reply_post_id) {
    this.reply_post_id = reply_post_id;
  }

  public Long getMain_post_id() {
    return main_post_id;
  }

  public void setMain_post_id(Long main_post_id) {
    this.main_post_id = main_post_id;
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

  public Timestamp getReply_time() {
    return reply_time;
  }

  public void setReply_time(Timestamp reply_time) {
    this.reply_time = reply_time;
  }

  @Override
  public String toString() {
    return "reply_post{" +
        "reply_post_id=" + reply_post_id +
        ", main_post_id=" + main_post_id +
        ", user_id=" + user_id +
        ", post_text='" + post_text + '\'' +
        ", reply_time=" + reply_time +
        '}';
  }
}
