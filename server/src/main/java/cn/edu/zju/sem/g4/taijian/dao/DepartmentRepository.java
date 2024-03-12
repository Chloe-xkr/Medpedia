package cn.edu.zju.sem.g4.taijian.dao;

import cn.edu.zju.sem.g4.taijian.entity.Department;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DepartmentRepository extends JpaRepository<Department, Long> {

  List<Department> findDepartmentsByLevel(Integer level);

  List<Department> findDepartmentsByBelong(String belong_to);
}
