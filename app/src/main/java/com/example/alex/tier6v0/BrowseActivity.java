package com.example.alex.tier6v0;

import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.ScrollView;
import android.widget.TextView;

import org.w3c.dom.Text;

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
        pokemonWidth = size.x ;
        updateView( );
    }

    public void updateView() {
        ArrayList<Pokemon> pokemon = dbManager.selectAllPokemon();
        if(pokemon.size() > 0){
            scrollView.removeAllViewsInLayout();
            GridLayout grid = new GridLayout(this);
            grid.setRowCount((pokemon.size()));
            grid.setColumnCount(1);

            int i=0;
            for(Pokemon p : pokemon){
                RelativeLayout poketypelayout = findViewById(R.id.poketype_layout);
                View pokeType = LayoutInflater.from(this).inflate(R.layout.poketype_template, poketypelayout, false);

                //Add name
                TextView name = pokeType.findViewById(R.id.pokemonName);
                name.setText(p.getPokemon());
                //Add first type
                ImageView typeI = pokeType.findViewById(R.id.pokemonType1);
                String type_1 = getStringType(p.getType_I());

                //Add second type if it exists
                typeI.setImageResource(getImage(type_1));

                String type_2 = getStringType(p.getType_II());
                ImageView typeII = pokeType.findViewById(R.id.pokemonType2);

                if(!type_2.equals("No Type Found")){
                    typeII.setImageResource(getImage(type_2));
                }else{
                    //typeII.setBack
                }
             grid.addView(pokeType, pokemonWidth, GridLayout.LayoutParams.WRAP_CONTENT);

                i++;
            }
            scrollView.addView(grid);
        }
    }

    public int getImage(String name){
        if(name.equals("Normal"))
            return R.drawable.normal;
        else if(name.equals("Fighting"))
            return R.drawable.fighting;
        else if(name.equals("Flying"))
            return R.drawable.flying;
        else if(name.equals("Poison"))
            return R.drawable.poison;
        else if(name.equals("Ground"))
            return R.drawable.ground;
        else if(name.equals("Rock"))
            return R.drawable.rock;
        else if(name.equals("Bug"))
            return R.drawable.bug;
        else if(name.equals("Ghost"))
            return R.drawable.ghost;
        else if(name.equals("Steel"))
            return R.drawable.steel;
        else if(name.equals("Fire"))
            return R.drawable.fire;
        else if(name.equals("Water"))
            return R.drawable.water;
        else if(name.equals("Grass"))
            return R.drawable.grass;
        else if(name.equals("Electric"))
            return R.drawable.electric;
        else if(name.equals("Psychic"))
            return R.drawable.psychic;
        else if(name.equals("Ice"))
            return R.drawable.ice;
        else if(name.equals("Dragon"))
            return R.drawable.dragon;
        else if(name.equals("Dark"))
            return R.drawable.dark;
        else if(name.equals("Fairy"))
            return R.drawable.fairy;
        else
            return R.drawable.normal;
    }

    public String getStringType(int type){
        switch(type){
            case 1:
                return "Normal";
            case 2:
                return "Fighting";
            case 3:
                return "Flying";
            case 4:
                return "Poison";
            case 5:
                return "Ground";
            case 6:
                return "Rock";
            case 7:
                return "Bug";
            case 8:
                return "Ghost";
            case 9:
                return "Steel";
            case 10:
                return "Fire";
            case 11:
                return "Water";
            case 12:
                return "Grass";
            case 13:
                return "Electric";
            case 14:
                return "Psychic";
            case 15:
                return "Ice";
            case 16:
                return "Dragon";
            case 17:
                return "Dark";
            case 18:
                return "Fairy";
            default:
                return "No Type Found";
        }
    }
}
