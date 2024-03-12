package cn.edu.zju.sem.g4.taijian.dao;

import java.util.List;

import cn.edu.zju.sem.g4.taijian.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    AppUser findAppUserByUserName(String userName);
}
