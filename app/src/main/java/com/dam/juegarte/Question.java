package com.dam.juegarte;

public class Question {

    private String questionText;
    private String questionInformation;
    private String questionImage;


    public Question(String questionText, String questionInformation, String questionImage) {
        this.questionText = questionText;
        this.questionInformation = questionInformation;
        this.questionImage = questionImage;
    }


    public String getQuestionText() {
        return questionText;
    }

    public void setQuestionText(String questionText) {
        this.questionText = questionText;
    }

    public String getQuestionInformation() {
        return questionInformation;
    }

    public void setQuestionInformation(String questionInformation) {
        this.questionInformation = questionInformation;
    }

    public String getQuestionImage() {
        return questionImage;
    }

    public void setQuestionImage(String questionImage) {
        this.questionImage = questionImage;
    }
}
