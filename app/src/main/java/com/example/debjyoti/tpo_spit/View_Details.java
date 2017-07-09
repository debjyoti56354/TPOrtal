package com.example.debjyoti.tpo_spit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import static android.R.attr.value;

public class View_Details extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef2;
    TextView name;
    Button apply;
    TextView elg;
    private String compname, elig, position, mail,uid;
    FirebaseAuth mAuth;
    FirebaseUser user;
    //FirebaseAuth.AuthStateListener mAuthListener;

    String TAG = "", cname, pid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view__details);
        name = (TextView) findViewById(R.id.compname);
        elg = (TextView) findViewById(R.id.elg);
        apply = (Button) findViewById(R.id.apply);
        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();
        if(user!=null)
            mail=user.getEmail();
        //System.out.println(mail);
        myRef = database.getReference("Company");
        myRef2 = database.getReference("Student");

       /* mAuthListener = new FirebaseAuth.AuthStateListener() {

            @Override
            public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                user = firebaseAuth.getCurrentUser();
                if (user != null)
                    mail = user.getEmail();

            }
        };
        FirebaseAuth.getInstance().addAuthStateListener(mAuthListener);*/
        //System.out.println(mail);
        myRef2.orderByChild("email").equalTo(mail).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot datasnap:dataSnapshot.getChildren())
                    uid=datasnap.getKey();
                //          System.out.println(uid);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        position = Integer.toString(getIntent().getIntExtra("position", 0));
        cname = getIntent().getStringExtra("cname");
        //System.out.println(uid);
        Toast.makeText(this, cname, Toast.LENGTH_SHORT).show();
        // String id=getIntent().getStringExtra("id");


        myRef.orderByChild("cname").equalTo(cname).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //  values = dataSnapshot1.getKey();
                    pid = dataSnapshot1.getKey();
                    System.out.println(pid);
                    Toast.makeText(View_Details.this, "PID:" + pid, Toast.LENGTH_SHORT).show();
                    myRef.child(pid).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            compname = dataSnapshot.child("cname").getValue().toString();
                            elig = dataSnapshot.child("eligibilty").getValue().toString();
                            name.setText(compname);
                            elg.setText(elig);


                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });


                }
                //      compname=dataSnapshot.child("cname").getValue().toString();
                //elig=dataSnapshot.child("eligibilty").getValue().toString();

                //name.setText(compname);
                //elg.setText(elig);


                Log.d(TAG, "Value is: " + value);
            }


            //  Toast.makeText(this, "Received",Toast.LENGTH_LONG).show();

            @Override
            public void onCancelled(DatabaseError error) {

                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
        System.out.println(position);
        System.out.println(pid);
        System.out.println(uid);

        apply.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                myRef2.child(uid).child("Apply").child(position).setValue(pid);
            }
        });

    }
/*

    @Override
    public void onStart() {
        super.onStart();
        mAuth.addAuthStateListener(mAuthListener);
    }

    @Override
    public void onStop() {
        super.onStop();
        if (mAuthListener != null) {
            mAuth.removeAuthStateListener(mAuthListener);
        }
    }*/

}

