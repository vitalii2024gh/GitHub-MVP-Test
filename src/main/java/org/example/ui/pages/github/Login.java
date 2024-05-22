package org.example.ui.pages.github;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.Selenide;
import org.example.ui.pages.interfaces.ILogin;
import org.example.ui.pages.interfaces.IMainPage;
import org.example.ui.pages.interfaces.IPage;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class Login implements ILogin, IPage {
    private static final String PAGE_URL = "/login";

    public static Login open() {
        return Selenide.open(PAGE_URL, Login.class);
    }

    public Login() {
        waitUntilLoaded();
    }

    @Override
    public IMainPage login(String username, String password) {
        loginAttempt(username, password);
        return new MainPage();
    }

    /**
     * The method is for testing purposes as not all login attempt is successful
     * and we should not throw an exception in some cases
     * @param username username to login with
     * @param password password to login with
     */
    public void loginAttempt(String username, String password) {
        $("#login_field").setValue(username);
        $("#password").setValue(password);
        $(By.name("commit")).click();
    }

    @Override
    public void waitUntilLoaded() {
        $(".auth-form-header").shouldBe(Condition.visible);
    }
}
