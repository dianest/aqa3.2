package ru.netology.page;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.SelenideElement;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Selenide.$;

public class DashboardPage {
    private final SelenideElement account = $(By.cssSelector("h2"));

    public DashboardPage() {
    }

    public void assertCorrectLoading() {
        account.waitUntil(Condition.visible, 5000).
                shouldHave(text("Личный кабинет"));
    }
}
