package su.zencode.testapp05;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.AppCompatTextView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class UserDataFormFragment extends Fragment {

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_data_form, container, false);

        /*ActionBar actionBar = ((AppCompatActivity) getActivity()).getSupportActionBar();
        AppCompatTextView mTitleTextView = new AppCompatTextView(getActivity().getApplicationContext());
        mTitleTextView.setSingleLine();
        ActionBar.LayoutParams layoutParams =
                new ActionBar.LayoutParams(
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        ActionBar.LayoutParams.WRAP_CONTENT,
                        Gravity.CENTER);
        actionBar.setCustomView(mTitleTextView, layoutParams);
        actionBar.setDisplayOptions(ActionBar.DISPLAY_SHOW_CUSTOM | ActionBar.DISPLAY_HOME_AS_UP);
        mTitleTextView.setText("TestApp05");
        mTitleTextView.setTextAppearance(getActivity().getApplicationContext(), android.R.style.TextAppearance_DeviceDefault);
        */
        return view;
    }
}
