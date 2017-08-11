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
        Teams.clearAllTeams();
    }

    @Test
    public void newTeamsInstanceGetsCorrectlyCreated_true() throws Exception {
        Teams newTeam = setupNewTeam();
        assertEquals(true, newTeam instanceof Teams);
    }

    @Test
    public void newTeamsInstantiatesWithContent_Epicodus() throws Exception {
        Teams newTeam = setupNewTeam();
        assertEquals( "Epicodus", newTeam.getTeamName());
    }

    @Test
    public void getAllTeams_AllTeamsAreReturnedCorrectly_true() throws Exception {
        Teams newTeam = setupNewTeam();
        Teams newOtherTeam = setupNewTeam();
        assertEquals(2, Teams.getAllTeams().size());

    }

    @Test
    public void getAllTeams_ReturnsAllTeamsInfo_true() throws Exception {
        Teams newTeam = setupNewTeam();
        Teams newOtherTeam = setupNewTeam();
        assertEquals(true, Teams.getAllTeams().contains(newTeam));
        assertEquals(true, Teams.getAllTeams().contains(newOtherTeam));

    }

    @Test
    public void getTeamCreatedAt_instantiatesWithCurrentTime_today() throws Exception {
        Teams newTeam = setupNewTeam();
        assertEquals(LocalDateTime.now().getDayOfWeek(), newTeam.getTeamCreatedAt().getDayOfWeek());

    }

    @Test
    public void getTeamId_postInstantiatesWithAnID_1() throws Exception {
        Teams.clearAllTeams();
        Teams newTeam = setupNewTeam();
        assertEquals(1, newTeam.getTeamId());
    }

    @Test
    public void findReturnsCorrectTeamID_true() throws Exception {
        Teams newTeam = setupNewTeam();
        assertEquals(1, Teams.findById(newTeam.getTeamId()).getTeamId());
    }

    @Test
    public void findReturnsCorrectTeamWhenMoreThanOneTeamExists_true() throws Exception {
        Teams newTeam = setupNewTeam();
        Teams newOtherTeam = setupNewTeam();
        assertEquals(2, Teams.findById(newOtherTeam.getTeamId()).getTeamId());
    }


    @Test
    public void updateChangesTeamsName_true() throws Exception {
        Teams newTeam = setupNewTeam();
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
        Teams newTeam = setupNewTeam();
        assertEquals( "Class of Summer 2017",  newTeam.getTeamDescription());

    }



    //helper
    public Teams setupNewTeam(){
        return new Teams("Epicodus","Class of Summer 2017");
    }
}