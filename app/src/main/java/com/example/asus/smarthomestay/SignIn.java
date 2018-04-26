package com.example.asus.smarthomestay;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SignIn extends AppCompatActivity {

    EditText userName,password;
    Button btnSignIn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);


        userName=(EditText) findViewById(R.id.userName);
        password=(EditText) findViewById(R.id.password);
        btnSignIn=(Button) findViewById(R.id.btnSignIn);

        FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference table_user = database.getReference("user");

        btnSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if(TextUtils.isEmpty(userName.getText().toString()) || TextUtils.isEmpty(password.getText().toString()))
                {
                    Toast.makeText(SignIn.this, "Please Insert Username And Password!", Toast.LENGTH_SHORT).show();
                } else {
                    final ProgressDialog mDialog = new ProgressDialog(SignIn.this);
                    mDialog.setMessage("Please Wait...");
                    mDialog.show();

                    table_user.addListenerForSingleValueEvent (new ValueEventListener(){
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {



                            //Check if user not exist in database
                            if (dataSnapshot.child(userName.getText().toString()).exists()) {
                                //Get User Information
                                mDialog.dismiss();
                                switch_all user = dataSnapshot.child(userName.getText().toString()).getValue(switch_all.class);
                                user.setName(userName.getText().toString());

                                if (user.getPassword().equals(password.getText().toString())) {
                                    {
                                        Intent homeIntent = new Intent(SignIn.this,SwitchActivity.class);
                                        Common.currentUser = user;
                                        startActivity(homeIntent);
                                        finish();
                                    }

                                } else {
                                    Toast.makeText(SignIn.this, "Incorrect Password !", Toast.LENGTH_SHORT).show();
                                }
                            } else {
                                mDialog.dismiss();
                                Toast.makeText(SignIn.this, "User doesn't exist in Database!", Toast.LENGTH_SHORT).show();
                            }


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError){
                        }
                    });
                }

            }
        });

    }
}