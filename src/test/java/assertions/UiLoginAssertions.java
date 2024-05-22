package assertions;

import org.example.ui.pages.github.MainPage;

import static org.assertj.core.api.Assertions.assertThat;

public class UiLoginAssertions {
    private UiLoginAssertions() {}

    public static void checkSuccessfulUiLogin() {
        assertThat(MainPage.isLoaded()).as("Main page was not loaded after login").isTrue();
    }
}
