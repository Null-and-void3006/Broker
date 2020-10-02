package com.ditra.sql_crud_webservice.controller;

import com.ditra.sql_crud_webservice.enums.AttributeType;
import com.ditra.sql_crud_webservice.model.*;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.ArrayList;

@RestController
public class CRUDController {
    @PutMapping(path="/add-row")
    public String addTableRow(@RequestBody AddRowData addData) throws IOException {
        String sql = "INSERT INTO " + addData.getTableName() + " (";

        boolean first = true;
        for(Column col: addData.getColumns()) {
            if (first) {
                first = false;
            } else {
                sql += ", ";
            }

            sql += col.getName();
        }

        sql += ")" + "\n" + "VALUES (";

        first = true;
        for(Column col: addData.getColumns()) {
            if (first) {
                first = false;
            } else {
                sql += ", ";
            }

            if (col.getType() == AttributeType.VARCHAR || col.getType() == AttributeType.CHAR || col.getType() == AttributeType.LONGTEXT) {
                sql += "\'" + col.getValue() + "\'";
            } else {
                sql += col.getValue();
            }
        }

        sql += ")";

        System.out.println(sql);

        try {
            Connection conn = Database.getEstablishedConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> data = Database.getData(addData.getTableName());

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

    @PostMapping(path="/delete-row")
    public String deleteTableRow(@RequestBody DeleteRowData deleteData) throws IOException {
        String sql = "DELETE FROM " + deleteData.getTableName() + " WHERE ";
        sql += generatePrimaryKeySQL(deleteData.getPrimaryKeys());

        System.out.println(sql);

        try {
            Connection conn = Database.getEstablishedConnection();
            PreparedStatement statement = conn.prepareStatement(sql);
            statement.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> data = Database.getData(deleteData.getTableName());

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

    @PostMapping(path="/edit-row")
    public String editTableRow(@RequestBody EditRowData editData) throws IOException {
        String sql = "UPDATE " + editData.getTableName() + " SET ";

        boolean first = true;
        for (Column uk: editData.getUpdateColumns()) {
            if (first) {
                first = false;
            } else {
                sql += ", ";
            }

            sql += uk.getName() + "=";

            if (uk.getType() == AttributeType.VARCHAR || uk.getType() == AttributeType.CHAR || uk.getType() == AttributeType.LONGTEXT) {
                sql += "\'" + uk.getValue() + "\'";
            } else {
                sql += uk.getValue();
            }
        }

        sql += " WHERE ";

        sql += generatePrimaryKeySQL(editData.getPrimaryKeys());

        System.out.println(sql);

        try {
            Connection conn = Database.getEstablishedConnection();
            PreparedStatement stmt = conn.prepareStatement(sql);
            stmt.executeUpdate();
        }catch(Exception e) {
            e.printStackTrace();
        }

        ArrayList<ArrayList<String>> data = Database.getData(editData.getTableName());

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
    }

    private String generatePrimaryKeySQL(ArrayList<Column> primaryKeys) {
        boolean first = true;
        String sql = "";

        for(Column pk: primaryKeys) {
            if (first) {
                first = false;
            } else {
                sql += " AND ";
            }

            sql += pk.getName() + "=";
            if (pk.getType() == AttributeType.VARCHAR || pk.getType() == AttributeType.CHAR || pk.getType() == AttributeType.LONGTEXT) {
                sql += "\'" + pk.getValue() + "\'";
            } else {
                sql += pk.getValue();
            }
        }

        return sql;
    }
}
