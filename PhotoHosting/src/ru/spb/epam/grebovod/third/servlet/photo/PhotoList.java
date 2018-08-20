package ru.spb.epam.grebovod.third.servlet.photo;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import ru.spb.epam.grebovod.third.CookieWorker;
import ru.spb.epam.grebovod.third.model.PhotoModel;
import ru.spb.epam.grebovod.third.repository.tsql.PhotoRepository;

public class PhotoList extends HttpServlet {

  protected void doPost(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {
    doGet(request,response);
  }

  protected void doGet(HttpServletRequest request,
      HttpServletResponse response)
      throws ServletException, IOException {

    if(CookieWorker.getUserIDCookie(request)==null){
      response.sendError(0, "Необходимо войти в личный кабинет");
    }
    else {
      Integer userID = null;
      Integer categoryID = null;

      Integer curUserID = Integer.parseInt(CookieWorker.getUserIDCookie(request).getValue());
      List<PhotoModel> photoModelList = null;
      PhotoRepository photoRepository = new PhotoRepository();

      if(request.getParameter("category")!=null){
        categoryID = Integer.parseInt(request.getParameter("category"));
        photoModelList = photoRepository.getPhotosFromCategory(categoryID);
        request.setAttribute("category", categoryID);
      }
      else {
        if (request.getParameter("userID") != null) {
          userID = Integer.parseInt(request.getParameter("userID"));
        } else {
          userID = curUserID;
        }

        if (userID != -1) {
          photoModelList = photoRepository.getPhotoFromUser(userID);
        } else {
          photoModelList = photoRepository.getItemList();
        }

        request.setAttribute("userID", userID);
      }

      request.setAttribute("photoList", photoModelList);
      request.setAttribute("curUserID", curUserID);
      request.getRequestDispatcher("/WEB-INF/photo/list.jsp").forward(request, response);
    }
  }
}
