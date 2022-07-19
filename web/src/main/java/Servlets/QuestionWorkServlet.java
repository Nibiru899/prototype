package Servlets;

import data.Answer;
import data.BaseQuestion;
import data.Theme;
import controllers.ThemeController;
import fileworkers.ThemesSaverLoader;

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
        String indx = req.getParameter("index");

        BaseQuestion question = ThemeController.getQuestion(name, indx);
        goBack(req,resp,question,name);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws IOException, ServletException {
        resp.setCharacterEncoding("UTF-8");

        String name  = getStr(req.getParameter("name"));
        String indx = req.getParameter("index");
        Theme theme = ThemeController.getTheme(name);
        BaseQuestion question = theme.getQuestionByIndex(indx);
        int last = Integer.parseInt(req.getParameter("lastIdElement"));
        question.getAnswers().clear();
        for (int i = 0; i < last;i++){
            String ans = req.getParameter("ans"+i);
            if (ans!= null){
                String check = req.getParameter("check"+i);
                Answer answer = new Answer();
                answer.setId(i);
                answer.setAnswer(getStr(ans));
                answer.setSuccess(check != null);
                question.getAnswers().add(answer);
            }
        }
        //обновление id, чтобы не перегружать сервер
        for (int i = 0;i<question.getAnswers().size();i++){
            question.getAnswers().get(i).setId(i);
        }
        //
        question.setLastId(question.getAnswers().size());

        String quest = getStr(req.getParameter("quest"));
        question.setQuestion(quest);
        ThemesSaverLoader.saveTheme(theme);

        goBack(req,resp,question,name);
    }

    private void goBack(HttpServletRequest req, HttpServletResponse resp, BaseQuestion question, String name) throws ServletException, IOException {
        req.setAttribute("question",question);
        req.setAttribute("themeName",name);
        RequestDispatcher dispatcher = req.getRequestDispatcher("questionWork.jsp");
        dispatcher.forward(req,resp);
    }

    private  String getStr(String str) throws UnsupportedEncodingException {
        return new String(str.getBytes("ISO-8859-1"));
    }


}

