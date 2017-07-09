package com.example.debjyoti.tpo_spit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Edit_profile extends AppCompatActivity {
    private EditText name,classname,ten,twelve,cgpa,kt;
    private Button update;

    FirebaseDatabase database;
    DatabaseReference myRef;
    DatabaseReference myRef2;
    String email="";
    String uname,uemail;

    FirebaseAuth mAuth;
    FirebaseUser user;
    //FirebaseAuth.AuthStateListener mAuthListener;

    String TAG = "", cname, pid;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile);
        name=(EditText)findViewById(R.id.name);
        classname=(EditText)findViewById(R.id.classname);
        ten=(EditText)findViewById(R.id.ten);
        twelve=(EditText)findViewById(R.id.twelve);
        cgpa=(EditText)findViewById(R.id.cgpa);
        kt=(EditText)findViewById(R.id.kt);
        update=(Button)findViewById(R.id.update);

        database= FirebaseDatabase.getInstance();
        mAuth = FirebaseAuth.getInstance();
        user=mAuth.getCurrentUser();

        email = getIntent().getStringExtra("email");

        myRef = database.getReference("Company");
        myRef2 = database.getReference("Student");
        Toast.makeText(Edit_profile.this, "mail is"+email, Toast.LENGTH_SHORT).show();




      /*  myRef2.orderByChild("email").equalTo(cname).addListenerForSingleValueEvent(new ValueEventListener() {
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


                }*/
    }
}
