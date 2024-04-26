package com.netsuite.session;

import com.netsuite.cassandra.CassandraSessionStorage;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.ConnectException;

/**
 * @author David Rusnak
 * <p>
 * A SessionService implementation where two storages are used (SQL and Cassandra)
 * Cassandra should be the primary (potentially the only one in the future) and SQL is the fallback
 * storage in case a Session was created on V1 system.
 * <p>
 * NOTES: I noticed, the sessions are not refreshed when retrieved, so they always last only 1 hour and then new
 * ones are created. Therefore, this service also does not handle refreshing to preserve functionality.
 * <p>
 * This service also does not handle migration or writethrough from the SqlStorage to Cassandra. The Sessions
 * stored in SqlStorage are handled from there.
 * <p>
 * Once all servers will be on V2, the SqlStorage can be completely removed.
 */
public class DualStorageSessionService implements SessionService {

    private final SessionService sqlSessionService;
    private final CassandraSessionStorage cassandraSessionStorage;

    public DualStorageSessionService(SessionService sqlSessionService, CassandraSessionStorage cassandraSessionStorage) {
        this.sqlSessionService = sqlSessionService;
        this.cassandraSessionStorage = cassandraSessionStorage;
    }

    /**
     * Persists the given session into both storages.
     */
    @Override
    public void persist(Session session) throws Exception {
        cassandraSessionStorage.persist(session);
        sqlSessionService.persist(session);
    }

    /**
     * Tries to retrieve a session from Cassandra. If not found there, try SqlStorage. If not found in any, returns null.
     * @param id string identification of ID
     * @return the Session with given ID or null if not found
     */
    @Nullable
    @Override
    public Session getSession(String id) {
        try {
            //first check Cassandra
            var sessionFromCass = cassandraSessionStorage.find(id);

            if (sessionFromCass != null && sessionFromCass.isLive()) {
                return sessionFromCass;
            }

            //second, check SqlStorage (as fallback if Session was created on v1)
            var sessionFromSql = sqlSessionService.getSession(id);

            if (sessionFromSql != null && sessionFromSql.isLive()) {
                return sessionFromSql;
            }
        } catch (IllegalArgumentException e) {
            //log Invalid key <id>: e.getMessage
        } catch (ConnectException e) {
            //log Could not connect to DB: e.getMessage
        }

        return null;
    }

    @NotNull
    @Override
    public Session createSession(String email) throws Exception {
        var session = new Session(email);
        persist(session);
        return session;
    }
}
