package ru.spb.epam.grebovod.third.test;

import static org.junit.Assert.*;

import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import ru.spb.epam.grebovod.third.model.CategoryModel;
import ru.spb.epam.grebovod.third.model.UserModel;
import ru.spb.epam.grebovod.third.repository.tsql.CategoryRepository;
import ru.spb.epam.grebovod.third.repository.tsql.UserRepository;

public class CategoryRepositoryTest {
  @Test
  public void createCategory(){
    // Arrange
    UserRepository repository = new UserRepository();
    UserModel user = repository.findByName("Alleshka");

    CategoryModel categoryModel = new CategoryModel();
    categoryModel.setCategoryName("Category " + UUID.randomUUID());
    categoryModel.setCategoryCreator(user.getUserID());

    // Act
    CategoryRepository categoryRepository = new CategoryRepository();
    int countCategories = categoryRepository.getCategoriesByUserID(user.getUserID()).size();

    categoryModel = categoryRepository.create(categoryModel);
    int newCountCategories = categoryRepository.getCategoriesByUserID(user.getUserID()).size();

    // Asserts
    Assert.assertEquals(countCategories+1, newCountCategories);
  }

  @Test
  public void deleteCategory(){
    // Arrange
    UserRepository repository = new UserRepository();
    UserModel user = repository.findByName("Alleshka");

    CategoryModel categoryModel = new CategoryModel();
    categoryModel.setCategoryName("Category " + UUID.randomUUID());
    categoryModel.setCategoryCreator(user.getUserID());

    // Act
    CategoryRepository categoryRepository = new CategoryRepository();
    int countCategories = categoryRepository.getCategoriesByUserID(user.getUserID()).size();

    categoryModel = categoryRepository.create(categoryModel);
    int newCountCategories = categoryRepository.getCategoriesByUserID(user.getUserID()).size();

    categoryRepository.delete(categoryModel);
    int countCategoriesAfterDelete = categoryRepository.getCategoriesByUserID(user.getUserID()).size();

    // Asserts
    Assert.assertEquals(countCategories+1, newCountCategories);
    Assert.assertEquals(countCategories, countCategoriesAfterDelete);
  }

  @Test
  public void editCategoryName(){
    // Arrange
    UserRepository repository = new UserRepository();
    UserModel user = repository.findByName("Alleshka");
    String oldName = "oldCategory";
    String newName = "newCategory";

    // Act
    CategoryModel categoryModel = new CategoryModel();
    categoryModel.setCategoryName(oldName);
    categoryModel.setCategoryCreator(user.getUserID());

    CategoryRepository categoryRepository = new CategoryRepository();
    categoryModel = categoryRepository.create(categoryModel);
    CategoryModel newCategoryModel = new CategoryModel();

    newCategoryModel.setCategoryCreator(categoryModel.getCategoryCreator());
    newCategoryModel.setCategoryID(categoryModel.getCategoryID());
    newCategoryModel.setCategoryName(newName);
    categoryModel = categoryRepository.edit(newCategoryModel);

    // Asserts
    Assert.assertEquals(categoryModel.getCategoryName(), newName);
  }
}