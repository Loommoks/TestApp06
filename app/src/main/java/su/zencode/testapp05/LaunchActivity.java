package su.zencode.testapp05;

import android.os.AsyncTask;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;


import su.zencode.testapp05.intravisiontestappapiclient.IntraVisionApiClient;
import su.zencode.testapp05.intravisiontestapprepositories.entities.WorkSheet;
import su.zencode.testapp05.intravisiontestapprepositories.UserDataBaseRepository;
import su.zencode.testapp05.intravisiontestapprepositories.WorkSheetHolder;

public class LaunchActivity extends AppCompatActivity implements View.OnClickListener {
    private Button mReadyButton;

    private IDataChecker mUserDataChecker;
    private IDataChecker mCarDataChecker;

    private UserDataBaseRepository mDataBaseRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mDataBaseRepository = UserDataBaseRepository.getInstance(getApplicationContext());
        if(savedInstanceState == null) {
            WorkSheet savedWorkSheet = mDataBaseRepository.get();
            if(savedWorkSheet != null)
                WorkSheetHolder.getInstance().updateWorkSheet(savedWorkSheet);
        }

        FragmentManager fm = getSupportFragmentManager();
        Fragment userDataFragment = fm.findFragmentById(R.id.launch_activity_user_data_fragment_container);
        Fragment carAndDealerDataFragment = fm.findFragmentById(R.id.launch_activity_car_and_service_data_fragment_container);

        if(userDataFragment == null) {
            userDataFragment = new UserDataFormFragment();
            fm.beginTransaction()
                    .add(R.id.launch_activity_user_data_fragment_container, userDataFragment)
                    .commit();
        }

        if(carAndDealerDataFragment == null) {
            carAndDealerDataFragment = new CarAndServiceDataFromFragment();
            fm.beginTransaction()
                    .add(R.id.launch_activity_car_and_service_data_fragment_container, carAndDealerDataFragment)
                    .commit();
        }

        mUserDataChecker = (IDataChecker) userDataFragment;
        mCarDataChecker = (IDataChecker) carAndDealerDataFragment;

    }

    @Override
    protected void onStart() {
        super.onStart();
        mReadyButton = findViewById(R.id.ready_button);
        mReadyButton.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.ready_button:
                if(mUserDataChecker.checkProvidedData() && mCarDataChecker.checkProvidedData()) {
                    mUserDataChecker.saveData();
                    mCarDataChecker.saveData();
                    if(WorkSheetHolder.getInstance().isCompletelyFilled()){
                        sendBlank(WorkSheetHolder.getInstance().getWorkSheet());
                        mDataBaseRepository.saveUserData(WorkSheetHolder.getInstance().getWorkSheet());
                    } else {
                        Toast.makeText(LaunchActivity.this,
                                R.string.fill_all_fields,
                                Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(LaunchActivity.this,
                            R.string.fill_all_fields,
                            Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }

    private void sendBlank(final WorkSheet myWorkSheet) {

        new AsyncTask<Void, Void, Boolean>() {

            @Override
            protected Boolean doInBackground(Void... v) {
                return new IntraVisionApiClient().sendWorkSheet(myWorkSheet);
            }

            @Override
            protected void onPostExecute(Boolean responseIsSuccessful) {
                if(responseIsSuccessful) {
                    Toast.makeText(
                            LaunchActivity.this,
                            R.string.request_success,
                            Toast.LENGTH_LONG).show();
                } else {
                    Toast.makeText(
                            LaunchActivity.this,
                            R.string.request_failed,
                            Toast.LENGTH_LONG).show();
                }
            }
        }.execute();
    }

}
