package cn.edu.zju.sem.g4.taijian.service;

import cn.edu.zju.sem.g4.taijian.dao.DepartmentRepository;
import cn.edu.zju.sem.g4.taijian.entity.Department;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CategoryService {
  @Autowired
  DepartmentRepository departmentRepository;

  /**
   * save one department information
   * @param department
   */
  public void saveDepartment(Department department){
    departmentRepository.save(department);
  }

  /**
   * save a set of department information
   * @param departments
   */
  public void saveDepartments(List<Department> departments){
    departmentRepository.saveAll(departments);
  }

  /**
   * get all first level departments
   * @return
   */
  public List<Department> getAllFirstLevelDepartments(){
    return departmentRepository.findDepartmentsByLevel(1);
  }

  /**
   * get second level departments by first level department
   * @param departmentName
   * @return
   */
  public List<Department> getSecondLevelDepartments(String departmentName){
    return departmentRepository.findDepartmentsByBelong(departmentName);
  }

  /**
   * get department by id
   * @param id
   * @return
   */
  public Department getDepartmentById(Long id){
    return departmentRepository.findById(id).get();
  }
}
