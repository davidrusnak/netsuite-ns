package com.netsuite.sql;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Copyright © 2016, NetSuite, Inc.
 */
public final class SQLTool
{
    /**
     * Executes sql query with binds as a second parameter
     *
     * @param sql   SQL query where ? stands for bound value
     * @param binds list of bind values
     * @return ResultSet iterator
     */
    public static ResultSet execute(@NotNull String sql, @Nullable Object... binds) throws SQLException
    {
        System.out.println("Executing: " + sql + (binds != null ? " with " + binds.length + " binds: " : ""));

        if (binds != null)
        {
            for (Object bind : binds)
            {
                System.out.println("\t" + bind);
            }
        }

        return null;
    }
}
