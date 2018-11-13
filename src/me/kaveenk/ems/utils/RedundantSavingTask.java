/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.kaveenk.ems.utils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.channels.FileChannel;
import java.nio.channels.FileLock;
import java.util.Date;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import me.kaveenk.ems.main.EMSMain;
import me.kaveenk.ems.types.Employee;

/**
 *
 * @author Kaveen Kumarasinghe
 *
 */
public class RedundantSavingTask extends TimerTask {

    private int interval;

    public RedundantSavingTask(int interval) {
        this.interval = interval;
    }

    @Override
    public void run() {
        EMSMain.logger.info(interval + " second saving task started");
        copyFileUsingChannel(new File(Employee.SERIAL_FILE), new File(Employee.BACKUP_SERIAL_FILE));
        Employee.serialize();
    }

    /**
     * Copy a file using Java Channels and file input/output streams
     *
     * @param source The file to be copied
     * @param dest The file to be copied into
     */
    private static void copyFileUsingChannel(File source, File dest) {
        FileChannel sourceChannel = null;
        FileChannel destChannel = null;
        FileLock lock = null;
        try {
            sourceChannel = new FileInputStream(source).getChannel();

            destChannel = new FileOutputStream(dest).getChannel();
            lock = destChannel.lock();

            destChannel.transferFrom(sourceChannel, 0, sourceChannel.size());

        } catch (Exception e) {
            EMSMain.logger.error("Error backing up files", e);
        } finally {

            try {
                lock.release();
                sourceChannel.close();
                destChannel.close();
            } catch (IOException ex) {
                EMSMain.logger.error("Error releasing file lock on file channel copy mechanism", ex);

                //Handle TODO.
            }
        }
    }

}
