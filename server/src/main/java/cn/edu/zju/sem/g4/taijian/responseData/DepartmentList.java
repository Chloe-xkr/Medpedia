package cn.edu.zju.sem.g4.taijian.responseData;

import cn.edu.zju.sem.g4.taijian.entity.Department;
import java.util.ArrayList;
import java.util.List;
import lombok.Data;

@Data
public class DepartmentList{
  private Long id;
  private String name;
  private List<Department> childDeparment;

  public DepartmentList(Long id, String name){
    this.id = id;
    this.name = name;
    this.childDeparment = new ArrayList<>();
  }

  public void addChildDepartment(Department department){
    this.childDeparment.add(department);
  }

  public void addChildDepartments(List<Department> departments){
    this.childDeparment.addAll(departments);
  }
}
