package dao;

import models.Member;
import models.Team;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oTeamDao implements TeamDao {
    private final Sql2o sql2o;

    public Sql2oTeamDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
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

    @Override
    public Team findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM team WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Team.class);
        }
    }
    @Override
    public List<Team> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM team")
                    .executeAndFetch(Team.class);
        }
    }

    @Override
    public void update(String newTeamName, String newTeamDescription, int id){
        String sql = "UPDATE team SET (teamName, teamDescription) = (:teamName, :teamDescription) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("teamName", newTeamName)
                    .addParameter("teamDescription", newTeamDescription)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from team WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllTeams() {
        String sql = "DELETE from team";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public List<Member> getAllMembersByTeam(int teamId) {
        try(Connection con = sql2o.open()){
            return con.createQuery("SELECT * FROM member WHERE teamId = :teamId")
                    .addParameter("teamId", teamId)
                    .addColumnMapping("TEAMID", "teamId")
                    .addColumnMapping("NAME", "memberName")
                    .executeAndFetch(Member.class);
        }
    }

}
