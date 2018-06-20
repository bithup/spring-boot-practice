package com.example.logback;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

@Slf4j
public class LogBackTests {

    @Test
    public void logTest() {
        log.debug("debug");
        log.info("info");
        log.warn("warn");
        log.error("error");
    }
}
