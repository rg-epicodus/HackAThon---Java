package dao;

import models.Team;

import java.util.List;

public interface TeamDao {
    void add(Team team);

    List<Team> getAll();

    Team findById(int id);



}
