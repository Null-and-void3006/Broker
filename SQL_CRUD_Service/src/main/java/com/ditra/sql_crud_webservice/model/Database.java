package com.ditra.sql_crud_webservice.model;

import com.ditra.sql_crud_webservice.enums.AttributeType;



import java.sql.*;
import java.util.ArrayList;

public class Database {
    private static Connection conn = null;
    private static ArrayList<Entity> entities = new ArrayList<>();

    public static Connection getEstablishedConnection() {
        return conn;
    }

    public static ArrayList<Entity> getEntities() {
        return entities;
    }

    public static Connection establishDefaultConnection() {
        if (conn == null) {
            try {
                conn = DriverManager.getConnection("jdbc:mysql://64.225.110.65/tim_401_6_si2019",
                        "tim_401_6_si2019", "DJuPhSnL");
                getDatabaseMetadata();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        return conn;
    }

    public static void getDatabaseMetadata() {
        DatabaseMetaData md;

        try {
            md = conn.getMetaData();
            ResultSet rs = md.getTables("tim_401_6_si2019", null, "%", null);

            //Filling the entities array list
            while (rs.next()) {
                String currTable = rs.getString(3);
                Entity curr = new Entity(currTable);
                ResultSet columns = md.getColumns("tim_401_6_si2019", null, curr.getName(), null);

                //Reading attributes and adding them to an entity
                while (columns.next()) {
                    Attribut currAttr = new Attribut(columns.getString("COLUMN_NAME"));

                    currAttr.setType(AttributeType.valueOf(columns.getString(6)));

                    if(columns.getString("IS_NULLABLE").equals("NO")) {
                        currAttr.setMandatory(true);
                    } else {
                        currAttr.setMandatory(false);
                    }

                    curr.addAttribute(currAttr);
                }

                entities.add(curr);
            }

            //Defining primary attributes
            for (int  i = 0; i < entities.size(); i++) {
                ResultSet primaryKeys = md.getPrimaryKeys("tim_401_6_si2019", null, entities.get(i).getName());
                while (primaryKeys.next()) {
                    String attrName = primaryKeys.getString("COLUMN_NAME");
                    Entity currEntity = entities.get(i);
                    int pos = 0;

                    while (!currEntity.getAttributes().get(pos).getName().equals(attrName)) pos++;

                    Attribut currAttr = currEntity.getAttributes().get(pos);
                    currAttr.setPrimaryKey(true); //primary key

                    currEntity.getAttributes().set(pos, currAttr);
                    entities.set(i, currEntity);
                }
            }

            //Setting up relations
            for (int  i = 0; i < entities.size(); i++) {
                ResultSet keys = md.getImportedKeys("tim_401_6_si2019", null, entities.get(i).getName());
                int pos  = 0;
                while (keys.next()) {
                    Entity fromEntity = entities.get(i);

                    String toEntityName = keys.getString(3);
                    pos = 0;
                    while(!entities.get(pos).getName().equals(toEntityName)) pos++;
                    Entity toEntity = entities.get(pos);

                    String fromAttributeName = keys.getString(8);
                    pos = 0;
                    while(!fromEntity.getAttributes().get(pos).getName().equals(fromAttributeName)) pos++;
                    Attribut fromAttribut = fromEntity.getAttributes().get(pos);


                    String toAttributeName = keys.getString(4);
                    pos = 0;
                    while(!toEntity.getAttributes().get(pos).getName().equals(toAttributeName)) pos++;
                    Attribut toAttribut = toEntity.getAttributes().get(pos);


                    Relation r = new Relation(fromEntity.getName() + " -> " + toEntity.getName(), fromEntity, toEntity, fromAttribut, toAttribut );

                    fromEntity.addRelation(r);
                    entities.set(i, fromEntity);
                }
            }

        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }

    public static ArrayList<ArrayList<String>> getData(String tableName) {
        try {
            conn = getEstablishedConnection();
            Statement stmt = conn.createStatement();

            Entity table = entities.stream()
                    .filter(entity -> tableName.equals(entity.getName()))
                    .findAny()
                    .orElse(null);
            int colSize = table.getAttributes().size();

            String sql = "SELECT * FROM " + tableName;
            ResultSet rs = stmt.executeQuery(sql);

            System.out.println(tableName);

            ArrayList<ArrayList<String>> tableRows = new ArrayList<>();

            while (rs.next()) {
                ArrayList<String> rowData = new ArrayList<>();

                for(int i = 0; i < colSize; i++){
                    rowData.add(rs.getString(i+1));
                }

                tableRows.add(rowData);
            }

            rs.close();
            stmt.close();
            return tableRows;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
