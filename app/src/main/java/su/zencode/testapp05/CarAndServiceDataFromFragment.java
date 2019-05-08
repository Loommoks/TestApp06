package su.zencode.testapp05;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

import su.zencode.testapp05.ChooseDialog.ChooseDialogFragment;
import su.zencode.testapp05.ChooseDialog.IChooseDialogListener;
import su.zencode.testapp05.IntravisionTestAppApiClient.IntraVisionApiClient;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.CarClass;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.City;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.ShowRoom;
import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.WorkSheet;
import su.zencode.testapp05.IntravisionTestAppRepositories.WorkSheetHolder;

public class CarAndServiceDataFromFragment extends Fragment implements IDataChecker{
    final String[] years = new String[14];
    ArrayList<CarClass> mCarClassesList;
    ArrayList<City> mCitiesList;
    ArrayList<ShowRoom> mShowRoomsList;
    String[] classes;
    String[] cities;
    String[] dealers;

    private String mChosenYear;
    private CarClass mChosenClass;
    private City mChosenCity;
    private ShowRoom mChosenShowRoom;

    private WorkSheet mWorkSheet;

    EditText mVinInputView;
    TextView mYearHintView;
    TextView mClassHintView;
    TextView mCityHintView;
    TextView mDealerHintView;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mWorkSheet = WorkSheetHolder.getInstance().getWorkSheet();

        initializeYearArray();
    }

    private void initializeYearArray() {
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);

        for(int i = 0; i < 14; i++) {
            years[i] = Integer.toString(currentYear - 15 + i);
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_and_service_data_form, container, false);

        mVinInputView = view.findViewById(R.id.vin_editText_view);
        if(mWorkSheet.getVin() != null) mVinInputView.setText(mWorkSheet.getVin());

        initializeYearChooseViewGroup(view);
        initializeClassChooseViewGroup(view);
        initializeCityChooseViewGroup(view);
        initializeDealerChooseViewGroup(view);

        return view;
    }

    private void initializeYearChooseViewGroup(View view) {
        mYearHintView = view.findViewById(R.id.manufacture_year_hint);
        if(mChosenYear != null)
            mYearHintView.setText(mChosenYear);

        mYearHintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearChooseDialog();
            }
        });
    }


    private void initializeClassChooseViewGroup(View view) {
        mClassHintView = view.findViewById(R.id.class_choose_hint);
        if(classes != null) {
            setupClassChooseViewGroup();
            if(mChosenClass != null)
                mClassHintView.setText(mChosenClass.getName());
        } else {
            mClassHintView.setText(R.string.loading_label);
            requestClasses();
        }
    }

    private void setupClassChooseViewGroup() {
        mClassHintView.setText(R.string.class_choose_hint);
        mClassHintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showClassChooseDialog(mClassHintView, classes);
            }
        });
    }

    private void initializeCityChooseViewGroup(View view) {
        mCityHintView = view.findViewById(R.id.city_choose_hint);
        if(cities != null) {
            setupCityChooseViewGroup();
            if(mChosenCity != null)
                mCityHintView.setText(mChosenCity.getName());
        } else {
            mCityHintView.setText(R.string.loading_label);
            requestCities();
        }
    }

    private void setupCityChooseViewGroup() {
        mCityHintView.setText(R.string.city_choose_hint);
        mCityHintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showCityChoseDialog(mCityHintView, cities);
            }
        });
    }

    private void initializeDealerChooseViewGroup(View view) {
        mDealerHintView = view.findViewById(R.id.dealer_editText);
        if(dealers != null && mChosenCity != null) {
            setupDealerChooseViewGroup();
            if (mChosenShowRoom != null)
                mDealerHintView.setText(mChosenShowRoom.getName());
        } else {
            mDealerHintView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Toast.makeText(
                            getActivity(),
                            R.string.select_city_first,
                            Toast.LENGTH_SHORT).show();
                }
            });
        }
    }

    private void setupDealerChooseViewGroup() {
        mDealerHintView.setText(R.string.dealer_choose_hint);
        mDealerHintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDealerChooseDialog();
            }
        });
    }

    private void showClassChooseDialog(final TextView targetView,
                                       final String[] dataArray) {
        ChooseDialogFragment dialogFragment = new ChooseDialogFragment();
        dialogFragment.setListener(new IChooseDialogListener() {
            @Override
            public void onItemSelected(int position) {
                targetView.setText(dataArray[position]);
                mChosenClass = mCarClassesList.get(position);
            }
        });
        dialogFragment.setArrayData(classes);
        dialogFragment.show(getFragmentManager(), "custom");
    }

    private void showCityChoseDialog(final TextView targetView, final String[] dataArray) {
        ChooseDialogFragment dialogFragment = new ChooseDialogFragment();
        dialogFragment.setListener(new IChooseDialogListener() {
            @Override
            public void onItemSelected(int position) {
                targetView.setText(dataArray[position]);
                mChosenCity = mCitiesList.get(position);
                mChosenShowRoom = null;
                dealers = null;
                mDealerHintView.setText(R.string.loading_label);
                requestDealers(mChosenCity.getId());
            }
        });
        dialogFragment.setArrayData(cities);
        dialogFragment.show(getFragmentManager(), "custom");
    }

    private void showDealerChooseDialog() {
        ChooseDialogFragment dialogFragment = new ChooseDialogFragment();
        dialogFragment.setListener(new IChooseDialogListener() {
            @Override
            public void onItemSelected(int position) {
                mDealerHintView.setText(dealers[position]);
                mChosenShowRoom = mShowRoomsList.get(position);
            }
        });
        dialogFragment.setArrayData(dealers);
        dialogFragment.show(getFragmentManager(), "custom");
    }

    private void showYearChooseDialog() {
        ChooseDialogFragment dialogFragment = new ChooseDialogFragment();
        dialogFragment.setListener(new IChooseDialogListener() {
            @Override
            public void onItemSelected(int position) {
                mYearHintView.setText(years[position]);
                mChosenYear = years[position];
            }
        });
        dialogFragment.setArrayData(years);
        dialogFragment.show(getFragmentManager(), "custom");
    }

    private void requestClasses() {
        new AsyncTask<Void, Void, Void>() {
            ArrayList<CarClass> mResult;
            @Override
            protected Void doInBackground(Void... v) {
                mResult = new IntraVisionApiClient().getClasses();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(mResult == null) return;
                Log.d("FetcherClass",mResult.toString());
                mCarClassesList = mResult;
                classes = new String[mCarClassesList.size()];
                for(int i = 0; i < mCarClassesList.size(); i++) {
                    classes[i] = mCarClassesList.get(i).getName();
                }
                setupClassChooseViewGroup();
                //Toast.makeText(LaunchActivity.this, mResult.toString(), Toast.LENGTH_SHORT).show();
            }
        }.execute();
    }

    private void requestCities() {
        new AsyncTask<Void, Void, Void>() {
            ArrayList<City> mResult;
            @Override
            protected Void doInBackground(Void... v) {
                mResult = new IntraVisionApiClient().getCities();
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                if(mResult == null) return;
                mCitiesList = mResult;
                cities = new String[mCitiesList.size()];
                for(int i = 0; i < mCitiesList.size(); i++) {
                    cities[i] = mCitiesList.get(i).getName();
                }
                setupCityChooseViewGroup();
            }
        }.execute();
    }

    private void requestDealers(int cityId) {
        new AsyncTask<Integer, Void, Void>() {
            ArrayList<ShowRoom> mResult;

            @Override
            protected Void doInBackground(Integer... id) {
                mResult = new IntraVisionApiClient().getDealers(id[0]);
                return null;
            }

            @Override
            protected void onPostExecute(Void aVoid) {
                mShowRoomsList = mResult;
                dealers = new String[mShowRoomsList.size()];
                for(int i = 0; i < mShowRoomsList.size(); i++) {
                    dealers[i] = mShowRoomsList.get(i).getName();
                }
                setupDealerChooseViewGroup();
            }
        }.execute(cityId);
    }

    @Override
    public boolean checkProvidedData() {
        if(mVinInputView.getText().length() == 0) return false;
        if(mChosenYear == null) return false;
        else if(mChosenYear.length() == 0) return false;
        if(mChosenClass == null) return false;
        if(mChosenShowRoom == null) return false;

        return true;
    }

    @Override
    public void saveData() {
        mWorkSheet.setVin(mVinInputView.getText().toString());
        mWorkSheet.setYear(mChosenYear);
        mWorkSheet.setClassId(mChosenClass.getId());
        mWorkSheet.setCityId(mChosenCity.getId());
        mWorkSheet.setShowRoomId(mChosenShowRoom.getId());
    }

}
