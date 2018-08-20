package ru.spb.epam.grebovod.third.servlet.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.repository.tsql.CategoryRepository;

@WebServlet(name = "DeleteCategory")
public class DeleteCategory extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    String IDCategory = request.getParameter("categoryID");

    if(IDCategory!=null){
      CategoryRepository categoryRepository = new CategoryRepository();
      categoryRepository.delete(Integer.parseInt(IDCategory));
    }

    request.getRequestDispatcher("/category/list").forward(request, response);
}
}
