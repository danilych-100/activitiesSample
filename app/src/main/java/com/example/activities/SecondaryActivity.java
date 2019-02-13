package com.example.activities;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.support.v4.content.LocalBroadcastManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import com.example.activities.services.ContactsIntentService;

import java.util.List;

public class SecondaryActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_secondary);

        ContactsIntentService.startGetContactAction(this);

        LocalBroadcastManager.getInstance(this).registerReceiver(contactsReceiver, new IntentFilter(Actions.GET_CONTACTS));
    }

    private BroadcastReceiver contactsReceiver = new BroadcastReceiver() {
        @Override
        public void onReceive(Context context, Intent intent) {
            SecondaryActivity.this.setResult(RESULT_OK, intent);
            finish();
        }
    };


}
