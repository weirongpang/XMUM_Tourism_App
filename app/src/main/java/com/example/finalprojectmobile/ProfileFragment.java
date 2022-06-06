package com.example.finalprojectmobile;

import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

public class ProfileFragment extends Fragment{

    public LoginDatabaseHelper loginDatabaseHelper;
    EmailAddress emailAddress;

    private EditText Name, Email, Contact, Password;
    private Button btnUpdate;
    private Button btnLogOut;
    String identityEmail;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        ViewGroup root = (ViewGroup) inflater.inflate(R.layout.fragment_profile, container, false);

        ((AppCompatActivity) getActivity()).getSupportActionBar().hide();

        loginDatabaseHelper = new LoginDatabaseHelper(getActivity());

        Name = root.findViewById(R.id.et_profileName);
        Email = root.findViewById(R.id.et_profileEmail);
        Contact = root.findViewById(R.id.et_profileContact);
        Password = root.findViewById(R.id.et_profilePassword);
        btnUpdate = root.findViewById(R.id.btn_profileUpdate);
        btnLogOut = root.findViewById(R.id.logoutbtn);

        identityEmail = LoginActivity.getEmail();
        Email.setText(identityEmail);
        Email.setFocusable(false);

        Cursor res = loginDatabaseHelper.getUserDetails(identityEmail);
        if (res.getCount() == 0)
        {
            //show message
            showMessage("Error", "No data found");
        }
        if(res.moveToNext())
        {
            Name.setText(res.getString(1));
            Password.setText(res.getString(2));
            Contact.setText(res.getString(3));
        }

        btnLogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(getActivity(), LoginActivity.class);
                startActivity(intent);
                getActivity().finish();
            }
        });

        btnUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = Email.getText().toString();
                String name = Name.getText().toString();
                String contact = Contact.getText().toString();
                String password = Password.getText().toString();


                if(name.equals(""))
                {
                    Toast.makeText(getActivity(), "Please Enter Your Name.", Toast.LENGTH_SHORT).show();
                }
                else if(contact.equals(""))
                {
                    Toast.makeText(getActivity(), "Please Enter YourContact.", Toast.LENGTH_SHORT).show();
                }
                else if(password.equals(""))
                {
                    Toast.makeText(getActivity(), "Please Enter YourContact.", Toast.LENGTH_SHORT).show();
                }
                else
                {
                    boolean Updated = loginDatabaseHelper.updateUserDetails(email,name,contact,password);
                    if(Updated == true)
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                        alertDialog.setTitle("Update Successful")
                                .setCancelable(true)
                                .setMessage("Your Personal Details Has Successfully Updated")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                    else
                    {
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(getActivity());
                        alertDialog.setTitle("Update Fail")
                                .setCancelable(true)
                                .setMessage("Your Personal Details Has Fail to Update")
                                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        dialog.dismiss();
                                    }
                                });
                        alertDialog.show();
                    }
                }
            }
        });

        return root;
    }

    private void showMessage(String title, String Message) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(Message);
        builder.show();
    }
}
