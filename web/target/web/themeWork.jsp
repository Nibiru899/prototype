<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <meta charset="UTF-8" />
        <title>User Info</title>
</head>

<body>
<form action = "/theme" method = "post" >
         <input type="text" name="second" value="${theme.getName()}">
         <input type="hidden" name="first" value="${theme.getName()}">
         <input type="hidden" name="_method" value="put">
         <input type="submit" value="Сохранить имя темы">
</form>
<table border="0">
    <c:forEach items="${theme.getQuestions()}" var = "question">
        <tr>
            <td>
                ${question.getQuestion()}
            </td>
            <td>
                <form action = "/question" method = "get">
                      <input type="hidden" name="name" value="${theme.getName()}">
                      <input type="hidden" name = "index" value="${question.getIndex()}">

                      <input type="submit" value="Изменить">
                </form>
            </td>
            <td>
                <form action = "/theme" method = "post">
                      <input type="hidden" name="name" value="${theme.getName()}">
                      <input type="hidden" name="index" value="${question.getIndex()}">
                      <input type="hidden" name="_method" value="delete">
                      <input type="submit" value="Удалить">
                </form>
            </td>
        </tr>
    </c:forEach>
</table>
<form action = "/theme" method = "post">
         <input type="hidden" name="name" value="${theme.getName()}">
         <input type="submit" value="Добавить вопрос">
</form>
<a href="/themes">Назад</a>

</body>
</html>
