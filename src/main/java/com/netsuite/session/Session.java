package com.netsuite.session;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.UUID;

/**
 * <p>Copyright 2000-2015, NetSuite, Inc.</p>
 */
public class Session
{

	@NotNull
	private String id;
	@NotNull
	private String email;
	@NotNull
	private Timestamp created;

	/**
	 * Used to create brand new session
	 */
	Session(@NotNull String email)
	{
		if (email == null)
		{
			throw new IllegalArgumentException("Invalid e-mail passed");
		}

		this.id = UUID.randomUUID().toString();
		this.email = email;
		this.created = new Timestamp(System.currentTimeMillis());
	}

	/**
	 * Used to create object loaded from session storage
	 */
	public Session(@NotNull String id, @NotNull String email, @NotNull Timestamp created)
	{
		if (id == null || email == null || created == null)
		{
			throw new IllegalArgumentException("Invalid arguments passed");
		}

		this.id = id;
		this.email = email;
		this.created = created;
	}

	/**
	 * @return check if session contains valid data
	 */
	public boolean isValid()
	{
		return id != null && !id.isEmpty() && email != null && created != null;
	}

	/**
	 * @return true if session is no older than 60 minutes (is still live)
	 */
	public boolean isLive()
	{
		return created.after(Timestamp.from(Instant.now().minus(1, ChronoUnit.HOURS)));
	}

	public String getId()
	{
		return id;
	}

	public String getEmail()
	{
		return email;
	}

	public Timestamp getCreated()
	{
		return created;
	}
}
