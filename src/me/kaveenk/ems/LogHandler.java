/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems;

import java.io.IOException;
import java.util.Date;
import java.util.logging.FileHandler;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;

/**
 *
 * @author Kaveen Kumarasinghe
 */
public class LogHandler {

    private String logFile;
    private Logger logger;

    public LogHandler(String logFile) {
       
        this.logFile = logFile;
        initialize();
    }
    public void error(String logMessage, Throwable t) {
        logger.log(Level.SEVERE, new Date() +": "+logMessage, t);
    }

    public void info(String logMessage) {
        logger.info(new Date() +":  " + logMessage);
    }
    public void warning(String logMessage) {
        logger.log(Level.WARNING, new Date() +": "+logMessage);
    }
    public void severe(String logMessage) {
        logger.log(Level.SEVERE,new Date() +": "+logMessage);
    }
    

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
