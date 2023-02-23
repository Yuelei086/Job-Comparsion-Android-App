package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ComparisonTableActivity extends AppCompatActivity{

    private TextView titleOne, titleTwo, companyOne, companyTwo, locationOne, locationTwo, salaryOne, salaryTwo, bonusOne, bonusTwo;
    private TextView teleworkOne, teleworkTwo, leaveTimeOne, leaveTimeTwo, gymOne, gymTwo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.comparison_table);

        titleOne = (TextView) findViewById(R.id.titleOne);
        titleTwo = (TextView) findViewById(R.id.titleTwo);
        companyOne = (TextView) findViewById(R.id.companyOne);
        companyTwo = (TextView) findViewById(R.id.companyTwo);
        locationOne = (TextView) findViewById(R.id.locationOne);
        locationTwo = (TextView) findViewById(R.id.locationTwo);
        salaryOne = (TextView) findViewById(R.id.salaryOne);
        salaryTwo = (TextView) findViewById(R.id.salaryTwo);
        bonusOne = (TextView) findViewById(R.id.bonusOne);
        bonusTwo = (TextView) findViewById(R.id.bonusTwo);
        teleworkOne = (TextView) findViewById(R.id.teleworkOne);
        teleworkTwo = (TextView) findViewById(R.id.teleworkTwo);
        leaveTimeOne = (TextView) findViewById(R.id.leaveTimeOne);
        leaveTimeTwo = (TextView) findViewById(R.id.leaveTimeTwo);
        gymOne = (TextView) findViewById(R.id.gymOne);
        gymTwo = (TextView) findViewById(R.id.gymTwo);

        Bundle bundle = getIntent().getExtras();
        Job jobOne = (Job) bundle.getSerializable("jobOne");
        Job jobTwo = (Job) bundle.getSerializable("jobTwo");

        String location1 = jobOne.getCity() + ", " + jobOne.getState();
        String location2 = jobTwo.getCity() + ", " + jobTwo.getState();

        titleOne.setText(jobOne.getTitle());
        titleTwo.setText(jobTwo.getTitle());
        companyOne.setText(jobOne.getCompany());
        companyTwo.setText(jobTwo.getCompany());
        locationOne.setText(location1);
        locationTwo.setText(location2);
        salaryOne.setText(String.valueOf(jobOne.getAdjYearlySalary()));
        salaryTwo.setText(String.valueOf(jobTwo.getAdjYearlySalary()));
        bonusOne.setText(String.valueOf(jobOne.getAdjYearlyBonus()));
        bonusTwo.setText(String.valueOf(jobTwo.getAdjYearlyBonus()));
        teleworkOne.setText(String.valueOf(jobOne.getWeeklyTelework()));
        teleworkTwo.setText(String.valueOf(jobTwo.getWeeklyTelework()));
        leaveTimeOne.setText(String.valueOf(jobOne.getLeaveTime()));
        leaveTimeTwo.setText(String.valueOf(jobTwo.getLeaveTime()));
        gymOne.setText(String.valueOf(jobOne.getGymAllowance()));
        gymTwo.setText(String.valueOf(jobTwo.getGymAllowance()));
    }

    public void anotherComparison(View view) {
        //Need compare job class
        Intent intent = new Intent(this, RankJobActivity.class);
        startActivity(intent);
    }

    public void backToMain(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
