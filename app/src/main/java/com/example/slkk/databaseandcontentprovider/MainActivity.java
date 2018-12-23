package com.example.slkk.databaseandcontentprovider;

import android.content.Context;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Context mContext;
    private MyDatabaseHelper myDatabaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btn_createDatabase = findViewById(R.id.btn_click);
        btn_createDatabase.setOnClickListener(this);

        myDatabaseHelper = new MyDatabaseHelper(this, "book.db", null, 1);
    }

    @Override
    public void onClick(View v) {
        myDatabaseHelper.getWritableDatabase();
    }
}
