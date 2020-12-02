package com.dam.juegarte;

public class Achievements {

    private int id;
    private String title;
    private String description;
    private int completedStep;

    public Achievements() {
    }

    public Achievements(int id, String title, String description, int completedStep) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.completedStep = completedStep;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getCompletedStep() {
        return completedStep;
    }

    public void setCompletedStep(int completedStep) {
        this.completedStep = completedStep;
    }
}

