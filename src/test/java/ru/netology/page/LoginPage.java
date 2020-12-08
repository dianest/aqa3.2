package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class LoginPage {
    private final SelenideElement usernameInput = $("[data-test-id=login] input");
    private final SelenideElement passwordInput = $("[data-test-id=password] input");
    private final SelenideElement loginButton = $("[data-test-id=action-login]");
    private final SelenideElement errorNotification = $("[data-test-id=error-notification]");

    public LoginPage() {
    }

    public VerificationPage loginValidUser(String userName, String password) {
        usernameInput.sendKeys(userName);
        passwordInput.sendKeys(password);
        loginButton.click();

        return new VerificationPage();
    }

    public LoginPage loginInvalidUser(String userName, String password) {
        usernameInput.doubleClick();
        usernameInput.sendKeys(userName);
        passwordInput.doubleClick();
        passwordInput.sendKeys(password);
        loginButton.click();

        return new LoginPage();
    }

    public void assertLoginBlocked() {
        errorNotification.waitUntil(Condition.visible, 5000).
                shouldHave(text("Система временно заблокирована. Приходите попозже."));
    }
}
