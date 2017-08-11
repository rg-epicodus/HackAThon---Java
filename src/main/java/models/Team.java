package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String teamDescription;
    private ArrayList<String> teamMembers;
    private static ArrayList<Team> allTeams = new ArrayList<>();
    private static int id;
    private static int teamListTotal;
    private LocalDateTime teamCreatedAt;


    //Constructor

    public Team(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.teamCreatedAt = LocalDateTime.now();
        allTeams.add(this);
        this.id = allTeams.size();
        this.teamMembers = new ArrayList<>();
        teamListTotal++;
    }

    //Getters


    public String getTeamName() {
        return teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }

    public static ArrayList<Team> getAllTeams(){
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

    public static Team findById(int id){
        Team test= null;
        for (Team allTeam : allTeams) {
            if (allTeam.getTeamId() == id) {
                test = allTeam;
            }
        }
        return test;
    }

    public ArrayList<String> getTeamMembers() {
        return teamMembers;
    }

    public void addTeamMember(String teamMemberName){
        String newMember = teamMemberName;
        teamMembers.add(newMember);
    }



    public void updateTeamName(String teamName) {
        this.teamName = teamName;
    }
}