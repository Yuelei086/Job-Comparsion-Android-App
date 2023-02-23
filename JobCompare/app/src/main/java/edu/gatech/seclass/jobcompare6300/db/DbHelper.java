package edu.gatech.seclass.jobcompare6300.db;

import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;

public class DbHelper extends SQLiteOpenHelper {
    // If you change the database schema, you must increment the database version.
    public static final int DATABASE_VERSION = 1;
    public static final String DATABASE_NAME = "app.db";

    public DbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        CurrentJobTable user = new CurrentJobTable();
        JobOffersTable offers = new JobOffersTable();
        SettingsTable settings = new SettingsTable();

        String tab1 = user.SQL_CREATE_ENTRIES;
        String tab2 = offers.SQL_CREATE_ENTRIES;
        String tab3 = settings.SQL_CREATE_ENTRIES;

        db.execSQL(tab1);
        db.execSQL(tab2);
        db.execSQL(tab3);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // This database is only a cache for online data, so its upgrade policy is
        // to simply to discard the data and start over
        CurrentJobTable user = new CurrentJobTable();
        JobOffersTable offers = new JobOffersTable();
        SettingsTable settings = new SettingsTable();

        String tab1 = user.SQL_DELETE_ENTRIES;
        String tab2 = offers.SQL_DELETE_ENTRIES;
        String tab3 = settings.SQL_DELETE_ENTRIES;

        db.execSQL(tab1);
        db.execSQL(tab2);
        db.execSQL(tab3);
        onCreate(db);
    }
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        onUpgrade(db, oldVersion, newVersion);
    }

}