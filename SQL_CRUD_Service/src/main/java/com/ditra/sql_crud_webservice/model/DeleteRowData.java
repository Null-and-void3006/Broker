package com.ditra.sql_crud_webservice.model;

import java.io.Serializable;
import java.util.ArrayList;

public class DeleteRowData implements Serializable {
    private String tableName;
    private ArrayList<Column> primaryKeys = new ArrayList<>();

    public DeleteRowData(String tableName) {
        this.tableName = tableName;
    }

    public DeleteRowData(String tableName, ArrayList<Column> primaryKeys) {
        this.tableName = tableName;
        this.primaryKeys = primaryKeys;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<Column> getPrimaryKeys() {
        return primaryKeys;
    }

    public void setPrimaryKeys(ArrayList<Column> primaryKeys) {
        this.primaryKeys = primaryKeys;
    }
}
