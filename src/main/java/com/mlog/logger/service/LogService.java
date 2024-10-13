package com.mlog.logger.service;


import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

// import org.slf4j.Logger;
// import org.slf4j.LoggerFactory;
// import org.springframework.beans.factory.annotation.Value;
// import org.springframework.stereotype.Service;

// import java.io.BufferedWriter;
// import java.io.FileWriter;
// import java.io.IOException;
// import java.time.LocalDateTime;
// import java.time.format.DateTimeFormatter;

// @Service
// public class LogService {

//     private final Logger logger = LoggerFactory.getLogger(LogService.class);
//     private final String logFileName;

//     public LogService(@Value("${logging.file.name:app.log}") String logFileName) {
//         this.logFileName = logFileName;
//     }

//     public void log(String title, String description, String logLevel) {
//         String timestamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
//         String logMessage = String.format("%s - [%s] %s: %s", timestamp, logLevel, title, description);
        
//         // Log to console
//         switch (logLevel.toUpperCase()) {
//             case "ERROR":
//                 logger.error(logMessage);
//                 break;
//             case "INFO":
//                 logger.info(logMessage);
//                 break;
//             case "DEBUG":
//                 logger.debug(logMessage);
//                 break;
//             default:
//                 logger.info(logMessage);
//                 break;
//         }
        
//         // Log to file
//         try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName, true))) {
//             writer.write(logMessage);
//             writer.newLine();
//         } catch (IOException e) {
//             logger.error("Failed to write log to file", e);
//         }
//     }
// }


import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.mlog.logger.model.LogEntry;

@Service
public class LogService implements Logger {

private final String logFileName;
private final List<LogEntry> logEntries = new ArrayList<>();

public LogService(@Value("${logging.file.name:app.log}") String logFileName) {
    this.logFileName = logFileName;
}

    @Override
    public void info(String title, String description) {
        log(title, description, "INFO");
    }

    @Override
    public void error(String title, String description) {
        log(title, description, "ERROR");
    }

    @Override
    public void debug(String title, String description) {
        log(title, description, "DEBUG");
    }

    private void log(String title, String description, String logLevel) {
        String timestamp = LocalDateTime.now().toString();
        LogEntry logEntry = new LogEntry(title, description, logLevel, timestamp);
        
        logEntries.add(0, logEntry); // Add the latest log first
        saveLogToFile(logEntry);
    }

    private void saveLogToFile(LogEntry logEntry) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(logFileName, true))) {
            writer.write(logEntry.toString());
        } catch (IOException e) {
            System.err.println("Failed to write log entry to file: " + e.getMessage());
        }
    }

    public List<LogEntry> getLogEntries() {
        return logEntries;
    }
}
