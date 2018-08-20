package ru.spb.epam.grebovod.third.model;

import ru.spb.epam.grebovod.third.repository.tsql.CategoryRepository;
import ru.spb.epam.grebovod.third.repository.tsql.ICommand;

public class UserModel {

  private Integer userID;
  private String userLogin;
  private String userPassword;
  private Integer defaultCategoryID;

  public Integer getDefaultCategoryID() {

    if(defaultCategoryID==null){
      CategoryRepository categoryRepository = new CategoryRepository();
      setDefaultCategoryID(categoryRepository.getDefaultCategory(getUserID()).getCategoryID());
    }

    return defaultCategoryID;
  }

  public void setDefaultCategoryID(Integer defaultCategoryID) {
    this.defaultCategoryID = defaultCategoryID;
  }

  public Integer getUserID() {
    return userID;
  }

  public void setUserID(Integer userID) {
    this.userID = userID;
  }

  public String getUserLogin() {
    return userLogin;
  }

  public void setUserLogin(String userLogin) {
    this.userLogin = userLogin;
  }

  public String getUserPassword() {
    return userPassword;
  }

  public void setUserPassword(String userPassword) {
    this.userPassword = userPassword;
  }

  @Override
  public String toString() {
    return String.format("'%1$s', '%2$s'", userLogin, userPassword);
  }
}
