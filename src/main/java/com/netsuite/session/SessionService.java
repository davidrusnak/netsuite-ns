package com.netsuite.session;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;


/**
 * Copyright 2016, NetSuite, Inc.
 */
public interface SessionService
{

	/**
	 * Sores session to a storage
	 */
	void persist(Session session) throws Exception;

	/**
	 * Retrieves valid (no older than 1 hour) session from storage
	 *
	 * @param id string identification of ID
	 * @return session object or null in case of some problem
	 */
	@Nullable
	Session getSession(String id);

	/**
	 * Creates new session with given e-mail
	 *
	 * @param email user's email
	 * @return new Session object
	 */
	@NotNull
	Session createSession(String email) throws Exception;
}
