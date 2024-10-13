package com.mlog.logger.service;

public interface Logger {
    void info(String title, String description);
    void error(String title, String description);
    void debug(String title, String description);
}

