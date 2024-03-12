package cn.edu.zju.sem.g4.taijian;

import lombok.RequiredArgsConstructor;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class TaijianApplicationRunner implements ApplicationRunner {

  @Override
  public void run(ApplicationArguments args){
    try {
      System.out.println("服务端已成功启动！");
    }catch (Exception e){
      e.printStackTrace();
    }
  }
}
