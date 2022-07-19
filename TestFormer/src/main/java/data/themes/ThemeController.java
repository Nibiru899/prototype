package data.themes;

import data.questions.BaseQuestion;

import java.io.IOException;
import java.util.ArrayList;
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
        List<String> names = Arrays.asList(getNames());
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

    public static BaseQuestion getQuestion(String name,String index){
        BaseQuestion[] bc = getAllQuestion(name);
        for (BaseQuestion question : bc){
            if (question.getIndex().equals(index)){
                return question;
            }
        }
        return null;
    }

    public static void delQuestion(String name,String index){
        Theme theme = getTheme(name);
        for (BaseQuestion question : theme.getQuestions()){
            if (question.getIndex().equals(index)){
                theme.getQuestions().remove(question);
                break;
            }
        }
        ThemesSaverLoader.saveTheme(theme);
    }

    public static void setQuestions(String name, BaseQuestion[] questions){
        Theme theme = ThemesSaverLoader.getTheme(name);
        theme.setQuestions(new ArrayList<BaseQuestion>(Arrays.asList(questions)));
    }

    public static void addQuestion(String name) throws IOException {
        Theme theme = ThemesSaverLoader.getTheme(name);
        List<BaseQuestion>  questions = theme.getQuestions();
        BaseQuestion question = new BaseQuestion();
        question.setQuestion("Новый вопрос");
        question.setIndex(String.valueOf(theme.getLastId()));
        theme.setLastId(theme.getLastId()+1);
        questions.add(question);
        ThemesSaverLoader.saveTheme(theme);
    }
}
