package dao;

import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTeamDao {
    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }


    public void add(Team team) {
        String sql = "INSERT INTO team (teamName, teamDescription) VALUES (:teamName, :teamDescription, )";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(team)
                    .executeUpdate()
                    .getKey();
            team.setId(id);
        } catch (Sql2oException ex) {;
        System.out.println(ex);
        }
    }


    public List<Team> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM team")
                    .executeAndFetch(Team.class);
        }
    }


//    public Team findById(int id) {
//        try (Connection con = sql2o.open()) {
//            return con.createQuery("SELECT * FROM team WHERE id = :id")
//                    .addParameter("id", id)
//                    .executeAndFetchFirst(Team.class);
//        }
//    }



}
