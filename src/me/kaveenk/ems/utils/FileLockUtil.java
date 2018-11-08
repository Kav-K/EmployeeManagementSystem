package me.kaveenk.ems.utils;

import java.io.*;
import java.nio.channels.*;

public class FileLockUtil {

    private String appID;
    private File file;
    private FileChannel channel;
    private FileLock lock;
    private boolean alreadyActive;

    /**
     * Try to lock a .tmp file in the user's home directory using FileLock and
     * FileChannel. This lock represents the running state of the program and it
     * will disallow the program from starting multiple instances!
     *
     * @param appID The name of the app//To be used in the lock file.
     */
    public FileLockUtil(String appID) {
        this.appID = appID;
        file = new File(System.getProperty("user.home"), appID + ".tmp");
        try {
            channel = new RandomAccessFile(file, "rw").getChannel();
            //Try to lock the file, it will error if it is already locked.
            try {
                lock = channel.tryLock();
            } catch (Exception e) {
                //This means it's already locked
                release(false);
                alreadyActive = true;
                return;
                
            }

            //Another check for if the file is already locked//wasn't able to lock now.
            if (lock == null) {
                release(false);
                alreadyActive = true;
                return;
            }
            Runtime.getRuntime().addShutdownHook(new Thread() {
                // destroy the lock when the JVM is closing
                public void run() {
                    release(true);
                }
            });
            alreadyActive = false;

        } catch (Exception e) {
            release(false);
            alreadyActive = true;
            

        }

    }
   /**
    * Release the lock
    * @param delete Decides whether to delete the lock file or not.
    */
    private void release(boolean delete) {
        try {
            lock.release();
            channel.close();
        } catch (Exception e) {

        }
        if (delete) {
            try {
                file.delete();
            } catch (Exception e) {

            }
        }

    }
    public boolean isAppAlreadyRunning() {
        return this.alreadyActive;
    }

}
