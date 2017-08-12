package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String teamDescription;
    private ArrayList<String> teamMembers;
    private static ArrayList<Team> allTeams = new ArrayList<>();
    private LocalDateTime teamCreatedAt;
    private int id;



    //Constructor

    public Team(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.teamCreatedAt = LocalDateTime.now();
        allTeams.add(this);
        this.id = allTeams.size();
        this.teamMembers = new ArrayList<>();
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

    public  int getTeamId() {
        return id;
    }

    public void updateTeamName(String teamName) {
        this.teamName = teamName;
    }

    public static Team findById(int id){
        return allTeams.get(id-1);
    }

    public ArrayList<String> getTeamMembers() {
        return teamMembers;
    }

    public void addTeamMember(String teamMemberName){
        String newTeamMember = teamMemberName;
        teamMembers.add(newTeamMember);
    }




}
