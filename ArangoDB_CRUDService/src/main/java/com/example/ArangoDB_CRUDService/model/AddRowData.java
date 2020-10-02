package com.example.ArangoDB_CRUDService.model;

import java.util.ArrayList;

public class AddRowData {
    private String tableName;
    private ArrayList<Column> columns;

    public AddRowData(String tableName) {
        this.tableName = tableName;
        this.columns = new ArrayList<>();
    }

    public AddRowData(String tableName, ArrayList<Column> columns) {
        this.tableName = tableName;
        this.columns = columns;
    }

    public String getTableName() {
        return tableName;
    }

    public void setTableName(String tableName) {
        this.tableName = tableName;
    }

    public ArrayList<Column> getColumns() {
        return columns;
    }

    public void setColumns(ArrayList<Column> columns) {
        this.columns = columns;
    }
}
