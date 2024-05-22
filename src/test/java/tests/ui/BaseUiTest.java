package tests.ui;

import com.codeborne.selenide.Configuration;
import org.junit.jupiter.api.BeforeAll;

import static org.example.utils.PropertyUtils.getSelenideProperties;

public class BaseUiTest {
    @BeforeAll
    static void baseSetup() {
        Configuration.baseUrl = getSelenideProperties().getString("baseUrl");
        Configuration.browser = getSelenideProperties().getString("browser");
        Configuration.timeout = Long.parseLong(getSelenideProperties().getString("timeout"));
    }
}
