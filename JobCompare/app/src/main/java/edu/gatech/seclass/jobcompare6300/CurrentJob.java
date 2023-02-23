package edu.gatech.seclass.jobcompare6300;

public class CurrentJob extends Job {
    public CurrentJob(String title, String company, String city, String state, double costOfLivingIndex, double yearlySalary, double yearlyBonus, int weeklyTelework, int leaveTime, double gymAllowance) {
        super(title, company, city, state, costOfLivingIndex, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance);
    }

    public boolean isCurrentJob() {
        return true;
    }

    public void updateCurrentJob(String title, String company, String city, String state, double costOfLivingIndex, double yearlySalary, double yearlyBonus, int weeklyTelework, int leaveTime, double gymAllowance) {
        setValues(title, company, city, state, costOfLivingIndex, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance);
    }
}
