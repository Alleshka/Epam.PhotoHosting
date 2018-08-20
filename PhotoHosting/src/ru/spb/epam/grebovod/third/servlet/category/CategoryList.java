package ru.spb.epam.grebovod.third.servlet.category;

import java.io.IOException;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder.In;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.CookieWorker;
import ru.spb.epam.grebovod.third.model.CategoryModel;
import ru.spb.epam.grebovod.third.repository.tsql.CategoryRepository;

public class CategoryList extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    CategoryRepository repository = new CategoryRepository();
    List<CategoryModel> list = repository.getCategoriesByUserID(
        Integer.parseInt(CookieWorker.getUserIDCookie(request.getCookies()).getValue()));
    request.setAttribute("categoryList", list);
    request.getRequestDispatcher("/WEB-INF/category/list.jsp").forward(request, response);
  }
}
