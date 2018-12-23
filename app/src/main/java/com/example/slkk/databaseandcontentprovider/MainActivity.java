package com.example.slkk.databaseandcontentprovider;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private static final String TAG = "MainActivity";
    private Context mContext;
    private MyDatabaseHelper myDatabaseHelper;
    private Button btn_insertData;
    private Button btn_createDatabase;
    private Button btn_updateData;
    private Button btn_deleteData;
    private Button btn_queryData;
    private Button btn_repalceData;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_createDatabase = findViewById(R.id.btn_creatDatabase);
        btn_insertData = findViewById(R.id.btn_addData);
        btn_updateData = findViewById(R.id.btn_updateData);
        btn_deleteData = findViewById(R.id.btn_deleteData);
        btn_queryData = findViewById(R.id.btn_queryData);
        btn_repalceData = findViewById(R.id.btn_replaceData);

        btn_createDatabase.setOnClickListener(this);
        btn_insertData.setOnClickListener(this);
        btn_updateData.setOnClickListener(this);
        btn_deleteData.setOnClickListener(this);
        btn_queryData.setOnClickListener(this);
        btn_repalceData.setOnClickListener(this);
        myDatabaseHelper = new MyDatabaseHelper(this, "book.db", null, 2);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_creatDatabase:
                SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
                break;
            case R.id.btn_addData:
                insertData();
                break;
            case R.id.btn_updateData:
                updateData();
                break;
            case R.id.btn_deleteData:
                deleteData();
                break;
            case R.id.btn_queryData:
                queryData();
                break;
            case R.id.btn_replaceData:
                replaceData();
                break;
        }
    }

    private void replaceData() {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        db.beginTransaction();
        db.delete("User", null, null);
        try {
//            if (true) {
//                throw new NullPointerException();
//            }

            ContentValues contentValues = new ContentValues();
            contentValues.put("name", "sk");
            contentValues.put("age", 34);
            contentValues.put("address", "sh");
            db.insert("User", null, contentValues);
            db.setTransactionSuccessful();

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            db.endTransaction();
        }
    }

    private void queryData() {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        Cursor user = db.query("User", null, null, null, null, null, null);
        if (user.moveToFirst()) {
            do {
                String name = user.getString(user.getColumnIndex("name"));
                int age = user.getInt(user.getColumnIndex("age"));
                String address = user.getString(user.getColumnIndex("address"));
                Log.d(TAG, "queryData: name: " + name + " age: " + age + " address: " + address);
            } while (user.moveToNext());

        }

    }

    private void deleteData() {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        db.delete("User", "name = ?", new String[]{"xMan"});
    }

    private void updateData() {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("address", "shanghai");
        db.update("User", contentValues, "name = ?", new String[]{"slkk"});

    }

    private void insertData() {
        SQLiteDatabase db = myDatabaseHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("name", "slkk");
        contentValues.put("age", 32);
        contentValues.put("address", "shandong");
        db.insert("User", null, contentValues);
        contentValues.clear();

        contentValues.put("name", "yj");
        contentValues.put("age", 44);
        contentValues.put("address", "anhui");
        db.insert("User", null, contentValues);
        contentValues.clear();

        contentValues.put("name", "xMan");
        contentValues.put("age", 100);
        contentValues.put("address", "xj");
        db.insert("User", null, contentValues);
    }
}
