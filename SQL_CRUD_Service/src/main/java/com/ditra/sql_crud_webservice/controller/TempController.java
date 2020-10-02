package com.ditra.sql_crud_webservice.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
public class TempController {
    @GetMapping(path="/students")
    public String getStudents()  {
        String students = "[{\"id\": 1, \"name\": \"Marko Markovic\", \"smer\": \"RN\",  \"godina_upisa\": 2018}, {\"id\": 2, \"name\": \"Jovan Jovanovic\", \"smer\": \"RN\",  \"godina_upisa\": 2017}, {\"id\": 3, \"name\": \"Marko Markovic\", \"smer\": \"RN\",  \"godina_upisa\": 2019}]";

        JSONArray studentJSON = new JSONArray(students);

        return studentJSON.toString();
    }

    @PostMapping(path="/student-grade-avg")
    public String getStudentGradeAvg(@RequestBody String payload) {
        if(payload.length() == 0) {
            return "No students sent.";
            //OH LORD GET ME OUT!!!!!!!!!!!!
        }

        JSONObject gradeJSON = new JSONObject("{\"average\": 8.3}");

        return gradeJSON.toString();
    }

    @GetMapping(path="/student-grade-sum")
    public String getStudentGradeSum(@RequestBody String payload) {
        if(payload.length() == 0) {
            return "No students sent.";
        }

        JSONObject gradeJSON = new JSONObject("{\"sum\": 24.8}");

        return gradeJSON.toString();
    }
}
