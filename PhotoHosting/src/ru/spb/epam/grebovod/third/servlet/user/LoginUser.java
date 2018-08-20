package ru.spb.epam.grebovod.third.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.CookieWorker;
import ru.spb.epam.grebovod.third.model.UserModel;
import ru.spb.epam.grebovod.third.repository.tsql.UserRepository;

public class LoginUser extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    String login = request.getParameter("login");
    String password = request.getParameter("password");

    UserRepository userRepository = new UserRepository();

    if(userRepository.LoginUser(login, password)){
      UserModel user = userRepository.findByName(login);
      response.addCookie(CookieWorker.createUserIDCookie(user));
      response.sendRedirect("/home");
    }
    else response.getOutputStream().write("что-то не так".getBytes());
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/user/login.jsp").forward(request, response);
  }
}
