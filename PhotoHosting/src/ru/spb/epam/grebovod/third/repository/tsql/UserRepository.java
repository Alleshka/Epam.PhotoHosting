package ru.spb.epam.grebovod.third.repository.tsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ru.spb.epam.grebovod.third.MD5HashCalculation;
import ru.spb.epam.grebovod.third.model.UserModel;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class UserRepository extends AbstractTSqlRepository<UserModel> {

  @Override
  protected List<UserModel> parseResponse(ResultSet item) {
    try {
      List<UserModel> list = new LinkedList<>();
      while (item.next()) {
        UserModel user = new UserModel();
        user.setUserID(item.getInt("id"));
        user.setUserLogin(item.getString("userLogin"));
        user.setUserPassword(null);
        list.add(user);
      }
      return list;
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }

  @Override
  public UserModel create(UserModel item) {
    item.setUserPassword(MD5HashCalculation.getStringHash(item.getUserPassword()));
    rootLogger.info("Попытка создания пользователя");
    String request = "exec CreateUser ?, ?";// , item.getUserLogin(), item.getUserPassword());
    Execute(request, (x) -> {
      x.setString(1, item.getUserLogin());
      x.setString(2, item.getUserPassword());
      x.executeUpdate();
      return null;
    });

    UserModel model = findByName(item.getUserLogin());
    CategoryRepository repository = new CategoryRepository();
    repository.create("default " + item.getUserLogin(), model.getUserID());
    rootLogger.info(String.format("Пользователь %1$s был создан", model.getUserLogin()));
    return model;
  }

  public UserModel create(String login, String password) {
    UserModel model = new UserModel();
    model.setUserLogin(login);
    model.setUserPassword(password);
    return create(model);
  }

  public boolean LoginUser(UserModel item) {
    rootLogger.info("Попытка входа пользователя" + item.getUserLogin());
    item.setUserPassword(MD5HashCalculation.getStringHash(item.getUserPassword()));
    String request = "exec SighIn ?, ?";
    List<UserModel> userModelList = Execute(request, (x) -> {
      x.setString(1, item.getUserLogin());
      x.setString(2, item.getUserPassword());
      return parseResponse(x.executeQuery());
    });

    if (userModelList == null) {
      return false;
    } else {
      return true;
    }
  }

  public boolean LoginUser(String login, String password) {
    UserModel model = new UserModel();
    model.setUserLogin(login);
    model.setUserPassword(password);
    return LoginUser(model);
  }

  @Override
  public void delete(UserModel item) {
    ExecuteUpdateCommand(
        (x) -> x.executeUpdate(String.format("exec DeleteUser %1$s", item.getUserID())));
  }

  public void restore(UserModel item) {
    ExecuteUpdateCommand(
        (x) -> x.executeUpdate(String.format("exec RestoreUser %1$s", item.getUserID())));
  }

  @Override
  public UserModel edit(UserModel item) {
    throw new UnsupportedOperationException();
  }

  @Override
  public UserModel findByID(int id) {
    String request = "exec FindUserByID ?";
    return Execute(request, (x)->{
      x.setInt(1, id);
      return parseResponse(x.executeQuery());
    }).get(0);
  }

  @Override
  public UserModel findByName(String name) {
    return ExecuteGetCommand((x) -> {
      try (ResultSet set = x.executeQuery(String.format("exec FindUserByLogin '%1$s'", name))) {
        return parseResponse(set);
      }
    }).get(0);
  }

  @Override
  public List<UserModel> getItemList() {
    return ExecuteGetCommand((x) -> {
      try (ResultSet set = x.executeQuery(String.format("exec GetAllUsers"))) {
        return parseResponse(set);
      }
    });
  }
}
