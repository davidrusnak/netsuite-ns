// Copyright 2018 NetSuite Inc.
package com.netsuite.controller;

import com.netsuite.request.Request;
import com.netsuite.response.Response;
import com.netsuite.session.Session;
import com.netsuite.session.SessionService;

import java.util.Objects;

public class SessionController {

	private final SessionService sessionService;

	public SessionController(SessionService sessionService) {
		this.sessionService = Objects.requireNonNull(sessionService);
	}

	public Response doGet(Request request) {
		try {
			Session session = sessionService.getSession(request.getSessionId());
			if (session != null) {
				System.out.println("Session loaded. ID: " + session.getId());
				return new Response(session.getId(), "Response 1 content");
			} else {
				Session newSession = sessionService.createSession(request.getEmail());
				System.out.println("Session created. ID: " + newSession.getId());
				return new Response(newSession.getId(), "Response 2 content");
			}
		}
		catch (Exception e) {
			System.out.println("Session failed: " + e.getMessage());
			return null; //normally would rethrow and handle in a common exception handler
		}
	}
}
