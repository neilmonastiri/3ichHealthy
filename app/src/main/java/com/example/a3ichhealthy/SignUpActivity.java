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

public class SignUpActivity extends AppCompatActivity {

    EditText username , password , repassword;
    androidx.appcompat.widget.AppCompatButton signup;
    TextView textSignIn;

    DBHelper DB;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.signup_activity);


        setContentView(R.layout.signup_activity);
        username=findViewById(R.id.EmailOrUsernameSignUp);
        password=findViewById(R.id.PasswordSignUp);
        repassword=findViewById(R.id.ConfirmPassword);
        signup=findViewById(R.id.SignUpBtn_2);
        textSignIn = findViewById(R.id.RedirectionSignin);
        DB=new DBHelper(this);
        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user=username.getText().toString();
                String pass=password.getText().toString();
                String repass=repassword.getText().toString();
                if(TextUtils.isEmpty(user)||TextUtils.isEmpty(pass)||TextUtils.isEmpty(repass))
                    Toast.makeText(SignUpActivity.this,"All fields Required",Toast.LENGTH_SHORT).show();
                else
                if(pass.equals(repass)){
                    Boolean checkuser=DB.checkusername(user);
                    if(checkuser==false){
                        Boolean insert=DB.insertData(user,pass);
                        if(insert==true){
                            Toast.makeText(SignUpActivity.this,"Registred Successfully",Toast.LENGTH_SHORT).show();
                            Intent intent=new Intent (getApplicationContext(),pre_personnalisation_page.class);
                            startActivity(intent);
                            overridePendingTransition(R.anim.slide_in_right, R.anim.slide_out_left);

                        }
                        else{
                            Toast.makeText(SignUpActivity.this,"Registration Failed",Toast.LENGTH_SHORT).show();
                        }
                    }
                    else{
                        Toast.makeText(SignUpActivity.this,"This email is already taken!",Toast.LENGTH_SHORT).show();
                    }
                }
                else{
                    Toast.makeText(SignUpActivity.this,"Passwords are not matching",Toast.LENGTH_SHORT).show();
                }
            }
        });

        textSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (getApplicationContext(),SignInActivity.class);
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
