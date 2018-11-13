package com.netsuite;

import com.netsuite.request.FakeRequest;
import com.netsuite.request.Request;
import com.netsuite.session.Session;
import com.netsuite.session.SessionService;
import com.netsuite.session.SessionServiceImpl;

/**
 * Copyright © 2016, NetSuite, Inc.
 */
class App
{
    public static void main(String[] args)
    {
        Request request = new FakeRequest();
        SessionService service = new SessionServiceImpl();

        try
        {
            Session session = service.getSession(request.getSessionId());
            if (session != null)
            {
                System.out.println("Session loaded. ID: " + session.getId());
            }
            else
            {
                session = service.createSession("john.doe@internet.org");
                System.out.println("Session created. ID: " + session.getId());
            }
        } catch (Exception e)
        {
            System.out.println("FAIL. " + e.getMessage());
        }

        System.out.println("BYE.");
    }
}
