package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String teamDescription;
    private LocalDateTime teamCreatedAt;
    private int id;



    //Constructor

    public Team(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
        this.teamCreatedAt = LocalDateTime.now();
    }

    //Getters

    public String getTeamName() {
        return teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
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



    // Setters


    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (!teamName.equals(team.teamName)) return false;
        if (teamDescription != null ? !teamDescription.equals(team.teamDescription) : team.teamDescription != null)
            return false;
        return teamCreatedAt != null ? teamCreatedAt.equals(team.teamCreatedAt) : team.teamCreatedAt == null;
    }

    @Override
    public int hashCode() {
        int result = teamName.hashCode();
        result = 31 * result + (teamDescription != null ? teamDescription.hashCode() : 0);
        result = 31 * result + (teamCreatedAt != null ? teamCreatedAt.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
