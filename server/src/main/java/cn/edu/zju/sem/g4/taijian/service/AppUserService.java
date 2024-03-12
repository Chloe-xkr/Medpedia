package cn.edu.zju.sem.g4.taijian.service;
import cn.edu.zju.sem.g4.taijian.dao.AppUserRepository;
import cn.edu.zju.sem.g4.taijian.entity.AppUser;

import java.util.List;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
@Slf4j
public class AppUserService {
    private final AppUserRepository repository;
    private final PasswordEncoder passwordEncoder;

    /**
     * Get an account by id
     * @param id id of a user
     * @return the user
     */
    public AppUser getById(Long id){
        log.info("Saving new user to the database");
        return repository.findById(id)
                .orElseThrow(() -> new UserNotFoundException(id));
    }

    /**
     * Get an account by userName
     * @param userName
     * @return
     */
    public AppUser getByAppUserName(String userName){
        return repository.findAppUserByUserName(userName);
    }
    /**
     * Get all users
     * @return list of all articles
     */
    public List<AppUser> getAll() {
        return repository.findAll();
    }

    public AppUser create(AppUser newAppUser){
        log.info("Saving new user to the database");
        return repository.save(newAppUser);
    }

    /**
     * Update a user
     * @param id
     * @param newAppUser
     * @return the updated user
     */
    public AppUser updateById (Long id, AppUser newAppUser){
        return repository.findById(id)
                .map(user -> {
                    user.setUserName(newAppUser.getUserName());
                    user.setEmail(newAppUser.getEmail());
                    user.setIsAdmin(newAppUser.getIsAdmin());
                    user.setPassword(newAppUser.getPassword());
                    return repository.save(user);
                }).orElseThrow(() -> new ArticleNotFoundException(id));
    }

    /**
     * Delete a user
     * @param id
     */
    public void deleteById(Long id){
        repository.deleteById(id);
    }

}
