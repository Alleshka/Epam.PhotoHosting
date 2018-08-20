<%--
  Created by IntelliJ IDEA.
  User: alles
  Date: 04.06.2018
  Time: 4:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <a href="/home">На главную</a><br/>
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
            <input type="submit" value="Отправить">
        </tr>
    </td>
</form>
</head>
<body>

</body>
</html>
