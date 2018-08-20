package ru.spb.epam.grebovod.third.model;

import java.util.UUID;

public class CategoryModel {

  private Integer categoryID;
  private String categoryName;
  private Integer categoryCreator;

  public Integer getCategoryCreator() {
    return categoryCreator;
  }

  public void setCategoryCreator(Integer categoryCreator) {
    this.categoryCreator = categoryCreator;
  }

  public Integer getCategoryID() {
    return categoryID;
  }

  public void setCategoryID(Integer categoryID) {
    this.categoryID = categoryID;
  }

  public String getCategoryName() {
    return categoryName;
  }

  public void setCategoryName(String categoryName) {
    this.categoryName = categoryName;
  }

  @Override
  public String toString() {
    return String.format("%1$s '%2$s'", categoryID, categoryName);
  }
}
