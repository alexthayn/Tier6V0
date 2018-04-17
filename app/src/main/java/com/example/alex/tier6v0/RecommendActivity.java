package com.example.alex.tier6v0;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;
import android.widget.TextView;

public class RecommendActivity extends AppCompatActivity {
    private DatabaseManager dbManager;
    public RaidBoss boss = new RaidBoss();

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        dbManager = new DatabaseManager( this );
        setContentView( R.layout.recommend );
        setData();
    }

    public void setData() {
        int pokeNum = getIntent().getIntExtra("PokemonID",0);
        boss = dbManager.getBossData(pokeNum);

        TextView title = findViewById(R.id.pokeName);
        TextView cpTag = findViewById(R.id.cpTag);
        TextView poke1 = findViewById(R.id.poke1);
        TextView poke2 = findViewById(R.id.poke2);
        TextView poke3 = findViewById(R.id.poke3);
        TextView poke4 = findViewById(R.id.poke4);
        TextView poke5 = findViewById(R.id.poke5);
        TextView poke6 = findViewById(R.id.poke6);
        ImageView type1 = findViewById(R.id.type1);
        ImageView type2 = findViewById(R.id.type2);
        ImageView type3 = findViewById(R.id.type3);
        ImageView type4 = findViewById(R.id.type4);

        type1.setImageResource(getImage(boss.getType(1)));
        type2.setImageResource(getImage(boss.getType(2)));
        type3.setImageResource(getImage(boss.getType(3)));
        type4.setImageResource(getImage(boss.getType(4)));

        title.setText(boss.getName());
        cpTag.setText("Max CP: " + boss.getMaxCP() + " | " + boss.getMaxCPBoosted());
        poke1.setText(boss.getTeam(1));
        poke2.setText(boss.getTeam(2));
        poke3.setText(boss.getTeam(3));
        poke4.setText(boss.getTeam(4));
        poke5.setText(boss.getTeam(5));
        poke6.setText(boss.getTeam(6));
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
}
