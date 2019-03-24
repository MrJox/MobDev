package com.example.mdlab1;

import java.util.ArrayList;

public class DemoDataProvider implements IDataProvider {
    private ArrayList<Person> UsersList;

    DemoDataProvider() {
        this.UsersList = new ArrayList<>();
        this.UsersList.add(new Person("Чувак", "Тестовый", "mailru@govno.ru", "chuvaak", "123456"));
        this.UsersList.add(new Person("Чубрик", "Тестовый", "nomerdva@mail.ru", "chuubrik", "123456"));
    }

    @Override
    public void addUser(Person user) {
        this.UsersList.add(user);
    }

    @Override
    public ArrayList<Person> getUsersList() {
        return this.UsersList;
    }
}
