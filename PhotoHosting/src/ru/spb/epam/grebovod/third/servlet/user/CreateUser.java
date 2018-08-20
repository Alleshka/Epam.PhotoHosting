package ru.spb.epam.grebovod.third.servlet.user;

import java.io.IOException;
import javax.servlet.http.Cookie;
import ru.spb.epam.grebovod.third.CookieWorker;
import ru.spb.epam.grebovod.third.model.UserModel;
import ru.spb.epam.grebovod.third.repository.tsql.UserRepository;

public class CreateUser extends javax.servlet.http.HttpServlet {

  protected void doPost(javax.servlet.http.HttpServletRequest request,
      javax.servlet.http.HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {

    String login = request.getParameter("login");
    String password = request.getParameter("password");

    UserRepository userRepository = new UserRepository();
    UserModel user = userRepository.create(login, password);
    response.addCookie(CookieWorker.createUserIDCookie(user));
    response.sendRedirect("/home");
  }

  protected void doGet(javax.servlet.http.HttpServletRequest request,
      javax.servlet.http.HttpServletResponse response)
      throws javax.servlet.ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/user/create.jsp").forward(request, response);
  }
}
