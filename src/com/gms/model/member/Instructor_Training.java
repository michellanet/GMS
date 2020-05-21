package com.gms.model.member;

import com.gms.model.trainer.Trainer;

import java.sql.Date;

public class Instructor_Training {
    Trainer trainer;
    Date training_begin;

    public Instructor_Training(Trainer trainer, Date training_begin) {
        this.trainer = trainer;
        this.training_begin = training_begin;
    }

    public Trainer getTrainer() {
        return trainer;
    }

    public void setTrainer(Trainer trainer) {
        this.trainer = trainer;
    }

    public Date getTraining_begin() {
        return training_begin;
    }

    public void setTraining_begin(Date training_begin) {
        this.training_begin = training_begin;
    }
}
