package ru.spb.epam.grebovod.third.servlet.photo;

import java.io.*;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.commons.io.IOUtils;
import ru.spb.epam.grebovod.third.CookieWorker;
import ru.spb.epam.grebovod.third.model.PhotoModel;
import ru.spb.epam.grebovod.third.repository.tsql.PhotoRepository;

@MultipartConfig
public class LoadPhoto extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    PhotoModel model = new PhotoModel();
    model.setName(request.getParameter("name"));
    model.setUploader(
        Integer.parseInt(CookieWorker.getUserIDCookie(request.getCookies()).getValue()));

    InputStream inputStream = request.getPart("file").getInputStream();
    byte[] data = IOUtils.toByteArray(inputStream);
    model.setPhotodata(data);
    PhotoRepository repository = new PhotoRepository();
    repository.create(model);
    response.sendRedirect("/photo/list");
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    request.getRequestDispatcher("/WEB-INF/photo/load.jsp").forward(request, response);
  }
}
