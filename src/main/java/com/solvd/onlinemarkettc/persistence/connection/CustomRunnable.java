package com.solvd.onlinemarkettc.persistence.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class CustomRunnable implements Runnable {

    private static final Logger log = LogManager.getLogger(CustomRunnable.class);
    private String desk = "desk";

    public CustomRunnable(String desk) {
        this.desk = desk;
    }

    @Override
    public void run() {
        log.info("{} is open", desk);
        try {

            Thread.sleep(10000);
            log.info("{} is closed after 10 sec", desk);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
