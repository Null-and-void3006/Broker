package com.ditra.sql_crud_webservice.controller;

import com.ditra.sql_crud_webservice.model.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.ArrayList;

@RestController
public class TableContentController {
    @GetMapping(path="/table-content")
    public String getTableContent(@RequestParam String tableName) throws IOException {
        ArrayList<ArrayList<String>> data = Database.getData(tableName);

        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(data);
        
    }
}
