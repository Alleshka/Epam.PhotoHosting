package ru.spb.epam.grebovod.third.repository.tsql;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import ru.spb.epam.grebovod.third.repository.IRepository;
import com.microsoft.sqlserver.jdbc.SQLServerDriver;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import sun.rmi.runtime.Log;

public abstract class AbstractTSqlRepository<T> implements IRepository<T> {

  protected String connectionString = "jdbc:sqlserver://sql6003.site4now.net;database=DB_A3CB6E_epamphoto;user=DB_A3CB6E_epamphoto_admin;password=epamphoto123qwe";
  protected static final Logger rootLogger = LogManager.getRootLogger();
  protected static final Logger errorLogger = LogManager.getLogger(AbstractTSqlRepository.class);

  {
    SQLServerDriver driver = new SQLServerDriver();
  }

  protected abstract List<T> parseResponse(ResultSet item);

  protected void ExecuteUpdateCommand(IUpdateCommand command) {
    try (Connection connection = DriverManager
        .getConnection(connectionString); Statement statement = connection.createStatement()) {
      command.Execute(statement);
    } catch (SQLException ex) {
      errorLogger.error(ex.getMessage());
      rootLogger.error(ex.getMessage());
      rootLogger.error(ex.getStackTrace());
    }
  }

  protected List<T> ExecuteGetCommand(IGetCommand<T> command) {
    try (Connection connection = DriverManager
        .getConnection(connectionString); Statement statement = connection.createStatement()) {
      return command.Execute(statement);
    } catch (SQLException ex) {
      errorLogger.error(ex.getMessage());
      rootLogger.error(ex.getMessage());
      rootLogger.error(ex.getStackTrace());
      return null;
    }
  }

  protected List<T> Execute(String sql, ICommand<T> command) {
    rootLogger.info("Выполнение команды: " + command.toString());
    try (Connection connection = DriverManager
        .getConnection(connectionString); PreparedStatement statement = connection
        .prepareStatement(sql)) {
      return command.Execute(statement);
    }
    catch (SQLException ex){
      errorLogger.error(ex.getMessage());
      rootLogger.error(ex.getMessage());
      rootLogger.error(ex.getStackTrace());
      return null;
    }
  }
}
