package edu.gatech.seclass.jobcompare6300;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class CompsettingActivity extends AppCompatActivity {
    ComparisonSetting comp;
    private Button compsaveButton;
    private Button compcancelButton;

    private EditText yrlySalaryWeightEdit;
    private EditText yrlyBonusWeightEdit;
    private EditText wklyTeleworkWeightEdit;
    private EditText lvTimeWeightEdit;
    private EditText gymAllowanceWeightEdit;
    private int yrlySalaryWeightI=-1;
    private int yrlyBonusWeightI=-1;
    private int wklyTeleworkWeightI=-1;
    private int lvTimeWeightI=-1;
    private int gymAllowanceWeightI=-1;



    public int checkInput(EditText theEditText){

        int theOutput = -1;
        try {
            theOutput = Integer.parseInt(theEditText.getText().toString());
            if (theOutput< 0) {
                theEditText.setError("This should be a positive number or 0.");
            }
        } catch (Exception e) {
            theEditText.setError("This should be a valid integer number.");
        }
        return theOutput;

    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compsetting);
        comp = ComparisonSetting.getInstance(getApplicationContext());
        compsaveButton = (Button) findViewById(R.id.weightSave);
        compcancelButton=(Button) findViewById(R.id.weightCancel);
        yrlySalaryWeightEdit = (EditText) findViewById(R.id.yearlySalaryWeight);
        yrlyBonusWeightEdit = (EditText) findViewById(R.id.yearlyBonusWeight);
        wklyTeleworkWeightEdit = (EditText) findViewById(R.id.wklytlworkWeight);
        lvTimeWeightEdit = (EditText) findViewById(R.id.lvTimeWeight);
        gymAllowanceWeightEdit = (EditText) findViewById(R.id.gymAllowanceWeight);
        System.out.println("hiii");
        yrlySalaryWeightEdit.setText(Integer.toString(comp.getYrlySalaryWeight()));
        yrlyBonusWeightEdit.setText(Integer.toString(comp.getYrlyBonusWeight()));
        wklyTeleworkWeightEdit.setText(Integer.toString(comp.getWklyTeleworkWeight()));
        lvTimeWeightEdit.setText(Integer.toString(comp.getLvTimeWeight()));
        gymAllowanceWeightEdit.setText(Integer.toString(comp.getGymAllowanceWeight()));



        compsaveButton.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {





                // Intents are objects of the android.content.Intent type. Your code can send them
                // to the Android system defining the components you are targeting.
                // Intent to start an activity called ThirdActivity with the following code:
                //Log.i("here:",yrlySalaryWeightEdit.getClass().getSimpleName());
                //System.out.println("data type:"+yrlySalaryWeightEdit.getClass().getSimpleName());
                yrlySalaryWeightI = checkInput(yrlySalaryWeightEdit);
                        //Double.valueOf(yrlySalaryWeightEdit.getText().toString());
                yrlyBonusWeightI = checkInput(yrlyBonusWeightEdit);
                        //Double.valueOf(yrlyBonusWeightEdit.getText().toString());
                wklyTeleworkWeightI = checkInput(wklyTeleworkWeightEdit);
                        //Double.valueOf(wklyTeleworkWeightEdit.getText().toString());
                lvTimeWeightI = checkInput(lvTimeWeightEdit);
                        //Double.valueOf(lvTimeWeightEdit.getText().toString());
                gymAllowanceWeightI =checkInput(gymAllowanceWeightEdit);
                        //Double.valueOf(gymAllowanceWeightEdit.getText().toString());
                if ((yrlySalaryWeightI==0)&&(yrlyBonusWeightI==0)&&(wklyTeleworkWeightI==0)&&(lvTimeWeightI==0)
                    &&(gymAllowanceWeightI==0)){
                    AlertDialog.Builder builder = new AlertDialog.Builder(CompsettingActivity.this);
                    //builder.setTitle("My title");
                    builder.setMessage("At least 1 weight should be positive");

                    // add a button
                    builder.setPositiveButton("Ok",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }

               if (((yrlySalaryWeightI>=0)&&(yrlyBonusWeightI>=0)&&(wklyTeleworkWeightI>=0)
            &&(lvTimeWeightI>=0)&&(gymAllowanceWeightI>=0))&((yrlySalaryWeightI>0)|(yrlyBonusWeightI>0)|(wklyTeleworkWeightI>0)
                       |(lvTimeWeightI>0)|(gymAllowanceWeightI>0)))
               {
                   comp.saveWeights(yrlySalaryWeightI,
                           yrlyBonusWeightI,
                           wklyTeleworkWeightI,
                           lvTimeWeightI,
                           gymAllowanceWeightI,
                           getApplicationContext()
                   );

                   // setup the alert builder
                   AlertDialog.Builder builder = new AlertDialog.Builder(CompsettingActivity.this);
                   //builder.setTitle("My title");
                   builder.setMessage("Comparison settings saved.");

                   // add a button
                   builder.setPositiveButton("Ok", new DialogInterface.OnClickListener() {

                       @Override
                       public void onClick(DialogInterface dialogInterface, int i) {
                           // do something like...
                           Intent intent = new Intent(CompsettingActivity.this, MainActivity.class);

                           // start the activity connect to the specified class
                           startActivity(intent);

                       }

                   });

                   // create and show the alert dialog
                   AlertDialog dialog = builder.create();
                   dialog.show();



               }



            }
        });

        compcancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent intent = new Intent(CompsettingActivity.this, MainActivity.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });

    }
}
