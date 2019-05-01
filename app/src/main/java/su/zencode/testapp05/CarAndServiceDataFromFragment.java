package su.zencode.testapp05;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.TextView;

public class CarAndServiceDataFromFragment extends Fragment {
    String[] years = {"2005", "2006", "2007", "2008", "2009", "2010"};
    String[] classes = {"А класс", "B класс", "C класс", "D класс","E класс"};
    String[] cities = {"Москва", "Санкт-Петербург", "Воронеж", "Новосибирск","Екатеринбург"};

    TextView mYearHintView;
    TextView mClassHintView;
    TextView mCityHintView;
    TextView mDealerHintView;
    Spinner mClassesSpinner;
    Spinner mCitiesSpinner;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_car_and_service_data_form, container, false);

        initializeYearChooseViewGroup(view);
        initializeClassChooseViewGroup(view);
        initializeCityChooseViewGroup(view);
        initializeDealerChooseViewGroup(view);

        return view;

    }

    private void initializeYearChooseViewGroup(View view) {
        mYearHintView = view.findViewById(R.id.manufacture_year_hint);
        mYearHintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showYearChooseDialog();
            }
        });
    }

    private void initializeClassChooseViewGroup(View view) {
        mClassHintView = view.findViewById(R.id.class_choose_hint);
        mClassHintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(mClassHintView, classes);
            }
        });
    }

    private void initializeCityChooseViewGroup(View view) {
        mCityHintView = view.findViewById(R.id.city_choose_hint);
        mCityHintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showChooseDialog(mCityHintView, cities);
            }
        });
    }

    private void initializeDealerChooseViewGroup(View view) {
        mDealerHintView = view.findViewById(R.id.dealer_hint_view);
        mDealerHintView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDealerChooseDialog();
            }
        });
    }

    private void showChooseDialog(TextView targetView, String[] dataArray) {
        YearChooseDialogFragment dialogFragment = new YearChooseDialogFragment();
        dialogFragment.setTarget(targetView);
        dialogFragment.setArrayData(dataArray);
        dialogFragment.show(getFragmentManager(), "custom");
    }

    private void showDealerChooseDialog() {
        FireMissilesDialogFragment dialogFragment = new FireMissilesDialogFragment();
        dialogFragment.setTarget(mDealerHintView);
        dialogFragment.show(getFragmentManager(), "custom");
    }

    private void showYearChooseDialog() {
        YearChooseDialogFragment dialogFragment = new YearChooseDialogFragment();
        dialogFragment.setTarget(mYearHintView);
        dialogFragment.setArrayData(years);
        dialogFragment.show(getFragmentManager(), "custom");
    }

    private void setupDealerChooseViewGroup(View view, String[] data) {
        mDealerHintView = view.findViewById(R.id.dealer_hint_view);

    }
}
