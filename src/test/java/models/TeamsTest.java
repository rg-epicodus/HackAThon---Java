package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

public class TeamsTest {
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
        Team.clearAllTeams();
    }

    @Test
    public void newTeamInstanceGetsCorrectlyCreated_true() throws Exception {
        Team newTeam = setupNewTeam();
        assertEquals(true, newTeam instanceof Team);
    }

    @Test
    public void newTeamInstantiatesWithContent_Epicodus() throws Exception {
        Team newTeam = setupNewTeam();
        assertEquals( "Epicodus", newTeam.getTeamName());
    }

    @Test
    public void getAllTeam_AllTeamAreReturnedCorrectly_true() throws Exception {
        Team newTeam = setupNewTeam();
        Team newOtherTeam = setupNewTeam();
        assertEquals(2, Team.getAllTeams().size());

    }

    @Test
    public void getAllTeam_ReturnsAllTeamInfo_true() throws Exception {
        Team newTeam = setupNewTeam();
        Team newOtherTeam = setupNewTeam();
        assertEquals(true, Team.getAllTeams().contains(newTeam));
        assertEquals(true, Team.getAllTeams().contains(newOtherTeam));

    }

    @Test
    public void getTeamCreatedAt_instantiatesWithCurrentTime_today() throws Exception {
        Team newTeam = setupNewTeam();
        assertEquals(LocalDateTime.now().getDayOfWeek(), newTeam.getTeamCreatedAt().getDayOfWeek());

    }

    @Test
    public void getTeamId_postInstantiatesWithAnID_1() throws Exception {
        Team.clearAllTeams();
        Team newTeam = setupNewTeam();
        assertEquals(1, newTeam.getTeamId());
    }

    @Test
    public void findReturnsCorrectTeamID_true() throws Exception {
        Team newTeam = setupNewTeam();
        assertEquals(1, Team.findById(newTeam.getTeamId()).getTeamId());
    }

    @Test
    public void findReturnsCorrectTeamWhenMoreThanOneTeamExists_true() throws Exception {
        Team newTeam = setupNewTeam();
        Team newOtherTeam = new Team("Test", "23444");
        assertEquals(2, Team.findById(newOtherTeam.getTeamId()).getTeamId());
    }


    @Test
    public void updateChangesTeamName_true() throws Exception {
        Team newTeam = setupNewTeam();
        String formerTeamName = newTeam.getTeamName();
        LocalDateTime formerDate = newTeam.getTeamCreatedAt();
        int formerId = newTeam.getTeamId();
        newTeam.updateTeamName("Portland Code Guild");
        assertEquals(formerId, newTeam.getTeamId());
        assertEquals(formerDate, newTeam.getTeamCreatedAt());
        assertNotEquals(formerTeamName, newTeam.getTeamName());
    }

    @Test
    public void newTeamInstantiatesWithTeamDescription_ClassOf2017() throws Exception {
        Team newTeam = setupNewTeam();
        assertEquals( "Class of Summer 2017",  newTeam.getTeamDescription());
    }

    @Test
    public void newTeamInstantiatesWithTeamMembers_1() throws Exception {
        Team newTeam = setupNewTeam();
        newTeam.addTeamMember("Pat");
        assertEquals(1, newTeam.getTeamMembers().size());
    }





    //helper
    public Team setupNewTeam(){
        return new Team("Epicodus","Class of Summer 2017");
    }
}