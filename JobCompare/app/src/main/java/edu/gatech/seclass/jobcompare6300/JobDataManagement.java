package edu.gatech.seclass.jobcompare6300;

import java.util.ArrayList;

import edu.gatech.seclass.jobcompare6300.db.CurrentJobTable;
import edu.gatech.seclass.jobcompare6300.db.DbHelper;
import edu.gatech.seclass.jobcompare6300.db.JobOffersTable;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.provider.BaseColumns;


public class JobDataManagement {
    private CurrentJob currentJob;

    private ArrayList<Job> jobOffers = new ArrayList<>();
    private static final JobDataManagement jdm = new JobDataManagement();

    public static JobDataManagement getInstance() {
        return jdm;
    }

    public CurrentJob getCurrentJob(Context dbHelper) {
        if (currentJob != null) {
            return currentJob;
        }
        DbHelper helper = new DbHelper(dbHelper);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query(
                CurrentJobTable.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        if (cursor.moveToFirst()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_title));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_company));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_city));
            String state = cursor.getString(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_state));
            double costOfLivingIndex = cursor.getDouble(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_costOfLiving));
            double yearlySalary = cursor.getDouble(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_yearlySalary));
            double yearlyBonus = cursor.getDouble(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_yearlyBonus));
            int weeklyTelework = cursor.getInt(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_teleworkDays));
            int leaveTime = cursor.getInt(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_leaveTime));
            double gymAllowance = cursor.getDouble(cursor.getColumnIndexOrThrow(CurrentJobTable.FeedEntry.COLUMN_NAME_gymAllowance));
            currentJob = new CurrentJob(title, company, city, state, costOfLivingIndex, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance);
        }
        return currentJob;
    }

    public ArrayList<Job> getJobOffers(Context dbHelper) {
        if (jobOffers.size() > 0) {
            return jobOffers;
        }
        DbHelper helper = new DbHelper(dbHelper);
        SQLiteDatabase db = helper.getWritableDatabase();

        Cursor cursor = db.query(
                JobOffersTable.FeedEntry.TABLE_NAME,
                null,
                null,
                null,
                null,
                null,
                null
        );

        while(cursor.moveToNext()) {
            String title = cursor.getString(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_title));
            String company = cursor.getString(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_company));
            String city = cursor.getString(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_city));
            String state = cursor.getString(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_state));
            double costOfLivingIndex = cursor.getDouble(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_costOfLiving));
            double yearlySalary = cursor.getDouble(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_yearlySalary));
            double yearlyBonus = cursor.getDouble(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_yearlyBonus));
            int weeklyTelework = cursor.getInt(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_teleworkDays));
            int leaveTime = cursor.getInt(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_leaveTime));
            double gymAllowance = cursor.getDouble(cursor.getColumnIndexOrThrow(JobOffersTable.FeedEntry.COLUMN_NAME_gymAllowance));

            Job offer = new Job(title, company, city, state, costOfLivingIndex, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance);
            jobOffers.add(offer);
        }

        return jobOffers;
    }

    public void saveCurrentJob(Context dbHelper) {
        DbHelper helper = new DbHelper(dbHelper);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_title, currentJob.getTitle());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_company, currentJob.getCompany());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_city, currentJob.getCity());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_state, currentJob.getState());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_costOfLiving, currentJob.getCostOfLivingIndex());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_yearlySalary, currentJob.getYearlySalary());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_yearlyBonus, currentJob.getYearlyBonus());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_teleworkDays, currentJob.getWeeklyTelework());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_leaveTime, currentJob.getLeaveTime());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_gymAllowance, currentJob.getGymAllowance());
        String where = "_id=?";
        String[] whereArgs = new String[] {String.valueOf(1)};
        db.update(CurrentJobTable.FeedEntry.TABLE_NAME, values, where, whereArgs);
    }

    public CurrentJob setCurrentJob(CurrentJob currentJob, Context dbHelper) {
        this.currentJob = currentJob;
        DbHelper helper = new DbHelper(dbHelper);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_title, currentJob.getTitle());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_company, currentJob.getCompany());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_city, currentJob.getCity());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_state, currentJob.getState());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_costOfLiving, currentJob.getCostOfLivingIndex());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_yearlySalary, currentJob.getYearlySalary());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_yearlyBonus, currentJob.getYearlyBonus());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_teleworkDays, currentJob.getWeeklyTelework());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_leaveTime, currentJob.getLeaveTime());
        values.put(CurrentJobTable.FeedEntry.COLUMN_NAME_gymAllowance, currentJob.getGymAllowance());

        db.insert(CurrentJobTable.FeedEntry.TABLE_NAME, null, values);
        return currentJob;
    }

    public void addJobOffer(Job newJob, Context dbHelper) {
        jobOffers.add(newJob);
        DbHelper helper = new DbHelper(dbHelper);
        SQLiteDatabase db = helper.getWritableDatabase();

        ContentValues values = new ContentValues();
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_title, newJob.getTitle());
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_company, newJob.getCompany());
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_city, newJob.getCity());
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_state, newJob.getState());
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_costOfLiving, newJob.getCostOfLivingIndex());
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_yearlySalary, newJob.getYearlySalary());
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_yearlyBonus, newJob.getYearlyBonus());

        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_teleworkDays, newJob.getWeeklyTelework());
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_leaveTime, newJob.getLeaveTime());
        values.put(JobOffersTable.FeedEntry.COLUMN_NAME_gymAllowance, newJob.getGymAllowance());

        db.insert(JobOffersTable.FeedEntry.TABLE_NAME, null, values);

    }

}
