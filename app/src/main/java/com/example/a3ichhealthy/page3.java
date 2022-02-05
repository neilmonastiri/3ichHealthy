package com.example.a3ichhealthy;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class page3 extends AppCompatActivity {
    androidx.appcompat.widget.AppCompatButton page3btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.personnalisation_page3);

        page3btn = findViewById(R.id.page3btn);
        page3btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (getApplicationContext(),page4.class);
                startActivity(intent);
                overridePendingTransition(0, 0);
            }
        });
    }

    @Override
    public void finish(){
        super.finish();
        overridePendingTransition(0, 0);
    }
}