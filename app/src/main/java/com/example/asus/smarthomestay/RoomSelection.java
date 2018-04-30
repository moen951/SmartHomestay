package com.example.asus.smarthomestay;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomSelection extends AppCompatActivity {

    private static final String SWITCH_URL = "https://smart-homestay.firebaseio.com/";

    Context context;



    Button house1, house2;
    SharedPreferences sharedpreferences;
    public static final String MyPREFERENCES = "MyPrefs" ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selection);

        this.context=this;

        house1= (Button) findViewById(R.id.house1);
        house2= (Button) findViewById(R.id.house2);
        sharedpreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);


        final DatabaseReference table_house1 =FirebaseDatabase.getInstance().getReference();
        final DatabaseReference status1 = table_house1.child("House").child("Room1").child("status1");

        status1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        final DatabaseReference table_house2 =FirebaseDatabase.getInstance().getReference();
        final DatabaseReference status2 = table_house2.child("House").child("Room2").child("status2");

        status2.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(final DataSnapshot dataSnapshot) {
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

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



}
}
