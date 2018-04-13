package com.example.alex.tier6v0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

/**
 * Created by Alex on 4/10/2018.
 */

public class SettingsActivity extends AppCompatActivity {
    DatabaseManager dbManager;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager(this);
        updateView();
    }

    //Build view dynamically with the user settings
    public void updateView() {

    }
}
