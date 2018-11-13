package com.netsuite.session;

import com.netsuite.sql.SQLTool;
import org.jetbrains.annotations.NotNull;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;

/**
 * Copyright © 2016, NetSuite, Inc.
 */
public class SessionServiceImpl implements SessionService
{

	@Override
	public void persist(@NotNull Session session) throws SQLException
	{
		SQLTool.execute("INSERT INTO Session (sId, sEmail, dCreated) VALUES(?, ?, ?)", session.getId(), session.getEmail(), session.getCreated());
	}

	@Override
	public Session getSession(@NotNull String id)
	{
		try
		{
			ResultSet result = SQLTool.execute("SELECT * FROM Session WHERE sId = ? AND dCreated >= ?", id, new Timestamp(System.currentTimeMillis() - 60 * 60 * 1000));

			if (result == null || !result.next())
			{
				return null;
			}

			Session session = new Session(
					result.getString("sId"),
					result.getString("sEmail"),
					result.getTimestamp("dCreated")
			);

			if (session.isValid())
			{
				return session;
			}
		}
		catch (SQLException e)
		{
			return null;
		}

		return null;
	}

	@NotNull
	@Override
	public Session createSession(String email) throws SQLException
	{
		Session session = new Session(email);
		persist(session);
		return session;
	}
}
