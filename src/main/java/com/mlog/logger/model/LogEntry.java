package com.mlog.logger.model;


public class LogEntry {
    private String title;
    private String description;
    private String level;
    private String timestamp;

    public LogEntry(String title, String description, String level, String timestamp) {
        this.title = title;
        this.description = description;
        this.level = level;
        this.timestamp = timestamp;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public String getLevel() {
        return level;
    }

    public String getTimestamp() {
        return timestamp;
    }
}


