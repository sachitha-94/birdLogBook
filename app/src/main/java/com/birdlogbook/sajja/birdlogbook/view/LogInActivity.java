package com.birdlogbook.sajja.birdlogbook.view;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.birdlogbook.sajja.birdlogbook.R;
import com.birdlogbook.sajja.birdlogbook.db.BackGroundWorker;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
        guiCreat();
    }
    private void offLineButtonOnclick(View view){

        startActivity(new Intent(this,LogBookActivity.class));
    }
    private void createAccButtonOnclick(View view){
        startActivity(new Intent(this,SignInActivity.class));

    }
    private void loginButtonOnclick(View view){
        String email = emailText.getText().toString();
        String password = passwordText.getText().toString();

        if(!email.equals("") && !password.equals("")){
            startActivity(new Intent(this,LogBookActivity.class));
        }else {
            Toast.makeText(this,"enter email and password", Toast.LENGTH_SHORT).show();
        }


    }
     private void onLogIn(){
         String email=emailText.getText().toString();
         String password=passwordText.getText().toString();
         String type="logIn";
        BackGroundWorker backGroundWorker=new BackGroundWorker(this);

        backGroundWorker.execute(type,email,password);
         String result = backGroundWorker.getResult();
         System.out.println("**********************************************");
         System.out.println("*****************");
         System.out.println("*****************");
         System.out.println("--"+result);
         System.out.println("*****************");
         System.out.println("**************************************");
         if (result.equals("login success")){
             startActivity(new Intent(this,LogBookActivity.class));
         }

     }

    private void guiCreat(){
        emailText=(TextView) findViewById(R.id.emailText);
        passwordText=(TextView)findViewById(R.id.passwordText);
        createAccButton=(Button)findViewById(R.id.createAccBtn);
        logInButton=(Button)findViewById(R.id.logInBtn);
        offlineButton=(Button)findViewById(R.id.offLineBtn);

        progressDialog=new ProgressDialog(this);


        emailText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //emailTextOnclick(v);

            }
        });
        passwordText.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               // passwordOnclick(v);
            }
        });
        createAccButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                createAccButtonOnclick(v);
            }
        });

        offlineButton.setOnClickListener(
                new View.OnClickListener() {
            @Override
            public void onClick(View v) {
               offLineButtonOnclick(v);
            }
        });

        logInButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                onLogIn();
                loginButtonOnclick(view);
            }
        });

    }

    //guiDefine
    private TextView emailText;
    private TextView passwordText;
    private Button createAccButton;
    private Button offlineButton;
    private Button logInButton;
    private ProgressDialog progressDialog;

}
