package com.gms.model.member;

public class Difficulty {

    private int id;
    private String difficulty;

    public Difficulty(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Difficulty(int id, String difficulty) {
        this.id = id;
        this.difficulty = difficulty;
    }

    public String getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(String difficulty) {
        this.difficulty = difficulty;
    }

    @Override
    public String toString() {
        return difficulty;
    }
}
