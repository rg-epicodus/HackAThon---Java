package models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Team {
    private String teamName;
    private String teamDescription;
    private int id;



    //Constructor

    public Team(String teamName, String teamDescription) {
        this.teamName = teamName;
        this.teamDescription = teamDescription;
    }

    //Getters

    public String getTeamName() {
        return teamName;
    }

    public String getTeamDescription() {
        return teamDescription;
    }


    public  int getId() {
        return id;
    }

    public void updateName(String teamName) {
        this.teamName = teamName;
    }



    // Setters


    public void setId(int id) {
        this.id = id;
    }



    // equals and hashcode


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Team team = (Team) o;

        if (id != team.id) return false;
        if (!teamName.equals(team.teamName)) return false;
        return teamDescription != null ? teamDescription.equals(team.teamDescription) : team.teamDescription == null;
    }

    @Override
    public int hashCode() {
        int result = teamName.hashCode();
        result = 31 * result + (teamDescription != null ? teamDescription.hashCode() : 0);
        result = 31 * result + id;
        return result;
    }
}
