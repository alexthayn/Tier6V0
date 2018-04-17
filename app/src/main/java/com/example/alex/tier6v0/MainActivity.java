package com.example.alex.tier6v0;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener{
    private DatabaseManager dbManager;
    //The spinner element
    Spinner spinner;
    Button browsePokemon;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        dbManager = new DatabaseManager(this);

        spinner = (Spinner) findViewById(R.id.raidbossSpinner);
        spinner.setOnItemSelectedListener(new spinnerListener());
        loadSpinnerData();

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.addDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        browsePokemon = findViewById(R.id.btnPokemonTypes);

    }

    @Override
    public void onBackPressed() {

            updateUserInfo();
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }
    @Override
    public void onWindowFocusChanged(boolean hasFocus){
        updateUserInfo();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        updateUserInfo();
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_faq) {
            Intent faqIntent = new Intent(this, FAQActivity.class);
            this.startActivity(faqIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_pokemon) {

        } else if (id == R.id.nav_settings) {
            Intent settingsIntent = new Intent( this , SettingsActivity.class );
            this.startActivity( settingsIntent );
            return true;
        }

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }

    /*@Override
    public void onDrawerOpened(View drawerView){
        super.onDrawerOpened(drawerView);
    }*/

    public void updateUserInfo(){
        String username =dbManager.getUsername();
        String userXP = dbManager.getUserXP();
        String userPokemonCount = dbManager.getUserPokemonCount();
        TextView tvUsername = findViewById(R.id.username);
        TextView tvUserXP = findViewById(R.id.userXP);
        TextView tvUserPokemonCount = findViewById(R.id.userPokemonCount);

        if(username != null && userXP != null && userPokemonCount != null) {
            tvUsername.setText("Hello " + dbManager.getUsername());
            tvUserXP.setText("XP: " +dbManager.getUserXP());
            tvUserPokemonCount.setText("Pokemon Count: " + dbManager.getUserPokemonCount());
        }
    }

    private void loadSpinnerData(){

        List<String> raidBosses = dbManager.getAllRaidBosses();

        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, raidBosses);
        dataAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinner.setAdapter(dataAdapter);
    }


    public void onClick( View v ) {
        if(v.getId() == R.id.btnPokemonTypes){
            Intent browseIntent = new Intent(MainActivity.this, BrowseActivity.class);
            MainActivity.this.startActivity(browseIntent);

        }else if(v.getId() == R.id.go){
            String curVal = spinner.getSelectedItem().toString();
            curVal = curVal.substring(curVal.indexOf("(") + 1,curVal.indexOf(")"));
            int pokeId = Integer.parseInt(curVal);

            Intent bossIntent = new Intent(MainActivity.this, RecommendActivity.class);
            bossIntent.putExtra("PokemonID", pokeId);
            MainActivity.this.startActivity(bossIntent);
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
}
