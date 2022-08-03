package data.questions.baseQuestion;

import controllers.QuestionTypes;
import data.questions.Question;

import java.util.ArrayList;

/**
 * Обычный вопрос с выбором правильных вариантов ответа из предложенных
 */
public class BaseQuestion extends Question {
    ArrayList<Answer> answers;

    public BaseQuestion(){
        answers = new ArrayList<>();
        setType(QuestionTypes.BASE);
    }

    public ArrayList<Answer> getAnswers() {
        return answers;
    }

    public void setAnswers(ArrayList<Answer> answers) {
        this.answers = answers;
    }

}
