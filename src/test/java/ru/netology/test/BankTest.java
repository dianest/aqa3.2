package ru.netology.test;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Test;
import ru.netology.data.DataGenerator;
import ru.netology.data.User;
import ru.netology.db.DbUtils;
import ru.netology.page.DashboardPage;
import ru.netology.page.LoginPage;
import ru.netology.page.VerificationPage;

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
                DbUtils.getAuthCode(user.getLogin())
        );
        dashboardPage.assertCorrectLoading();
    }

    @Test
    public void testThreeWrongPasswords() {
        open("http://localhost:9999/");

        final User user = DataGenerator.generateInvalidPasswordUser();
        LoginPage loginPage = new LoginPage();
        for(int i = 0; i < 3; i++) {
            loginPage = loginPage.loginInvalidUser(user.getLogin(), user.getPassword());
        }

        loginPage.assertLoginBlocked();
    }
}
