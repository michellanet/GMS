package com.gms.model.member;

public class Cardio_Workout extends Workout {


    public Cardio_Workout(){

    }
    private int workoutLength; //in min

    public Cardio_Workout(int workoutLength) {
        this.workoutLength = workoutLength;
    }

    public Cardio_Workout(int workout_id, String workout_type, String workout, int workoutLength) {
        super(workout_id, workout_type, workout);
        this.workoutLength = workoutLength;
    }

    public int getWorkoutLength() {
        return workoutLength;
    }

    public void setWorkoutLength(int workoutLength) {
        this.workoutLength = workoutLength;
    }

    @Override
    public String toString() {
        return super.getWorkout() +" " + this.workoutLength +" mins";
    }
}
