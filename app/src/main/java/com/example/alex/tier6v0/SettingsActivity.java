package com.example.alex.tier6v0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 4/10/2018.
 */

public class SettingsActivity extends AppCompatActivity {
    DatabaseManager dbManager;
    Spinner spinner;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
        dbManager = new DatabaseManager(this);
        updateView();

        spinner = (Spinner) findViewById(R.id.lblEditTeam);
        spinner.setOnItemSelectedListener(new SettingsActivity.spinnerListener());
        loadSpinnerData();
    }

    public void onStart(){
        super.onStart();
        String username = dbManager.getUsername();
        String XP = dbManager.getUserXP();
    }

    //Build view dynamically with the user settings
    public void updateView() {
        TextView username = findViewById(R.id.username);
        TextView xp = findViewById(R.id.userXP);
        if(username != null && xp != null) {
            username.setText(dbManager.getUsername());
            xp.setText(dbManager.getUserXP());
        }
    }


    public void onClick(View v) {

        EditText username =  findViewById(R.id.lblEditUsername);
        EditText xp = findViewById(R.id.lblEditXP);
        String prevUserName, prevXP, prevTeam;
        prevUserName = dbManager.getUsername();
        prevXP = dbManager.getUserXP();
        prevTeam = dbManager.getUserTeam();

        if(v.getId() == R.id.updateUsername){
            dbManager.setNewUserData(username.getText().toString(),prevXP,prevTeam);
            Toast.makeText(SettingsActivity.this, "Your data has been updated", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.updateXP){
            dbManager.setNewUserData(prevUserName,xp.getText().toString(),prevTeam);
            Toast.makeText(SettingsActivity.this, "Your data has been updated", Toast.LENGTH_SHORT).show();
        }
        else if(v.getId() == R.id.updateTeam){
            dbManager.setNewUserData(prevUserName,prevXP,spinner.getSelectedItem().toString());
            Toast.makeText(SettingsActivity.this, "Your data has been updated", Toast.LENGTH_SHORT).show();
        }
    }

    private class spinnerListener implements AdapterView.OnItemSelectedListener {
        @Override
        public void onItemSelected(AdapterView<?> parent, View v, int position, long id){

        }

        @Override
        public void onNothingSelected(AdapterView<?> arg0) {
            // TODO Auto-generated method stub
        }
    }

    private void loadSpinnerData(){

        List<String> teams = new ArrayList<String>();

        teams.add("Mystic");
        teams.add("Valor");
        teams.add("Instinct");

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, teams);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }

}
