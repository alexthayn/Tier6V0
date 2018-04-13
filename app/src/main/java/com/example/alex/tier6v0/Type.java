package com.example.alex.tier6v0;

import android.view.Gravity;

/**
 * Created by Alex on 4/12/2018.
 */

public class Type {
    private int id;
    private String type;
    private int Normal;
    private int Fighting;
    private int Flying;
    private int Poison;
    private int Ground;
    private int Rock;
    private int Bug;
    private int Ghost;
    private int Steel;
    private int Fire;
    private int Water;
    private int Grass;
    private int Electric;
    private int Psychic;
    private int Ice;
    private int Dragon;
    private int Dark;
    private int Fairy;

    public Type(int id, String type, int normal, int fighting, int flying, int poison,
                int ground, int rock, int bug, int ghost, int steel, int fire, int water,
                int grass, int electric, int psychic, int ice, int dragon, int dark, int fairy){
        this.id = id;
        this.type = type;
        Normal = normal;
        Fighting = fighting;
        Flying = flying;
        Poison = poison;
        Ground = ground;
        Rock = rock;
        Bug = bug;
        Ghost = ghost;
        Steel = steel;
        Fire = fire;
        Water = water;
        Grass = grass;
        Electric = electric;
        Psychic = psychic;
        Ice = ice;
        Dragon = dragon;
        Dark = dark;
        Fairy = fairy;
    }

    public int getId(){return id;}
    public String getType() { return type;}
    public int getNormal(){ return  Normal;}
    public int getFighting(){ return Fighting;}
    public int getFlying(){ return Flying;}
    public int getPoison(){ return Poison;}
    public int getGround(){ return Ground;}
    public int getRock(){ return Rock;}
    public int getBug(){ return Bug;}
    public int getGhost(){ return Ghost;}
    public int getSteel(){ return Steel;}
    public int getFire(){return Fire;}
    public int getWater(){ return Water;}
    public int getGrass(){ return Grass;}
    public int getElectric(){ return  Electric;}
    public int getPsychic(){ return  Psychic;}
    public int getIce(){ return Ice;}
    public int getDragon(){ return Dragon;}
    public int getDark(){ return Dark;}
    public int getFairy(){ return Fairy;}
}
