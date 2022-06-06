package com.example.finalprojectmobile;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class LoginActivity extends AppCompatActivity {

    private LoginDatabaseHelper dbHelper;
    public EmailAddress emailAddress = new EmailAddress();

    private EditText email, password;
    private TextView register, infoRegister;
    private Button login;
    private FloatingActionButton twitter, fb, IG;
    private float a = 0;
    public static String userEmail;
    public String userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        dbHelper = new LoginDatabaseHelper(this);

        email = (EditText) findViewById(R.id.et_email);
        password = (EditText) findViewById(R.id.et_password);
        login = (Button) findViewById(R.id.btn_Login);
        register = (TextView) findViewById(R.id.tv_register);

        infoRegister = (TextView) findViewById(R.id.tv1);
        twitter = findViewById(R.id.lg_twitter);
        fb = findViewById(R.id.lg_fb);
        IG = findViewById(R.id.lg_ins);


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                userEmail = email.getText().toString();
                userPassword = password.getText().toString();

                if(userEmail.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Please Re-enter Your Email", Toast.LENGTH_SHORT).show();
                }
                else if(userPassword.equals(""))
                {
                    Toast.makeText(LoginActivity.this, "Invalid Password. Please Re-enter.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    Boolean UserPass = dbHelper.loginCheck(userEmail,userPassword);
                    if(UserPass)
                    {
                        //emailAddress.setEmail(userEmail);
                        Toast.makeText(LoginActivity.this, "Login Successful", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                        finish();
                    }
                    else
                    {
                        Toast.makeText(LoginActivity.this, "Unsuccessful Login", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(getApplicationContext(), RegisterActivity.class);
                startActivity(intent);
            }
        });


        login.setTranslationY(500);
        infoRegister.setTranslationY(500);
        register.setTranslationY(500);
        twitter.setTranslationY(500);
        fb.setTranslationY(500);
        IG.setTranslationY(500);

        login.setAlpha(a);
        infoRegister.setAlpha(a);
        infoRegister.setAlpha(a);
        twitter.setAlpha(a);
        fb.setAlpha(a);
        IG.setAlpha(a);

        login.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        infoRegister.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        register.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        twitter.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(200).start();
        fb.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(400).start();
        IG.animate().translationY(0).alpha(1).setDuration(1000).setStartDelay(600).start();
    }

    public static String getEmail()
    {
        return userEmail;
    }
}
