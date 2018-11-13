package com.netsuite.session;

import org.junit.Assert;
import org.junit.Test;

import java.sql.Timestamp;
import java.time.Instant;


/**
 * Copyright © 2016, NetSuite, Inc.
 */
public class SessionTest
{

    @Test
    public void testIsValid_validSession()
    {
        Session session = new Session("123123", "test@example.com", Timestamp.from(Instant.now()));
        Assert.assertTrue(session.isValid());
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValid_invalidId()
    {
        new Session(null, "test@example.com", Timestamp.from(Instant.now()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValid_invalidEmail()
    {
        new Session("123", null, Timestamp.from(Instant.now()));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testIsValid_invalidTimestamp()
    {
        new Session("123", "test@example.com", null);
    }
}
