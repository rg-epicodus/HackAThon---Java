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
    private LocalDateTime teamCreatedAt;


    //Constructor

    public Teams(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.teamCreatedAt = LocalDateTime.now();
        allTeams.add(this);
        this.id = allTeams.size();
        teamListTotal++;
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

    public LocalDateTime getTeamCreatedAt() {
        return teamCreatedAt;
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

    public void updateTeamName(String teamName) {
        this.teamName = teamName;
    }
}
