package com.example.alex.tier6v0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Alex on 4/10/2018.
 */

public class SettingsActivity extends AppCompatActivity {
    DatabaseManager dbManager;
    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);

        dbManager = new DatabaseManager(this);
        Button btnSave = (Button) (findViewById(R.id.btnSave));
        btnSave.setOnClickListener(new ButtonHandler());
        updateView();
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

    private class ButtonHandler implements View.OnClickListener {
        public void onClick(View v) {
            EditText username =  findViewById(R.id.lblEditUsername);
            EditText xp = findViewById(R.id.lblEditXP);

            dbManager.setNewUserData(username.getText().toString(), xp.getText().toString());
            Toast.makeText(SettingsActivity.this, "Your data has been updated", Toast.LENGTH_SHORT).show();
        }
    }
}
