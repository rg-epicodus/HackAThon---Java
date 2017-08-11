package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Teams {
    private String teamName;
    private String teamDescription;
    private static ArrayList<Teams> allTeams = new ArrayList<>();
    private static int id;
    private static int teamListTotal;
    private LocalDateTime createdAt;



    //Constructor
    public Teams(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        teamListTotal++;
        this.id = teamListTotal;
        this.createdAt = LocalDateTime.now();
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

    public static int getTeamId() {
        return id;
    }

    public static Teams findById(int id){
        Teams test= null;
        for (Teams allTeam : allTeams) {
            if (allTeam.getTeamId() == id) {
                test = allTeam;
            }
        }
        return test;
    }

    public LocalDateTime getTeamCreatedAt() {
        return createdAt;
    }
}
