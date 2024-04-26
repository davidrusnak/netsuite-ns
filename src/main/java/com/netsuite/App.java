package com.netsuite;

import com.netsuite.cassandra.CassandraSessionStorageImpl;
import com.netsuite.controller.SessionController;
import com.netsuite.request.RealRequest;
import com.netsuite.session.DualStorageSessionService;
import com.netsuite.session.SessionService;
import com.netsuite.session.SessionServiceImpl;

/**
 * Copyright 2016, NetSuite, Inc.
 */
class App {
	private static final String MY_MAIL = "real@email.com";

	private static boolean isV2(String[] args) {
		return args.length == 1 && args[0].equals("v2");
	}

	public static void main(String[] args) {
		SessionService service;
		if (isV2(args)) {
			var sqlSessionService = new SessionServiceImpl();
			var cassandraSessionStorage = new CassandraSessionStorageImpl();
			service = new DualStorageSessionService(sqlSessionService, cassandraSessionStorage); //manual DI :D
		} else { // v1
			service = new SessionServiceImpl();
		}

		var sessionController = new SessionController(service);

		//Simulation...
		//NOTE: running this simulation with v1 makes no sense as the fake SqlStorage does not work at all
		var request1 = new RealRequest(null, MY_MAIL);
		var response1 = sessionController.doGet(request1);

		var request2 = new RealRequest(response1.sessionId(), MY_MAIL);
		var response2 = sessionController.doGet(request2);

		System.out.println(response1);
		System.out.println(response2);
	}
}
