package ru.mahotin.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;

import java.io.IOException;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.WatchEvent;
import java.nio.file.WatchKey;
import java.nio.file.WatchService;

import static java.nio.file.StandardWatchEventKinds.ENTRY_CREATE;

@Controller
@Log4j
public class FileWatcherController {

    @Value("${file-watcher.pathMonitoring}")
    private String pathMonitoring;

    @EventListener(ApplicationStartedEvent.class)
    public void runWatchService() {
        log.debug("FileWatcher is running");

        try {
            WatchService watchService = FileSystems.getDefault().newWatchService();
            Path path = Paths.get(pathMonitoring);
            path.register(watchService, ENTRY_CREATE);
            WatchKey key;
            while((key = watchService.take()) != null) {
                for(WatchEvent<?> event : key.pollEvents()) {
                    log.debug("Event kind: " +event.kind() +"; File affected: " + event.context());
                }
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
