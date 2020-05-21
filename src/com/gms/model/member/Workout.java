package com.gms.model.member;

public class Workout {

    private int workout_id;
    private String workout_type;
    private String workout;

    public Workout(){

    }
    public Workout(int workout_id, String workout_type, String workout) {
        this.workout_id = workout_id;
        this.workout_type = workout_type;
        this.workout = workout;
    }

    public int getWorkout_id() {
        return workout_id;
    }

    public void setWorkout_id(int workout_id) {
        this.workout_id = workout_id;
    }

    public String getWorkout_type() {
        return workout_type;
    }

    public void setWorkout_type(String workout_type) {
        this.workout_type = workout_type;
    }

    public String getWorkout() {
        return workout;
    }

    public void setWorkout(String workout) {
        this.workout = workout;
    }
}
