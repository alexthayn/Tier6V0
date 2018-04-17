package com.example.alex.tier6v0;

public class RaidBoss {
    String name, type1, type2, type3, type4, team1, team2, team3, team4, team5, team6;
    int maxCP, maxCPBoosted;

    public RaidBoss(){
        name = "Magikarp";
        maxCP = 125;
        maxCPBoosted = 157;
        type1 = type2 = type3 = type4 = "Electric";
        team1 = team2 = team3 = team4 = team5 = team6 = "Jolteon";
    }

    public void setStage1(String name, int maxCP, int maxCPBoosted){
        this.name = name;
        this.maxCP = maxCP;
        this.maxCPBoosted = maxCPBoosted;
    }

    public void setStage2(String type1, String type2, String type3, String type4){
        this.type1 = type1;
        this.type2 = type2;
        this.type3 = type3;
        this.type4 = type4;
    }

    public void setStage3(String team1, String team2, String team3, String team4, String team5, String team6){
        this.team1 = team1;
        this.team2 = team2;
        this.team3 = team3;
        this.team4 = team4;
        this.team5 = team5;
        this.team6 = team6;
    }

    public String getName(){
        return name;
    }

    public int getMaxCP(){
        return maxCP;
    }

    public int getMaxCPBoosted(){
        return maxCPBoosted;
    }

    public String getType(int val){
        if(val == 1)
            return type1;
        else if(val == 2)
            return type2;
        else if(val == 3)
            return type3;
        else if(val == 4)
            return type4;
        else
            return type1;
    }

    public String getTeam(int val){
        if(val == 1)
            return team1;
        else if(val == 2)
            return team2;
        else if(val == 3)
            return team3;
        else if(val == 4)
            return team4;
        else if(val == 5)
            return team5;
        else if(val == 6)
            return team6;
        else
            return team1;
    }
}
