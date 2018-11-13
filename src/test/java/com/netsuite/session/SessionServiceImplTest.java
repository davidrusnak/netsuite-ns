package com.netsuite.session;

import com.netsuite.sql.SQLTool;
import junit.framework.TestCase;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.Timestamp;

/**
 * Copyright © 2016, NetSuite, Inc.
 */
@RunWith(JMockit.class)
public class SessionServiceImplTest extends TestCase {

    @Test
    public void testGetSessionNullResultReturnsNull() throws Exception {
        new NonStrictExpectations(SQLTool.class) {{
            SQLTool.execute(anyString, any, any);
            returns(null);
        }};

        SessionServiceImpl service = new SessionServiceImpl();
        Session session = service.getSession("123");
        Assert.assertNull(session);
    }

    @Test
    public void testGetSessionValidResultReturnsSession() throws Exception {

        final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.doReturn(true).when(resultSetMock).next();
        Mockito.doReturn("123123").when(resultSetMock).getString("id");
        Mockito.doReturn("test@example.com").when(resultSetMock).getString("email");
        Mockito.doReturn(new Timestamp(System.currentTimeMillis()-20000)).when(resultSetMock).getTimestamp("created");

        new NonStrictExpectations(SQLTool.class) {{
            SQLTool.execute(anyString, any, any);
            returns(resultSetMock);
        }};

        SessionServiceImpl service = new SessionServiceImpl();
        Session session = service.getSession("123");
        Assert.assertNotNull(session);
        Assert.assertEquals("test@example.com", session.getEmail());
        Assert.assertEquals("123123", session.getId());
    }

    @Test
    public void testGetSessionInvalidSessionReturnsNull() throws Exception {

        final ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.doReturn(true).when(resultSetMock).next();
        Mockito.doReturn("123123").when(resultSetMock).getString("id");
        Mockito.doReturn("test@example.com").when(resultSetMock).getString("email");
        Mockito.doReturn(null).when(resultSetMock).getTimestamp("created");

        new NonStrictExpectations(SQLTool.class) {{
            SQLTool.execute(anyString, any, any);
            returns(resultSetMock);
        }};

        SessionServiceImpl service = new SessionServiceImpl();
        Session session = service.getSession("123");
        Assert.assertNull(session);
    }

    @Test
    public void testCreateSession() throws Exception {
        SessionServiceImpl service = new SessionServiceImpl();

        Session session = service.createSession("test@example.com");
        Assert.assertEquals("test@example.com", session.getEmail());
    }
}
