<%--
  Created by IntelliJ IDEA.
  User: alles
  Date: 04.06.2018
  Time: 16:31
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/home">На главную</a><br/>
<a href="/photo/list">Все мои фотографии</a>
<table border="1">
    <c:choose>
        <c:when test="${requestScope.category ne null}"><c:out value="Список фотографий в альбоме"></c:out></c:when>

        <c:when test="${requestScope.userID eq -1}"><c:out value="Список всех фотографий"></c:out></c:when>
        <c:when test="${requestScope.userID eq requestScope.curUserID}"><c:out value="Личные фотографии"></c:out></c:when>

        <c:when test="${requestScope.userID ne requestScope.curUserID}"><c:out value="Фотографии пользователя"></c:out></c:when>
    </c:choose>

    <c:forEach items="${requestScope.photoList}" var="photo">
        <tr>
            <td>${photo.id}</td>
            <td>${photo.name}</td>

            <td>
                <form method="get" action="/photo/view" target="_blank">
                    <input type="hidden" name="photoID" value="${photo.id}">
                    <input value="Посмотреть фото" type="submit">
                </form>
            </td>

        <c:if test="${requestScope.curUserID eq photo.uploader}">
            <td>
                <form method="post" action="/photo/delete">
                    <input type="hidden" name="photoID" value="${photo.id}">
                    <c:if test="${requestScope.category ne null}">
                        <input type="hidden" name="category" value="${requestScope.category}">
                    </c:if>
                    <input type="hidden" name="userID" value="${requestScope.userID}">
                    <input value="Удалить фото" type="submit">
                </form>
            </td>
        </c:if>
        </tr>
    </c:forEach>
</table>
<br/>
<c:if test="${requestScope.category ne null}">
    <form action="/category/addPhoto" method="post">
        <input type="text" name="photoID">
        <input type="hidden" name="category" value="${requestScope.category}">
        <input type="submit" value="Добавить в категорию">
    </form>
    <br/>
    <form action="/category/deletePhoto" method="post">
        <input type="text" name="photoID">
        <input type="hidden" name="category" value="${requestScope.category}">
        <input type="submit" value="Удалить из категории">
    </form>
</c:if>
</body>
</html>
