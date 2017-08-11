package models;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

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
        Teams testTeams = new Teams("Epicodus");
        assertEquals(true, testTeams instanceof Teams);
    }

    @Test
    public void newTeamsInstantiatesWithContent_Epicodus() throws Exception {
        Teams testTeams = new Teams("Epicodus");
        assertEquals( "Epicodus", testTeams.getTeams());
    }

    @Test
    public void getAllTeams_AllTeamsAreReturnedCorrectly_true() throws Exception {
        Teams testTeams = new Teams("Epicodus");
        Teams testOtherTeams = new Teams("Portland Code Guild");
        assertEquals(2, Teams.getAllTeams().size());

    }

    @Test
    public void getAllTeams_ReturnsAllTeamsInfo_true() throws Exception {
        Teams testTeams = new Teams("Epicodus");
        Teams testOtherTeams = new Teams("Portland Code Guild");
        assertEquals(true, Teams.getAllTeams().contains(testTeams));
        assertEquals(true, Teams.getAllTeams().contains(testOtherTeams));

    }

    @Test
    public void getAllTeamMembers_ReturnsTeamMember_true() throws Exception {
        teamMember newMember = new teamMember("Jo");
        teamMember newOtherMember = new teamMember("Pat");
        assertEquals(2, teamMember.getAllMembers().size());
    }



}