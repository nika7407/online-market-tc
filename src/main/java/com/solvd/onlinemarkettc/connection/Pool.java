package com.solvd.onlinemarkettc.connection;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.UUID;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.TimeUnit;

public class Pool {

    private static final Logger log = LogManager.getLogger(Pool.class);
    private static volatile Pool pool;
    private final BlockingQueue<Connection> availableConnections;
    private final int poolSize;

    private Pool(int poolSize) {
        this.availableConnections = new LinkedBlockingQueue<Connection>(poolSize);
        this.poolSize = poolSize;
        initConects();
    }

    public static Pool getInstance(int poolSize) {
        if (pool == null) {
            synchronized (Pool.class) {
                if (pool == null) {
                    pool = new Pool(poolSize);
                }
            }
        }
        return pool;
    }

    public Connection getConnection(long time, TimeUnit timeUnit) throws InterruptedException {
        String name = Thread.currentThread().getName();
        log.info("{} requests connection", name);
        Connection connection = availableConnections.poll(time, timeUnit);

        if (connection != null) {
            log.info("{} granted connection", name);
        } else {
            log.info("{} timeout", name);
        }

        return connection;
    }

    public void releaseConnection(Connection connection) {
        if (connection == null) {
            return;
        }

        if (!connection.getActive()) {
            Connection newConnection = createSingleConn();
            availableConnections.offer(newConnection);
            log.info("connection is broken, new one is created {}", newConnection.getID());
        } else {
            if (availableConnections.offer(connection)) {
                log.info("{} coonection is free", connection.getID());
            } else {
                log.info("pool closed");
                connection.close();
            }
        }
    }

    private void initConects() {
        int i = 1;

        while (i <= poolSize) {
            Connection connection = createSingleConn();
            availableConnections.offer(connection);
            i++;
        }
    }

    private Connection createSingleConn() {
        return new Connection(UUID.randomUUID().toString());
    }
}
