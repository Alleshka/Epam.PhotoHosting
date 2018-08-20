package ru.spb.epam.grebovod.third;
import org.apache.commons.codec.digest.DigestUtils;

public class MD5HashCalculation {

  public static String getPhotoHash(byte[] data) {
    return DigestUtils.md5Hex(data);
  }

  public static String getStringHash(String data){
    return DigestUtils.md5Hex(data);
  }
}
