package dao;
import models.Team;
import models.Member;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.sql2o.Connection;
import org.sql2o.Sql2o;

import static org.junit.Assert.*;

public class Sql2oTeamDaoTest {
    private Sql2oTeamDao teamDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "","");
        teamDao = new Sql2oTeamDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addTeamToDatabaseById() throws Exception {
        Team team = setupNewTeam();
        int originalTeamId = team.getId();
        teamDao.add(team);
        assertNotEquals(originalTeamId, team.getId());
    }

    @Test
    public void existingTeamCanBeFoundById() throws Exception {
        Team team  = setupNewTeam();
        teamDao.add(team);
        Team foundTeam = teamDao.findById(team.getId());
        assertEquals(team, foundTeam);
    }

    @Test
    public void getListOfAllTeams() throws Exception {
        Team team = setupNewTeam();
        teamDao.add(team);
        assertEquals(1, teamDao.getAll().size());
    }

    @Test
    public void updateTeamInformation() throws Exception {
        String initialDescription = "";
        Team team = setupNewTeam();
        teamDao.add(team);
        teamDao.update("test name", "test description", team.getId());
        Team updatedTeam = teamDao.findById(team.getId());
        assertNotEquals(initialDescription, updatedTeam.getTeamName());
    }

    @Test
    public void deleteByIdDeletesCorrectTeam() throws Exception {
        Team team  = setupNewTeam();
        teamDao.add(team);
        teamDao.deleteById(team.getId());
        assertEquals(0, teamDao.getAll().size());
    }

    @Test
    public void clearAllClearsAll() throws Exception {
        Team team  = setupNewTeam();
        Team otherTeam = setupOtherTeam();
        teamDao.add(team);
        teamDao.add(otherTeam);
        int teamSize = teamDao.getAll().size();
        teamDao.clearAllTeams();
        assertTrue(teamSize > 0 && teamSize > teamDao.getAll().size());
    }







    // helopers
    public Team setupNewTeam() {
        return new Team("Epicodus", "Class of Summer 2017");
    }

    public Team setupOtherTeam() {
        return new Team("Portland Code Guild", "Class of Winter 2017");
    }

}