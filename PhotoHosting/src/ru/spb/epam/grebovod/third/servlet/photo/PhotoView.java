package ru.spb.epam.grebovod.third.servlet.photo;

import java.io.IOException;
import java.util.List;
import javax.imageio.ImageIO;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.model.PhotoModel;
import ru.spb.epam.grebovod.third.repository.tsql.PhotoRepository;

public class PhotoView extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    int id = Integer.parseInt(request.getParameter("photoID"));

    PhotoRepository repository = new PhotoRepository();
    PhotoModel model = repository.findByID(id);

    response.getOutputStream().write(model.getPhotodata());

  }
}
