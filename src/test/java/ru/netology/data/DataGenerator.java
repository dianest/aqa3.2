package ru.netology.data;

import ru.netology.db.DbUtils;

public class DataGenerator {
    private DataGenerator() {
    }

    public static User generateValidUser() {
        return new User("vasya", "qwerty123");
    }

    public static String getVerificationCode(String login) {
        return DbUtils.getAuthCode(login);
    }
}
