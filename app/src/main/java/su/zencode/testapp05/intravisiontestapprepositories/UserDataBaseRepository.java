package su.zencode.testapp05.intravisiontestapprepositories;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import su.zencode.testapp05.Config;
import su.zencode.testapp05.Config.DbSchema.UserDataTable;
import su.zencode.testapp05.intravisiontestapprepositories.entities.WorkSheet;
import su.zencode.testapp05.userdatabase.UserCursorWrapper;
import su.zencode.testapp05.userdatabase.UserDataBaseHelper;

public class UserDataBaseRepository {
    private static UserDataBaseRepository sUserDataBaseRepository;
    private Context mContext;
    private SQLiteDatabase mDatabase;


    public static UserDataBaseRepository getInstance(Context context) {
        if(sUserDataBaseRepository == null) {
            sUserDataBaseRepository = new UserDataBaseRepository(context);
        }
        return sUserDataBaseRepository;
    }

    private UserDataBaseRepository(Context context) {
        mContext = context;
        mDatabase = new UserDataBaseHelper(mContext).getWritableDatabase();
    }

    public WorkSheet get() {
        String id;
        if(Config.DbSchema.SAVE_ONLY_ONE_COPY) id = Config.DbSchema.LONER_ID;

        UserCursorWrapper cursor = queryUser(
                UserDataTable.Cols.ID + " = ?",
                new String[] { id }
        );

        try {
            if (cursor.getCount() == 0) return null;

            cursor.moveToFirst();
            return cursor.getWorksheet();
        } finally {
            cursor.close();
        }
    }

    public void saveUserData(WorkSheet workSheet) {
        if(get() == null) add(workSheet);
        else update(workSheet);
    }

    public void add(WorkSheet workSheetgory) {
        ContentValues values = getContentValues(workSheetgory);
        mDatabase.insert(UserDataTable.NAME, null, values);
    }

    public void update(WorkSheet workSheet) {
        String id;
        if(Config.DbSchema.SAVE_ONLY_ONE_COPY) id = Config.DbSchema.LONER_ID;
        ContentValues values = getContentValues(workSheet);

        mDatabase.update(UserDataTable.NAME, values,
                UserDataTable.Cols.ID + " = ?",
                new String[] {id});
    }

    private UserCursorWrapper queryUser(String whereClause, String[] whereArgs) {
        Cursor cursor = mDatabase.query(
                UserDataTable.NAME,
                null,
                whereClause,
                whereArgs,
                null,
                null,
                null
        );

        return new UserCursorWrapper(cursor);
    }

    private ContentValues getContentValues(WorkSheet workSheet) {
        ContentValues values = new ContentValues();
        String id;
        if(Config.DbSchema.SAVE_ONLY_ONE_COPY) id = Config.DbSchema.LONER_ID;
        values.put(UserDataTable.Cols.ID, id);

        values.put(UserDataTable.Cols.GENDER, workSheet.getGender());
        values.put(UserDataTable.Cols.LASTNAME, workSheet.getLastName());
        values.put(UserDataTable.Cols.FIRSTNAME, workSheet.getFirstName());
        values.put(UserDataTable.Cols.MIDDLENAME, workSheet.getMiddleName());
        values.put(UserDataTable.Cols.EMAIL, workSheet.getEmail());
        values.put(UserDataTable.Cols.PHONE, workSheet.getPhone());
        values.put(UserDataTable.Cols.VIN, workSheet.getVin());
        values.put(UserDataTable.Cols.YEAR, workSheet.getYear());

        return values;
    }
}
