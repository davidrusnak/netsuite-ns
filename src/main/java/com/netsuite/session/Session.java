package com.netsuite.session;

import org.jetbrains.annotations.NotNull;

import java.sql.Timestamp;
import java.util.UUID;

/**
 * Copyright © 2016, NetSuite, Inc.
 */
public class Session
{
    private String id;
    private String email;
    private Timestamp created;

    /**
     * Used to create brand new session
     */
    Session(@NotNull String email)
    {
        if(email == null)
        {
            throw new IllegalArgumentException("Invalid e-mail passed");
        }

        this.id = UUID.randomUUID().toString();
        this.email = email;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    /**
     * Used to create object loaded from session storage
     */
    Session(@NotNull String id, @NotNull String email)
    {
        if(id == null || email == null)
        {
            throw new IllegalArgumentException("Invalid arguments passed");
        }

        this.id = id;
        this.email = email;
        this.created = new Timestamp(System.currentTimeMillis());
    }

    /**
     * @return trie if session contains valid data
     */
    public boolean isValid()
    {
        return id != null && !id.isEmpty() && email != null && created != null;
    }

    public String getId()
    {
        return id;
    }

    public String getEmail()
    {
        return email;
    }

    public Timestamp getCreated()
    {
        return created;
    }

    void setCreated(Timestamp created)
    {
        this.created = created;
    }
}
