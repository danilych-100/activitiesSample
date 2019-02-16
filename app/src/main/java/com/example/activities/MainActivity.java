package com.example.activities;

import android.content.Intent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private final int SIMPLE_REQUEST_CODE = 12345;

    public static final String CONTACTS = "CONTACTS";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent simpleIntent = new Intent(this, SecondaryActivity.class);
        startActivityForResult(simpleIntent, SIMPLE_REQUEST_CODE);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if(requestCode == SIMPLE_REQUEST_CODE){
            final TextView contactsView = findViewById(R.id.contactsData);

            if(data != null){
                ArrayList<String> names = data.getStringArrayListExtra(CONTACTS);
                StringBuilder sb = new StringBuilder();
                for(String name : names) {
                    sb.append(getString(R.string.contactName))
                            .append(name)
                            .append("\n");
                }
                String showText = sb.length() != 0 ?
                        sb.toString() :
                        getString(R.string.noContactsName);
                contactsView.setText(showText);
            } else {
                contactsView.setText(getString(R.string.noContactsName));
            }

        }
    }
}
