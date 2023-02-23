package edu.gatech.seclass.jobcompare6300;

import org.junit.Test;

import static org.junit.Assert.*;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.Arrays;

import edu.gatech.seclass.jobcompare6300.db.DbHelper;
import edu.gatech.seclass.jobcompare6300.db.SettingsTable;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class UnitTest {
    @Test
    public void within_gymAllowanceRange() {
        Job test = new Job("abc", "abc", "abc", "abc", 0, 0, 0, 0, 0, 0);
        double val = test.getGymAllowance();
        assertTrue(val >= 0 && val <= 500);
    }

    @Test
    public void below_gymAllowanceRange() {
        Job test = new Job("abc", "abc", "abc", "abc", 0, 0, 0, 0, 0, -1);
        double val = test.getGymAllowance();
        assertFalse(val >= 0 && val <= 500);
    }

    @Test
    public void above_gymAllowanceRange() {
        Job test = new Job("abc", "abc", "abc", "abc", 0, 0, 0, 0, 0, 501);
        double val = test.getGymAllowance();
        assertFalse(val >= 0 && val <= 500);
    }

    @Test
    public void above_teleworkDays() {
        Job test = new Job("abc", "abc", "abc", "abc", 0, 0, 0, 7, 0, 501);
        double val = test.getWeeklyTelework();
        assertFalse(val >= 0 && val <= 5);
    }

    @Test
    public void below_teleworkDays() {
        Job test = new Job("abc", "abc", "abc", "abc", 0, 0, 0, -1, 0, 501);
        double val = test.getWeeklyTelework();
        assertFalse(val >= 0 && val <= 5);
    }

    @Test
    public void within_teleworkDays() {
        Job test = new Job("abc", "abc", "abc", "abc", 0, 0, 0, 3, 0, 501);
        double val = test.getWeeklyTelework();
        assertTrue(val >= 0 && val <= 5);
    }

    @Test
    public void base_formulaCheck() {
        Job test = new Job("abc", "abc", "abc", "abc", 100, 50000, 3000, 3, 2, 250);

        int weightVals[] = {1, 2, 3, 4, 5};
        double sum = Arrays.stream(weightVals).sum();

        double AYS = test.getAdjYearlySalary();
        double AYB = test.getAdjYearlyBonus();
        double GYM = test.getGymAllowance();
        int LT = test.getLeaveTime();
        int RWT = test.getWeeklyTelework();

        int WAYS = weightVals[0];
        int WAYB = weightVals[1];
        int WGYM = weightVals[2];
        int WLT = weightVals[3];
        int WRWT = weightVals[4];

        double score=WAYS*AYS + WAYB*AYB + WGYM*GYM + WLT*(LT * AYS / 260) - WRWT*((260 - 52 * RWT) * (AYS / 260) / 8);
        score=score/sum;

        assertEquals(String.format("%.2f", score), "80.03");
    }

    @Test
    public void below_formulaCheck() {
        Job test = new Job("abc", "abc", "abc", "abc", 80, 50000, 3000, 3, 2, 250);

        int weightVals[] = {1, 2, 3, 4, 5};
        double sum = Arrays.stream(weightVals).sum();

        double AYS = test.getAdjYearlySalary();
        double AYB = test.getAdjYearlyBonus();
        double GYM = test.getGymAllowance();
        int LT = test.getLeaveTime();
        int RWT = test.getWeeklyTelework();

        int WAYS = weightVals[0];
        int WAYB = weightVals[1];
        int WGYM = weightVals[2];
        int WLT = weightVals[3];
        int WRWT = weightVals[4];

        double score=WAYS*AYS + WAYB*AYB + WGYM*GYM + WLT*(LT * AYS / 260) - WRWT*((260 - 52 * RWT) * (AYS / 260) / 8);
        score=score/sum;

        assertEquals(String.format("%.2f", score), "87.53");
    }

    @Test
    public void above_formulaCheck() {
        Job test = new Job("abc", "abc", "abc", "abc", 200, 50000, 3000, 3, 2, 250);

        int weightVals[] = {1, 2, 3, 4, 5};
        double sum = Arrays.stream(weightVals).sum();

        double AYS = test.getAdjYearlySalary();
        double AYB = test.getAdjYearlyBonus();
        double GYM = test.getGymAllowance();
        int LT = test.getLeaveTime();
        int RWT = test.getWeeklyTelework();

        int WAYS = weightVals[0];
        int WAYB = weightVals[1];
        int WGYM = weightVals[2];
        int WLT = weightVals[3];
        int WRWT = weightVals[4];

        double score=WAYS*AYS + WAYB*AYB + WGYM*GYM + WLT*(LT * AYS / 260) - WRWT*((260 - 52 * RWT) * (AYS / 260) / 8);
        score=score/sum;

        assertEquals(String.format("%.2f", score), "65.01");
    }

//    @Test
//    public void currentJob_datatype() {
//        ComparisonSetting comp = ComparisonSetting.getInstance();
//
//
//
//
//    }

}