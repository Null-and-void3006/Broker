package com.ditra.sql_crud_webservice;

import com.ditra.sql_crud_webservice.model.Database;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.sql.Connection;

@SpringBootApplication
public class SQLCrudMain {
    private static Connection conn;

    public static void main(String[] args) {
        conn = Database.establishDefaultConnection();

        SpringApplication.run(SQLCrudMain.class, args);
    }

    public static Connection getConnection() {
        return conn;
    }
}
