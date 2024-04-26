package com.netsuite.session;

import com.netsuite.cassandra.CassandraSessionStorageImpl;
import mockit.Mock;
import mockit.MockUp;
import org.jetbrains.annotations.NotNull;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;

public class DualStorageSessionServiceTest {

    @Test
    public void persist() {
    }

    @Test
    public void getNonExistentSession() {
        var sqlService = new SessionServiceImpl();
        var cassandraStorage = new CassandraSessionStorageImpl();
        var service = new DualStorageSessionService(sqlService, cassandraStorage);

        assertNull(service.getSession("non-existent"));
    }

    @Test
    public void getExistingSession() {
        new MockUp<CassandraSessionStorageImpl>() {
            @Mock
            public Session find(@NotNull String key) {
                System.out.println("Fetching session from MOCK Cassandra with id: " + key);
                return new Session(key,
                        "mail@mail.com",
                        Timestamp.from(Instant.now().minus(5, ChronoUnit.MINUTES))
                );
            }

        };
        var sqlService = new SessionServiceImpl();
        var cassandraStorage = new CassandraSessionStorageImpl();
        var service = new DualStorageSessionService(sqlService, cassandraStorage);

        assertNotNull(service.getSession("existing-id"));
    }

    @Test
    public void createSession() {
    }
}