<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head id="head">
    <style>
        .modalForm{
            position:fixed;
            width:100%;
            height:100%;
            background-color:rgba(0,0,0,0.5);
            top:0;
            left:0;
            visibility:hidden;
        }

        .modalForm:target{
            visibility:visible;
        }
        .modalBody{
            min-height:100%;
            display:flex;
            align-items:center;
            justify-content:center;
            padding:30px 10px;
        }
        .modalContent{
            background-color:#fff;
            color:#000;
            max-width:800px;
            padding:30px;
            position:relative;
        }
        .modalClose{
            position:absolute;
            right:10px;
            top:10px;
        }
        .modalTitle{
            font-size:40px;
            margin:0px 0px 1em 0px;
        }
        .modalText{

        }
    </style>
    <script type='text/javascript'>
        function createTable(name){
            var container = document.getElementById("content");
            var div = document.createElement("div");
            div.id = name;
            var tr = document.createElement("tr");
            container.appendChild(div);
            var tdx = document.createElement("td");
            var tdy = document.createElement("td");
            var tdz = document.createElement("td");
            //первый
            tdx.innerHTML = name;
            //второй
            var input = document.createElement("input");
            input.type = "number";
            input.name = "forTheme:"+name;
            input.value = 0;
            tdy.appendChild(input);
            //третье <- ДОБАВИТЬ УДАЛЕНИЕ
            var button = document.createElement("button");
            button.type = "button";
            button.onclick = function (){
                    removeTheme(name);
            };
            button.innerHTML = "X";
            tdz.appendChild(button);

            tr.appendChild(tdx);
            tr.appendChild(tdy);
            tr.appendChild(tdz);
            div.appendChild(tr);
        }



        function addThemes(){
                            console.log("Добавление тем");
                            var container = document.getElementById("content");
                            var modalContainer = document.getElementById("modalChecks");
                            //перебор модалки
                            for(var modalNode=modalContainer.firstChild; modalNode!==null; modalNode=modalNode.nextSibling) {
                                 //проверка на присутствие
                                 if (modalNode.type == "checkbox") {
                                    let flag = 0;
                                    for(var node=container.firstChild; node!==null; node=node.nextSibling) {
                                        if (node.id == modalNode.name){
                                                flag=1;
                                                break;
                                        }
                                    }
                                    if (modalNode.checked){
                                        if (flag==0){
                                               createTable(modalNode.name);
                                        }
                                        modalNode.checked = false;
                                    }

                                 }
                            }
                            document.location.href = "#head";
                        }

                function removeTheme(name){
                    console.log("delete : " + name);
                    var container = document.getElementById("content");
                    for(var node=container.firstChild; node!==null; node=node.nextSibling) {
                        if (node.id == name){
                            container.removeChild(node);
                        }
                    }
                }
    </script>
</head>
<body>
<h2>Работа с планом тестирования</h2>
<form action = "/blank" method="post">
    <input type="hidden" name = "firstName" value="${blank.getName()}">
    Название:
    <input type="text" name = "newName" value="${blank.getName()}">
    <c:if test = "${exist == true}" >
              Данное имя темы уже существует
    </c:if>
    <br>
    Начало:
    <input type="datetime-local" name = "start" value="${blank.getISOStart()}">
    <br>
    Конец:
    <input type="datetime-local" name = "end" value="${blank.getISOEnd()}">
    <br>
    Длительность:
    <input type ="number" name="duration" value="${blank.getDuration()}">
    <br>
    <h3>Темы в плане тестирования:</h3>
    <div id="content">
         <c:forEach items="${blank.getThemeMap().entrySet()}" var = "entry">
                <div id="${entry.getKey()}">
                     ${entry.getKey()}
                     <input type ="number" name="forTheme:${entry.getKey()}" value="${entry.getValue()}">
                     <button type="button" onclick="removeTheme('${entry.getKey()}')">X</button>
                </div>
          </c:forEach>
    </div>
    <button type = "button" onclick="window.location.href = '#modal'">+</button>
    <br>
    <br>
    <input type="submit" value="Сохранить изменения">
    <br>
</form>
   <form action="/blanks" method="get">
       <input type="submit" value="<---">
   </form>
    <div id="modal" class="modalForm" id = "mainModal">
        <div class="modalBody">
            <div class = "modalContent">
                <a href="#head" class="modalClose">X</a>
                <div class="modalTitle">Темы:</div>
                <div id="modalChecks" class="modalText">
                    <c:forEach items="${themes}" var = "theme">
                        ${theme}<input type="checkBox" name="${theme}">
                        <br>
                    </c:forEach>
                <button type="button" onclick="addThemes()">Добавить темы</button>
                </div>
            </div>
        </div>
    </div>
</body>

</html>
