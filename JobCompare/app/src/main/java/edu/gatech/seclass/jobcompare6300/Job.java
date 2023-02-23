package edu.gatech.seclass.jobcompare6300;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class Job implements Serializable {
    private String title, company, city, state;
    private int weeklyTelework, leaveTime;
    private double costOfLivingIndex, yearlySalary, yearlyBonus, gymAllowance;

    public Job(String title, String company, String city, String state, double costOfLivingIndex, double yearlySalary, double yearlyBonus, int weeklyTelework, int leaveTime, double gymAllowance) {
        setValues(title, company, city, state, costOfLivingIndex, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance);
    }

    protected void setValues(String title, String company, String city, String state, double costOfLivingIndex, double yearlySalary, double yearlyBonus, int weeklyTelework, int leaveTime, double gymAllowance) {
        this.title = title;
        this.company = company;
        this.city = city;
        this.state = state;
        this.costOfLivingIndex = costOfLivingIndex;
        this.yearlySalary = yearlySalary;
        this.yearlyBonus = yearlyBonus;
        this.weeklyTelework = weeklyTelework;
        this.leaveTime = leaveTime;
        this.gymAllowance = gymAllowance;
    }

    public String getTitle() {
        return title;
    }

    public String getCompany() {
        return company;
    }

    public String getCity() {
        return city;
    }

    public String getState() {
        return state;
    }

    @NonNull
    public String formattedLocation() {
        String c = (city != null) ? city : "";
        String s = (state != null) ? state : "";
        return c + ", " + s;
    }


    public double getCostOfLivingIndex() {
        return costOfLivingIndex;
    }


    public int getWeeklyTelework() {
        return weeklyTelework;
    }

    public int getLeaveTime() {
        return leaveTime;
    }

    public double getYearlySalary() {
        return yearlySalary;
    }

    public double getAdjYearlySalary() {
        return yearlySalary/costOfLivingIndex;
    }

    public double getYearlyBonus() {
        return yearlyBonus;
    }

    public double getAdjYearlyBonus() {
        return yearlyBonus/costOfLivingIndex;
    }

    public double getGymAllowance() {
        return gymAllowance;
    }

    public boolean isCurrentJob() {
        return false;
    }
}
