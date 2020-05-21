package com.gms.model.member;

public class Abs_Workout extends  Workout {

    private int ideal_reps;
    private int ideal_sets;

    public Abs_Workout(){

    }
    public Abs_Workout(int ideal_reps, int ideal_sets) {
        this.ideal_reps = ideal_reps;
        this.ideal_sets = ideal_sets;
    }

    public Abs_Workout(int ideal_reps) {
        this.ideal_reps = ideal_reps;
    }

    public Abs_Workout(int workout_id, String workout_type, String workout, int ideal_reps) {
        super(workout_id, workout_type, workout);
        this.ideal_reps = ideal_reps;
    }

    public int getIdeal_reps() {
        return ideal_reps;
    }

    public void setIdeal_reps(int ideal_reps) {
        this.ideal_reps = ideal_reps;
    }

    public int getIdeal_sets() {
        return ideal_sets;
    }

    public void setIdeal_sets(int ideal_sets) {
        this.ideal_sets = ideal_sets;
    }

    @Override
    public String toString() {
        String setreps = generateSetRepString(ideal_sets,ideal_reps);
        return super.getWorkout() + " "+ setreps;
    }
    private String generateSetRepString(int ideal_sets,int ideal_reps){
        String generatedString= "";
        for(int i=1; i<ideal_sets; i++){
            generatedString += ideal_reps-2;
            if(i<ideal_sets-1){
                generatedString += ",";
            }
        }
        return  generatedString;
    }
}
