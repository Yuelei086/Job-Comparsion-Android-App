package edu.gatech.seclass.jobcompare6300;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity extends AppCompatActivity {
    FloatingActionButton settingButton;
    Button compareButton;

    private CurrentJob currentJob;
    JobDataManagement jdm = JobDataManagement.getInstance();
    TextView txtNumOfOffers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        // Uncomment the line below
        // Run the app
        // Comment it out again
        // Re-run
        // This will reset the database if there are issues creating new tables
        //getApplicationContext().deleteDatabase("app.db");

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        settingButton= (FloatingActionButton)findViewById(R.id.btnSettings);
        compareButton=(Button)findViewById(R.id.btnCompareJobs);

        settingButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {

                


                // Intents are objects of the android.content.Intent type. Your code can send them
                // to the Android system defining the components you are targeting.
                // Intent to start an activity called ThirdActivity with the following code:

                Intent intent = new Intent(MainActivity.this, CompsettingActivity.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });

        compareButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v)
            {




                // Intents are objects of the android.content.Intent type. Your code can send them
                // to the Android system defining the components you are targeting.
                // Intent to start an activity called ThirdActivity with the following code:

                Intent intent = new Intent(MainActivity.this,
                        RankJobActivity.class);
                        //CompsettingActivity.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (currentJob == null) {
            currentJob = jdm.getCurrentJob(getApplicationContext());
        }

        Button btnEditCurrentJob = findViewById(R.id.btnEditCurrentJob);
        TextView txtCurrentJobTitle = findViewById(R.id.txtCurrentJobTitle);
        TextView txtCurrentJobCompany = findViewById(R.id.txtCurrentJobCompany);
        TextView txtCurrentJobLocation = findViewById(R.id.txtCurrentJobLocation);
        if (currentJob != null) {
            txtCurrentJobTitle.setText(currentJob.getTitle());
            txtCurrentJobCompany.setText(currentJob.getCompany());
            txtCurrentJobLocation.setText(currentJob.formattedLocation());
            btnEditCurrentJob.setText(R.string.edit);
        } else {
            txtCurrentJobTitle.setText(R.string.noCurrJob);
            btnEditCurrentJob.setText(R.string.add);
        }

        txtNumOfOffers = findViewById(R.id.txtNumOfOffers);
        txtNumOfOffers.setText(getString(R.string.offers, jdm.getJobOffers(getApplicationContext()).size()));
    }


    public void editCurrentJob(View view) {
        Intent intent = new Intent(this, EditJobActivity.class);
        intent.putExtra("currentJob", currentJob != null);
        startActivity(intent);
    }

    public void addJobOffer(View view) {
        Intent intent = new Intent(this, EditJobActivity.class);
        startActivity(intent);
    }

}
