package cn.edu.zju.sem.g4.taijian;

import cn.edu.zju.sem.g4.taijian.entity.AppUser;
import cn.edu.zju.sem.g4.taijian.service.AppUserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class TaijianApplication {

	public static void main(String[] args) {
		SpringApplication.run(TaijianApplication.class, args);
	}
	@Bean
	public BCryptPasswordEncoder bCryptPasswordEncoder(){
		return new BCryptPasswordEncoder();
	}
}
