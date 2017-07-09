package com.example.debjyoti.tpo_spit;

/**
 * Created by Debjyoti on 26-03-2017.
 */

public class Company {

    private String name;
    private String eligibilty;
    private String deadline;

    public Company() {
      /*Blank default constructor essential for Firebase*/
    }
    public Company(String name,String eligibilty,String deadline) {
      /*Blank default constructor essential for Firebase*/
        name=this.name;
        eligibilty=this.eligibilty;
        deadline=this.deadline;
    }
    //Getters and setters
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEligibilty() {
        return eligibilty;
    }

    public void setEligibilty(String address) {
        this.eligibilty = eligibilty;
    }

    public String getDeadline() {
        return deadline;
    }

    public void setDeadline(String deadline) {
        this.deadline = deadline;
    }


}
