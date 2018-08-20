package ru.spb.epam.grebovod.third.test;
import java.util.Random;
import java.util.UUID;
import org.junit.Assert;
import org.junit.Test;
import ru.spb.epam.grebovod.third.model.CategoryModel;
import ru.spb.epam.grebovod.third.model.UserModel;
import ru.spb.epam.grebovod.third.repository.tsql.CategoryRepository;
import ru.spb.epam.grebovod.third.repository.tsql.UserRepository;

public class UserRepositoryTest {

  @Test
  public void CreateUser(){
    // Arrange
    UserModel user = new UserModel();
    user.setUserLogin("Alleshka " + UUID.randomUUID());
    user.setUserPassword("123qwe");

    // Act
    UserRepository repository = new UserRepository();
    int itemCount = repository.getItemList().size();

    user = repository.create(user);
    int newItemCount = repository.getItemList().size();

    // Asserts
    Assert.assertEquals(itemCount+1, newItemCount);
    Assert.assertEquals(null, user.getUserPassword());
  }

  @Test
  public void DeleteUser(){
    // Arrange
    UserRepository repository = new UserRepository();
    UserModel user = repository.findByName("Alleshka");

    // Act
    int countItems = repository.getItemList().size();
    repository.delete(user);
    int countAfter = repository.getItemList().size();
    repository.restore(user);
    int countNew = repository.getItemList().size();

    // Asserts
    Assert.assertEquals(countItems, countAfter + 1);
    Assert.assertEquals(countItems, countNew);
  }

}