package data.themes;


import data.questions.BaseQuestion;

import java.io.Serializable;
import java.util.ArrayList;

public class Theme implements Serializable {

    private String name;
    private long lastId;
    private ArrayList<BaseQuestion> questions;

    public Theme(){
        lastId = 0;
        questions = new ArrayList<>();
    }

    public long getLastId() {
        return lastId;
    }

    public void setLastId(long lastId) {
        this.lastId = lastId;
    }

    public ArrayList<BaseQuestion> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<BaseQuestion> questions) {
        this.questions = questions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public BaseQuestion getQuestionByIndex(String index){
        for (BaseQuestion question : questions){
            if (question.getIndex().equals(index)){
                return question;
            }
        }
        return null;
    }
}
