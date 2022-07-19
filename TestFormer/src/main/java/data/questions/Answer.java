package data.questions;

import java.io.Serializable;

public class Answer implements Serializable {
    private String answer;
    private boolean success;
    private int id;

    public Answer(){
        answer = "";
        success = false;
    }

    public Answer(String ans,boolean succ,int id){
        this.id = id;
        answer = ans;
        success = succ;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public String getAnswer() {
        return answer;
    }

    public void setAnswer(String answer) {
        this.answer = answer;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }
}
