package com.netsuite.cassandra;

import com.netsuite.session.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.net.ConnectException;
import java.util.Collection;

/**
 * Interface available for Cassandra cluster. Currently we don't have any other possibilities of data retrieval. To
 * change behavior requires change in production cluster, which requires a lot of testing upfront.
 * <p>
 * Copyright © 2016, NetSuite, Inc.
 */
public interface CassandraSessionStorage
{

	/**
	 * Finds single session by its string ID
	 *
	 * @param key JSESSIONID
	 * @return Session object, or null if not found
	 * @throws ConnectException when connection to Cassandra cluster failed
	 * @throws IllegalArgumentException if key is not valid
	 */
	@Nullable
	Session find(@NotNull String key) throws ConnectException, IllegalArgumentException;

	/**
	 * @return all sessions as a collection or empty collection if there are no sessions
	 */
	@NotNull
	Collection<Session> findAll();

	/**
	 * @param session session object
	 * @return true if success
	 * @throws IllegalArgumentException when session object doesn't match cassandra schema
	 */
	boolean persist(@NotNull Session session) throws IllegalArgumentException;
}
