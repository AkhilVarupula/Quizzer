// Authors: Varun Lingabathini, Akhil Varapula
package com.example.quizzer;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Patterns;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class EmailUpdateActivity extends AppCompatActivity {
    EditText newEmailAdress;
    Button back;
    Button save;
    String newEmail;
    String user;
    DataBaseHandler mydb;
    Integer uid;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_email_update);
        mydb= new DataBaseHandler(this);
        SessionManagement sessionManagement=new SessionManagement(EmailUpdateActivity.this);
        user=sessionManagement.getName();
        uid=mydb.getID(user);
        newEmailAdress=findViewById(R.id.editTextEmailAddress);
        newEmail=newEmailAdress.getText().toString();
        back=findViewById(R.id.buttonBack);
        // going back to the profileActivity on click of back button
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(),ProfileActivity.class);
                startActivity(intent);
            }
        });
        save=findViewById(R.id.buttonSave);
        // Saving the updated email on clicking of save button
        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(Patterns.EMAIL_ADDRESS.matcher(newEmailAdress.getText().toString()).matches()) {
                    mydb.updateEmail(uid,newEmailAdress.getText().toString());
                    Toast.makeText(getApplicationContext(), "Email Address updated Successfully ", Toast.LENGTH_SHORT).show();
                    Intent intent = new Intent(getApplicationContext(), ProfileActivity.class);
                   startActivity(intent);
                }
                else {
                    Toast.makeText(getApplicationContext(), "Invalid Email Format", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}