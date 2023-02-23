package edu.gatech.seclass.jobcompare6300;

import android.content.Context;
import android.util.Log;
import java.lang.Double;

public class RankedJob {
        //implements Comparable<RankedJob>


    private String displayname;
    private Job job;
    private boolean selected;
    public double score;
    public boolean currentjob;
    ComparisonSetting comp;


    public RankedJob(Job theJob,boolean currentJob, Context context) {
        comp = ComparisonSetting.getInstance(context);

        this.job = theJob;
        this.currentjob=currentJob;
        this.displayname=this.job.getTitle()+" | "+this.job.getCompany();
        this.score=getScore(theJob);
        selected = false;
    }

    public String getDisplayName() {
        return displayname;
    }

    public Job getJob() {
        return job;
    }



    public boolean isSelected() {
        return selected;
    }


    public void setSelected(boolean selected) {
        this.selected = selected;

    }

    public double getScore(Job theJob){

        double AYS=theJob.getAdjYearlySalary();
        int WAYS=comp.getYrlySalaryWeight();
        double GYM=theJob.getGymAllowance();
        int WGYM=comp.getGymAllowanceWeight();
        double AYB=theJob.getAdjYearlyBonus();
        int WAYB=comp.getYrlyBonusWeight();
        int RWT=theJob.getWeeklyTelework();
        int WRWT=comp.getWklyTeleworkWeight();
        int LT=theJob.getLeaveTime();
        int WLT=comp.getLvTimeWeight();
        int WSUM=WAYS+WGYM+WAYB+WRWT+WLT;

        double score=WAYS*AYS + WAYB*AYB + WGYM*GYM + WLT*(LT * AYS / 260) - WRWT*((260 - 52 * RWT) * (AYS / 260) / 8);
        score=score/WSUM;
        return score;

    }


    //@Override
    //public int compareTo(RankedJob rankedJob) {
       // Double obj1 = Double.valueOf(this.score);
        //Double obj2 = Double.valueOf(rankedJob.score);
        //return -1 * obj1.compareTo(obj2);
    //}
}
