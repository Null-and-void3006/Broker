package com.ditra.sql_crud_webservice.controller;

import com.ditra.sql_crud_webservice.model.Database;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class DatabaseMetadataController {
    @GetMapping(path="/database-metadata")
    public String getDatabaseMetadata() throws IOException {
        final ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(Database.getEntities());
    }
}
