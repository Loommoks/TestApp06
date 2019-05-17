package su.zencode.testapp05.userdatabase;

import android.database.Cursor;
import android.database.CursorWrapper;

import su.zencode.testapp05.Config.DbSchema.UserDataTable;
import su.zencode.testapp05.intravisiontestapprepositories.entities.WorkSheet;

public class UserCursorWrapper extends CursorWrapper {

    public UserCursorWrapper(Cursor cursor) {
        super(cursor);
    }

    public WorkSheet getWorksheet() {

        WorkSheet workSheet = new WorkSheet();
        int gender = getInt(getColumnIndex(UserDataTable.Cols.GENDER));
        String lastName = getString(getColumnIndex(UserDataTable.Cols.LASTNAME));
        String firstName = getString(getColumnIndex(UserDataTable.Cols.FIRSTNAME));
        String middleName = getString(getColumnIndex(UserDataTable.Cols.MIDDLENAME));
        String email = getString(getColumnIndex(UserDataTable.Cols.EMAIL));
        String phone = getString(getColumnIndex(UserDataTable.Cols.PHONE));
        String vin = getString(getColumnIndex(UserDataTable.Cols.VIN));
        String year = getString(getColumnIndex(UserDataTable.Cols.YEAR));

        workSheet.setGender(gender);
        workSheet.setLastName(lastName);
        workSheet.setFirstName(firstName);
        workSheet.setMiddleName(middleName);
        workSheet.setEmail(email);
        workSheet.setPhone(phone);
        workSheet.setVin(vin);
        workSheet.setYear(year);

        return workSheet;
    }
}
