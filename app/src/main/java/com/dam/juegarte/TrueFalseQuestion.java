package com.dam.juegarte;

public class TrueFalseQuestion extends Question {

    private String tf_answer;

    public TrueFalseQuestion(String questionText, String questionInformation, String questionImage, String tf_answer) {
        super(questionText, questionInformation, questionImage);
        this.tf_answer = tf_answer;
    }

    public String getTf_answer() {
        return tf_answer;
    }

    public void setTf_answer(String tf_answer) {
        this.tf_answer = tf_answer;
    }
}
