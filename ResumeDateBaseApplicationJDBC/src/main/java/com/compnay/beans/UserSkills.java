package com.compnay.beans;

public class UserSkills {

    private Integer id;
    private Users user;
    private Skill skill;
    private int power;

    public UserSkills(Integer id, Users user, Skill skill, int power) {
        this.id = id;
        this.user = user;
        this.skill = skill;
        this.power = power;
    }

    public UserSkills() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Users getUser() {
        return user;
    }

    public void setUser(Users user) {
        this.user = user;
    }

    public Skill getSkill() {
        return skill;
    }

    public void setSkill(Skill skill) {
        this.skill = skill;
    }

    public int getPower() {
        return power;
    }

    public void setPower(int power) {
        this.power = power;
    }

    @Override
    public String toString() {
        return "UserSkills{" + "id=" + id + ", user=" + user + ", skill=" + skill + ", power=" + power + '}';
    }
    
    

}
