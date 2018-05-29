package com.example.aizenmang2856.mycontactapp_2018;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.security.PublicKey;

public class MainActivity extends AppCompatActivity {

    DatabaseHelper myDb;
    EditText editName;
    EditText editNumber;
    EditText editAddress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editName = findViewById(R.id.editText_name);
        editNumber = findViewById(R.id.editText_number);
        editAddress = findViewById(R.id.editText_address);

        myDb = new DatabaseHelper(this);
        Log.d("MyContactApp", "DatabaseHelper: instantiated myDb");
    }

    public void addData(View view){
        Log.d("MyContactApp", "DatabaseHelper: Add contact button pressed");

        boolean isInserted = myDb.insertData(editName.getText().toString(), editNumber.getText().toString(), editAddress.getText().toString());


        if(isInserted == true)
            Toast.makeText(MainActivity.this, "Success- contact inserted", Toast.LENGTH_LONG).show();
        else{
            Toast.makeText(MainActivity.this, "Failed- contact not inserted", Toast.LENGTH_LONG).show();
        }

    }

    public void viewData (View view){
        Cursor res = myDb.getAllData();

        Log.d("MyContactApp", "MainActivity: viewData: recieved cursor");

        if (res.getCount() == 0){
            showMessage("Error", "No data foind in database");
            return;
        }

        StringBuffer buffer = new StringBuffer();
        while (res.moveToNext()){
            //append res column 0,1,2,3 to the buffer - see StringBuffer and Cursor api's
            //Delimit each of the "appends" with a line feed "\n"
            buffer.append("                 - Contact " + res.getString(0) + " - " + "\n");
            buffer.append(" Name: " + res.getString(1) + "\n");
            buffer.append(" Number: " + res.getString(2) + "\n");
            buffer.append(" Address: " + res.getString(3) + "\n\n");
        }
        showMessage("Data", buffer.toString());

    }

    private void showMessage(String title, String message) {
        Log.d("MyContactApp", "MainActivity: showMessage: assembling allert");
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }


    public static final String EXTRA_MESSAGE = "com.example.aizenmang2856.mycontactapp_2018.MESSAGE";
    public void searchRecord(View view){
        Log.d("MyContactApp", "MainActivity: launching searchActivity");
        Intent intent = new Intent(this, SearchActivity.class);
        intent.putExtra(EXTRA_MESSAGE, editName.getText().toString());
        startActivity(intent);
    }

}