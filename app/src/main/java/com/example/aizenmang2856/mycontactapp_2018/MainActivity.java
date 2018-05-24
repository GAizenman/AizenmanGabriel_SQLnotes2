package com.example.aizenmang2856.mycontactapp_2018;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editNumber;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "DatabaseHelper: instantiated myDb");
    }

    public void addData(View view){
        Log.d("MyContactApp", "DatabaseHelper: Add contact button pressed");

        boolean isInserted;
        isInserted = myDb.insertData(editName.getText().toString(), editNumber.getText().toString());
        if(isInserted == true){
            Toast.makeText(MainActivity.this, "Success- contact inserted", Toast.LENGTH_LONG).show();
        }
        else{
            Toast.makeText(MainActivity.this, "Failed- contact not inserted", Toast.LENGTH_LONG).show();
        }

    }
}