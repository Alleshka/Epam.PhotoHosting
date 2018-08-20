package ru.spb.epam.grebovod.third.repository.tsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;

import ru.spb.epam.grebovod.third.model.CategoryModel;

public class CategoryRepository extends AbstractTSqlRepository<CategoryModel> {

  @Override
  protected List<CategoryModel> parseResponse(ResultSet item) {
    try {
      List<CategoryModel> list = new LinkedList<>();
      while (item.next()) {
        CategoryModel category = new CategoryModel();
        category.setCategoryID(item.getInt("id"));
        category.setCategoryName(item.getString("categoryName"));
        category.setCategoryCreator(item.getInt("creator"));
        list.add(category);
      }
      return list;
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }

  @Override
  public CategoryModel create(CategoryModel item) {

    String request = String.format("exec CreateCategory '%1$s', %2$s", item.getCategoryName(),
        item.getCategoryCreator());

    ExecuteUpdateCommand((x) -> {
      x.executeUpdate(request);
    });

    List<CategoryModel> allCategories = getCategoriesByUserID(item.getCategoryCreator());
    return allCategories.get(allCategories.size() - 1);
  }

  public CategoryModel create(String categoryName, Integer categoryCreator) {
    CategoryModel model = new CategoryModel();
    model.setCategoryName(categoryName);
    model.setCategoryCreator(categoryCreator);
    return create(model);
  }

  @Override
  public void delete(CategoryModel item) {
    String request = String.format("exec DeleteCategory %1$s", item.getCategoryID());
    ExecuteUpdateCommand((x) -> x.executeUpdate(request));
  }

  public void delete(Integer id) {
    CategoryModel model = new CategoryModel();
    model.setCategoryID(id);
    delete(model);
  }

  @Override
  public CategoryModel edit(CategoryModel item) {
    String request = String
        .format("exec EditCategory %1$s, '%2$s'", item.getCategoryID(), item.getCategoryName());
    ExecuteUpdateCommand((x) -> x.executeUpdate(request));
    return findByID(item.getCategoryID());
  }

  @Override
  public CategoryModel findByID(int id) {
    String request = String.format("exec FindCategoryByID %1$s", id);
    return ExecuteGetCommand((x) -> parseResponse(x.executeQuery((request)))).get(0);
  }

  @Override
  public CategoryModel findByName(String name) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<CategoryModel> getItemList() {
    return ExecuteGetCommand((x) -> parseResponse(x.executeQuery("select * FROM TCategory")));
  }

  public List<CategoryModel> getCategoriesByUserID(int userID) {
    String request = "select * from TCategory where creator = ?";
    List<CategoryModel> categoiries =  Execute(request, (x) -> {
      x.setInt(1, userID);
      return parseResponse(x.executeQuery());
    });
    return categoiries;
  }

  public CategoryModel getDefaultCategory(int userID) {
    String request = "select top 1* from TCategory where creator = ?";
    return Execute(request, (x) -> {
      x.setInt(1, userID);
      return parseResponse(x.executeQuery());
    }).get(0);
  }
}
