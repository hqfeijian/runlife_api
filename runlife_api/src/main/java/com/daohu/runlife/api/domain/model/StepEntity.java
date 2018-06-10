package com.daohu.runlife.api.domain.model;

public class StepEntity {
    private int step; //步数
    private float stepCoin; //大约可兑换step数量

    public int getStep() {
        return step;
    }

    public void setStep(int step) {
        this.step = step;
    }

    public float getAboutStep() {
        return stepCoin;
    }

    public void setAboutStep(float stepCoin) {
        this.stepCoin = stepCoin;
    }
}
