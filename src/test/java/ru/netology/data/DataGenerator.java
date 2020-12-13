package ru.netology.data;

import ru.netology.db.DbUtils;

public class DataGenerator {
    private DataGenerator() {
    }

    public static User generateValidUser() {
        return new User("vasya", "qwerty123");
    }

    public static User generateInvalidPasswordUser() {
        return new User("vasya", "123");
    }
}
