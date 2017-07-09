package com.example.debjyoti.tpo_spit;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Send_Activity extends AppCompatActivity {
    Button btnSendEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_send_);

        btnSendEmail = (Button) findViewById(R.id.mail);
        btnSendEmail.setOnClickListener(new View.OnClickListener()
        {
            public void onClick(View v)
            {
                String[] to = {""}; // put a mail id
                String[] cc = {""};
                sendEmail(to, cc, "Hello", "Hello my friends!");
            }
        });


    }

    private void sendEmail(String[] emailAddresses,
                           String[] carbonCopies, String subject, String message)
    {
        Intent emailIntent = new Intent(Intent.ACTION_SEND); // 3 parameters required 1. Action 2. data 3. type
        emailIntent.setData(Uri.parse("mailto:"));
        String[] to = emailAddresses;
        String[] cc = carbonCopies;
        emailIntent.putExtra(Intent.EXTRA_EMAIL, to); // Intent.EXTRA_EMAIL is a predefined Key and "to" is its value.
        emailIntent.putExtra(Intent.EXTRA_CC, cc);
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);
        emailIntent.putExtra(Intent.EXTRA_TEXT, message);
        emailIntent.setType("message/rfc822");

        startActivity(Intent.createChooser(emailIntent, "Email"));
        //startActivity(emailIntent);
    }


}
