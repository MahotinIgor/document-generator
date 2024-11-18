package ru.mahotin.service;

import java.util.HashMap;

public interface FileWatcherService {
    void run(HashMap<String, String> filePathAndTopic);
}
