package cn.edu.zju.sem.g4.taijian.controller;
//import cn.edu.zju.sem.g4.taijian.security.JwtUtil;
//import cn.edu.zju.sem.g4.taijian.TokenRequired;
//import cn.edu.zju.sem.g4.taijian.security.JwtUtil;

import cn.edu.zju.sem.g4.taijian.entity.AppUser;
import cn.edu.zju.sem.g4.taijian.service.AppUserService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@RequestMapping("/users")
public class AppUserController {

  private AppUserService service;
  private BCryptPasswordEncoder bCryptPasswordEncoder;


  public AppUserController(AppUserService service, BCryptPasswordEncoder bCryptPasswordEncoder) {
    this.service = service;
    this.bCryptPasswordEncoder = bCryptPasswordEncoder;
  }

  @PostMapping("/users/register")
  AppUser register(@Valid @RequestBody AppUser appUser) {
    System.out.println("register");
    appUser.setPassword(bCryptPasswordEncoder.encode(appUser.getPassword()));
    System.out.println(appUser.getPassword());
    AppUser newAppUser = new AppUser(appUser.getUserName(), appUser.getEmail(), appUser.getIsAdmin(), appUser.getPassword());
//        URI uri = URI.create(ServletUriComponentsBuilder.fromCurrentContextPath().path("/api/auth/register").toUriString());
    service.create(newAppUser);
    return (appUser);
  }

  @GetMapping("/users/getUser")
  AppUser GetOneByName(String userName) {
    return service.getByAppUserName(userName);
  }

  @PostMapping("/users/{id}/forget/")
  AppUser FindPassword(@PathVariable Long id, @Valid @RequestBody AppUser newAppUser) {
    return service.updateById(id, newAppUser);
  }

  @GetMapping("/users")
  List<AppUser> all() {
    return service.getAll();
  }
//    public ResponseEntity<List<AppUser>>getAppUsers(){
//        return ResponseEntity.ok().body(service.getAll());
//    }

  @GetMapping("/users/{id}")
  AppUser getOne(@PathVariable Long id) {
    return service.getById(id);
  }

  @PutMapping("/users/{id}")
  AppUser updateOne(@PathVariable Long id, @Valid @RequestBody AppUser newAppUser) {
    return service.updateById(id, newAppUser);
  }

  @DeleteMapping("/users/{id}")
  void delete(@PathVariable Long id) {
    service.deleteById(id);
  }
}
