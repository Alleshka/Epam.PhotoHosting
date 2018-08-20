<%--
  Created by IntelliJ IDEA.
  User: alles
  Date: 04.06.2018
  Time: 3:00
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>

<a href="/home">На главную</a>
<br/>
<br/>
<table border="1">
    <c:forEach items="${requestScope.categoryList}" var="cat">
        <tr>
            <td>${cat.categoryID}</td>
            <td><a href="/photo/list?category=${cat.categoryID}">${cat.categoryName}</a></td>
            <td><form action="/category/delete" method="get"><input name="categoryID" type="hidden" value=${cat.categoryID}><input type="submit" value="Удалить"></form></td>
        </tr>
    </c:forEach>
</table>
<br/>
<br/>
<form action="/category/create" method="post">
    <td>
        <tr>
            Название:
        </tr>
        <tr>
            <input type="text" name="name">
        </tr>
    </td>
    <td>
        <tr rowspan="2" align="center">
            <input type="submit" value="Добавить">
        </tr>
    </td>
</form>

</body>
</html>
