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
            final LinearLayout rl = findViewById(R.id.MainLayout);
            rl.addView(createTextView("Контакты"));
            for(String name : names) {
                rl.addView(createTextView(String.format("Имя контакта: %s", name)));
            }
        }
    }

    private TextView createTextView(final String text){
        TextView mainText = new TextView(this);
        mainText.setText(text);
        return mainText;
    }
}
