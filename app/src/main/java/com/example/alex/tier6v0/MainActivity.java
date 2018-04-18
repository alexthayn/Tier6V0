package com.example.alex.tier6v0;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
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
import android.widget.ImageView;
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

        }
        else if (id == R.id.nav_toGame){
            openApp(this, "com.nianticlabs.pokemongo");
        }
        else if (id == R.id.nav_settings) {
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
        int userLevel = getLevel(Integer.parseInt(userXP));
        String userPokemonCount = dbManager.getUserPokemonCount();
        String userTeam = dbManager.getUserTeam().trim();

        Log.w("Team Debug", userTeam);
        TextView tvUsername = findViewById(R.id.username);
        TextView tvUserLevel = findViewById(R.id.userLevel);
        TextView tvUserXP = findViewById(R.id.userXP);
        TextView tvUserPokemonCount = findViewById(R.id.userPokemonCount);
        ImageView icon = findViewById(R.id.teamLogo);
        View side = findViewById(R.id.sideMenu);

        tvUsername.setText(username);
        tvUserLevel.setText("Level: " + userLevel);
        tvUserXP.setText("XP: " + userXP);
        tvUserPokemonCount.setText("Pokemon Count: " + userPokemonCount);

        if(userTeam.equals("Mystic")){
            icon.setImageResource(R.drawable.mystic);
            side.setBackgroundResource(R.drawable.mysticback);
        }
        if(userTeam.equals("Valor")){
            icon.setImageResource(R.drawable.valor);
            side.setBackgroundResource(R.drawable.valorback);
        }
        if(userTeam.equals("Instinct")){
            icon.setImageResource(R.drawable.instinct);
            side.setBackgroundResource(R.drawable.instinctback);
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

    public static boolean openApp(Context context, String packageName) {
        PackageManager manager = context.getPackageManager();
        try {
            Intent i = manager.getLaunchIntentForPackage(packageName);
            if (i == null) {
                return false;
                //throw new ActivityNotFoundException();
            }
            i.addCategory(Intent.CATEGORY_LAUNCHER);
            context.startActivity(i);
            return true;
        } catch (ActivityNotFoundException e) {
            return false;
        }
    }

    public int getLevel(int xp){
        if(xp < 1000)
            return 1;
        else if(xp < 3000)
            return 2;
        else if(xp < 6000)
            return 3;
        else if(xp < 10000)
            return 4;
        else if(xp < 15000)
            return 5;
        else if(xp < 21000)
            return 6;
        else if(xp < 28000)
            return 7;
        else if(xp < 36000)
            return 8;
        else if(xp < 45000)
            return 9;
        else if(xp < 55000)
            return 10;
        else if(xp < 65000)
            return 11;
        else if(xp < 75000)
            return 12;
        else if(xp < 85000)
            return 13;
        else if(xp < 100000)
            return 14;
        else if(xp < 120000)
            return 15;
        else if(xp < 140000)
            return 16;
        else if(xp < 160000)
            return 17;
        else if(xp < 185000)
            return 18;
        else if(xp < 210000)
            return 19;
        else if(xp < 260000)
            return 20;
        else if(xp < 335000)
            return 21;
        else if(xp < 435000)
            return 22;
        else if(xp < 560000)
            return 23;
        else if(xp < 710000)
            return 24;
        else if(xp < 900000)
            return 25;
        else if(xp < 1100000)
            return 26;
        else if(xp < 1350000)
            return 27;
        else if(xp < 1650000)
            return 28;
        else if(xp < 2000000)
            return 29;
        else if(xp < 2500000)
            return 30;
        else if(xp < 3000000)
            return 31;
        else if(xp < 3750000)
            return 32;
        else if(xp < 4750000)
            return 33;
        else if(xp < 6000000)
            return 34;
        else if(xp < 7500000)
            return 35;
        else if(xp < 9500000)
            return 36;
        else if(xp < 12000000)
            return 37;
        else if(xp < 15000000)
            return 38;
        else if(xp < 20000000)
            return 39;
        else
            return 40;
    }
}
