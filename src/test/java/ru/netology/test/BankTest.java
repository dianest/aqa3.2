package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;
import ru.netology.data.DataGenerator;
import ru.netology.data.User;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import static com.codeborne.selenide.Selenide.open;
import static ru.netology.db.DbUtils.clearTables;

public class BankTest {

    @AfterAll
    public static void teardown() {
        clearTables();
    }

    @Test
    public void testLogin() {
        final User user = DataGenerator.generateValidUser();

        open("http://localhost:9999/");

        final LoginPage loginPage = new LoginPage();
        final VerificationPage verificationPage = loginPage.loginValidUser(user.getLogin(), user.getPassword());
        final DashboardPage dashboardPage = verificationPage.enterValidCode(
                DataGenerator.getVerificationCode(user.getLogin())
        );
        dashboardPage.assertCorrectLoading();
    }

    @Test
    public void testThreeWrongPasswords() {
        final User user = DataGenerator.generateValidUser();

        open("http://localhost:9999/");

        LoginPage loginPage = new LoginPage();
        loginPage = loginPage.loginInvalidUser(user.getLogin(), "1");
        loginPage = loginPage.loginInvalidUser(user.getLogin(), "2");
        loginPage = loginPage.loginInvalidUser(user.getLogin(), "3");

        loginPage.assertLoginBlocked();
    }
}
