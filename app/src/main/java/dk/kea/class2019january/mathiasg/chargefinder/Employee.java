package dk.kea.class2019january.mathiasg.chargefinder;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Employee
{
    private String firstName;
    private int age;
    private String mail;
    private String password;

    public Employee(String firstName, int age, String mail, String password)
    {
        this.firstName = firstName;
        this.age = age;
        this.mail = mail;
        this.password = password;
    }
}
