package ru.spb.epam.grebovod.third.model;

import ru.spb.epam.grebovod.third.repository.tsql.TDataPhotoRepository;

public class PhotoModel {
  private Integer id;
  private String name;
  private int uploader;
  private int photoDataID;

  private byte[] photodata = null;

  public int getPhotoDataID() {
    return photoDataID;
  }

  public void setPhotoDataID(int photoDataID) {
    this.photoDataID = photoDataID;
  }

  public int getUploader() {
    return uploader;
  }

  public void setUploader(int uploader) {
    this.uploader = uploader;
  }

  public byte[] getPhotodata() {

    if(photodata==null){
      TDataPhotoRepository tDataPhotoRepository = new TDataPhotoRepository();
      photodata = tDataPhotoRepository.findByID(photoDataID).getDataimg();
    }

    return photodata;

  }

  public void setPhotodata(byte[] photodata) {
    this.photodata = photodata;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
    this.id = id;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  @Override
  public String toString() {
    return String.format("'%2$s' '%3$s'", name);
  }
}
