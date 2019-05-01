package su.zencode.testapp05;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mReadyButton;
    private TextView mMaleGenderView;
    private TextView mFemaleGenderView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FragmentManager fm = getSupportFragmentManager();
        Fragment userDaraFragment = fm.findFragmentById(R.id.launch_activity_user_data_fragment_container);
        Fragment carAndDealerDataFragment = fm.findFragmentById(R.id.launch_activity_car_and_service_data_fragment_container);

        if(userDaraFragment == null) {
            userDaraFragment = new UserDataFormFragment();
            fm.beginTransaction()
                    .add(R.id.launch_activity_user_data_fragment_container, userDaraFragment)
                    .commit();
        }

        if(carAndDealerDataFragment == null) {
            carAndDealerDataFragment = new CarAndServiceDataFromFragment();
            fm.beginTransaction()
                    .add(R.id.launch_activity_car_and_service_data_fragment_container, carAndDealerDataFragment)
                    .commit();
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        mReadyButton = findViewById(R.id.ready_button);
        mReadyButton.setOnClickListener(this);
        mMaleGenderView = findViewById(R.id.gender_view_male);
        mMaleGenderView.setOnClickListener(this);
        mFemaleGenderView = findViewById(R.id.gender_view_female);
        mFemaleGenderView.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gender_view_male:
                mMaleGenderView.setBackgroundColor(getResources().getColor(android.R.color.black));
                mMaleGenderView.setTextColor(getResources().getColor(android.R.color.white));
                mFemaleGenderView.setBackgroundColor(getResources().getColor(android.R.color.white));
                mFemaleGenderView.setTextColor(getResources().getColor(android.R.color.black));
                break;
            case R.id.gender_view_female:
                mMaleGenderView.setBackgroundColor(getResources().getColor(android.R.color.white));
                mMaleGenderView.setTextColor(getResources().getColor(android.R.color.black));
                mFemaleGenderView.setBackgroundColor(getResources().getColor(android.R.color.black));
                mFemaleGenderView.setTextColor(getResources().getColor(android.R.color.white));
                break;
            case R.id.ready_button:
                Log.d("LauncActiv", "Send button clicked");
                Toast.makeText(LaunchActivity.this, "Greeting", Toast.LENGTH_SHORT).show();
                break;
        }
    }
}
