package com.londonappbrewery.quizzler;

public class TrueFalse {

    private int questionID;
    private boolean answer;

    public TrueFalse(int questionID, boolean answer) {
        this.questionID = questionID;
        this.answer = answer;
    }

    public int getQuestionID() {
        return questionID;
    }

    public void setQuestionID(int questionID) {
        this.questionID = questionID;
    }

    public boolean isAnswer() {
        return answer;
    }

    public void setAnswer(boolean answer) {
        this.answer = answer;
    }
}
