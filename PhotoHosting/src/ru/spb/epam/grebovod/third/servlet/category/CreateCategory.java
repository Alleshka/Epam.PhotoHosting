package ru.spb.epam.grebovod.third.servlet.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.CookieWorker;
import ru.spb.epam.grebovod.third.model.CategoryModel;
import ru.spb.epam.grebovod.third.repository.tsql.CategoryRepository;

public class CreateCategory extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    String categoryName = request.getParameter("name");
    String categoryCreator = CookieWorker.getUserIDCookie(request.getCookies()).getValue();
    CategoryRepository categoryRepository = new CategoryRepository();
    categoryRepository.create(categoryName, Integer.parseInt(categoryCreator));
    response.sendRedirect("/category/list");
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/category/create.jsp").forward(request, response);
  }
}
