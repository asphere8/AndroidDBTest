package com.test.kahdek.dbtest;

public class UserData {

    //private variables
    String user_name = "";
    String user_occupation= "";

    public UserData(String user_name, String user_occupation) {
        this.user_name = user_name;
        this.user_occupation = user_occupation;
    }

    public UserData(){}

    // getting user name
    public String getUserName() {
        return this.user_name;
    }

    // setting user name
    public void setUserName(String name) {
        this.user_name = name;
    }

    // getting user occupation
    public String getUserOccupation() {
        return this.user_occupation;
    }

    // setting user occupation
    public void setUserOccupation(String occupation) {
        this.user_occupation = occupation;
    }
}