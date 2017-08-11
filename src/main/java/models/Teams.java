package models;

import java.util.ArrayList;
import java.util.List;

public class Teams {
    private String teamName;
    private String teamDescription;
    private static ArrayList<Teams> allTeams = new ArrayList<>();
    private int id;
    private static int teamListTotal;



    //Constructor
    public Teams(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        teamListTotal++;
        this.id = teamListTotal;
        allTeams.add(this);
    }

    //Getters


    public String getTeamName() {
        return teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public static ArrayList<Teams> getAllTeams(){
        return allTeams;
    }

    public static void clearAllTeams(){
        allTeams.clear();
    }

}
