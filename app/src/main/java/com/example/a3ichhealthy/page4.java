package com.example.a3ichhealthy;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class page4 extends AppCompatActivity {
    androidx.appcompat.widget.AppCompatButton page4btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //getSupportActionBar().hide();
        setContentView(R.layout.personnalisation_page4);

        page4btn = findViewById(R.id.page4btn);
        page4btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent (getApplicationContext(),page5.class);
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