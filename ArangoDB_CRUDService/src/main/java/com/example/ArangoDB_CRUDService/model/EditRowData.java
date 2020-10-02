package com.example.ArangoDB_CRUDService.model;

import java.io.Serializable;
import java.util.ArrayList;

public class EditRowData implements Serializable {
    private String tableName;
    private ArrayList<Column> primaryKeys = new ArrayList<>();
    private ArrayList<Column> updateColumns = new ArrayList<>();

    public EditRowData(String tableName) {
        this.tableName = tableName;
    }

    public EditRowData(String tableName, ArrayList<Column> primaryKeys, ArrayList<Column> updateColumns) {
        this.tableName = tableName;
        this.primaryKeys = primaryKeys;
        this.updateColumns = updateColumns;
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

    public ArrayList<Column> getUpdateColumns() {
        return updateColumns;
    }

    public void setUpdateColumns(ArrayList<Column> updateColumns) {
        this.updateColumns = updateColumns;
    }
}
