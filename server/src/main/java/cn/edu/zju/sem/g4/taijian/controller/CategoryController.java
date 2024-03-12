package cn.edu.zju.sem.g4.taijian.controller;

import cn.edu.zju.sem.g4.taijian.entity.Article;
import cn.edu.zju.sem.g4.taijian.entity.Department;
import cn.edu.zju.sem.g4.taijian.responseData.DepartmentList;
import cn.edu.zju.sem.g4.taijian.service.ArticleService;
import cn.edu.zju.sem.g4.taijian.service.CategoryService;
import java.util.ArrayList;
import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CategoryController {

  private final CategoryService categoryService;
  private final ArticleService articleService;

  public CategoryController(CategoryService categoryService, ArticleService articleService) {
    this.categoryService = categoryService;
    this.articleService = articleService;
  }

  @GetMapping("/categories")
  public List<DepartmentList> getAllFisrtLevelCategories(){
    List<Department> firstLevelDepartments = categoryService.getAllFirstLevelDepartments();
    List<DepartmentList> ret = new ArrayList<>();
    for(Department fld: firstLevelDepartments){
      DepartmentList departmentList = new DepartmentList(fld.getId(), fld.getName());
      departmentList.addChildDepartments(categoryService.getSecondLevelDepartments(fld.getName()));
      ret.add(departmentList);
    }
    return ret;
  }

  @GetMapping("/entriesofcat/{id}")
  public List<Article> getArticlesByDepartmentId(@PathVariable Long id){
    return articleService.getByDepartmentId(id);
  }
}
