package cn.edu.zju.sem.g4.taijian.entity;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.Objects;

@Entity
@Data
@NoArgsConstructor
@Getter
@Setter
public class AppUser {
    @Id
    @GeneratedValue
    @JsonView(View.Summary.class)
    private Long id;

    @JsonView(View.Summary.class)
    @NotBlank(message = "Name is mandatory.")
    private String userName;

    @JsonView(View.Summary.class)
    @NotBlank(message = "Email is mandatory")
    private String email;

//   小坑 不能用NotBlank 要用NotNull
    @JsonView(View.Summary.class)
    @NotNull(message = "Type is mandatory")
    private int isAdmin;

    @JsonView(View.Summary.class)
    @NotBlank(message = "Password is mandatory")
    private String password;

    public AppUser(String userName, String email, int isAdmin, String password) {
        this.userName = userName;
        this.email = email;
        this.isAdmin = isAdmin;
        this.password = password;
    }


    public Long getId(){return id;}
    public String getUserName(){return userName;}
    public String getEmail(){return email;}
    public String getPassword(){return password;}
    public void setPassword(String password){this.password = password;}
    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (!(o instanceof AppUser another)) {
            return false;
        }
        return Objects.equals(this.id, another.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.id + this.userName + this.email + this.isAdmin + this.password);
    }

    @Override
    public String toString() {
        return "User{" + "id=" + this.id + ", userName='" + this.userName + '\'' + ", isAdmin=" + this.isAdmin + ", email='" + this.email + '\'' + '}';
    }
}