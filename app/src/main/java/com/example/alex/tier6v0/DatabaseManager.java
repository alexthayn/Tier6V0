package com.example.alex.tier6v0;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Alex on 3/25/2018.
 */

public class DatabaseManager extends SQLiteOpenHelper {
    boolean isCreating = false;
    SQLiteDatabase currentDB = null;

    private static final String DATABASE_NAME = "pokemonDB";
    private static final int DATABASE_VERSION = 1;

    private static final String TABLE_POKEMON = "Pokemon";
    private static final String ID = "id";
    private static final String LOCKED = "Locked";
    private static final String POKEMON = "Pokemon";
    private static final String TYPE1 = "TYPE_I";
    private static final String TYPE2 = "TYPE_II";

    private static final String TABLE_RAIDBOSSES = "RaidBosses";
    private static final String TIER = "Tier";
    private static final String POKEMON_ID = "Pokemon_ID";
    private static final String MAX_CP = "Max_CP";
    private static final String MAX_CP_BOOSTED = "Max_CP_Boosted";

    private static final String TABLE_TYPES = "Types";
    private static final String NORMAL = "Normal";
    private static final String FIGHTING = "Fighting";
    private static final String FLYING = "Flying";
    private static final String POISON = "Poison";
    private static final String GROUND = "Ground";
    private static final String ROCK = "Rock";
    private static final String BUG = "Bug";
    private static final String GHOST = "Ghost";
    private static final String STEEL = "Steel";
    private static final String FIRE = "Fire";
    private static final String WATER = "Water";
    private static final String GRASS = "Grass";
    private static final String ELECTRIC = "Electric";
    private static final String PSYCHIC = "Psychic";
    private static final String ICE = "Ice";
    private static final String DRAGON = "Dragon";
    private static final String DARK = "Dark";
    private static final String FAIRY = "Fairy";

    private static final String TABLE_USER_DATA = "UserData";
    private static final String USERNAME = "Username";
    private static final String USER_XP = "User_XP";

    private static final String TABLE_USER_POKEMON = "UserPokemon";

    public DatabaseManager(Context context){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    public void onCreate(SQLiteDatabase db){
        isCreating = true;
        currentDB = db;
        //build sql statement for pokemon table
        String sqlCreatePokemon = "create table " + TABLE_POKEMON + "( " + ID;
        sqlCreatePokemon += " integer primary key, " + LOCKED + " VARCHAR, "
                + POKEMON + " VARCHAR, " + TYPE1 + " INTEGER, " + TYPE2 + " INTEGER );";

        db.execSQL(sqlCreatePokemon);

        /*************************************/
        /********Add pokemon to table*********/
        /*************************************/
        Pokemon pokemon = new Pokemon(1,"FALSE","Bulbasaur",12,4);
        insertPokemonTable(pokemon);


        String sqlCreateRaidBosses = "create table " +  TABLE_RAIDBOSSES + "( " + ID
                + " integer primary key autoincrement, " + TIER + " INTEGER, "
                + POKEMON_ID + " INTEGER, " + MAX_CP + " INTEGER, " + MAX_CP_BOOSTED
                + " INTEGER );";

        String raidBosses = "Insert into " + TABLE_RAIDBOSSES + "(null, 1,1, 100, 100)";

        db.execSQL(sqlCreateRaidBosses);

        String sqlCreateTypes = "create table " + TABLE_TYPES + "( " + ID
                + " integer primary key autoincrement, " + TABLE_TYPES
                + " VARCHAR, " + NORMAL + " INTEGER, " + FIGHTING
                + " INTEGER, " + FLYING + " INTEGER, " + POISON
                + " INTEGER, " + GROUND + " INTEGER, " + ROCK
                + " INTEGER, " + BUG + " INTEGER, " + GHOST
                + " INTEGER, " + STEEL + " INTEGER, " + FIRE
                + " INTEGER, " + WATER + " INTEGER, " + GRASS
                + " INTEGER, " + ELECTRIC + " INTEGER, " + PSYCHIC
                + " INTEGER, " + ICE + " INTEGER, " + DRAGON
                + " INTEGER, " + DARK + " INTEGER, " + FAIRY
                + " INTEGER ) ";

        db.execSQL(sqlCreateTypes);

        String sqlCreateUserData = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_DATA +
                "( " + USER_XP + " INTEGER, " + USERNAME + " VARCHAR );";

        db.execSQL(sqlCreateUserData);

        String sqlCreateUserPokemon = "CREATE TABLE IF NOT EXISTS " + TABLE_USER_POKEMON
                + "( " + ID + " INTEGER, " + POKEMON_ID + " INTEGER);";

        db.execSQL(sqlCreateUserPokemon);
        isCreating = false;
        currentDB = null;
    }

    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion){
        //Drop old table is it exists
        db.execSQL("drop table if exists " + TABLE_POKEMON);
        db.execSQL("drop table if exists " + TABLE_RAIDBOSSES);
        db.execSQL("drop table if exists " + TABLE_TYPES);
        //recreate tables
        onCreate(db);
    }

    @Override
    public SQLiteDatabase getWritableDatabase() {
        // TODO Auto-generated method stub
        if(isCreating && currentDB != null){
            return currentDB;
        }
        return super.getWritableDatabase();
    }


    public Pokemon selectPokemonById(int id){
        String sqlQuery = "select * from " + TABLE_POKEMON
                + " where " + ID + " = " + id;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        Pokemon pokemon = null;
        if(cursor.moveToFirst())
            pokemon = new Pokemon(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                    Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
        cursor.close();
        return pokemon;
    }

    //get username
    public String getUsername(){
        String sqlQuery = "select " +USERNAME + " from " + TABLE_USER_DATA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        String username = "";
        if( cursor != null && cursor.moveToFirst() ) {
            username = cursor.getString(0);
            cursor.close();
        }
        return username;
    }

    //get user XP
    public String getUserXP(){
        String sqlQuery = "select " +USER_XP + " from " + TABLE_USER_DATA;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        String userXP = "";
        if(cursor != null && cursor.moveToFirst()){
            userXP = cursor.getString(0);
            cursor.close();
        }

        return userXP;
    }

    //get user pokemon count
    public String getUserPokemonCount(){
        String sqlQuery = "select COUNT(*)from " + TABLE_USER_POKEMON;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(sqlQuery, null);

        String pokemonCount = "";
        if( cursor != null && cursor.moveToFirst() ) {
            pokemonCount = cursor.getString(0);
            cursor.close();
        }
        return pokemonCount;
    }

    //allows us to insert a pokemon into the pokemon table
    public void insertPokemonTable(Pokemon p){
        String insertPokemon = "INSERT INTO " + TABLE_POKEMON + " VALUES (" +
                + p.getId() + ", '" + p.getLocked() + "','" + p.getPokemon() + "',"
                + p.getType_I() + ", " + p.getType_II() + ")";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(insertPokemon);
    }

    public ArrayList<Pokemon> selectAllPokemon(){
        String selectAll = "SELECT * FROM " + TABLE_POKEMON;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectAll, null);

        ArrayList<Pokemon> pokemon = new ArrayList<>();
        Pokemon temp;
        if(cursor.moveToFirst() && cursor!=null) {
            for (int i = 0; i < cursor.getCount(); i++) {
                temp = new Pokemon(Integer.parseInt(cursor.getString(0)), cursor.getString(1), cursor.getString(2),
                        Integer.parseInt(cursor.getString(3)), Integer.parseInt(cursor.getString(4)));
                pokemon.add(temp);
                cursor.moveToNext();
            }
        }
        return pokemon;
    }

    public void setNewUserData(String username, String xp){
        String updateData = "Delete from " + TABLE_USER_DATA;
        String insert = " Insert into " + TABLE_USER_DATA + " VALUES ( " + Integer.parseInt(xp) + " , '" + username + "')";

        SQLiteDatabase db = this.getWritableDatabase();
        db.execSQL(updateData);
        db.execSQL(insert);
        Log.w("Username update in db","My username is set to : " + getUsername());

    }

    public ArrayList<String> getAllRaidBosses(){
        String getRaidBosses = "Select "+ POKEMON + " from " + TABLE_RAIDBOSSES + " inner join "
                + TABLE_POKEMON + " On " + TABLE_RAIDBOSSES + "." + POKEMON_ID + " = "
                + TABLE_POKEMON + "." + ID;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(getRaidBosses, null);

        ArrayList<String> bosses = new ArrayList<>();

        if(cursor != null && cursor.moveToFirst()){
            for(int i = 0; i < cursor.getCount(); i++){
                bosses.add(cursor.getString(i));
            }
            cursor.close();
        }
        return bosses;
    }
}
