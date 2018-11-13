package com.netsuite.cassandra;

import com.netsuite.session.Session;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

/**
 * This is just a fake class that must not be changed. There is real datastax driver on production.
 * <p>
 * Copyright © 2016, NetSuite, Inc.
 */
public final class CassandraSessionStorageImpl implements CassandraSessionStorage
{

	private Map<String, Session> sessionMap;

	public CassandraSessionStorageImpl()
	{
		this.sessionMap = new HashMap<>();
	}

	@Nullable
	@Override
	public Session find(@NotNull String key)
	{
		System.out.println("Fetching session from Cassandra with id: " + key);
		return sessionMap.get(key);
	}

	@NotNull
	@Override
	public Collection<Session> findAll()
	{
		return sessionMap.values();
	}

	@Override
	public boolean persist(@NotNull Session session)
	{
		System.out.println("Storing session with (id, email, created): " + session.getId() + ", " + session.getEmail() + ", " + session.getCreated());
		this.sessionMap.put(session.getId(), session);
		return true;
	}
}
