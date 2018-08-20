package ru.spb.epam.grebovod.third.repository.tsql;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;
import javax.persistence.criteria.CriteriaBuilder.In;
import ru.spb.epam.grebovod.third.MD5HashCalculation;
import ru.spb.epam.grebovod.third.model.DataPhoto;
import ru.spb.epam.grebovod.third.model.PhotoModel;

public class PhotoRepository extends AbstractTSqlRepository<PhotoModel> {

  @Override
  protected List<PhotoModel> parseResponse(ResultSet item) {
    try {
      List<PhotoModel> list = new LinkedList<>();
      TDataPhotoRepository photoRepository = new TDataPhotoRepository();

      while (item.next()) {

        PhotoModel photoModel = new PhotoModel();
        photoModel.setId(item.getInt("id"));
        photoModel.setName(item.getString("imgname"));
        photoModel.setUploader(item.getInt("uploader"));
        photoModel.setPhotoDataID(item.getInt("img"));
        // photoModel.setPhotodata(photoRepository.findByID(item.getInt("img")).getDataimg());

        list.add(photoModel);
      }
      return list;
    } catch (SQLException ex) {
      System.out.println(ex.getMessage());
      return null;
    }
  }


  public void addPhotoToCategory(Integer photoID, Integer categoryID){
    String request = "exec AddPhotoToCategory ?, ?";
    photoInCategory(request, photoID, categoryID);
  }

  public void removePhotoFromCategory(Integer photoID, Integer categoryID){
   String request = "exec RemovePhotoFromCategory ?, ?";
   photoInCategory(request, photoID, categoryID);
  }

  @Override
  public PhotoModel create(PhotoModel item) {
    String request = "exec AddPhoto ?, ?, ?";
    List<PhotoModel> modelList = Execute(request, (x) -> {
      DataPhoto data = getDataPhoto(item);
      x.setString(1, item.getName());
      x.setInt(2, data.getId());
      x.setInt(3, item.getUploader());
      x.executeUpdate();

      return getPhotoFromUser(item.getUploader());
    });

    if (modelList == null || modelList.size() == 0)
      return null;
    else {
      PhotoModel model = modelList.stream().skip(modelList.size() - 1).collect(Collectors.toList())
          .get(0);
      CategoryRepository repository = new CategoryRepository();
      addPhotoToCategory(model.getId(),
          repository.getDefaultCategory(model.getUploader()).getCategoryID());
      return model;
    }
  }

  @Override
  public void delete(PhotoModel item) {
    ExecuteUpdateCommand((x)->x.executeUpdate(String.format("exec DeletePhoto %1$s", item.getId())));
  }

  @Override
  public PhotoModel edit(PhotoModel item) {
    throw new UnsupportedOperationException();
  }

  @Override
  public PhotoModel findByID(int id) {
    return ExecuteGetCommand((x)->parseResponse(x.executeQuery(String.format("exec FindPhotoByID %1$s", id)))).get(0);
  }

  @Override
  public PhotoModel findByName(String name) {
    throw new UnsupportedOperationException();
  }

  @Override
  public List<PhotoModel> getItemList() {
    return ExecuteGetCommand((x)->parseResponse(x.executeQuery(String.format("select * from TPhoto order by id desc"))));
  }

  public List<PhotoModel> getPhotoFromUser(int userID){
    String request = "select * from TPhoto where uploader = ?";
    return Execute(request, (x)->{
      x.setInt(1, userID);
      return parseResponse(x.executeQuery());
    });
  }

  public List<PhotoModel> getPhotosFromCategory(int categoryID){
    String request = "exec GetPhotosFromCategory ?";
    return Execute(request, (x)->{
      x.setInt(1, categoryID);
      return parseResponse(x.executeQuery());
    });
  }

  private DataPhoto getDataPhoto(PhotoModel item){

    TDataPhotoRepository repository = new TDataPhotoRepository();
    String photoHashCode = MD5HashCalculation.getPhotoHash(item.getPhotodata());

    DataPhoto data = repository.findByName(photoHashCode);

    if (data == null) {
      data = new DataPhoto();
      data.setImgHash(photoHashCode);
      data.setDataimg(item.getPhotodata());
      data = repository.create(data);
    }

    return data;
  }

  private void photoInCategory(String request, Integer photoID,Integer categoryID){
    Execute(request, (x)->{
      x.setInt(1, photoID);
      x.setInt(2, categoryID);
      x.executeUpdate();
      return null;
    });
  }

}
