package com.netsuite;

import com.netsuite.controller.SessionController;
import com.netsuite.request.FakeRequest;
import com.netsuite.request.Request;
import com.netsuite.session.SessionService;
import com.netsuite.session.SessionServiceImpl;

/**
 * Copyright 2016, NetSuite, Inc.
 */
class App
{

	public static void main(String[] args)
	{
		Request request = new FakeRequest();
		SessionService service = new SessionServiceImpl();

		SessionController sessionController = new SessionController(service);
		String returnValue = sessionController.doGet(request);

		System.out.println(returnValue);
		System.out.println("BYE.");
	}
}
