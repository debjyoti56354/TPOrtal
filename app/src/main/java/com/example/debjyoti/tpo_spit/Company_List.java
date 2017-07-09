package com.example.debjyoti.tpo_spit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

//import static com.example.debjyoti.tpo_spit.R.id.cname;

public class Company_List extends AppCompatActivity {


    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef;
    ListView lv;
    String[] items;
    private String val;
    String TAG = "";
    String id;
    private String value, values;
    private ArrayList<String> obj;
    private Button logout,edit;
    String mail="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company__list);
        logout=(Button)findViewById(R.id.logout);
        mail = getIntent().getStringExtra("email");

        // String value;

        obj = new ArrayList<String>();
        myRef = database.getReference("Company");
        edit=(Button)findViewById(R.id.edit);

        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    // This method is called once with the initial value and again
                    // whenever data at this location is updated.
                    //  values = dataSnapshot1.getKey();
                //  Toast.makeText(Company_List.this, "Gone", Toast.LENGTH_SHORT).show();
                    value = dataSnapshot1.child("cname").getValue().toString();
                    myRef.orderByChild("cname").equalTo(value).addListenerForSingleValueEvent(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                           Toast.makeText(Company_List.this,"", Toast.LENGTH_SHORT).show();
                            id = dataSnapshot.getValue().toString();
                            //   Toast.makeText(Company_List.this,"ID:"+id,Toast.LENGTH_LONG).show();

                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });

                    obj.add(value);
                    Log.d(TAG, "Value is: " + value);
                }


            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });




        // items = getResources().getStringArray(R.id.l);
        lv = (ListView) findViewById(R.id.lv);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(
                getBaseContext(), android.R.layout.simple_list_item_1,
                obj);

        lv.setAdapter(adapter);


        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                val = lv.getItemAtPosition(position).toString();

                Toast.makeText(Company_List.this, "ID:" + val, Toast.LENGTH_LONG).show();
                final String[] id1 = new String[1];
                Intent intent=new Intent(Company_List.this,View_Details.class);
                intent.putExtra("position",position);
                intent.putExtra("cname",val);
                startActivity(intent);
            }
        });

        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                finish();
            }
        });


        edit.setOnClickListener(new View.OnClickListener()
        {


            public void onClick(View v)
            {
                Intent intent1=new Intent(Company_List.this,Edit_profile.class);
                intent1.putExtra("email",mail);
                Toast.makeText(Company_List.this, "mail is"+mail, Toast.LENGTH_SHORT).show();
                startActivity(intent1);

            }
        });



    }
    /*@Override
    public void onDestroy(){
        super.onDestroy();
        FirebaseAuth.getInstance().signOut();
        finish();
    }

    @Override
    public void onStop(){
        super.onStop();
        FirebaseAuth.getInstance().signOut();
        finish();
    }
*/




}

   /* @Override
   public void onCreateContextMenu(ContextMenu menu, View view,
                                    ContextMenu.ContextMenuInfo menuInfo) {
        // ContextMenuInfo holds more info about the view
        // E.g: If we have registered Context menu Listn to many views then
        // to know addn info about the View we need menuInfo object.
        // Used only if we want to show different Context Menu for different views.

        //super.onCreateContextMenu(menu, view, menuInfo);


    }*/
   /* public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        MenuItem mnu1 = menu.add(0, 0, 0, "Apply");

        //mnu1.setAlphabeticShortcut('a');
        // mnu1.setIcon(R.drawable.icon);

        MenuItem mnu2 = menu.add(0, 1, 1, "View Details");
    }


    @Override
    public boolean onContextItemSelected(MenuItem item) {
        return MenuChoice(item);
    }



    private boolean MenuChoice(MenuItem item) {
        switch (item.getItemId()) {
            case 0:
                Toast.makeText(this, "You have applied",
                        Toast.LENGTH_LONG).show();
                return true;
            case 1: {
                Toast.makeText(this, "View details",
                        Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Company_List.this, View_Details.class);
                Toast.makeText(this, "value" + value + "id" + id,
                        Toast.LENGTH_LONG).show();

                //intent.putExtra("cname", value);


                startActivity(intent);
                return true;
            }
        }
        return false;
    }


    //public void contextmenu() {


  //  }


}*/