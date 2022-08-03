package data;

import data.questions.Question;

import java.io.Serializable;
import java.util.ArrayList;

public class Theme implements Serializable {

    private String name;
    private long lastId;
    private ArrayList<Question> questions;

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

    public ArrayList<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(ArrayList<Question> questions) {
        this.questions = questions;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public Question getQuestionById(long id){
        for (Question question : questions){
            if (question.getId() == id){
                return question;
            }
        }
        return null;
    }
}
