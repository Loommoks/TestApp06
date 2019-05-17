package su.zencode.testapp05;

import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

public class MyCustomTextWatcher implements TextWatcher {
    private EditText mEditText;
    private boolean mLock = false;
    private String mBefore;
    private String mOld;
    private String mNew;
    private String mAfter;


    public MyCustomTextWatcher(EditText editText) {
        super();
        mEditText = editText;
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        mBefore = s.subSequence(0,start).toString();
        mOld = s.subSequence(start, start+count).toString();
        mAfter = s.subSequence(start+count, s.length()).toString();

    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        mNew = s.subSequence(start, start+count).toString();

    }

    @Override
    public void afterTextChanged(Editable s) {
        if(mLock) return;
        correctText(mBefore, mOld, mNew, mAfter);
    }

    protected void correctText(String before, String old, String aNew, String after) {

        String completeOldText = before + old + after;
        String completeNewText = before + aNew + after;
        String code_template = "+7";
        String phone_mask_template = "(XXX)-XXX-XX-XX";

        if (completeOldText.contains(code_template) && !completeNewText.contains(code_template)){

            if(old.contains("+") && !aNew.contains("+")) {
                if (aNew.length() == 0) {
                    startUpdate();
                    mEditText.setText(completeOldText);
                    mEditText.setSelection(completeOldText.length());
                    endUpdate();
                    return;
                }
            }

            if(old.contains("7") && !aNew.contains("7")) {
                if (aNew.length() == 0) {
                    startUpdate();
                    mEditText.setText(completeOldText);
                    mEditText.setSelection(completeOldText.length());
                    endUpdate();
                    return;
                }
            }
        }

        //if(aNew.length() == 0) return;

        if(completeOldText.length() < code_template.length() && completeOldText.length() < 3) {
            String prefix = code_template.substring(completeOldText.length(), code_template.length());
            aNew = prefix + aNew;
            completeNewText = before + aNew + after;
            startUpdate();
            mEditText.setText(completeNewText);
            endUpdate();
        }

        String decodedPhone = completeNewText.replace(code_template, "");
        decodedPhone = decodedPhone.replace("(","");
        decodedPhone = decodedPhone.replace(")","");
        decodedPhone = decodedPhone.replace("-","");
        decodedPhone = decodedPhone.replace("+","");

        if(decodedPhone.length() == 0) {
            startUpdate();
            mEditText.setText(code_template);
            mEditText.setSelection(code_template.length());
            endUpdate();
            return;
        }

        if(decodedPhone.length() > 10) {
            decodedPhone = decodedPhone.substring(0,10);
        }

        StringBuffer stringBuffer = new StringBuffer(phone_mask_template);
        int x_index;
        for(int i = 0; i < decodedPhone.length(); i++){
            x_index = stringBuffer.indexOf("X");
            stringBuffer.setCharAt(x_index,decodedPhone.charAt(i));


            if(i == decodedPhone.length() - 1 && i < 3) {
                stringBuffer.delete(i+2, stringBuffer.length());
                stringBuffer.insert(0, code_template);
            }

            if(i == decodedPhone.length() - 1 && i >= 3 && i < 6) {
                stringBuffer.delete(i+4, stringBuffer.length());
                stringBuffer.insert(0, code_template);
            }

            if(i == decodedPhone.length() - 1 && i >= 6 && i < 8) {
                stringBuffer.delete(i+5, stringBuffer.length());
                stringBuffer.insert(0, code_template);
            }

            if(i == decodedPhone.length() - 1 && i >= 8 && i < 10) {
                stringBuffer.delete(i+6, stringBuffer.length());
                stringBuffer.insert(0, code_template);
            }
        }
        startUpdate();
        mEditText.setText(stringBuffer.toString());
        mEditText.setSelection(stringBuffer.length());
        endUpdate();
    }

    private void startUpdate() {
        mLock = true;
    }

    private void endUpdate() {
        mLock = false;
    }
}
