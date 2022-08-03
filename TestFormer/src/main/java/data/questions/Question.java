package data.questions;

import controllers.QuestionTypes;

import java.io.Serializable;

/**
 * Абстрактный класс вопроса, содержит базовые поля и методы работы с ними.
 */
public abstract class Question implements Serializable {
    /**
     * ID вопроса в рамках одной темы
     */
    private long id;
    /**
     * Текст вопроса
     */
    private String question;

    private static QuestionTypes type;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getQuestion() {
        return question;
    }

    public void setQuestion(String question) {
        this.question = question;
    }


    public static void setType(QuestionTypes type) {
        Question.type = type;
    }

    public static QuestionTypes getType() {
        return type;
    }
}
