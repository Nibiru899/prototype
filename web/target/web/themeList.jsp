<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <meta charset="UTF-8" />
        <title>User Info</title>
    </head>
<body>
<h2>Лист тем:</h2>

<table border="2">
    <tr>
        <td>Тема</td>
        <td>Действия</td>
    </tr>
    <c:forEach items="${themes}" var = "theme">
        <tr>
                    <td>
                        ${theme}
                    </td>
                    <td>
                        <form action = "/theme" method = "get">
                                     <input type="hidden" name = "name" value="${theme}">
                                     <input type="hidden" name="create" value="false">
                                     <input type="submit" value="Изменить">
                        </form>

                        <form action = "/themes" method = "post">
                                       <input type="hidden" name = "name" value="${theme}">
                                       <input type="hidden" name="_method" value="delete">
                                       <input type="submit" value="Удалить">
                        </form>
                    </td>
                </tr>
    </c:forEach>
</table>

<form action = "/theme" method = "get">
               <input type="hidden" name="create" value="true">
               <input type="hidden" name = "name" value="Новая тема">
               <input type="submit" value="Добавить тему">
</form>

</body>
</html>
