package com.example.asus.smarthomestay;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class SwitchTimeReceiver extends BroadcastReceiver {
//    private static final String SWITCH_URL = "https://smart-homestay.firebaseio.com/";
//
//    private DatabaseReference smartHomestayDatabaseRef;
//
//    private switch_all smart_homestay;
    @Override
    public void onReceive(Context context, Intent intent) {

//        smartHomestayDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl(SWITCH_URL);
//
//        smartHomestayDatabaseRef.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(DataSnapshot dataSnapshot) {
//                smart_homestay= dataSnapshot.getValue(switch_all.class);
//            }
//
//            @Override
//            public void onCancelled(DatabaseError databaseError) {
//
//            }
//        });

//        smart_homestay.setLampuLuar(true);
//        smartHomestayDatabaseRef.setValue(smart_homestay);

        Log.e("We are in the receiver", "wow");
    }
}
