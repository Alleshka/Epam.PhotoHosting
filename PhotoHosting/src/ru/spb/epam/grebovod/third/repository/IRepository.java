package ru.spb.epam.grebovod.third.repository;

import java.util.List;

public interface IRepository <T> {
  public T create(T item);
  public void delete(T item);
  public T edit(T item);
  public T findByID(int id);
  public T findByName(String name);
  public List<T> getItemList();
}
