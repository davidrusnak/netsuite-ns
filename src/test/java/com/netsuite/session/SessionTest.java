package com.netsuite.session;

import org.junit.Assert;
import org.junit.Test;


/**
 * Copyright © 2016, NetSuite, Inc.
 */
public class SessionTest
{

    @Test
    public void testIsValid_validSession() throws Exception
    {
        Session session = new Session("123123", "test@example.com");
        Assert.assertTrue(session.isValid());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValid_invalidParams() throws Exception
    {
        new Session(null, null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValid_invalidId() throws Exception
    {
        new Session(null, "test@example.com");
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValid_invalidEmail() throws Exception
    {
        new Session("123", null);
    }
}
