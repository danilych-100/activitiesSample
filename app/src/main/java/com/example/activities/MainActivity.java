package com.example.activities;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int SIMPLE_REQUEST_CODE = 12345;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent simpleIntent = new Intent(this, SecondaryActivity.class);
        startActivityForResult(simpleIntent, SIMPLE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (data == null) {return;}

        if(requestCode == SIMPLE_REQUEST_CODE){
            ArrayList<String> names = data.getStringArrayListExtra(Extras.CONTACTS);
            for(String name : names){
                Log.d("1", name);
            }
        }
    }
}
