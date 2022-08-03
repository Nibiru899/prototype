package controllers;

import data.Theme;
import data.questions.Question;
import data.questions.baseQuestion.Answer;
import data.questions.baseQuestion.BaseQuestion;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.UnsupportedEncodingException;

/**
 * Обработка параметров запроса для типов вопросов
 */
public class QuestionController {

    /**
     * Обработка базового вопроса
     */
    public static Question baseQuestionWork(HttpServletRequest req){
        String name  = getStr(req.getParameter("name"));
        Theme theme = ThemeController.getTheme(name);

        String strId = req.getParameter("id");
        long id = Long.parseLong(strId);

        BaseQuestion question = new BaseQuestion();
        question.setId(id);

        int last = Integer.parseInt(req.getParameter("lastIdElement"));

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
        String quest = getStr(req.getParameter("quest"));
        question.setQuestion(quest);
        return question;
    }

    private static String getStr(String str){
        try {
            return new String(str.getBytes("ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
            return null;
        }
    }
}
