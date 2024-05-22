package org.example.ui.pages.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.example.ui.pages.interfaces.IMainPage;
import org.example.ui.pages.interfaces.IPage;

import static com.codeborne.selenide.Selenide.$;

public class MainPage implements IMainPage, IPage {
    private static final String PAGE_URL = "/";

    public static MainPage open() {
        return Selenide.open(PAGE_URL, MainPage.class);
    }

    public MainPage() {
        waitUntilLoaded();
    }

    @Override
    public void waitUntilLoaded() {
        $(".AppHeader-user").shouldBe(Condition.visible);
    }

    public static boolean isLoaded() {
        return $(".AppHeader-user").isDisplayed();
    }
}
