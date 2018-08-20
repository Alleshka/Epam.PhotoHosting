<%--
  Created by IntelliJ IDEA.
  User: alles
  Date: 03.06.2018
  Time: 21:08
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<a href="/home">На главную</a><br/>
<form method="post" action="/photo/load" enctype="multipart/form-data">
    <table>
        <tr>
            <td>Название</td>
            <td><input type="text" name="name"></td>
        </tr>

        <tr>
            <td>Файл</td>
            <td><input type="file" name="file"></td>
        </tr>
        <tr>
            <td rowspan="2"><input type="submit" value="Загрузить"></td>
        </tr>
    </table>
</form>
</body>
</html>
