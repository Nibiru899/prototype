<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <meta charset="UTF-8" />
        <title>User Info</title>

        <script type='text/javascript'>

                function addFields(question,id){
                    //получение последнего id;
                    var hideParam = document.getElementById("lastIdElement");
                    var i = parseInt(hideParam.value)

                    var container = document.getElementById("container");
                    var input = document.createElement("input");
                    input.type = "text"
                    input.name = "ans" + i;
                    var checkBox = document.createElement("input");
                    checkBox.type = "checkBox";
                    checkBox.name = "check" + i;
                    container.appendChild(input);
                    container.appendChild(checkBox);
                    container.appendChild(document.createElement("br"));
                    container.appendChild(document.createElement("br"));

                    hideParam.value = i+1;
                }
            </script>
</head>
<body>
<form action="/question" method="post">
    Вопрос:
    <br>
    <input type="text" name="quest" value="${question.getQuestion()}">
    <br>
    <br>
    <br>
    Ответы:
    <input type="hidden" name="lastIdElement" value="${question.getLastId()}" id = "lastIdElement">
    <input type="hidden" name="prevName" value="${question.getQuestion()}">
    <input type = "hidden" value = "${themeName}" name = "name">
    <input type = "hidden" value = "${question.getIndex()}" name = "index">
    <%! int i = 0 ; %>
    <div id = "container">
        <c:forEach items="${question.getAnswers()}" var = "answer">
                <input type="text" name="ans${i}" value="${answer.getAnswer()} ${i}">
                <input type="checkBox" name="check${i}">
            <br>
            <br>
        </c:forEach>
    </div>
    <br>
    <button onClick="addFields()"
            type="button">
        +
    </button>
    <br>
    <br>
    <input type="submit" value="Подтвердить изменения">
</form>



<form action="/theme" method="get">
    <input type = "hidden" value = "${themeName}" name = "name">
    <input type = "hidden" value = "false" name = "mode">
    <input type = "submit" value = "Назад">
</body>

</html>
