package ru.spb.epam.grebovod.third;

import java.io.IOException;
import java.util.Arrays;
import java.util.stream.Collectors;
import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.model.UserModel;
import ru.spb.epam.grebovod.third.repository.tsql.UserRepository;

public class MainServlet extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    Cookie userIDCoockie = CookieWorker.getUserIDCookie(request.getCookies());
    if(userIDCoockie == null){
      response.sendRedirect("/user/login");
    }
    else {
      UserRepository repository = new UserRepository();
      UserModel model = repository.findByID(Integer.parseInt(userIDCoockie.getValue()));
      request.setAttribute("userLogin", model.getUserLogin());
      request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
    }
  }


}
