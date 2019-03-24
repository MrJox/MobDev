package com.example.mdlab1;
import java.util.ArrayList;

public interface IDataProvider {
    void addUser(Person user);
    ArrayList<Person> getUsersList();
}
