package com.gms.model.member;

public class Gym_Workout extends  Workout{
    private String target_muscle;
    private int ideal_reps;
    private int ideal_sets;
    private int ideal_weight;

    public Gym_Workout(String target_muscle, int ideal_reps, int ideal_sets, int ideal_weight) {
        this.target_muscle = target_muscle;
        this.ideal_reps = ideal_reps;
        this.ideal_sets = ideal_sets;
        this.ideal_weight = ideal_weight;
    }

    public Gym_Workout(){

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

    public int getIdeal_weight() {
        return ideal_weight;
    }

    public void setIdeal_weight(int ideal_weight) {
        this.ideal_weight = ideal_weight;
    }

    public Gym_Workout(int workout_id, String workout_type, String workout, String target_muscle, int ideal_reps, int ideal_sets, int ideal_weight) {
        super(workout_id, workout_type, workout);
        this.target_muscle = target_muscle;
        this.ideal_reps = ideal_reps;
        this.ideal_sets = ideal_sets;
        this.ideal_weight = ideal_weight;
    }

    public Gym_Workout(String target_muscle) {
        this.target_muscle = target_muscle;
    }

    public Gym_Workout(int workout_id, String workout_type, String workout, String target_muscle) {
        super(workout_id, workout_type, workout);
        this.target_muscle = target_muscle;
    }

    public String getTarget_muscle() {
        return target_muscle;
    }

    public void setTarget_muscle(String target_muscle) {
        this.target_muscle = target_muscle;
    }

    @Override
    public String toString() {
        String setreps = generateSetRepString(ideal_sets,ideal_reps);
        return "("+target_muscle + ") "+super.getWorkout() +
                ", " + setreps + ", "+ ideal_weight +"kg"
                ;
    }
    private String generateSetRepString(int ideal_sets,int ideal_reps){
        String generatedString= "";
        for(int i=1; i<=ideal_sets; i++){
            generatedString += (ideal_reps);
            ideal_reps -= 2;
            if(i<ideal_sets){
                generatedString += "X";
            }
        }
        return  generatedString;
    }
}
