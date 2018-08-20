package ru.spb.epam.grebovod.third.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.CookieWorker;

public class LogOut extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    Cookie cookie = CookieWorker.getUserIDCookie(request.getCookies());
    cookie = CookieWorker.deletCookie(cookie);
    response.addCookie(cookie);

    response.sendRedirect("/home");
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    doPost(request, response);
  }


}
