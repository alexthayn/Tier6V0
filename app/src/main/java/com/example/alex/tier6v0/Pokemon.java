package com.example.alex.tier6v0;

/**
 * Created by Alex on 4/12/2018.
 */

public class Pokemon {
    private Integer id;
    private String Locked;
    private String Pokemon;
    private Integer Type_I;
    private Integer Type_II;

    public Pokemon(int id, String locked, String pokemon, int typeI, int typeII){
        this.id = id;
        Locked = locked;
        Pokemon = pokemon;
        Type_I = typeI;
        Type_II = typeII;
    }

    public int getId(){ return id;}
    public String getLocked(){ return Locked;}
    public String getPokemon(){ return Pokemon;}
    public int getType_I(){ return Type_I;}
    public int getType_II(){ return Type_II;}
}
