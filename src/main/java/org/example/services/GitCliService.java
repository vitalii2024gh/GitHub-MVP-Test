package org.example.services;


import org.example.exceptions.GitExecutionException;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class GitCliService implements IGitService {
    private static final boolean IS_WINDOWS = System.getProperty("os.name").toLowerCase().startsWith("windows");
    // To workaround MacOS Transparency Consent and Control (TCC) restriction I wrapped all commands with a files
    // See https://stackoverflow.com/questions/69609875/cant-open-input-file-zsh for details
    private static final boolean IS_MAC = System.getProperty("os.name").toLowerCase().startsWith("mac os");
    private static final URL SCRIPTS_URL = GitCliService.class.getClassLoader().getResource("scripts");

    public GitCliService() {
        if (IS_MAC) {
            if (SCRIPTS_URL == null) {
                throw new GitExecutionException("MacOS requires scripts to be in the resources folder", null);
            }
        }
    }

    @Override
    public String runPullCommand(String path) {
        if (IS_MAC) {
            return executeCommand(path, List.of(SCRIPTS_URL.getPath() + "/pull.sh"));
        }
        return executeCommand(path, List.of("git", "pull"));
    }

    @Override
    public String runAddCommand(String path, String fileName) {
        if (IS_MAC) {
            return executeCommand(path, List.of(SCRIPTS_URL.getPath() + "/add.sh", fileName));
        }
        return executeCommand(path, List.of("git", "add", fileName));
    }

    @Override
    public String runCommitCommand(String path, String message) {
        if (IS_MAC) {
            return executeCommand(path, List.of(SCRIPTS_URL.getPath() + "/commit.sh", message));
        }
        return executeCommand(path, List.of("git", "commit", "-m", message));
    }

    @Override
    public String runPushCommand(String path) {
        if (IS_MAC) {
            return executeCommand(path, List.of(SCRIPTS_URL.getPath() + "/push.sh"));
        }
        return executeCommand(path, List.of("git", "push"));
    }

    public String makeRandomChange(String path, String filename) {
        if (IS_MAC) {
            return executeCommand(path, List.of(SCRIPTS_URL.getPath() + "/random-change.sh", filename));
        }
        return executeCommand(path, List.of("date", ">", filename));
    }

    public void makeRandomCommit(String path) {
        makeRandomChange(path, "README.md");
        runAddCommand(path, "README.md");
        runCommitCommand(path, "Next commit");
    }

    private String executeCommand(String path, List<String> command) {
        ProcessBuilder builder = new ProcessBuilder();
        List<String> commands = new ArrayList<>();
        if (IS_WINDOWS) {
            commands.add("cmd.exe");
        } else {
            commands.add("sh");
        }
        commands.addAll(command);
        builder.command(commands);

        builder.directory(new File(path));
        builder.redirectErrorStream(true);

        StringBuilder output = new StringBuilder();
        try {
            Process process = builder.start();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));

            String line;
            while ((line = reader.readLine()) != null) {
                output.append(line).append("\n");
            }

            int exitCode = process.waitFor();
            // here we can add logging for troubleshooting if needed instead of simple stdout
//            System.out.println(exitCode);
        } catch (IOException | InterruptedException e) {
            throw new GitExecutionException(e.getMessage(), e.getCause());
        }

        return output.toString();
    }
}
