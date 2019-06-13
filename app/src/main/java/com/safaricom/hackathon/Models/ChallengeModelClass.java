package com.safaricom.hackathon.Models;

public class ChallengeModelClass {
    private int week;
    private int depositAmount;
    private int totalAmount;

    public ChallengeModelClass(int week, int depositAmount, int totalAmount) {
        this.week = week;
        this.depositAmount = depositAmount;
        this.totalAmount = totalAmount;
    }

    public int getWeek() {
        return week;
    }

    public int getDepositAmount() {
        return depositAmount;
    }

    public int getTotalAmount() {
        return totalAmount;
    }
}
