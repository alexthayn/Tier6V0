package com.example.alex.tier6v0;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.GridLayout;
import android.widget.ScrollView;

import java.util.ArrayList;

/**
 * Created by Alex on 4/16/2018.
 */

public class BrowseActivity extends AppCompatActivity{
    private DatabaseManager dbManager;
    private ScrollView scrollView;
    private int pokemonWidth;

    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_browse);
        dbManager = new DatabaseManager( this );
        scrollView = ( ScrollView ) findViewById( R.id. scrollView );
        Point size = new Point( );
        getWindowManager( ).getDefaultDisplay( ).getSize( size );
        pokemonWidth = size. x / 2 ;
        updateView( );
    }

    public void updateView() {
        ArrayList<Pokemon> pokemon = dbManager.selectAllPokemon();
        if(pokemon.size() > 0){
            scrollView.removeAllViewsInLayout();
            GridLayout grid = new GridLayout(this);
            grid.setRowCount((pokemon.size()+1)/2);
            grid.setColumnCount(2);

            PokemonTextView [] ptv = new PokemonTextView[pokemon.size()];
            int i=0;
            for(Pokemon p : pokemon){
                ptv[i] = new PokemonTextView(this, p);
                ptv[i].setText(p.getPokemon());
                ptv[i].setBackgroundColor(Color.CYAN);
                grid.addView(ptv[i], pokemonWidth, GridLayout.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
        }
    }
}
