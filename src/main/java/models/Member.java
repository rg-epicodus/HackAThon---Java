package models;

public class Member {
    private String memberName;
    private int id;

    public Member(String memberName) {
        this.memberName = memberName;
    }


    //getters

    public String getMemberName() {
        return memberName;
    }

    public int getId() {
        return id;
    }


    //setters


    public void setMemberName(String memberName) {
        this.memberName = memberName;
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
