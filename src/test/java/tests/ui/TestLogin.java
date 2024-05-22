package tests.ui;

import org.example.ui.pages.github.Login;
import org.example.ui.pages.github.MainPage;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static assertions.UiLoginAssertions.checkSuccessfulUiLogin;
import static org.example.utils.PropertyUtils.getCredentialsProperties;

public class TestLogin extends BaseUiTest {
    ResourceBundle resourceBundle = getCredentialsProperties();
    String username = resourceBundle.getString("user.allowed.login");
    String password = resourceBundle.getString("user.allowed.password");

    @Test
    void testLogin() {

        // given
        var loginPage = Login.open();

        // when
        loginPage.loginAttempt(username, password);

        // then
        checkSuccessfulUiLogin();
    }
}
