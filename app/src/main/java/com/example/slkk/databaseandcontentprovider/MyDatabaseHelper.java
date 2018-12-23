package com.example.slkk.databaseandcontentprovider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

public class MyDatabaseHelper extends SQLiteOpenHelper {
    private static final String TAG = "MyDatabaseHelper";
    private Context mContext;
    private static final String CREATE_BOOK =
            "create table Book ("
                    + "id integer primary key autoincrement, "
                    + "author text, "
                    + "price real, "
                    + "pages integer, "
                    + "name text)";

    private static final String CREATE_USER =
            "create table User ("
                    + "id integer primary key autoincrement, "
                    + "name text, "
                    + "age integer, "
                    + "address text)";

    public MyDatabaseHelper(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
        mContext = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_BOOK);
        Toast.makeText(mContext, "create succeeded", Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        Log.d(TAG, "onUpgrade: ");
        if (newVersion == 2) {
            db.execSQL(CREATE_USER);
            Toast.makeText(mContext, "升级数据库", Toast.LENGTH_LONG).show();
        }
    }
}
