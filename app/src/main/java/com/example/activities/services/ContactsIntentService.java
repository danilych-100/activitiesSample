package com.example.activities.services;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.database.Cursor;
import android.provider.ContactsContract;
import android.support.v4.content.LocalBroadcastManager;

import java.util.ArrayList;


public class ContactsIntentService extends IntentService {

    public static final String CONTACTS = "CONTACTS";
    public final static String GET_CONTACTS = "com.example.activities.GET_CONTACTS";
    public final static String BROADCAST_GET_CONTACTS = "com.example.activities.BROADCAST_GET_CONTACTS";

    public ContactsIntentService() {
        super("ContactsIntentService");
    }

    /**
     * Helper для запуска сервиса.
     */
    public static void startGetContactAction(Context context) {
        Intent intent = new Intent(context, ContactsIntentService.class);
        intent.setAction(BROADCAST_GET_CONTACTS);
        context.startService(intent);
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {
            final String action = intent.getAction();
            if (BROADCAST_GET_CONTACTS.equals(action)) {
                ArrayList<String> contacts = handleGetContactAction();

                LocalBroadcastManager lbm = LocalBroadcastManager.getInstance(this);

                Intent contactsIntent = new Intent(GET_CONTACTS);
                contactsIntent.putStringArrayListExtra(CONTACTS, contacts);
                lbm.sendBroadcast(contactsIntent);
            }
        }
    }

    /**
     * Отправляем broadcast в reveiver на получение списка контактов.
     */
    private ArrayList<String> handleGetContactAction() {
        ArrayList<String> contacts = new ArrayList<>();

        Cursor cur = getRequestForContacts();

        if (cur != null){
            while (cur.moveToNext()){
                contacts.add(cur.getString(cur.getColumnIndex(
                        ContactsContract.Contacts.DISPLAY_NAME)));
            }
        }
        if(cur!=null){
            cur.close();
        }

        return contacts;
    }

    private Cursor getRequestForContacts(){
        String[] projection = new String[]{ContactsContract.Contacts.DISPLAY_NAME};
        return getContentResolver().query(ContactsContract.Contacts.CONTENT_URI,
                projection, null, null, null);
    }
}
