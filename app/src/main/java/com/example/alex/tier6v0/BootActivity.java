package com.example.alex.tier6v0;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

public class BootActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_boot);
    }

    public void onClick(View v){
        Intent mainIntent = new Intent(this, MainActivity.class);
        this.startActivity(mainIntent);
    }
}
