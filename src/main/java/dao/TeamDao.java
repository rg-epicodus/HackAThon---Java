package dao;

import models.Member;
import models.Team;

import java.util.List;

public interface TeamDao {

    //create
    void add(Team team);

    //read
    List<Team> getAll();
    List<Member> getAllMembersByTeam (int teamId);
    Team findById(int id);

    //update
    void update(String newTeamName, String newTeamDescription, int id);


    //DESTROY
    void deleteById(int id);
    void clearAllTeams();
}
