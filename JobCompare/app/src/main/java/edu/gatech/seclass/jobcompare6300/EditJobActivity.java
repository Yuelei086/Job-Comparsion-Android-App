package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

public class EditJobActivity extends AppCompatActivity {
    private Job newJobOffer;
    private CurrentJob currentJob;
    private boolean editingCurrentJob;
    JobDataManagement jdm = JobDataManagement.getInstance();
    TextView editJobHeading;
    EditText txtEditTitle, txtEditCompany, txtEditCity, txtEditCostOfLiving, txtEditYearlySalary, txtEditYearlyBonus, txtEditWeeklyTelework, txtEditLeaveTime, txtEditGymAllowance;
    Spinner spinner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_job);
        editingCurrentJob = false;
        spinner = (Spinner) findViewById(R.id.spinnerState);
        // Create an ArrayAdapter using the string array and a default spinner layout
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.states_array, android.R.layout.simple_spinner_item);
        // Specify the layout to use when the list of choices appears
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        // Apply the adapter to the spinner
        spinner.setAdapter(adapter);
        Bundle bundle = getIntent().getExtras();

        editJobHeading = findViewById(R.id.editJobHeading);
        txtEditTitle =  ((EditText) findViewById(R.id.txtEditTitle));
        txtEditCompany = ((EditText) findViewById(R.id.txtEditCompany));
        txtEditCity = ((EditText) findViewById(R.id.txtEditCity));
        txtEditCostOfLiving = ((EditText) findViewById(R.id.txtEditCostOfLiving));
        txtEditYearlySalary = ((EditText) findViewById(R.id.txtEditYearlySalary));
        txtEditYearlyBonus = ((EditText) findViewById(R.id.txtEditYearlyBonus));
        txtEditWeeklyTelework = ((EditText) findViewById(R.id.txtEditWeeklyTelework));
        txtEditLeaveTime = ((EditText) findViewById(R.id.txtEditLeaveTime));
        txtEditGymAllowance = ((EditText) findViewById(R.id.txtEditGymAllowance));

        if (bundle != null && bundle.containsKey("currentJob")) {
            // Current Job logic
            editingCurrentJob = true;
            bundle.getBoolean("currentJob");
            if (bundle.getBoolean("currentJob", false)) {
                currentJob = jdm.getCurrentJob(getApplicationContext());
                editJobHeading.setText(R.string.editCurrentJob);
                txtEditTitle.setText(currentJob.getTitle());
                txtEditCompany.setText(currentJob.getCompany());
                txtEditCity.setText(currentJob.getCity());
                txtEditCostOfLiving.setText(String.valueOf(currentJob.getCostOfLivingIndex()));
                spinner.setSelection(adapter.getPosition(currentJob.getState()));
                txtEditYearlySalary.setText(String.valueOf(currentJob.getYearlySalary()));
                txtEditYearlyBonus.setText(String.valueOf(currentJob.getYearlyBonus()));
                txtEditWeeklyTelework.setText(String.valueOf(currentJob.getWeeklyTelework()));
                txtEditLeaveTime.setText(String.valueOf(currentJob.getLeaveTime()));
                txtEditGymAllowance.setText(String.valueOf(currentJob.getGymAllowance()));
            } else {
                editJobHeading.setText(R.string.addCurrentJob);
            }
        } else {
            // Adding Job Offer
            editJobHeading.setText(R.string.addJobOffer);
        }
    }

    public void resetFields() {
        txtEditTitle.getText().clear();
        txtEditCompany.getText().clear();
        txtEditCity.getText().clear();
        txtEditCostOfLiving.getText().clear();
        spinner.setSelection(0);
        txtEditYearlySalary.getText().clear();
        txtEditYearlyBonus.getText().clear();
        txtEditWeeklyTelework.getText().clear();
        txtEditLeaveTime.getText().clear();
        txtEditGymAllowance.getText().clear();
    }
  
    public void compareCurrent() {
        Intent intent = new Intent(this, ComparisonTableActivity.class);
        intent.putExtra("jobOne", jdm.getCurrentJob(getApplicationContext()));
        intent.putExtra("jobTwo", newJobOffer);
        startActivity(intent);
    }

    public void onSave(View view) {
        boolean error = false;
        String title = txtEditTitle.getText().toString();
        if (title.length() < 1) {
            error = true;
            txtEditTitle.setError(getString(R.string.title_required));
        }
        String company = txtEditCompany.getText().toString();
        if (company.length() < 1) {
            error = true;
            txtEditCompany.setError(getString(R.string.company_required));
        }
        String city = txtEditCity.getText().toString();
        if (city.length() < 1) {
            error = true;
            txtEditCity.setError(getString(R.string.city_required));
        }
        String state = spinner.getSelectedItem().toString();

        // costOfLiving Errors
        double costOfLiving = -1;
        try {
            costOfLiving = Double.parseDouble(txtEditCostOfLiving.getText().toString());
            if (costOfLiving <= 0) {
                error = true;
                txtEditCostOfLiving.setError(getString(R.string.col_positive));
            }
        } catch (Exception e) {
            error = true;
            txtEditCostOfLiving.setError(getString(R.string.col_required));
        }

        // yearlySalary Errors
        double yearlySalary = -1;
        try {
            yearlySalary = Double.parseDouble(txtEditYearlySalary.getText().toString());
            if (yearlySalary <= 0) {
                error = true;
                txtEditYearlySalary.setError(getString(R.string.sal_positive));
            }
        } catch (Exception e) {
            error = true;
            txtEditYearlySalary.setError(getString(R.string.sal_required));
        }

        // yearlyBonus Errors
        double yearlyBonus = -1;
        try {
            yearlyBonus = Double.parseDouble(txtEditYearlyBonus.getText().toString());
            if (yearlyBonus < 0) {
                error = true;
                txtEditYearlyBonus.setError(getString(R.string.bonus_restrict));
            }
        } catch (Exception e) {
            error = true;
            txtEditYearlyBonus.setError(getString(R.string.bonus_required));
        }

        // weeklyTelework Errors
        int weeklyTelework = -1;
        try {
            weeklyTelework = Integer.parseInt(txtEditWeeklyTelework.getText().toString());
            if (weeklyTelework < 0 || weeklyTelework > 5) {
                error = true;
                txtEditWeeklyTelework.setError(getString(R.string.tele_restrict));
            }
        } catch (Exception e) {
            error = true;
            txtEditWeeklyTelework.setError(getString(R.string.tele_require));
        }

        // leaveTime Errors
        int leaveTime = -1;
        try {
            leaveTime = Integer.parseInt(txtEditLeaveTime.getText().toString());
            if (leaveTime < 0) {
                error = true;
                txtEditLeaveTime.setError(getString(R.string.leave_restrict));
            }
        } catch (Exception e) {
            error = true;
            txtEditLeaveTime.setError(getString(R.string.leave_required));
        }

        // gymAllowance Errors
        double gymAllowance = -1;
        try {
            gymAllowance = Double.parseDouble(txtEditGymAllowance.getText().toString());
            if (gymAllowance < 0 || gymAllowance > 500) {
                error = true;
                txtEditGymAllowance.setError(getString(R.string.gym_restrict));
            }
        } catch (Exception e) {
            error = true;
            txtEditGymAllowance.setError(getString(R.string.gym_required));
        }

        // Do not save if an error was found
        if (error) return;

        if (editingCurrentJob) {
            if (currentJob != null) {
                currentJob.updateCurrentJob(title, company, city, state, costOfLiving, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance);
                jdm.saveCurrentJob(getApplicationContext());
            } else {
                currentJob = jdm.setCurrentJob(new CurrentJob(title, company, city, state, costOfLiving, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance), getApplicationContext());
            }
            super.onBackPressed();
        } else {
            newJobOffer = new Job(title, company, city, state, costOfLiving, yearlySalary, yearlyBonus, weeklyTelework, leaveTime, gymAllowance);
            jdm.addJobOffer(newJobOffer, getApplicationContext());
            DialogFragment newFragment = new JobSavedDialog();
            Bundle bundle = new Bundle();
            CurrentJob cj = jdm.getCurrentJob(getApplicationContext());
            bundle.putSerializable("currentJob", cj);
            newFragment.setArguments(bundle);
            newFragment.show(getSupportFragmentManager(), "jobSavedDialog");
        }
    }
}