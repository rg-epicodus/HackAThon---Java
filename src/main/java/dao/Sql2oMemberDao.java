package dao;

import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.sql2o.Sql2oException;

import java.util.List;

public class Sql2oMemberDao implements MemberDao {
    private final Sql2o sql2o;

    public Sql2oMemberDao(Sql2o sql2o) {
        this.sql2o = sql2o;
    }

    @Override
    public void add(Member member) {
        String sql = "INSERT INTO member (memberName, teamId) VALUES (:memberName, :teamId)";
        try (Connection con = sql2o.open()) {
            int id = (int) con.createQuery(sql)
                    .bind(member)
                    .executeUpdate()
                    .getKey();
            member.setId(id);
        } catch (Sql2oException ex) {;
            System.out.println(ex);
        }
    }

    @Override
    public Member findById(int id) {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM member WHERE id = :id")
                    .addParameter("id", id)
                    .executeAndFetchFirst(Member.class);
        }
    }

    @Override
    public List<Member> getAll() {
        try (Connection con = sql2o.open()) {
            return con.createQuery("SELECT * FROM member")
                    .executeAndFetch(Member.class);
        }
    }

    @Override
    public void update(String newMemberName, int id, int teamId){
        String sql = "UPDATE member SET (memberName) = (:memberName) WHERE id=:id";
        try(Connection con = sql2o.open()){
            con.createQuery(sql)
                    .addParameter("memberName", newMemberName)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex) {
            System.out.println(ex);
        }
    }

    @Override
    public void deleteById(int id) {
        String sql = "DELETE from member WHERE id=:id";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .addParameter("id", id)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }

    @Override
    public void clearAllMembers() {
        String sql = "DELETE from member";
        try (Connection con = sql2o.open()) {
            con.createQuery(sql)
                    .executeUpdate();
        } catch (Sql2oException ex){
            System.out.println(ex);
        }
    }


}
