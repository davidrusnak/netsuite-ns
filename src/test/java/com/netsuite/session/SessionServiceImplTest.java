package com.netsuite.session;

import com.netsuite.sql.SQLTool;
import junit.framework.TestCase;
import mockit.NonStrictExpectations;
import mockit.integration.junit4.JMockit;
import org.hamcrest.CoreMatchers;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;

import java.sql.ResultSet;
import java.sql.Timestamp;

import static org.hamcrest.CoreMatchers.*;

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
        Assert.assertThat(session, nullValue());
    }

    @Test
    public void testGetSessionValidResultReturnsSession() throws Exception {

        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.doReturn(true).when(resultSetMock).next();
        Mockito.doReturn("123123").when(resultSetMock).getString("sId");
        Mockito.doReturn("test@example.com").when(resultSetMock).getString("sEmail");
        Mockito.doReturn(new Timestamp(System.currentTimeMillis()-20000)).when(resultSetMock).getTimestamp("dCreated");

        new NonStrictExpectations(SQLTool.class) {{
            SQLTool.execute(anyString, any, any);
            returns(resultSetMock);
        }};

        SessionServiceImpl service = new SessionServiceImpl();
        Session session = service.getSession("123");
        Assert.assertThat(session, notNullValue());
        Assert.assertThat(session.getEmail(), is("test@example.com"));
        Assert.assertThat(session.getId(), is("123123"));
    }

    @Test(expected = Exception.class)
    public void testGetSessionInvalidSessionReturnsNull() throws Exception {

        ResultSet resultSetMock = Mockito.mock(ResultSet.class);
        Mockito.doReturn(true).when(resultSetMock).next();
        Mockito.doReturn("123123").when(resultSetMock).getString("sId");
        Mockito.doReturn("test@example.com").when(resultSetMock).getString("sEmail");
        Mockito.doReturn(null).when(resultSetMock).getTimestamp("dCreated");

        new NonStrictExpectations(SQLTool.class) {{
            SQLTool.execute(anyString, any, any);
            returns(resultSetMock);
        }};

        SessionServiceImpl service = new SessionServiceImpl();
        service.getSession("123");
    }

    @Test
    public void testCreateSession() throws Exception {
        SessionServiceImpl service = new SessionServiceImpl();

        Session session = service.createSession("test@example.com");
        Assert.assertThat(session.getEmail(), is("test@example.com"));
    }
}
