package su.zencode.testapp05.UserDataBase;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import su.zencode.testapp05.Config.DbSchema.UserDataTable;

public class UserDataBaseHelper extends SQLiteOpenHelper {
    private static final int VERSION = 1;
    private static final String DATABASE_NAME = "userData.db";

    public UserDataBaseHelper(Context context) {
        super(context, DATABASE_NAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("create table " + UserDataTable.NAME +"(" +
                " _id integer primary key autoincrement, " +
                UserDataTable.Cols.ID + ", " +
                UserDataTable.Cols.GENDER + ", " +
                UserDataTable.Cols.LASTNAME + ", " +
                UserDataTable.Cols.FIRSTNAME + ", " +
                UserDataTable.Cols.MIDDLENAME + ", " +
                UserDataTable.Cols.EMAIL + ", " +
                UserDataTable.Cols.PHONE + ", " +
                UserDataTable.Cols.VIN + ", " +
                UserDataTable.Cols.YEAR +
                ")"
        );
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
