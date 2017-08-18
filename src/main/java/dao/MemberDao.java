package dao;

import models.Member;

import java.util.List;


public interface MemberDao {

    // create
    void add(Member member);

    //read
    List<Member> getAll();
    Member findById(int id);

    //update
    void update(String newMemberName, int id);


    //DESTROY
    void deleteById(int id);
    void clearAllMembers();
}
