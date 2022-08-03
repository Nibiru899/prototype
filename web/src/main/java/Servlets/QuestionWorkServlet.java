package Servlets;

import controllers.QuestionController;
import data.questions.Question;
import controllers.ThemeController;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

public class QuestionWorkServlet extends HttpServlet {

    public void init(ServletConfig servletConfig) {

        try {
            super.init(servletConfig);
        } catch (ServletException e) {
            e.printStackTrace();
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        resp.setCharacterEncoding("UTF-8");
        String name  = req.getParameter("name");
        String id = req.getParameter("id");

        Question question = ThemeController.getQuestion(name, Long.parseLong(id));
        goBack(req,resp,question,name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");
        String type = req.getParameter("type");
        String name  = getStr(req.getParameter("name"));
        Question question = null;
        switch (type){
            case "base": {
                question = QuestionController.baseQuestionWork(req);
                break;
            }
        }
        if (question!=null){
            ThemeController.replaceQuestion(name,question);
        }
        goBack(req,resp,question,name);
    }

    /**
     * Возвращение к редактированию вопроса
     * @param question - вопрос
     * @param name - имя темы
     */
    private void goBack(HttpServletRequest req, HttpServletResponse resp, Question question, String name) throws ServletException, IOException {
        req.setAttribute("question",question);
        req.setAttribute("themeName",name);
        RequestDispatcher dispatcher = req.getRequestDispatcher(question.getType().jspPath);
        dispatcher.forward(req,resp);
    }

    /**
     * Костыль! фикс кодировки
     */
    private static String getStr(String str){
        try {
            return new String(str.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }


}

