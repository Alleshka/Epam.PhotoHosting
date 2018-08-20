<%--
  Created by IntelliJ IDEA.
  User: alles
  Date: 03.06.2018
  Time: 19:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Вход</title>
</head>
<body>
Вход</br>
<a href="/home">На главную</a><br/>
<form action="/user/login" method="post">
    <table>
        <tr>
            <td>
                Логин
            </td>
            <td>
                <input name="login" type="text">
            </td>
        </tr>

        <tr>
            <td>
                Пароль
            </td>
            <td>
                <input name="password" type="password">
            </td>
        </tr>

        <tr>
            <td align="center">
                <input type="submit" value="Вход">
            </td>
            <td>
                <input formaction="/user/create" type="submit" formmethod="get" value="Регистрация">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
