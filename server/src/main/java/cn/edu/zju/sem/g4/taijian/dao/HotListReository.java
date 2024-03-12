package cn.edu.zju.sem.g4.taijian.dao;

import cn.edu.zju.sem.g4.taijian.entity.HotList;
import javax.transaction.Transactional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

public interface HotListReository extends JpaRepository<HotList, Long> {

  HotList findByTitle(String title);

  @Modifying
  @Query("update HotList  set searchCounter = searchCounter+1 where title=?1")
  @Transactional
  void increaseCounter(String title);
}
