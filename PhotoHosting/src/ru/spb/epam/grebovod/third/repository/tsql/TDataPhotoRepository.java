package ru.spb.epam.grebovod.third.repository.tsql;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import ru.spb.epam.grebovod.third.MD5HashCalculation;
import ru.spb.epam.grebovod.third.model.DataPhoto;

public class TDataPhotoRepository extends AbstractTSqlRepository<DataPhoto> {

  @Override
  protected List<DataPhoto> parseResponse(ResultSet item) {

    try {
      List<DataPhoto> list = new LinkedList<>();
      while (item.next()) {
        DataPhoto photo = new DataPhoto();
        photo.setId(item.getInt("id"));
        byte[] data = item.getBytes("dataimage");
        photo.setDataimg(data);
        photo.setImgHash(item.getString("imghash"));
        list.add(photo);
      }
      return list;
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }

  @Override
  public DataPhoto create(DataPhoto item) {

    if (item.getImgHash() == null) {
      item.setImgHash(MD5HashCalculation.getPhotoHash(item.getDataimg()));
    }

    if (findByName(item.getImgHash()) == null) {
      String request = "exec AddImg ?, ?";
      Execute(request, (x) ->{
        x.setBytes(1, item.getDataimg());
        x.setString(2, item.getImgHash());
        x.executeUpdate();
        return null;
      });
    }

    return findByName(item.getImgHash());
  }

  @Override
  public void delete(DataPhoto item) {
    throw new UnsupportedOperationException();
  }

  @Override
  public DataPhoto edit(DataPhoto item) {
    throw  new UnsupportedOperationException();
  }

  @Override
  public DataPhoto findByID(int id) {
    return ExecuteGetCommand((x)-> parseResponse(x.executeQuery(String.format("select * from TDataPhoto where id = %1$s", id)))).get(0);
  }

  @Override
  public DataPhoto findByName(String name) {
    String request = "exec FindByHash ?";
    List<DataPhoto> dataPhotos = Execute(request, (x)->{
      x.setString(1, name);
      return parseResponse(x.executeQuery());
    });

    if(dataPhotos==null || dataPhotos.size()==0) return null;
    else return dataPhotos.get(0);
  }

  @Override
  public List<DataPhoto> getItemList() {
    return ExecuteGetCommand((x)-> parseResponse(x.executeQuery(String.format("select * from TDataPhoto"))));
  }
}
