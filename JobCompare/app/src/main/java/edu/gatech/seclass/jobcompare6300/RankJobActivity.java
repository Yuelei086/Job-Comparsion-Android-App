package edu.gatech.seclass.jobcompare6300;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Iterator;
import java.util.List;
import java.util.Objects;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;

import androidx.appcompat.app.AlertDialog;

public class RankJobActivity extends ListActivity {
    private Button cancelButton;
    private Button moveCompareButton;
    public static ArrayList<RankedJob> dataList;
    JobDataManagement jdm = JobDataManagement.getInstance();


    /** Called when the activity is first created. */
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_rankjobs);
        dataList= (ArrayList) getModel();
        // create an array of Strings, that will be put to our ListActivity
        ArrayAdapter<RankedJob> adapter = new InteractiveArrayAdapter(this,
                dataList);
        setListAdapter(adapter);
        cancelButton = (Button) findViewById(R.id.btnReturn);
        moveCompareButton=(Button) findViewById(R.id.btn_addSnipet);
        cancelButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                //Integer selectedNum=countSelected();
                //Log.i("my array list count",Integer.toString(selectedNum));
                //Integer.toString(Model.totalSelected)
                //AlertDialog.Builder builder = new AlertDialog.Builder(MyList.this);
                //builder.setTitle("My title");
                //builder.setMessage(Integer.toString(Model.totalSelected));
                Intent intent = new Intent(RankJobActivity.this, MainActivity.class);

                // start the activity connect to the specified class
                startActivity(intent);
            }
        });

        moveCompareButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {

                Integer selectedNum=countSelected();
                Log.i("my array list count",Integer.toString(selectedNum));
                //Integer.toString(Model.totalSelected)
                if (selectedNum!=2){
                    AlertDialog.Builder builder = new AlertDialog.Builder(RankJobActivity.this);
                    builder.setTitle("Error Message");
                    builder.setMessage("Please make sure you selected 2 jobs.Now selected:"+selectedNum);
                    builder.setPositiveButton("Ok",null);
                    AlertDialog dialog = builder.create();
                    dialog.show();
                }
                else{
                    Intent i = new Intent(RankJobActivity.this, ComparisonTableActivity.class);
                    int firstTime=0;
                    for (RankedJob rj: dataList) {
                        if (rj.isSelected()){
                            firstTime++;
                            Job sj=rj.getJob();
                            if (firstTime==1){
                                i.putExtra("jobOne", sj);
                            }
                            else{
                                i.putExtra("jobTwo", sj);
                            }
                        }
                    }
                    startActivity(i);

                }
                //AlertDialog.Builder builder = new AlertDialog.Builder(MyList.this);
                //builder.setTitle("My title");
                //builder.setMessage(Integer.toString(Model.totalSelected));
                //Intent intent = new Intent(MyList.this, MainActivity.class);

                // start the activity connect to the specified class
                //startActivity(intent);
            }
        });
    }

    private List<RankedJob> getModel() {
        List<RankedJob> list = new ArrayList<RankedJob>();
        ArrayList<Job> jobOffers=jdm.getJobOffers(getApplicationContext());

        for (Job theJob:jobOffers) {
            list.add(generateRankedJob(theJob,false));
        }
        CurrentJob currentJob=jdm.getCurrentJob(getApplicationContext());
        if (Objects.nonNull(currentJob))
        {list.add(generateRankedJob(currentJob,true));}

        // Initially select one of the items
        //list.get(1).setSelected(true);
        Collections.sort(list, new Comparator<RankedJob>() {
            @Override
            public int compare(RankedJob lhs, RankedJob rhs) {
                // -1 - less than, 1 - greater than, 0 - equal, all inversed for descending

                return lhs.score > rhs.score ? -1 : (lhs.score < rhs.score) ? 1 : 0;
            }
        });

        return list;
    }

    private RankedJob generateRankedJob(Job job,boolean currentJob) {
        return new RankedJob(job,currentJob, getApplicationContext());
    }

    private int countSelected(){
        Integer theInt=0;
        for (RankedJob theModel: dataList) {

            if (theModel.isSelected()){
                theInt++;
            }
        }
        return theInt;

    }

}