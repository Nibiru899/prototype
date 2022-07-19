<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <script type='text/javascript'>

            </script>
</head>
<body>
<h2>Лист планов тестирования</h2>
<c:forEach items="${blanks}" var = "blank">
<table border="1">
    <tr>
        <td>Название:</td>
        <td>${blank.getName()}</td>
    </tr>
    <tr>
        <td>Начало:</td>
        <td>${blank.getViewStart()}</td>
    </tr>
    <tr>
        <td>Конец:</td>
        <td>${blank.getViewEnd()}</td>
    </tr>
    <tr>
        <td>
            <form action = "/blank" method = "get">
                <input type="hidden" name="mode" value="check">
                <input type="hidden" name="name" value="${blank.getName()}">
                <input type="submit" value="Изменить">
            </form>
        </td>
        <td>
            <form action = "/blanks" method = "post">
                <input type="hidden" name="name" value="${blank.getName()}">
                <input type="hidden" name="_method" value="delete">
                <input type="submit" value="Удалить">
            </form>
        </td>
        <td>
                <form action = "/blanks" method = "post">
                     <input type="hidden" name="name" value="${blank.getName()}">
                     <input type="hidden" name="_method" value="delete">
                     <input type="submit" value="Удалить">
                </form>
        </td>
    </tr>
</table>
<br>
</c:forEach>

<form action = "/blank" method = "post">
                <input type="hidden" name="mode" value="creation">
                <input type="hidden" name="_method" value="get">
                <input type="submit" value="Добавить план тестирования">;
</form>
<br>
<form action = "/" >
               <input type="submit" value="<---">
</form>
</body>

</html>
