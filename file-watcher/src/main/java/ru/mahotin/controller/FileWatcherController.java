package ru.mahotin.controller;

import lombok.extern.log4j.Log4j;
import org.springframework.boot.context.event.ApplicationStartedEvent;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Controller;


@Controller
@Log4j
public class FileWatcherController {

    @EventListener(ApplicationStartedEvent.class)
    public void testRun() {
        log.debug("FileWatcher is running");
    }
}
