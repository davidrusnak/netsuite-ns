// Copyright 2018 NetSuite Inc.
package com.netsuite.controller;

import com.netsuite.request.Request;
import com.netsuite.session.Session;
import com.netsuite.session.SessionService;

import java.util.Objects;

public class SessionController
{

	private final SessionService sessionService;

	public SessionController(SessionService sessionService)
	{
		this.sessionService = Objects.requireNonNull(sessionService);
	}

	public String doGet(Request request)
	{
		try
		{
			Session session = sessionService.getSession(request.getSessionId());
			if (session != null)
			{
				return "Session loaded. ID: " + session.getId();
			}
			else
			{
				Session newSession = sessionService.createSession(request.getEmail());
				return "Session created. ID: " + newSession.getId();
			}
		}
		catch (Exception e)
		{
			return "Session failed: " + e.getMessage();
		}
	}
}
