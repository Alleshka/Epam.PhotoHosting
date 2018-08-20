package ru.spb.epam.grebovod.third.repository.tsql;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

public interface ICommand<T> {

  List<T> Execute(PreparedStatement statement) throws SQLException;
}

interface IUpdateCommand {

  void Execute(Statement statement) throws SQLException;
}

interface IGetCommand<T> {

  List<T> Execute(Statement statement) throws SQLException;
}


