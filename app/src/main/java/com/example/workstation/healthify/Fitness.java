package com.example.workstation.healthify;

public class Fitness {

    private String Uid;
    private String StepCount;
    private String KmWalked;

    public Fitness() {
    }

    public Fitness(String uid, String stepCount, String kmWalked) {
        Uid = uid;
        StepCount = stepCount;
        KmWalked = kmWalked;
    }

    public String getUid() {
        return Uid;
    }

    public void setUid(String uid) {
        Uid = uid;
    }

    public String getStepCount() {
        return StepCount;
    }

    public void setStepCount(String stepCount) {
        StepCount = stepCount;
    }

    public String getKmWalked() {
        return KmWalked;
    }

    public void setKmWalked(String kmWalked) {
        KmWalked = kmWalked;
    }
}
