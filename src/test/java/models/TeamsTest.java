package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Teams testTeams = new Teams("Epicodus", "Class of Summer 2017");
        assertEquals(true, testTeams instanceof Teams);
    }

    @Test
    public void newTeamsInstantiatesWithContent_Epicodus() throws Exception {
        Teams testTeams = new Teams("Epicodus", "Class of Summer 2017");
        assertEquals( "Epicodus", testTeams.getTeamName());
    }

    @Test
    public void getAllTeams_AllTeamsAreReturnedCorrectly_true() throws Exception {
        Teams testTeams = new Teams("Epicodus", "Class of Summer 2017");
        Teams testOtherTeams = new Teams("Epicodus", "Class of Summer 2017");
        assertEquals(2, Teams.getAllTeams().size());

    }

    @Test
    public void getAllTeams_ReturnsAllTeamsInfo_true() throws Exception {
        Teams testTeams = new Teams("Epicodus", "Class of Summer 2017");
        Teams testOtherTeams = new Teams("Epicodus", "Class of Summer 2017");
        assertEquals(true, Teams.getAllTeams().contains(testTeams));
        assertEquals(true, Teams.getAllTeams().contains(testOtherTeams));

    }

    @Test
    public void getTeamId_postInstantiatesWithAnID_1() throws Exception {
        Teams.clearAllTeams();
        Teams testTeams = new Teams("Epicodus", "Class of Summer 2017");
        assertEquals(1, Teams.getTeamId());
    }

    @Test
    public void findReturnsCorrectTeamID_true() throws Exception {
        Teams testTeams = new Teams("Epicodus", "Class of Summer 2017");
        assertEquals(1, Teams.findById(Teams.getTeamId()).getId());
    }


}