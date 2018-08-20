<%--
  Created by IntelliJ IDEA.
  User: alles
  Date: 03.06.2018
  Time: 18:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
</head>
<body>
<table border="1">
    <tr>
        <td>
            Ваш логин: ${requestScope.userLogin};
        </td>

        <td>
            <a href="/user/logout">Выход</a>
        </td>
    </tr>

    <tr>
        <td colspan="2" align="center">Фотографии</td>
    </tr>


    <tr>
        <td colspan="2">
            <a href="/photo/list">Мои фото</a>
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <a href="/photo/list?userID=-1">Посмотреть все фото</a>
        </td>
    </tr>


    <tr>
        <td colspan="2">
            <a href="/photo/load">Загрузить новую фотографию</a>
        </td>
    </tr>

    <tr>
        <td colspan="2" align="center">
            Категории
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <a href="/category/list">Список личных категорий</a>
        </td>
    </tr>

    <tr>
        <td colspan="2">
            <a href="/category/create">Добавить категорию</a>
        </td>
    </tr>


</table>
</body>
</html>
