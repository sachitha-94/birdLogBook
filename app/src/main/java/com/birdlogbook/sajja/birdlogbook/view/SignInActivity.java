package com.birdlogbook.sajja.birdlogbook.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.birdlogbook.sajja.birdlogbook.db.BackGroundWorker;
import com.birdlogbook.sajja.birdlogbook.validation.SpinnerListCreate;

import java.util.List;

public class SignInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_in);
        guiCreate();

        //set user List
        List<String> userList = SpinnerListCreate.setUserType();
        ArrayAdapter<String> userListArrayAdapter=new ArrayAdapter<String>(this,android.R.layout.simple_spinner_item,userList);
        userListArrayAdapter.setDropDownViewResource(android.R.layout.simple_spinner_item);
        userTypeSpinner.setAdapter(userListArrayAdapter);
    }

    private void onRegiser(){
        String firstName = firstNameText.getText().toString();
        String lastName = lastNameText.getText().toString();
        String userName = userNameText.getText().toString();
        String email=emailText.getText().toString();
        String password=passwordText.getText().toString();
        String rePassword=rePasswordText.getText().toString();
        String userType = userTypeSpinner.getSelectedItem().toString();

        String type = "register";

        if (!firstName.equals("")&&!lastName.equals("")&&!userName.equals("")&&!email.equals("")&&!password.equals("")&&!rePassword.equals("")&&!userType.equals("")) {

            if (password.equals(rePassword)) {
                BackGroundWorker backGroundWorker = new BackGroundWorker(this);
                backGroundWorker.execute(type, firstName, lastName,userName,userType,email,password);
                startActivity(new Intent(this,LogBookActivity.class));
            }else{
                startActivity(new Intent(this,LogBookActivity.class));

            }
        }else{
            Toast.makeText(this, "Fill All fields", Toast.LENGTH_SHORT).show();
        }
    }

    private void guiCreate(){


        userTypeSpinner=(Spinner)findViewById(R.id.userTypeSpinner);

        progressDialog=new ProgressDialog(this);

        signUpButton=(Button)findViewById(R.id.signInButton);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               onRegiser();

            }
        });

        firstNameText=(TextView)findViewById(R.id.firstNameText);
        lastNameText=(TextView)findViewById(R.id.lastNameText);
        emailText=(TextView)findViewById(R.id.emailText);
        userNameText=(TextView)findViewById(R.id.userNameText);
        passwordText=(TextView)findViewById(R.id.passwordText);
        rePasswordText=(TextView)findViewById(R.id.reEnterPasswordText);


    }

    //guiDefine
    private TextView firstNameText;
    private TextView lastNameText;
    private TextView emailText;
    private TextView userNameText;
    private TextView passwordText;
    private TextView rePasswordText;

    private ProgressDialog progressDialog;

    private Button signUpButton;

    private Spinner userTypeSpinner;
}

