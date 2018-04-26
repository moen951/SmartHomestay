package com.example.asus.smarthomestay;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.TimePicker;

import java.util.Calendar;

public class OutsideSwitch extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

//    private static final String SWITCH_URL = "https://smart-homestay.firebaseio.com/";
//
//    private DatabaseReference smartHomestayDatabaseRef;
//
//    private switch_all smart_homestay;

    AlarmManager switch_alarm;
    TimePicker time_Picker;
    TextView switchCondition;
    Button onSwitchOut,offSwitchOut;
    Context context;
    PendingIntent pendingIntent;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_outside_switch);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

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


        this.context=this;

        switch_alarm = (AlarmManager) getSystemService(ALARM_SERVICE);

        time_Picker= findViewById(R.id.timePicker);
        //set the time picker in 12 hour view
        time_Picker.setIs24HourView(false);


        switchCondition= findViewById(R.id.switchCondition);

        //create an instance for calendar
        final Calendar calendar = Calendar.getInstance();

        //creating intent for time picker
        final Intent intent = new Intent(this.context, SwitchTimeReceiver.class);


        onSwitchOut=(Button) findViewById(R.id.onSwitchOut);

        //creating the onClick Listener
        onSwitchOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {



                //setting calendar to the time picker
                calendar.set(Calendar.HOUR_OF_DAY, time_Picker.getCurrentHour());
                calendar.set(Calendar.MINUTE,time_Picker.getCurrentMinute());

                //get the value of the time picker and change it into string

                int hour= time_Picker.getCurrentHour();
                int minute= time_Picker.getCurrentMinute();

                String hour_string = String.valueOf(hour);
                String minute_string = String.valueOf(minute);

                //convert 24 hour to 12 hour
                if(hour>12)
                {
                    hour_string=String.valueOf(hour - 12);
                }

                if(minute<10)
                {
                    minute_string="0" + String.valueOf(minute);
                }

                switchCondition("Suis akan dibuka pada "+ hour_string+":"+minute_string);
                switchCondition.setTextColor(Color.GREEN);

                // create pending intent to delay the intent until the time arrive

                pendingIntent= PendingIntent.getBroadcast(OutsideSwitch.this, 0,
                        intent, PendingIntent.FLAG_UPDATE_CURRENT);

                //set the alarm manager
                switch_alarm.setRepeating(AlarmManager.RTC_WAKEUP,calendar.getTimeInMillis(),AlarmManager.INTERVAL_DAY, pendingIntent);


//                smart_homestay.setLampuLuar(!smart_homestay.isLampuLuar());
//                smartHomestayDatabaseRef.setValue(smart_homestay);
            }
        });


        offSwitchOut=(Button)findViewById(R.id.offSwitchOut);
        offSwitchOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                switchCondition("SUIS TUTUP");
                switchCondition.setTextColor(Color.RED);

                //cancel alarm

                switch_alarm.cancel(pendingIntent);
            }

        });



    }

    private void switchCondition(String output) {
        switchCondition.setText(output);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.rooms, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.inHouse)
        {
            // Handle the camera action
            Intent intent = new Intent(this , SwitchActivity.class);
            startActivity(intent);

        }

        else if (id == R.id.outHouse)
        {
            Intent intent = new Intent(this , OutsideSwitch.class);
            startActivity(intent);
        }

        else if (id == R.id.signout)
        {

        }

        DrawerLayout drawer = findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

}
