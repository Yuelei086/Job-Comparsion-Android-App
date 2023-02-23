package edu.gatech.seclass.jobcompare6300;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import androidx.fragment.app.Fragment;

import edu.gatech.seclass.jobcompare6300.db.CurrentJobTable;
import edu.gatech.seclass.jobcompare6300.db.DbHelper;
import edu.gatech.seclass.jobcompare6300.db.SettingsTable;

public class ComparisonSetting extends Fragment {
    private static int yrlySalaryWeight = -1;
    private static int yrlyBonusWeight = -1;
    private static int wklyTeleworkWeight = -1;
    private static int lvTimeWeight = -1;
    private static int gymAllowanceWeight = -1;

    public ComparisonSetting() {
        // Required empty public constructor
    }

    private static final ComparisonSetting comp = new ComparisonSetting();

    public static ComparisonSetting getInstance(Context context) {
        if (yrlySalaryWeight == -1) {
            DbHelper helper = new DbHelper(context);
            SQLiteDatabase db = helper.getWritableDatabase();
            Cursor cursor = db.query(
                    SettingsTable.FeedEntry.TABLE_NAME,
                    null,
                    null,
                    null,
                    null,
                    null,
                    null
            );

            if (cursor.moveToFirst()) {
                yrlySalaryWeight = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsTable.FeedEntry.COLUMN_NAME_yearlySalaryWeight));
                yrlyBonusWeight = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsTable.FeedEntry.COLUMN_NAME_yearlyBonusWeight));
                wklyTeleworkWeight = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsTable.FeedEntry.COLUMN_NAME_allowedWeeklyTeleworkDaysWeight));
                lvTimeWeight = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsTable.FeedEntry.COLUMN_NAME_leaveTimeWeight));
                gymAllowanceWeight = cursor.getInt(cursor.getColumnIndexOrThrow(SettingsTable.FeedEntry.COLUMN_NAME_gymMembershipWeight));
            } else {
                // else use defaults
                saveWeights(1,1,1,1,1, context);
            }
        }
        return comp;
    }

    public int getYrlySalaryWeight() {
        return yrlySalaryWeight;
    }

    public int getYrlyBonusWeight() {
        return yrlyBonusWeight;
    }

    public int getWklyTeleworkWeight() {
        return wklyTeleworkWeight;
    }

    public int getLvTimeWeight() {
        return lvTimeWeight;
    }

    public int getGymAllowanceWeight() {
        return gymAllowanceWeight;
    }

    public static void saveWeights(int yrlySalaryWeightInput,
                            int yrlyBonusWeightInput,
                            int wklyTeleworkWeightInput,
                            int lvTimeWeightInput,
                            int gymAllowanceWeightInput, Context context
    ) {
        DbHelper helper = new DbHelper(context);
        SQLiteDatabase db = helper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(SettingsTable.FeedEntry.COLUMN_NAME_yearlySalaryWeight, yrlySalaryWeightInput);
        values.put(SettingsTable.FeedEntry.COLUMN_NAME_yearlyBonusWeight, yrlyBonusWeightInput);
        values.put(SettingsTable.FeedEntry.COLUMN_NAME_allowedWeeklyTeleworkDaysWeight, wklyTeleworkWeightInput);
        values.put(SettingsTable.FeedEntry.COLUMN_NAME_leaveTimeWeight, lvTimeWeightInput);
        values.put(SettingsTable.FeedEntry.COLUMN_NAME_gymMembershipWeight, gymAllowanceWeightInput);
        // first save, so insert
        if (yrlySalaryWeight == -1) {
            // first save, so insert
            db.insert(SettingsTable.FeedEntry.TABLE_NAME, null, values);
        } else {
            // already exists, so update
            String where = "_id=?";
            String[] whereArgs = new String[] {String.valueOf(1)};
            db.update(SettingsTable.FeedEntry.TABLE_NAME, values, where, whereArgs);
        }

        yrlySalaryWeight = yrlySalaryWeightInput;
        yrlyBonusWeight = yrlyBonusWeightInput;
        wklyTeleworkWeight = wklyTeleworkWeightInput;
        lvTimeWeight = lvTimeWeightInput;
        gymAllowanceWeight = gymAllowanceWeightInput;

    }
}
