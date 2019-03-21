package com.example.lamdonguyenbao.tablayout.model;

public class History {
    private String name,treatment,medicaltest,drug,time,reexamination;

    public History(String name, String treatment, String medicaltest, String drug, String time, String reexamination) {
        this.name = name;
        this.treatment = treatment;
        this.medicaltest = medicaltest;
        this.drug = drug;
        this.time = time;
        this.reexamination = reexamination;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTreatment() {
        return treatment;
    }

    public void setTreatment(String treatment) {
        this.treatment = treatment;
    }

    public String getMedicaltest() {
        return medicaltest;
    }

    public void setMedicaltest(String medicaltest) {
        this.medicaltest = medicaltest;
    }

    public String getDrug() {
        return drug;
    }

    public void setDrug(String drug) {
        this.drug = drug;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getReexamination() {
        return reexamination;
    }

    public void setReexamination(String reexamination) {
        this.reexamination = reexamination;
    }
}
