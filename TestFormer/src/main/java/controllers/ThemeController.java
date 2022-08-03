package controllers;

import data.questions.Question;
import data.questions.baseQuestion.BaseQuestion;
import data.Theme;
import fileworkers.ThemesSaverLoader;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class ThemeController {


    public static String[] getNames() {
        try {
            return ThemesSaverLoader.getAllThemes().stream().map(e->e.getName()).collect(Collectors.toList()).toArray(new String[]{});
        } catch (IOException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }

    public static boolean isExist(String name){
        String[] array = getNames();
        if (array == null){
            return false;
        }
        List<String> names = Arrays.asList(array);
        return names.contains(name);
    }

    public static Theme getTheme(String name) {
        return ThemesSaverLoader.getTheme(name);
    }

    public static void delTheme(String name) {
        ThemesSaverLoader.delTheme(name);
    }

    public static Theme newTheme(String name){
            Theme theme = ThemesSaverLoader.addTheme(name);
            return theme;
    }

    public static void renameTheme(String first,String second){
        Theme theme = ThemesSaverLoader.getTheme(first);
        ThemesSaverLoader.delTheme(first);
        theme.setName(second);
        ThemesSaverLoader.addTheme(theme);
    }
    public static BaseQuestion[] getAllQuestion(String name){
        Theme theme = getTheme(name);
        BaseQuestion[] questions = theme.getQuestions().toArray(new BaseQuestion[]{});
        return questions;
    }

    public static Question getQuestion(String name,long id){
        Question[] q = getAllQuestion(name);
        for (Question question : q){
            if (question.getId() == id){
                return question;
            }
        }
        return null;
    }

    public static void delQuestion(String name, long id){
        Theme theme = getTheme(name);
        theme.getQuestions().remove(theme.getQuestionById(id));
        ThemesSaverLoader.saveTheme(theme);
    }

    public static void addQuestionNoId(String name,Question question){
        Theme theme = getTheme(name);
        question.setId(theme.getLastId());
        theme.getQuestions().add(question);
        ThemesSaverLoader.saveTheme(theme);
    }

    public static void replaceQuestion(String name, Question question) {
        Theme theme = getTheme(name);
        theme.getQuestions().remove(theme.getQuestionById(question.getId()));
        theme.getQuestions().add(question);
        ThemesSaverLoader.saveTheme(theme);
    }
}
