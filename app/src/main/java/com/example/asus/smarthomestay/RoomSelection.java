package com.example.asus.smarthomestay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomSelection extends AppCompatActivity {

    private static final String SWITCH_URL = "https://smart-homestay.firebaseio.com/";

    Context context;

    private switch_all smart_homestay;

    Button house1, house2;
    Switch outsideRoom1,outsideRoom2,corridorSwitch;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selection);

        this.context=this;

        house1= (Button) findViewById(R.id.house1);
        house2= (Button) findViewById(R.id.house2);
        outsideRoom1=(Switch) findViewById(R.id.outsideRoom1);
        outsideRoom2=(Switch) findViewById(R.id.outsideRoom2);
        corridorSwitch=(Switch) findViewById(R.id.corridorSwitch);

        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);



        final DatabaseReference table_house1 =FirebaseDatabase.getInstance().getReference();
        final DatabaseReference status1 = table_house1.child("House").child("Room1").child("status1");

        status1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        boolean status= dataSnapshot.getValue(boolean.class);

//                Toast.makeText(RoomSelection.this, "Status 1:"+status, Toast.LENGTH_SHORT).show();

                        if (status)
                        {
                            house1.setEnabled(false);
                        }else {
                            house1.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    SharedPreferences.Editor edit = sharedpreferences.edit();
//                            Toast.makeText(getApplicationContext(), room+" "+stat, Toast.LENGTH_SHORT).show();
                                    edit.putString("House","House");
                                    edit.putString("Room", "Room1");
                                    edit.putString("Status", "status1");
                                    edit.commit();
//                            Toast.makeText(getApplicationContext(), sharedpreferences.getAll().toString(), Toast.LENGTH_LONG).show();
//                            System.out.println(sharedpreferences.getAll().toString());
                                    Intent stats =new Intent(RoomSelection.this,SwitchActivity.class);
                                    startActivity(stats);
                                }
                            });
                        }

                    }
                }).start();



            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }



        });

        final DatabaseReference table_house2 =FirebaseDatabase.getInstance().getReference();
        final DatabaseReference status2 = table_house2.child("House").child("Room2").child("status2");

        status2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {

                new Thread(new Runnable() {
                    @Override
                    public void run() {

                        boolean status= dataSnapshot.getValue(boolean.class);

//                Toast.makeText(RoomSelection.this, "Status 1:"+status, Toast.LENGTH_SHORT).show();

                        if (status)
                        {
                            house2.setEnabled(false);
                        }else {
                            house2.setOnClickListener(new View.OnClickListener() {
                                @Override
                                public void onClick(View view) {
                                    SharedPreferences.Editor edit = sharedpreferences.edit();
//                            Toast.makeText(getApplicationContext(), room+" "+stat, Toast.LENGTH_SHORT).show();
                                    edit.putString("House","House");
                                    edit.putString("Room", "Room2");
                                    edit.putString("Status", "status2");
                                    edit.commit();
//                            Toast.makeText(getApplicationContext(), sharedpreferences.getAll().toString(), Toast.LENGTH_LONG).show();
//                            System.out.println(sharedpreferences.getAll().toString());
                                    Intent stats =new Intent(RoomSelection.this,SwitchActivity.class);
                                    startActivity(stats);
                                }
                            });
                        }


                    }
                }).start();


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final DatabaseReference data =FirebaseDatabase.getInstance().getReference();
        final DatabaseReference getData = data.child("House");


        getData.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                boolean outsideRoom1Data = dataSnapshot.child("Room1").child("outsideSwitch").getValue(boolean.class);
                boolean outsideRoom2Data = dataSnapshot.child("Room2").child("outsideSwitch").getValue(boolean.class);
                boolean corridorSwitchData= dataSnapshot.child("corridor").getValue(boolean.class);


                //System.out.println( dataSnapshot.child("corridor").getValue(boolean.class));

                smart_homestay=  new switch_all(outsideRoom1Data,outsideRoom2Data );

                smart_homestay= new switch_all(corridorSwitchData);

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        boolean outsideRoom1Value= false;

        final SharedPreferences sharedPreferencesoutsideRoom1 = getSharedPreferences("isChecked10", 0);

        outsideRoom1Value = sharedPreferencesoutsideRoom1.getBoolean("isChecked10",outsideRoom1Value);

        outsideRoom1.setChecked(outsideRoom1Value);

        outsideRoom1.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked10) {

                if (isChecked10)
                {   sharedPreferencesoutsideRoom1.edit().putBoolean("isChecked10", true).apply();
                    smart_homestay.setOutsideRoom1(true);

                    String room="Room1";
                    String switch7 = "outsideSwitch";
                    boolean state7 = smart_homestay.isOutsideRoom1();
                    String switchName= "luar rumah 1";

                    updateSwitch(room,switch7,state7,switchName);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
                else
                {
                    sharedPreferencesoutsideRoom1.edit().putBoolean("isChecked10", false).apply();
                    smart_homestay.setOutsideRoom1(false);

                    String room="Room1";
                    String switch7 = "outsideSwitch";
                    boolean state7 = smart_homestay.isOutsideRoom1();
                    String switchName= "luar rumah 1";

                    updateSwitch(room,switch7,state7,switchName);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }

            }
        });
//
        boolean outsideRoom2Value= false;

        final SharedPreferences sharedPreferencesoutsideRoom2 = getSharedPreferences("isChecked20", 0);

        outsideRoom2Value = sharedPreferencesoutsideRoom2.getBoolean("isChecked20",outsideRoom2Value);

        outsideRoom2.setChecked(outsideRoom2Value);

        outsideRoom2.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked20) {

                if (isChecked20)
                {   sharedPreferencesoutsideRoom2.edit().putBoolean("isChecked20", true).apply();
                    smart_homestay.setOutsideRoom2(true);

                    String room="Room2";
                    String switch7 = "outsideSwitch";
                    boolean state7 = smart_homestay.isOutsideRoom2();
                    String switchName= "luar rumah 2";

                    updateSwitch(room,switch7,state7,switchName);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
                else
                {
                    sharedPreferencesoutsideRoom2.edit().putBoolean("isChecked20", false).apply();
                    smart_homestay.setOutsideRoom2(false);

                    String room="Room2";
                    String switch7 = "outsideSwitch";
                    boolean state7 = smart_homestay.isOutsideRoom2();
                    String switchName= "luar rumah 2";

                    updateSwitch(room, switch7,state7,switchName);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }

            }
        });


        boolean corridorSwitchValue= false;

        final SharedPreferences sharedPreferencesCorridorSwitch = getSharedPreferences("isChecked", 0);

        corridorSwitchValue = sharedPreferencesCorridorSwitch.getBoolean("isChecked",corridorSwitchValue);

        corridorSwitch.setChecked(corridorSwitchValue);

        corridorSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton compoundButton, boolean isChecked) {

                if (isChecked)
                {  sharedPreferencesCorridorSwitch.edit().putBoolean("isChecked", true).apply();
                    smart_homestay.setCorridorSwitch(true);


                    String switchCor = "corridor";
                    boolean state = smart_homestay.isCorridorSwitch();
                    String switchName= "balkoni";

                    updateSwitch2(switchCor,state,switchName);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }
                else
                {
                    sharedPreferencesCorridorSwitch.edit().putBoolean("isChecked", false).apply();
                    smart_homestay.setCorridorSwitch(false);


                    String switchCor = "corridor";
                    boolean state = smart_homestay.isCorridorSwitch();
                    String switchName= "balkoni";

                    updateSwitch2(switchCor,state,switchName);
                    //smartHomestayDatabaseRef.setValue(smart_homestay);

                }

            }
        });

    }

    private boolean updateSwitch (String room, String switchCon, boolean state, String switchName)
    {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("House").child(room).child(switchCon);

        databaseReference.setValue(state);

        Toast.makeText(this,"Suis untuk "+switchName+ " telah disimpan di dalam pengkalan data",Toast.LENGTH_SHORT).show();
        return true;
    }

    private boolean updateSwitch2 (String switchCon, boolean state, String switchName)
    {

        DatabaseReference databaseReference = FirebaseDatabase.getInstance().getReference().child("House").child(switchCon);

        databaseReference.setValue(state);

        Toast.makeText(this,"Suis untuk "+switchName+ " telah disimpan di dalam pengkalan data",Toast.LENGTH_SHORT).show();
        return true;
    }
}
