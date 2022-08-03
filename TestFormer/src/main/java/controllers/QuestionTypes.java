package controllers;

public enum QuestionTypes {

    BASE("baseQuestion","Базовый вопрос","questions/baseQuestion.jsp");


    public String name;
    public String userName;
    public String jspPath;
    QuestionTypes(String name, String userName, String jspPath){
        this.name = name;
        this.userName = userName;
        this.jspPath = jspPath;
    }

}
