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
    }

    @Test
    public void newTeamsInstanceGetsCorrectlyCreated_true() throws Exception {
        Teams testTeams = new Teams();
        assertEquals(true, testTeams instanceof Teams);
    }




}