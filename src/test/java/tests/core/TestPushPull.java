package tests.core;

import org.example.services.GitCliService;
import org.junit.jupiter.api.Test;

import java.util.ResourceBundle;

import static assertions.GitCommandAssertions.checkGitPullOutputRestricted;
import static assertions.GitCommandAssertions.checkGitPullOutputSuccessful;
import static assertions.GitCommandAssertions.checkGitPushOutputSuccessful;
import static org.example.utils.PropertyUtils.getCommonProperties;

public class TestPushPull {
    GitCliService gitService = new GitCliService();
    ResourceBundle resourceBundle = getCommonProperties();

    @Test
    void testPullCommand() {
        // given
        String testRepoPath = resourceBundle.getString("test.repo.path");

        // when
        String output = gitService.runPullCommand(testRepoPath);

        // then
        checkGitPullOutputSuccessful(output);
    }

    @Test
    void testPushCommand() {
        // given
        String testRepoPath = resourceBundle.getString("test.repo.path");
        gitService.makeRandomCommit(testRepoPath);

        // when
        String output = gitService.runPushCommand(testRepoPath);

        // then
        checkGitPushOutputSuccessful(output);
    }

    @Test
    void testRestrictedAccess() {
        // given
        String testRepoRestrictedPath = resourceBundle.getString("test.repoRestricted.path");

        // when
        String output = gitService.runPullCommand(testRepoRestrictedPath);

        // then
        checkGitPullOutputRestricted(output);
    }
}
