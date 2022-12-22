package data;

import data.Answer;

import java.io.Serializable;
import java.util.ArrayList;

public class BaseQuestion implements Serializable {
    String question;
    String index;
    int lastId;

    ArrayList<Answer> answers;


    public BaseQuestion(){
        answers = new ArrayList<>();
        lastId = 0;
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

    public int getLastId() {
        return lastId;
    }

    public void setLastId(int lastId) {
        this.lastId = lastId;
    }

    public void setQuestion(String question){
        this.question = question;
    }

    public String getQuestion() {
        return question;
    }

    public void setIndex(String index) {
        this.index = index;
    }

    public String getIndex() {
        return index;
    }
}
