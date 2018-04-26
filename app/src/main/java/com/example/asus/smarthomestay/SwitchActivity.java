package com.example.asus.smarthomestay;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SwitchActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{

    private static final String SWITCH_URL = "https://smart-homestay.firebaseio.com/";

    private DatabaseReference smartHomestayDatabaseRef;


    private  ToggleButton allSwitch;

    private Switch livingRoom, light,fan ,room1,room2, room3;

    private switch_all smart_homestay;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_switch);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        allSwitch =(ToggleButton) findViewById(R.id.toggleButtonAllSwitch);
        livingRoom=(Switch) findViewById(R.id.switchLivingRoom);
        light = (Switch) findViewById(R.id.switchLight);
        fan = (Switch) findViewById(R.id.switchFan) ;
        room1 = (Switch) findViewById(R.id.switchRoom1);
        room2 = (Switch) findViewById(R.id.switchRoom2);
        room3 = (Switch) findViewById(R.id.switchRoom3);

        //FirebaseDatabase.getInstance().setPersistenceEnabled(true);
        //FirebaseDatabase.getInstance();
        smartHomestayDatabaseRef = FirebaseDatabase.getInstance().getReferenceFromUrl(SWITCH_URL);

        smartHomestayDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
             smart_homestay= dataSnapshot.getValue(switch_all.class);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //test for all switch toggle button


        boolean allSwitchValue= false;

        boolean LivingRoom1Value= false;
        boolean Fan1Value= false;
        boolean Light1Value= false;
        boolean Room_1Value= false;
        boolean Room_2Value= false;
        boolean Room_3Value= false;



        final SharedPreferences sharedPreferencesAllSwitch = getSharedPreferences("isChecked_allSwitch", 0);

        final SharedPreferences sharedPreferencesLivingRoom1 = getSharedPreferences("isChecked_livingRoom", 0);
        final SharedPreferences sharedPreferencesFan1 = getSharedPreferences("isChecked_fan", 0);
        final SharedPreferences sharedPreferencesLight1 = getSharedPreferences("isChecked_light", 0);
        final SharedPreferences sharedPreferencesRoom_1 = getSharedPreferences("isChecked_room1", 0);
        final SharedPreferences sharedPreferencesRoom_2= getSharedPreferences("isChecked_room2", 0);
        final SharedPreferences sharedPreferencesRoom_3 = getSharedPreferences("isChecked_room3", 0);


        allSwitchValue= sharedPreferencesAllSwitch.getBoolean("isChecked_allSwitch",allSwitchValue);

        LivingRoom1Value=sharedPreferencesLivingRoom1.getBoolean("isChecked_livingRoom",LivingRoom1Value);
        Fan1Value=sharedPreferencesFan1.getBoolean("isChecked_fan",Fan1Value);
        Light1Value=sharedPreferencesLight1.getBoolean("isChecked_light",Light1Value);
        Room_1Value=sharedPreferencesRoom_1.getBoolean("isChecked_room1",Room_1Value);
        Room_2Value=sharedPreferencesRoom_2.getBoolean("isChecked_room2",Room_2Value);
        Room_3Value=sharedPreferencesRoom_3.getBoolean("isChecked_room3",Room_3Value);



        allSwitch.setChecked(allSwitchValue);

        livingRoom.setChecked(LivingRoom1Value);
        fan.setChecked(Fan1Value);
        light.setChecked(Light1Value);
        room1.setChecked(Room_1Value);
        room2.setChecked(Room_2Value);
        room3.setChecked(Room_3Value);

        allSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked1) {

                if (isChecked1)
                {
                    sharedPreferencesAllSwitch.edit().putBoolean("isChecked_allSwitch", true).apply();

                    sharedPreferencesLivingRoom1.edit().putBoolean("isChecked_livingRoom",true).apply();
                    sharedPreferencesFan1.edit().putBoolean("isChecked_fan",true).apply();
                    sharedPreferencesLight1.edit().putBoolean("isChecked_light",true).apply();
                    sharedPreferencesRoom_1.edit().putBoolean("isChecked_room1",true).apply();
                    sharedPreferencesRoom_2.edit().putBoolean("isChecked_room2",true).apply();
                    sharedPreferencesRoom_3.edit().putBoolean("isChecked_room3",true).apply();


                    //smart_homestay.setAllSwitch(true);

                    smart_homestay.setLivingRoom(true);
                    smart_homestay.setLight(true);
                    smart_homestay.setFan(true);
                    smart_homestay.setBilik1(true);
                    smart_homestay.setBilik2(true);
                    smart_homestay.setBilik3(true);

                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                    livingRoom.setChecked(true);
                    fan.setChecked(true);
                    light.setChecked(true);
                    room1.setChecked(true);
                    room2.setChecked(true);
                    room3.setChecked(true);

                    String switchCon[]=new String[6];
                    boolean state=true;

                    switchCon[0]="livingRoom";
                    switchCon[1]="fan";
                    switchCon[2]="light";
                    switchCon[3]="bilik1";
                    switchCon[4]="bilik2";
                    switchCon[5]="bilik3";

                    for ( int i=0;i<switchCon.length; i++)
                    {

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(SWITCH_URL).child(switchCon[i]);

                        databaseReference.setValue(state);
                    }

                }
                else
                {
                    sharedPreferencesAllSwitch.edit().putBoolean("isChecked_allSwitch", false).apply();

                    sharedPreferencesLivingRoom1.edit().putBoolean("isChecked_livingRoom",false).apply();
                    sharedPreferencesFan1.edit().putBoolean("isChecked_fan",false).apply();
                    sharedPreferencesLight1.edit().putBoolean("isChecked_light",false).apply();
                    sharedPreferencesRoom_1.edit().putBoolean("isChecked_room1",false).apply();
                    sharedPreferencesRoom_2.edit().putBoolean("isChecked_room2",false).apply();
                    sharedPreferencesRoom_3.edit().putBoolean("isChecked_room3",false).apply();

                    //smart_homestay.setAllSwitch(false);

                    smart_homestay.setLivingRoom(false);
                    smart_homestay.setLight(false);
                    smart_homestay.setFan(false);
                    smart_homestay.setBilik1(false);
                    smart_homestay.setBilik2(false);
                    smart_homestay.setBilik3(false);

                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                    livingRoom.setChecked(false);
                    fan.setChecked(false);
                    light.setChecked(false);
                    room1.setChecked(false);
                    room2.setChecked(false);
                    room3.setChecked(false);

                    String switchCon[]=new String[6];
                    boolean state=false;

                    switchCon[0]="livingRoom";
                    switchCon[1]="fan";
                    switchCon[2]="light";
                    switchCon[3]="bilik1";
                    switchCon[4]="bilik2";
                    switchCon[5]="bilik3";

                    for ( int i=0;i<switchCon.length; i++)
                    {

                        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(SWITCH_URL).child(switchCon[i]);

                        databaseReference.setValue(state);
                    }

                }

            }
        });


        boolean livingRoomValue= false;

        final SharedPreferences sharedPreferencesLivingRoom = getSharedPreferences("isChecked2", 0);

        livingRoomValue = sharedPreferencesLivingRoom.getBoolean("isChecked2",livingRoomValue);

        livingRoom.setChecked(livingRoomValue);

        livingRoom.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked2) {

                if (isChecked2)
                {
                    sharedPreferencesLivingRoom.edit().putBoolean("isChecked2", true).apply();
                    smart_homestay.setLivingRoom(true);
                    String switch7 = "livingRoom";
                    boolean state7 = smart_homestay.isLivingRoom();

                    updateSwitch(switch7,state7);

                    //smartHomestayDatabaseRef.setValue(smart_homestay);


                }
                else
                {
                    sharedPreferencesLivingRoom.edit().putBoolean("isChecked2", false).apply();
                    smart_homestay.setLivingRoom(false);

                    String switch7 = "livingRoom";
                    boolean state7 = smart_homestay.isLivingRoom();

                    updateSwitch(switch7,state7);

                    //smartHomestayDatabaseRef.setValue(smart_homestay);


                }
            }
        });


        boolean fanValue= false;

        final SharedPreferences sharedPreferencesFan = getSharedPreferences("isChecked3", 0);

        fanValue = sharedPreferencesFan.getBoolean("isChecked3",fanValue);

        fan.setChecked(fanValue);

        fan.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked3) {

                if (isChecked3)
                {
                    sharedPreferencesFan.edit().putBoolean("isChecked3", true).apply();
                    smart_homestay.setFan(true);

                    String switch7 = "fan";
                    boolean state7 = smart_homestay.isFan();

                    updateSwitch(switch7,state7);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
                else
                {
                    sharedPreferencesFan.edit().putBoolean("isChecked3", false).apply();
                    smart_homestay.setFan(false);

                    String switch7 = "fan";
                    boolean state7 = smart_homestay.isFan();

                    updateSwitch(switch7,state7);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
            }
        });

        boolean lightValue= false;

        final SharedPreferences sharedPreferencesLight = getSharedPreferences("isChecked4", 0);

        lightValue = sharedPreferencesLight.getBoolean("isChecked4",lightValue);

        light.setChecked(lightValue);

        light.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked4) {

                if (isChecked4)
                {
                    sharedPreferencesLight.edit().putBoolean("isChecked4", true).apply();
                    smart_homestay.setLight(true);

                    String switch7 = "light";
                    boolean state7 = smart_homestay.isLight();

                    updateSwitch(switch7,state7);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
                else
                {
                    sharedPreferencesLight.edit().putBoolean("isChecked4", false).apply();
                    smart_homestay.setLight(false);

                    String switch7 = "light";
                    boolean state7 = smart_homestay.isLight();

                    updateSwitch(switch7,state7);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);
                }
            }
        });



        boolean room1Value= false;

        final SharedPreferences sharedPreferencesRoom1 = getSharedPreferences("isChecked5", 0);

        room1Value = sharedPreferencesRoom1.getBoolean("isChecked5",room1Value);

        room1.setChecked(room1Value);

        room1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked5) {

                if (isChecked5)
                {
                    sharedPreferencesRoom1.edit().putBoolean("isChecked5", true).apply();
                    smart_homestay.setBilik1(true);

                    String switch7 = "bilik1";
                    boolean state7 = smart_homestay.isBilik1();

                    updateSwitch(switch7,state7);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
                else
                {
                    sharedPreferencesRoom1.edit().putBoolean("isChecked5", false).apply();
                    smart_homestay.setBilik1(false);

                    String switch7 = "bilik1";
                    boolean state7 = smart_homestay.isBilik1();

                    updateSwitch(switch7,state7);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);
                }
            }
        });


        boolean room2Value= false;

        final SharedPreferences sharedPreferencesRoom2 = getSharedPreferences("isChecked6", 0);

        room2Value = sharedPreferencesRoom2.getBoolean("isChecked6",room2Value);

        room2.setChecked(room2Value);

        room2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked6) {

                if (isChecked6)
                {   sharedPreferencesRoom2.edit().putBoolean("isChecked6", true).apply();
                    smart_homestay.setBilik2(true);

                    String switch7 = "bilik2";
                    boolean state7 = smart_homestay.isBilik2();

                    updateSwitch(switch7,state7);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
                else
                {
                    sharedPreferencesRoom2.edit().putBoolean("isChecked6", false).apply();
                    smart_homestay.setBilik2(false);

                    String switch7 = "bilik2";
                    boolean state7 = smart_homestay.isBilik2();

                    updateSwitch(switch7,state7);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }

            }
        });


        boolean room3Value= false;

        final SharedPreferences sharedPreferencesRoom3 = getSharedPreferences("isChecked7", 0);

        room3Value = sharedPreferencesRoom3.getBoolean("isChecked7",room3Value);

        room3.setChecked(room3Value);

        room3.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked7) {
                if (isChecked7)
                {   sharedPreferencesRoom3.edit().putBoolean("isChecked7", true).apply();
                    smart_homestay.setBilik3(true);

                    String switch7 = "bilik3";
                    boolean state7 = smart_homestay.isBilik3();

                    updateSwitch(switch7,state7);

                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
                else
                {
                    sharedPreferencesRoom3.edit().putBoolean("isChecked7", false).apply();
                    smart_homestay.setBilik3(false);

                    String switch7 = "bilik3";
                    boolean state7 = smart_homestay.isBilik3();

                    updateSwitch(switch7,state7);

                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }

            }
        });






    }


    private boolean updateSwitch (String switchCon, boolean state)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(SWITCH_URL).child(switchCon);

        databaseReference.setValue(state);

        Toast.makeText(this,"Switch for "+switchCon+ " is save in the database",Toast.LENGTH_LONG).show();
        return true;
    }

    private boolean updateAllSwitch (String switch1, boolean state1)
    {
        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReferenceFromUrl(SWITCH_URL).child(switch1);


        databaseReference.setValue(state1);

        Toast.makeText(this,"Switch for "+switch1+ " is save in the database",Toast.LENGTH_LONG).show();
        return true;
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
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

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout1);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }


}
