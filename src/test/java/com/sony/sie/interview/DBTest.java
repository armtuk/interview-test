package com.sony.sie.interview;

import org.dbunit.DBTestCase;
import org.dbunit.PropertiesBasedJdbcDatabaseTester;
import org.dbunit.dataset.IDataSet;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;

public class DBTest
{
    private Connection conn;

    @Before
    public void before() throws Exception {
        conn = DriverManager.getConnection("jdbc:hsqldb:mem:mymemdb", "SA", "");
        Statement stmt = conn.createStatement();
        stmt.executeUpdate("create table person(id int, first_name varchar(256), last_name varchar(256))");

        stmt.executeUpdate("create table address(street varchar(256), city varchar(256), state char(2), zipcode char(5))");

        stmt.executeUpdate("create table zipcode(zipcode char(5), latitude float, longitude float)");
    }

    public void bootstrapData() throws Exception {
        conn.createStatement().executeUpdate("insert into person (id, first_name, last_name) values (1, 'Alex',' Turner')");
        conn.createStatement().executeUpdate("insert into address (street, city, state, zipcode) values ('101 Main St', 'San Diego','CA', '92103')");
        conn.createStatement().executeUpdate("insert into zipcode (zipcode, latitude, longitude) values ('92103', '32.7157', '117.1611')");
    }

    @Test
    public void testInsert() throws Exception {
        Statement stmt = conn.createStatement();
        int r = stmt.executeUpdate("insert into person (id, first_name, last_name) values (1001, 'Joe',' Bloggs')");
        assertEquals(1, r);
    }
}
