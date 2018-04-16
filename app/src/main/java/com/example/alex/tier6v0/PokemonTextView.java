package com.example.alex.tier6v0;

import android.content.Context;
import android.support.v7.widget.AppCompatTextView;

/**
 * Created by Alex on 4/16/2018.
 */

public class PokemonTextView extends AppCompatTextView {
    private Pokemon pokemon;
    public PokemonTextView(Context context, Pokemon p){
        super(context);
        pokemon = p;
    }

    public String getName(){
        return pokemon.getPokemon();
    }
}
