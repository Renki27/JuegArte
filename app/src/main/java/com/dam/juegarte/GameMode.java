package com.dam.juegarte;


public class GameMode {

    private int idGame;
    private String gameMode;
    private String instructions;
    private int questionTimer;

    public GameMode(int idGame, String gameMode, String instructions, int questionTimer) {
        this.idGame = idGame;
        this.gameMode = gameMode;
        this.instructions = instructions;
        this.questionTimer = questionTimer;
    }

    public int getIdGame() {
        return idGame;
    }

    public void setIdGame(int idGame) {
        this.idGame = idGame;
    }

    public String getGameMode() {
        return gameMode;
    }

    public void setGameMode(String gameMode) {
        this.gameMode = gameMode;
    }

    public String getInstructions() {
        return instructions;
    }

    public void setInstructions(String instructions) {
        this.instructions = instructions;
    }

    public int getQuestionTimer() {
        return questionTimer;
    }

    public void setQuestionTimer(int questionTimer) {
        this.questionTimer = questionTimer;
    }

    @Override
    public String toString() {
        return "GameMode{" +
                "idGame=" + idGame +
                ", gameMode='" + gameMode + '\'' +
                ", instructions='" + instructions + '\'' +
                ", questionTimer=" + questionTimer +
                '}';
    }
}
