<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
        <title>Работа с вопросом ${question.getQuestion()}</title>
        <script type='text/javascript'>

                function addFields(){
                    //получение последнего id;
                    var hideParam = document.getElementById("lastIdElement");
                    var i = parseInt(hideParam.value)

                    var container = document.getElementById("container");
                    //создание блока
                    var block = document.createElement("div");
                    block.id = "div"+i;
                    var input = document.createElement("input");
                    input.type = "text"
                    input.name = "ans" + i;
                    var checkBox = document.createElement("input");
                    checkBox.type = "checkBox";
                    checkBox.name = "check" + i;
                    var btn = document.createElement("button");
                    btn.innerHTML = "X";
                        btn.onclick = function () {
                                       removeFields(i);
                                     };

                    block.appendChild(input);
                    block.appendChild(checkBox);
                    block.appendChild(btn);
                    block.appendChild(document.createElement("br"));
                    block.appendChild(document.createElement("br"));

                    container.appendChild(block);
                    hideParam.value = i+1;
                }

                function removeFields(id){
                                    var container = document.getElementById("container");
                                    console.log(id);

                                    for(var node=container.firstChild; node!==null; node=node.nextSibling) {
                                        if (node.id == "div"+id){
                                                container.removeChild(node);
                                        }
                                    }

                                }
            </script>
</head>
<body>
<form action="/question" method="post">
    Вопрос:
    <input type="text" name="quest" value="${question.getQuestion()}">
    <br>
    <br>
    <br>
    Ответы:
    <input type="hidden" name="lastIdElement" value="${question.getAnswers().size()}" id = "lastIdElement">
    <input type="hidden" name="prevName" value="${question.getQuestion()}">
    <input type = "hidden" value = "${themeName}" name = "name">
    <input type = "hidden" value = "${question.getIndex()}" name = "index">
    <div id = "container">
        <c:forEach items="${question.getAnswers()}" var = "answer">
                <div id = "div${answer.getId()}">
                     <input type="text" name="ans${answer.getId()}" value="${answer.getAnswer()}">
                     <c:if test = "${answer.isSuccess() == true}" >
                            <input type="checkBox" name="check${answer.getId()}" checked>
                     </c:if>
                     <c:if test = "${answer.isSuccess() != true}" >
                            <input type="checkBox" name="check${answer.getId()}">
                     </c:if>
                     <button type="button" onclick="removeFields(${answer.getId()})">X</button>
                     <br>
                     <br>
                </div>
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
    <input type = "hidden" value = "false" name = "create">
    <input type="submit" value="<---">
</form>

</body>

</html>
