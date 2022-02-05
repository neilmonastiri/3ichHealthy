package com.example.a3ichhealthy;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a3ichhealthy.DBHelper;

public class SignInActivity extends AppCompatActivity {
    EditText username , password;
    androidx.appcompat.widget.AppCompatButton signin;
    DBHelper DB;
    TextView textSignUp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.signin_activity);

        username=findViewById(R.id.EmailOrUsername);
        password=findViewById(R.id.Password);
        signin=findViewById(R.id.SignInBtn);
        textSignUp = findViewById(R.id.RedirectionSignup);
        DB = new DBHelper(this);
        signin.setOnClickListener(new View.OnClickListener() {
           @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass))
                    Toast.makeText(SignInActivity.this,"All fields Required",Toast.LENGTH_SHORT).show();
                else
                {Boolean checkuserpass=DB.checkusernamepassword(user,pass);
                    if(checkuserpass==true){
                        Toast.makeText(SignInActivity.this,"Login Successful",Toast.LENGTH_SHORT).show();
                        Intent intent=new Intent (getApplicationContext(),Home.class);
                        startActivity(intent);
                        overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                    }
                    else
                        Toast.makeText(SignInActivity.this,"User not found!",Toast.LENGTH_SHORT).show();

                }

            }
        });

        textSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (getApplicationContext(),SignUpActivity.class);
                startActivity(intent);

            }
        });


    }

    @Override
    public void finish() {
        super.finish();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
        overridePendingTransition(R.anim.slide_in_left, R.anim.slide_out_right);
    }
}
