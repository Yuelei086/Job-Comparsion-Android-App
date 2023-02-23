package edu.gatech.seclass.jobcompare6300;

import android.app.Dialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;



public class JobSavedDialog extends DialogFragment {
    @NonNull
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        EditJobActivity activity = (EditJobActivity) getActivity();
        assert(activity != null);
        Bundle bundle = this.getArguments();
        CurrentJob currentJob = (CurrentJob) bundle.getSerializable("currentJob");
        int stringArray =  currentJob == null ? R.array.job_saved_array : R.array.job_saved_array_all;
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);
        builder.setTitle(R.string.jobOfferSaved)
                .setItems(stringArray, (dialog, which) -> {
                    // The 'which' argument contains the index position
                    // of the selected item
                    switch (which) {
                        case 0:
                            activity.resetFields();
                            break;
                        case 1:
                            activity.onBackPressed();
                            break;
                        case 2:
                            activity.compareCurrent();
                            break;
                    }
                });
        return builder.create();
    }
}