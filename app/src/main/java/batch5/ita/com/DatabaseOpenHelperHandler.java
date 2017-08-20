package batch5.ita.com;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;

/**
 * Created by batch3 on 8/7/17.
 */

public class DatabaseOpenHelperHandler extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "contactManager";
    public static final int DATABASE_VERSION = 1;

    public static final String TABLE_CONTENTS = "contacts";
    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_PHONE = "phone";

    public DatabaseOpenHelperHandler(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE " + TABLE_CONTENTS + " ( "
                + KEY_ID + " INTEGER PRIMARY KEY, "
                + KEY_NAME + " TEXT, "
                + KEY_PHONE + " TEXT " + ") ";

        db.execSQL(createTable);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void addContact(String name, String phone) {

        SQLiteDatabase database = getWritableDatabase();

        ContentValues cv = new ContentValues();
        cv.put(KEY_NAME, name);
        cv.put(KEY_PHONE, phone);

        database.insert(TABLE_CONTENTS, null, cv);
    }

    public ArrayList<SQLiteModule> getData() {

        ArrayList<SQLiteModule> contactList = new ArrayList<>();

        String query = "SELECT * FROM " + TABLE_CONTENTS;

        SQLiteDatabase db = getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        cursor.moveToFirst();


        while (cursor.moveToNext()) {

            SQLiteModule sqLiteModule = new SQLiteModule();

            String id = String.valueOf(cursor.getInt(0));
            sqLiteModule.setDbId(id);

            String name = cursor.getString(1);
            sqLiteModule.setDbName(name);

            String phone = cursor.getString(2);
            sqLiteModule.setDbPhone(phone);

            contactList.add(sqLiteModule);
        }

        return contactList;
    }
}
