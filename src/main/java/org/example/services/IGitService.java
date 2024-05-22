package org.example.services;

public interface IGitService {

    String runPullCommand(String path);

    String runAddCommand(String path, String fileName);

    String runCommitCommand(String path, String message);

    String runPushCommand(String path);

}
