package com.ditra.Transformer.Controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.ditra.Transformer.Services.TransformService;

@RestController
public class TransformController {
	
	@Autowired
	private TransformService transformService;
	
	@PutMapping(path = "/transform/mongodb")
	public String acceptMongoDataSet(@RequestBody String body) {
		
		System.out.println("hey it works!");
		return transformService.toMongo(body);
	}
	
	@PutMapping(path = "/transform/arangodb")
	public String acceptArangoDataSet(@RequestBody String body) {
		return transformService.toArango(body);
	}
	
	@PutMapping(path = "/transfrom/arangodbedge")
	public String acceptArangoComplexDataSet(@RequestBody String body) {
		return transformService.toArangoComplex(body);
	}

}
