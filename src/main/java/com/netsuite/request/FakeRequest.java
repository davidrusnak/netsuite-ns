package com.netsuite.request;

/**
 * Copyright 2016, NetSuite, Inc.
 */
public class FakeRequest implements Request
{

	@Override
	public String getSessionId()
	{
		return "123123";
	}

	@Override
	public String getEmail() {
		return "john.doe@internet.org";
	}
}
