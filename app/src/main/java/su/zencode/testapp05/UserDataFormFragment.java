package su.zencode.testapp05;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import su.zencode.testapp05.IntravisionTestAppRepositories.Entities.WorkSheet;
import su.zencode.testapp05.IntravisionTestAppRepositories.UserDataBaseRepository;
import su.zencode.testapp05.IntravisionTestAppRepositories.WorkSheetHolder;

public class UserDataFormFragment extends Fragment implements IDataChecker, View.OnClickListener {
    private static final int GENDER_NOT_SELECTED = -1;
    private static final int MALE_GENDER = 1;
    private static final int FEMALE_GENDER = 2;

    private TextView mMaleGenderView;
    private TextView mFemaleGenderView;

    private EditText mLastNameInputView;
    private EditText mFirstNameInputView;
    private EditText mMiddleNameInputView;
    private EditText mPhoneInputView;
    private EditText mEmailInputView;

    private int mChoosenGender;
    private WorkSheet mWorkSheet;
    private UserDataBaseRepository mDataBaseRepository;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setRetainInstance(true);

        mWorkSheet = WorkSheetHolder.getInstance().getWorkSheet();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_user_data_form, container, false);

        mLastNameInputView = view.findViewById(R.id.lastName_editText);
        mFirstNameInputView = view.findViewById(R.id.firstName_editText);
        mMiddleNameInputView = view.findViewById(R.id.middleName_editText);
        mPhoneInputView = view.findViewById(R.id.phone_editText);
        mEmailInputView = view.findViewById(R.id.dealer_editText);

        mMaleGenderView = view.findViewById(R.id.gender_view_male);
        mMaleGenderView.setOnClickListener(this);
        mFemaleGenderView = view.findViewById(R.id.gender_view_female);
        mFemaleGenderView.setOnClickListener(this);

        if(mWorkSheet.getGender() != GENDER_NOT_SELECTED) {
            if(mWorkSheet.getGender() == MALE_GENDER) setMaleGender();
            else setFemaleGender();
        } else setMaleGender();
        if(mWorkSheet.getLastName() != null) mLastNameInputView.setText(mWorkSheet.getLastName());
        if(mWorkSheet.getFirstName() != null) mFirstNameInputView.setText(mWorkSheet.getFirstName());
        if(mWorkSheet.getMiddleName() != null) mMiddleNameInputView.setText(mWorkSheet.getMiddleName());
        if(mWorkSheet.getPhone() != null) mPhoneInputView.setText(mWorkSheet.getPhone());
        if(mWorkSheet.getEmail() != null) mEmailInputView.setText(mWorkSheet.getEmail());
        return view;
    }

    @Override
    public boolean checkProvidedData() {
        if(mLastNameInputView == null) return false;
        else if(mLastNameInputView.length() == 0) return false;

        if(mFirstNameInputView == null) return false;
        else if(mFirstNameInputView.length() == 0) return false;

        if(mMiddleNameInputView == null) return false;
        else if(mMiddleNameInputView.length() == 0) return false;

        if(mPhoneInputView == null) return false;
        else if(mPhoneInputView.length() == 0) return false;

        if(mEmailInputView == null) return false;
        else if(mEmailInputView.length() == 0) return false;

        return true;
    }

    @Override
    public void saveData() {
        mWorkSheet.setGender(mChoosenGender);
        mWorkSheet.setLastName(mLastNameInputView.getText().toString());
        mWorkSheet.setFirstName(mFirstNameInputView.getText().toString());
        mWorkSheet.setMiddleName(mMiddleNameInputView.getText().toString());
        mWorkSheet.setPhone(mPhoneInputView.getText().toString());
        mWorkSheet.setEmail(mEmailInputView.getText().toString());
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.gender_view_male:
                setMaleGender();
                break;
            case R.id.gender_view_female:
                setFemaleGender();
                break;
        }
    }

    private void setMaleGender() {
        mChoosenGender = MALE_GENDER;
        mMaleGenderView.setBackgroundColor(getResources().getColor(android.R.color.black));
        mMaleGenderView.setTextColor(getResources().getColor(android.R.color.white));
        mFemaleGenderView.setBackgroundColor(getResources().getColor(android.R.color.white));
        mFemaleGenderView.setTextColor(getResources().getColor(android.R.color.black));
    }

    private void setFemaleGender() {
        mChoosenGender = FEMALE_GENDER;
        mMaleGenderView.setBackgroundColor(getResources().getColor(android.R.color.white));
        mMaleGenderView.setTextColor(getResources().getColor(android.R.color.black));
        mFemaleGenderView.setBackgroundColor(getResources().getColor(android.R.color.black));
        mFemaleGenderView.setTextColor(getResources().getColor(android.R.color.white));
    }

}
