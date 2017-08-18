package models;

public class Member {
    private String memberName;
    private int id;
    private int teamId;

    public Member(String memberName, int teamId) {
        this.memberName = memberName;
        this.teamId = teamId;
    }


    //getters

    public String getMemberName() {
        return memberName;
    }

    public int getTeamId() {
        return teamId;
    }

    public int getId() {
        return id;
    }


    //setters


    public void setMemberName(String memberName) {
        this.memberName = memberName;
    }

    public void setTeamId(int teamId) {
        this.teamId = teamId;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Member member = (Member) o;

        return memberName.equals(member.memberName);
    }

    @Override
    public int hashCode() {
        return memberName.hashCode();
    }
}
