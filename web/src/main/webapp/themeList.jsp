<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <style type="text/css">
        	p{
        	    font-size:16pt;
        	}
        </style>
        <title>Список тем</title>
</head>
<body>
<h2>Лист тем:</h2>
    <c:forEach items="${themes}" var = "theme">
                                    <br>
                                   <table border="1" width="25%">
                                    <tr>
                                        <td width="75%" >Название</td>
                                        <td width="25%">Действия</td>
                                    </tr>
                                    <tr>
                                        <td>${theme}</td>
                                        <td>
                                            <br>
                                            <form action = "/theme" method = "get">
                                                        <input type='submit' value = "Редактировать">
                                                        <input type="hidden" name = "name" value="${theme}">
                                                        <input type="hidden" name="create" value="false">
                                            </form>
                                            <form action = "/themes" method = "post">
                                                        <input type="hidden" name = "name" value="${theme}">
                                                        <input type="hidden" name="_method" value="delete">
                                                        <input type='submit' value = "Удалить">
                                            </form>
                                        </td>
                                    </tr>
                                </table>
    </c:forEach>
<hr>
<form action = "/theme" method = "get">
               <input type="hidden" name="create" value="true">
               <input type="hidden" name = "name" value="Новая тема">
               <input type="submit" value="Добавить тему">
</form>
<form action = "/" >
               <input type="submit" value="<---">
</form>
</body>
</html>
