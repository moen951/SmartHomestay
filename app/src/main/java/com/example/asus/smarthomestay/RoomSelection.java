package com.example.asus.smarthomestay;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class RoomSelection extends AppCompatActivity {

    private static final String SWITCH_URL = "https://smart-homestay.firebaseio.com/";



    Button house1, house2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_room_selection);

        house1= (Button) findViewById(R.id.house1);
        house2= (Button) findViewById(R.id.house2);


        final DatabaseReference table_house1 =FirebaseDatabase.getInstance().getReference();
        DatabaseReference status1 = table_house1.child("House").child("Room1").child("status1");

        status1.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                boolean status= dataSnapshot.getValue(boolean.class);

                Toast.makeText(RoomSelection.this, "Status 1:"+status, Toast.LENGTH_SHORT).show();

                if (status)
                {
                    house1.setEnabled(false);
                }


            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



}
}
