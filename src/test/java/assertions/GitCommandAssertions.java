package assertions;

import static org.assertj.core.api.Assertions.assertThat;

public class GitCommandAssertions {
    private GitCommandAssertions() {}

    public static void checkGitPullOutputSuccessful(String commandOutput) {
        assertThat(commandOutput).contains("Already up to date.");
    }

    public static void checkGitPushOutputSuccessful(String commandOutput) {
        assertThat(commandOutput).contains("updating local tracking ref 'refs/remotes/origin/main'");
    }

    public static void checkGitPullOutputRestricted(String commandOutput) {
        assertThat(commandOutput).contains("Write access to repository not granted.");
    }
}

