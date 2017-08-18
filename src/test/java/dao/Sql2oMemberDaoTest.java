package dao;
import models.Member;
import models.Member;
import org.sql2o.Connection;
import org.sql2o.Sql2o;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class Sql2oMemberDaoTest {
    private Sql2oMemberDao memberDao;
    private Connection conn;

    @Before
    public void setUp() throws Exception {
        String connectionString = "jdbc:h2:mem:testing;INIT=RUNSCRIPT from 'classpath:db/create.sql'";
        Sql2o sql2o = new Sql2o(connectionString, "", "");
        memberDao = new Sql2oMemberDao(sql2o);
        conn = sql2o.open();
    }

    @After
    public void tearDown() throws Exception {
        conn.close();
    }

    @Test
    public void addMemberToDatabaseById() throws Exception {
        Member member = setupNew();
        int originalMemberId = member.getId();
        memberDao.add(member);
        assertNotEquals(originalMemberId, member.getId());
    }

    @Test
    public void existingMemberCanBeFoundById() throws Exception {
        Member member = setupNew();
        memberDao.add(member);
        Member foundMember = memberDao.findById(member.getId());
        assertEquals(member, foundMember);
    }

    @Test
    public void getListOfAllMembers() throws Exception {
        Member member = setupNew();
        memberDao.add(member);
        assertEquals(1, memberDao.getAll().size());
    }



















    //helpers
    public Member setupNew() {
        return new Member("Wonder Woman");
    }

    public Member setupOther() {
        return new Member("Elektra");
    }



}