package com.example.debjyoti.tpo_spit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_Company extends AppCompatActivity {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    private EditText cname;
    private EditText eligibilty;
    private EditText deadline;
    private Button add;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add__company);
        cname=(EditText)findViewById(R.id.cname);
        eligibilty=(EditText)findViewById(R.id.eligibilty);
        deadline=(EditText)findViewById(R.id.deadline);
        add=(Button)findViewById(R.id.add);
        myRef = database.getReference("Company").child("1");
      //  myRef.setValue("Company");


        add.setOnClickListener(new View.OnClickListener() {



          //  Toast.makeText(Add_Company.this, "Data Saved Successfully."+cn+elg,Toast.LENGTH_SHORT).show();


          @Override
            public void onClick(View v) {

                                       //  cn=cmp.getName()  ;
               // elg=cmp.getEligibilty();
              String cn=cname.getText().toString();
              String dd=deadline.getText().toString();
              String elg=eligibilty.getText().toString().trim();
              Company cmp = new Company(cn,elg,dd);
              myRef.child("cname").setValue(cn);
              myRef.child("eligibilty").setValue(elg);
              myRef.child("deadline").setValue(cn);
              Toast.makeText(Add_Company.this, "Data Saved Successfully."+cn+elg, Toast.LENGTH_SHORT).show();

            }
        });









    }
}
