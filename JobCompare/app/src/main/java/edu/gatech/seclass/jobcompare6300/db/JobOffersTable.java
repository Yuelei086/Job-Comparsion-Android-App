package edu.gatech.seclass.jobcompare6300.db;

import android.provider.BaseColumns;

public class JobOffersTable {
    public JobOffersTable() {}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "JobOffersTable";
        public static final String COLUMN_NAME_title = "title";
        public static final String COLUMN_NAME_company = "company";
        public static final String COLUMN_NAME_city = "city";
        public static final String COLUMN_NAME_state = "state";
        public static final String COLUMN_NAME_costOfLiving = "cost_of_living";
        public static final String COLUMN_NAME_yearlySalary = "yearly_salary";
        public static final String COLUMN_NAME_yearlyBonus = "yearly_bonus";
        public static final String COLUMN_NAME_teleworkDays = "telework_days";
        public static final String COLUMN_NAME_leaveTime = "leave_time";
        public static final String COLUMN_NAME_gymAllowance = "gym_allowance";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + FeedEntry.TABLE_NAME + " (" +
                    FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_title + " TEXT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_company + " TEXT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_city + " TEXT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_state + " TEXT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_costOfLiving + " FLOAT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_yearlySalary + " FLOAT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_yearlyBonus + " FLOAT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_teleworkDays + " INT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_leaveTime + " INT," +
                    CurrentJobTable.FeedEntry.COLUMN_NAME_gymAllowance + " FLOAT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}
