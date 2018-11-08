/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems.utils;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Kaveen Kumarasinghe Custom logger class to handle outputs to the log
 * file and to console.
 */
public class LogHandler {

    private String logFile;
    private Logger logger;

    /**
     * Initialize a new LogHandler that will log to the param logFile
     *
     * @param logFile the file to log data to.
     */
    public LogHandler(String logFile) {

        this.logFile = logFile;
        initialize();
    }

    /**
     * Log an error
     *
     * @param logMessage The message to log
     * @param t The throwable from the error to log
     */
    public void error(String logMessage, Throwable t) {
        logger.log(Level.SEVERE, new Date() + ": " + logMessage, t);
    }

    public void info(String logMessage) {
        logger.info(new Date() + ":  " + logMessage);
    }

    public void warning(String logMessage) {
        logger.log(Level.WARNING, new Date() + ": " + logMessage);
    }

    public void severe(String logMessage) {
        logger.log(Level.SEVERE, new Date() + ": " + logMessage);
    }

    /**
     * Initialize the logger and connect it to a FileHandler that logs to the
     * desired output file.
     */
    private void initialize() {
        logger = Logger.getLogger("EMS Log");
        FileHandler fh;

        try {

            // This block configure the logger with handler and formatter  
            fh = new FileHandler(logFile);
            logger.addHandler(fh);
            SimpleFormatter formatter = new SimpleFormatter();
            fh.setFormatter(formatter);

            // the following statement is used to log any messages  
            logger.info("Logger initialized at" + new Date());

        } catch (SecurityException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
