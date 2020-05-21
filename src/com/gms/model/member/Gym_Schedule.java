package com.gms.model.member;

import javafx.collections.ObservableList;

import java.sql.Date;
import java.util.ArrayList;

public class Gym_Schedule {

    private ObservableList<Workout> workouts;
    private int day_no;
    private Difficulty difficulty;
    private int completedStatus;
    private Date completedDate;

    public Gym_Schedule() {
    }

    public Gym_Schedule(ObservableList<Workout> workouts, int day_no, Difficulty difficulty, int completedStatus, Date completedDate) {
        this.workouts = workouts;
        this.day_no = day_no;
        this.difficulty = difficulty;
        this.completedStatus = completedStatus;
        this.completedDate = completedDate;
    }

    public ObservableList<Workout> getWorkouts() {
        return workouts;
    }

    public void setWorkouts(ObservableList<Workout> workouts) {
        this.workouts = workouts;
    }

    public int getDay_no() {
        return day_no;
    }

    public void setDay_no(int day_no) {
        this.day_no = day_no;
    }

    public Difficulty getDifficulty() {
        return difficulty;
    }

    public void setDifficulty(Difficulty difficulty) {
        this.difficulty = difficulty;
    }

    public int getCompletedStatus() {
        return completedStatus;
    }

    public void setCompletedStatus(int completedStatus) {
        this.completedStatus = completedStatus;
    }

    public Date getCompletedDate() {
        return completedDate;
    }

    public void setCompletedDate(Date completedDate) {
        this.completedDate = completedDate;
    }
}
