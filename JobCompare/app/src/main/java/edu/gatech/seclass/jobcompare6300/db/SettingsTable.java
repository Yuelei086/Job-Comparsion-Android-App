package edu.gatech.seclass.jobcompare6300.db;

import android.provider.BaseColumns;

public class SettingsTable {
    public SettingsTable(){}

    public static class FeedEntry implements BaseColumns {
        public static final String TABLE_NAME = "SettingsTable";
        public static final String COLUMN_NAME_yearlySalaryWeight = "salary_weight";
        public static final String COLUMN_NAME_yearlyBonusWeight = "bonus_weight";
        public static final String COLUMN_NAME_allowedWeeklyTeleworkDaysWeight = "telework_weight";
        public static final String COLUMN_NAME_leaveTimeWeight = "leave_time_weight";
        public static final String COLUMN_NAME_gymMembershipWeight = "gym_weight";
    }

    public static final String SQL_CREATE_ENTRIES =
            "CREATE TABLE IF NOT EXISTS " + FeedEntry.TABLE_NAME + " (" +
                    SettingsTable.FeedEntry._ID + " INTEGER PRIMARY KEY," +
                    SettingsTable.FeedEntry.COLUMN_NAME_yearlySalaryWeight + " FLOAT," +
                    SettingsTable.FeedEntry.COLUMN_NAME_yearlyBonusWeight + " FLOAT," +
                    SettingsTable.FeedEntry.COLUMN_NAME_allowedWeeklyTeleworkDaysWeight + " FLOAT," +
                    SettingsTable.FeedEntry.COLUMN_NAME_leaveTimeWeight + " FLOAT," +
                    SettingsTable.FeedEntry.COLUMN_NAME_gymMembershipWeight + " FLOAT)";

    public static final String SQL_DELETE_ENTRIES =
            "DROP TABLE IF EXISTS " + FeedEntry.TABLE_NAME;
}
