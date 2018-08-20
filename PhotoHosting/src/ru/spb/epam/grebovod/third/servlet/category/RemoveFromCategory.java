package ru.spb.epam.grebovod.third.servlet.category;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.repository.tsql.PhotoRepository;

public class RemoveFromCategory extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    int photoID = Integer.parseInt(request.getParameter("photoID"));
    int categoryID = Integer.parseInt(request.getParameter("category"));

    PhotoRepository repository = new PhotoRepository();
    repository.removePhotoFromCategory(photoID, categoryID);

    request.setAttribute("category", categoryID);
    request.getRequestDispatcher("/photo/list").forward(request, response);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }
}
