package com.example.finalprojectmobile;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class RegisterActivity extends AppCompatActivity {

    private LoginDatabaseHelper dbHelper;

    private EditText email, username, password, contact;
    private Button signup;
    private ImageButton back_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        getSupportActionBar().hide();

        dbHelper = new LoginDatabaseHelper(this);

        email = (EditText) findViewById(R.id.et_RegisterEmail);
        username = (EditText) findViewById(R.id.et_RegisterName);
        password = (EditText) findViewById(R.id.et_RegisterPassword);
        contact = (EditText) findViewById(R.id.et_RegisterContact);
        signup = (Button) findViewById(R.id.btn_SignUp);
        back_button = (ImageButton) findViewById(R.id.btn_back);

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String userEmail = email.getText().toString();
                String userName = username.getText().toString();
                String userPassword = password.getText().toString();
                String userContact = contact.getText().toString();

                if(userEmail.equals("") || userName.equals("") || userPassword.equals("") || userContact.equals(""))
                {
                    Toast.makeText(RegisterActivity.this, "Please Enter All the Required Information", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Boolean userExist = dbHelper.checkUserEmail(userEmail);
                    if(!userExist)
                    {
                        Boolean registered = dbHelper.register(userEmail,userName,userPassword,userContact);
                        if(registered)
                        {
                            Toast.makeText(RegisterActivity.this, "You have Successfully Registered", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                        else
                        {
                            Toast.makeText(RegisterActivity.this, "Your Registration Failed", Toast.LENGTH_LONG).show();
                            Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                            startActivity(intent);
                        }
                    }
                    else
                    {
                        Toast.makeText(RegisterActivity.this, "The Email Address Already Exist", Toast.LENGTH_LONG).show();

                    }
                }
            }
        });

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(intent);
            }
        });

    }
}
