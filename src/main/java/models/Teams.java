package models;

import java.util.ArrayList;

public class Teams {
    private String teams;
    private static ArrayList<Teams> instances = new ArrayList<>();



    //Constructor

    public Teams(String teams) {
        this.teams = teams;
        instances.add(this);
    }


    //Getters


    public String getTeams() {
        return teams;
    }

    public static ArrayList<Teams> getAllTeams(){
        return instances;
    }

    public static void clearAllTeams(){
        instances.clear();
    }
}
