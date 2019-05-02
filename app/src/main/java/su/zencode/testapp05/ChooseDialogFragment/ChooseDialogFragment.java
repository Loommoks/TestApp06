package su.zencode.testapp05.ChooseDialogFragment;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v7.app.AlertDialog;
import android.widget.TextView;

import su.zencode.testapp05.R;

public class ChooseDialogFragment extends DialogFragment {
    private TextView mTargetView;
    private String[] mDataArray;
    private IChooseDialogListener mListener;

    public void setListener(IChooseDialogListener listener) {mListener = listener;}

    public void  setArrayData(String[] arrayData) {
        mDataArray = arrayData;
    }

    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

        builder.setTitle(R.string.year_choose_hint)
                .setItems(mDataArray, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        //mTargetView.setText(mDataArray[which]);
                        mListener.onItemSelected(which);
                    }
                });
        builder.setCancelable(false);

        // Create the AlertDialog object and return it
        return builder.create();
    }
}
