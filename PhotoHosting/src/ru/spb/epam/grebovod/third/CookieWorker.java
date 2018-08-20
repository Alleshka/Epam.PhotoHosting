package ru.spb.epam.grebovod.third;

import java.util.Arrays;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import ru.spb.epam.grebovod.third.model.UserModel;

public class CookieWorker {

  public static Cookie getUserIDCookie(Cookie[] cookies) {
    if(cookies==null) return null;
    if(Arrays.stream(cookies).anyMatch(x -> x.getName().compareTo("userID") == 0)){
      return (Cookie) Arrays.stream(cookies).filter(x -> x.getName().compareTo("userID")==0).toArray()[0];
    }
    else return null;
  }

  public static Cookie getUserIDCookie(HttpServletRequest request){
    return getUserIDCookie(request.getCookies());
  }

  public static Cookie createUserIDCookie(UserModel user){
    Cookie userID = new Cookie("userID", user.getUserID().toString());
    userID.setMaxAge(60*60*48);
    userID.setPath("/");
    return userID;
  }


  public static Cookie deletCookie(Cookie cookie){
    cookie.setPath("/");
    cookie.setMaxAge(0);
    cookie.setComment("EXPIRING COOKIE at " + System.currentTimeMillis());
    return cookie;
  }
}
