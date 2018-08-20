package ru.spb.epam.grebovod.third.model;

public class DataPhoto {
  private int id;
  private byte[] dataimg;
  private String imgHash = null;

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public byte[] getDataimg() {
    return dataimg;
  }

  public void setDataimg(byte[] dataimg) {
    this.dataimg = dataimg;
  }

  public String getImgHash() {
    return imgHash;
  }

  public void setImgHash(String imgHash) {
    this.imgHash = imgHash;
  }
}
