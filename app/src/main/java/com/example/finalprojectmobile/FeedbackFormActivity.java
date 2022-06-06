package com.example.finalprojectmobile;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class FeedbackFormActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener
{

    public FeedbackDatabaseHelper dbHelper;

    private EditText Name, Contact, Comment;
    private EditText Date;
    private Button Submit, btnDate;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedbackform);
        getSupportActionBar().hide();

        dbHelper = new FeedbackDatabaseHelper(this);

        Name = (EditText) findViewById(R.id.et_Name);
        Contact = (EditText) findViewById(R.id.et_Contact);
        Comment = (EditText) findViewById(R.id.et_Comment);

        Date = findViewById(R.id.et_Date);

        btnDate = (Button) findViewById(R.id.btn_Date);
        Submit = findViewById(R.id.btn_submit);



        btnDate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatePickerFragment datePicker = new DatePickerFragment();
                datePicker.show(getSupportFragmentManager(), "DatePicker");
            }
        });

        Submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = Name.getText().toString();
                String contact = Contact.getText().toString();
                String comment = Comment.getText().toString();
                String date = Date.getText().toString();


                if(name.equals("") ||contact.equals("") ||comment.equals("") ||date.equals(""))
                {
                    Toast.makeText(FeedbackFormActivity.this, "Please Enter All the Required Information", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Boolean booked = dbHelper.booking(name,contact,comment,date);
                    if(booked)
                    {
                        Toast.makeText(FeedbackFormActivity.this, "You have Successfully Submit the Feedback", Toast.LENGTH_LONG).show();
                        Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                        startActivity(intent);
                    }
                    else
                        Toast.makeText(FeedbackFormActivity.this, "Submission Failed", Toast.LENGTH_LONG).show();
                }
            }
        });

    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        Date = (EditText) findViewById(R.id.et_Date);
        Date.setText(dayOfMonth + " / " + month + " / " + year);
    }
}