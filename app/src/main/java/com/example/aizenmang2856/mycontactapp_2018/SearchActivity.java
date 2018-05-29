package com.example.aizenmang2856.mycontactapp_2018;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

public class SearchActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        //get the intent that started the activity
        Intent intent = getIntent();String message = intent.getStringExtra(MainActivity.EXTRA_MESSAGE);

        //set the string in the textView
        TextView textView = findViewById(R.id.textView4);
        textView.setText(message);
        DatabaseHelper mDB = new DatabaseHelper(this);



    }



}
