<%--
  Created by IntelliJ IDEA.
  User: alles
  Date: 03.06.2018
  Time: 16:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>

</head>
<body>
Регистрация</br>
<a href="/home">На главную</a><br/>
<form action="/user/create" method="post">
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
            <td align="center" rowspan="2">
                <input type="submit" value="Зарегистрироваться">
            </td>
        </tr>
    </table>
</form>
</body>
</html>
