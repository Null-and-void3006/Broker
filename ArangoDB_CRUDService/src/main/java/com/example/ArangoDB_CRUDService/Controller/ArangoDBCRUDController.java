package com.example.ArangoDB_CRUDService.Controller;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import org.bson.Document;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import com.arangodb.ArangoCollection;
import com.arangodb.ArangoCursor;
import com.arangodb.ArangoDB;
import com.arangodb.ArangoDBException;
import com.arangodb.ArangoDatabase;
import com.arangodb.ArangoEdgeCollection;
import com.arangodb.ArangoGraph;
import com.arangodb.entity.BaseDocument;
import com.arangodb.entity.BaseEdgeDocument;
import com.arangodb.entity.CollectionEntity;
import com.arangodb.entity.CollectionType;
import com.arangodb.entity.EdgeDefinition;
import com.arangodb.entity.GraphEntity;
import com.arangodb.model.AqlQueryOptions;
import com.arangodb.model.CollectionCreateOptions;
import com.arangodb.model.EdgeReplaceOptions;
import com.arangodb.model.GraphCreateOptions;
import com.arangodb.util.MapBuilder;
import com.arangodb.velocypack.VPackSlice;
import com.arangodb.velocypack.exception.VPackException;
import com.example.ArangoDB_CRUDService.model.ArangoDBAddVertex;
import com.example.ArangoDB_CRUDService.model.ArangoDBCompareSubjectsData;
import com.example.ArangoDB_CRUDService.model.ArangoDBField;
import com.example.ArangoDB_CRUDService.model.Database;
import com.example.ArangoDB_CRUDService.utils.ArangoComplexDataSet;
import com.example.ArangoDB_CRUDService.utils.DataSet;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

@RestController
public class ArangoDBCRUDController {
	
	
	HashMap<String, ArrayList<HashMap<String, String>>> kljucevi = new HashMap<>();
	
	
	
	@PutMapping(path = "test-complex-dataset")
	public String testComplexDataset(@RequestBody String s) throws JsonProcessingException
	{
		ArangoComplexDataSet cds = new ArangoComplexDataSet();
		ArrayList<String> generalCompetencies = new ArrayList<>();
		generalCompetencies.add("Genrealna1");
		generalCompetencies.add("Generalna2");
		
		ArrayList<String> specificCompetencies = new ArrayList<>();
		specificCompetencies.add("Specificna1");
		specificCompetencies.add("Specificna2");
		
		cds.getSubjectGeneralCompetency().put("Matematika", generalCompetencies);
		cds.getSubjectSpecificCompetency().put("Matematika", specificCompetencies);
		
		generalCompetencies = new ArrayList<>();
		generalCompetencies.add("Genrealna3");
		generalCompetencies.add("Generalna4");
		
		
		specificCompetencies = new ArrayList<>();
		specificCompetencies.add("Specificna3");
		specificCompetencies.add("Specificna4");
		
		
		cds.getSubjectGeneralCompetency().put("Fizika", generalCompetencies);
		cds.getSubjectSpecificCompetency().put("Fizika", specificCompetencies);
		
		final ObjectMapper mapper = new ObjectMapper();
		String izlaz = mapper.writeValueAsString(cds);
		
		return izlaz;
		
	}
	
	@PutMapping(path = "/tpe")
	public void tpe(@RequestBody String s) throws SQLException, JsonProcessingException
	{
		//System.out.println("THIS WORKS");
		//System.out.println(s);
		populateEdges(s);
	}
	
	
	public void populateEdges(String vertex1) throws SQLException, JsonProcessingException
	{	
		
		 ArangoDB arangodb = new ArangoDB.Builder().host("64.225.110.65",8529).user("tim_401_6_arango_si2019").password("sdnteMxe").build(); 
	     //stem.out.println("Test Start");
	     ArangoDatabase db = arangodb.db("tim_401_6_arango_si2019");
	     
	     
	     //za vertex1, koji je entitet unasoj sql bazi podataka
	     //moramo naci sa kojim je sve drugim entitetima u vezi, i onda izvuci podatke iz
	     //naseg entieta i entieta sa kojim je u vezi
	     
	     //kada se to uradi, napravimo data set koji se sastoji od _from atributa koji je ID naseg 
	     //vertexa, i _to atributa, koji ce biti id nase mete
	     int i = 0;
	     DatabaseMetaData dm = Database.getEstablishedConnection().getMetaData();
	     ResultSet rs = dm.getImportedKeys(null, "tim_401_6_si2019", vertex1);
	     
    	 ObjectMapper mapper = new ObjectMapper();
    	 
    	 while(rs.next())
    	 {
    		 System.out.println("WRITING FOR A ROW");
    		 System.out.println("***************************8");
    		 System.out.println("PKTABLE_CAT " + rs.getString("PKTABLE_CAT"));
    		 System.out.println("PKTABLE_SCHEM  " + rs.getString("PKTABLE_SCHEM"));
    		 System.out.println("PKTABLE_NAME " + rs.getString("PKTABLE_NAME"));
    		 System.out.println("PKCOLUMN_NAME " + rs.getString("PKCOLUMN_NAME"));
    		 System.out.println("FKTABLE_CAT " + rs.getString("FKTABLE_CAT"));
    		 System.out.println("FKTABLE_SCHEM  " + rs.getString("FKTABLE_SCHEM"));
    		 System.out.println("FKTABLE_NAME " + rs.getString("FKTABLE_NAME"));
    		 System.out.println("FKCOLUMN_NAME  " + rs.getString("FKCOLUMN_NAME"));
    		 
    		 System.out.println("FK_NAME   " + rs.getString("FK_NAME"));
    		 System.out.println("PK_NAME   " + rs.getString("PK_NAME"));
    		 System.out.println("***************************8");
    		 
    		 
    		 //u parametru PKTABLE_NAME se nalazi ime entiteta koje 
    		 
    	 }
	}
	
	
	
	public void populateVertex(String collectionName)  throws SQLException, IOException
	{
		//Should work now es
		 ArangoDB arangodb = new ArangoDB.Builder().host("64.225.110.65",8529).user("tim_401_6_arango_si2019").password("sdnteMxe").build(); 
	     System.out.println("Test Start");
	     ArangoDatabase db = arangodb.db("tim_401_6_arango_si2019");
	     
	     
	     String currentName = collectionName;
	     
	     boolean found = false;
	     
	     Collection<CollectionEntity> c = db.getCollections();
	     for (CollectionEntity elem : c)
	    	 if (elem.getName().compareTo(collectionName) == 0)
	    		 found = true;
	     
	    System.out.println(found);
	    ResultSet columns = null;
	    
	    //There was no collection with that name, so we will create it
	    if(!found)
	    {
	    	Database.establishDefaultConnection();
			ArrayList<ArrayList<String>> data = Database.getData(collectionName);
	        final ObjectMapper mapper = new ObjectMapper();
	        
	        //we need to change data into the appropriate format
	        DatabaseMetaData md;
	       
	        
	        //if data size is zero, that means our database SQL database is empty, and there is no need to 
	        //contact the transformer, we just create a new collection normally and add a document to i
	        if (data.size() == 0)
	        {
	        	//CREATE NEW COLLECTION HERE, ADD DOCUMENT AND FINISH
	        	db.createCollection(collectionName);
	        	return;
	        }
	        
	        
	        
	        //The rest of the code is the same as in mongodb
	        try
	        {
	        	md = Database.getEstablishedConnection().getMetaData();
	        	columns = md.getColumns("tim_401_6_si2019", null, currentName, null);
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        }
	        
	        String[][] dataMatrix = new String[data.size()][data.get(0).size()];
	        
	        int colnum = 0, rownum = 0;
	        //We create our data matrix
	        for (ArrayList<String> list:data)
	        {
	        	colnum = 0;
	        	for (String string : list)
	        	{
	        		dataMatrix[rownum][colnum] = string;
	        		colnum++;
	        	}
	        	rownum++;
	        }
	        
	   
	        HashMap<String, ArrayList<String>> dataMap = new HashMap<>();
	        int cntr = 0;
	        while(columns.next())
	        {
	        	ArrayList<String> help = new ArrayList<>();
	        	for (int i = 0; i < rownum; i++)
	        		help.add(dataMatrix[i][cntr]);
	        	
	        	cntr++;
	        	dataMap.put(columns.getString("COLUMN_NAME"), help);
	        }
	        
	        DataSet ds = new DataSet();
	        ds.setData(dataMap);
	        ds.setVertex(true);
	        
	        String HTTPmessage = mapper.writeValueAsString(ds); //we send this to localhost
	        
	        
	        System.out.println(HTTPmessage);
	        
	        
	       //Asking for JWT
			URL url1 = new URL("http://localhost:8080/authenticate");
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			String userName = "{\"username\":\"ditra\",\"password\":\"ditra\"}";
			System.out.println(userName);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = userName.getBytes("utf-8");
				os.write(input, 0, input.length);
			}


			String auth = "Bearer ";

			BufferedReader readerr;
			String linee;
			StringBuffer responseContett = new StringBuffer();
			int sta = con.getResponseCode();
			if (sta > 299) {
				readerr = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
			} else {
				readerr = new BufferedReader(new InputStreamReader(con.getInputStream()));

				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
				String s = responseContett.toString();
				String[] array = s.split("\"");
				auth = auth + array[3];
			}
	        
	        
			System.out.println("THIS IS THE AUTHENTICATION WE ARE USING");
			System.out.println(auth);
	        
	     
	        
	        
		    RestTemplateBuilder rtb = new RestTemplateBuilder();
			final RestTemplate restTemplate = rtb.build();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", auth); //adding jwt to header
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(HTTPmessage, headers);
			
			String url = "http://localhost:8080/transform/arangodb"; //the url i am targeting
		
			//Sending the message and getting a response
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);

			
			
			//First we create a collection with that name
			db.createCollection(collectionName);
			//Now we parse response and fill our database, which should be simple
			ArrayList<Document> docList = mapper.readValue(response.getBody(), new TypeReference<ArrayList<Document>>() { });
			
			
			ArrayList<HashMap<String, String>> list1  = new ArrayList<>();
			ArrayList<HashMap<String, String>> list2 = new ArrayList<>();
			int brojac = 0; 
			
			
			for (Document d : docList)
			{
				System.out.println(d.toJson());
				BaseDocument doc = new BaseDocument();
				
				//We add all the fields
				for (String key : d.keySet())
				{
					doc.addAttribute(key, d.get(key));
				}
				
				db.collection(collectionName).insertDocument(doc);;
				
				HashMap<String, String> curr_map = new HashMap<>(); //mapiram 
				
				HashMap<String, String> curr_map2 = new HashMap<>();
				
				String trenutni_kljuc = "";
				if(collectionName.compareTo("COURSE") == 0)
				{
					trenutni_kljuc =  dataMatrix[brojac][2];
					curr_map.put(trenutni_kljuc, doc.getKey());
					list1.add(curr_map);
				}
				else if (collectionName.compareTo("COMPETENCE") == 0)
				{
					trenutni_kljuc =  dataMatrix[brojac][4];
					curr_map.put(trenutni_kljuc, doc.getKey());
					list1.add(curr_map);
					
					trenutni_kljuc = dataMatrix[brojac][3];
					curr_map2.put(trenutni_kljuc, doc.getKey());
					list2.add(curr_map2);
					
				}
				else if (collectionName.compareTo("COMPETENCE_CATEGORY") == 0)
				{
					trenutni_kljuc = dataMatrix[brojac][3];
					curr_map.put(trenutni_kljuc, doc.getKey());
					list1.add(curr_map);
				}
				
				brojac++;
			}
			
			
			
			if(collectionName.compareTo("COURSE") == 0)
			{
				kljucevi.put("COURSE", list1);
			}
			else if (collectionName.compareTo("COMPETENCE") == 0)
			{
				kljucevi.put("COMPETENCE_TO_COURSE", list1);
				
				kljucevi.put("COMPETENCE_TO_COMPETENCE_CATEGORY", list2);
				
			}
			else if (collectionName.compareTo("COMPETENCE_CATEGORY") == 0)
			{
				kljucevi.put("COMPETENCE_CATEGORY", list1);
			}
			
			
			
			
			//ovde ce mi sada redom biti zapisani kljucevi za svaku od tabela
			//posle toga, dovoljno je samo da proiteriram kroz njih kako
			// bih nasao odgovarajuce dokumente
			
			//sada treba da popunim

	    }
	        
	}
	
	
	
	
	@PutMapping(path = "/add-document")
	public String addDocument(@RequestBody ArangoDBAddVertex addData) throws SQLException, IOException
	{
		//Should work now es
		 ArangoDB arangodb = new ArangoDB.Builder().host("64.225.110.65",8529).user("tim_401_6_arango_si2019").password("sdnteMxe").build(); 
	     System.out.println("Test Start");
	     ArangoDatabase db = arangodb.db("tim_401_6_arango_si2019");
	     
	     
	     String currentName = addData.getCollectionName();
	     
	     boolean found = false;
	     
	     Collection<CollectionEntity> c = db.getCollections();
	     for (CollectionEntity elem : c)
	    	 if (elem.getName().compareTo(addData.getCollectionName()) == 0)
	    		 found = true;
	     
	    System.out.println(found);
	    
	    
	    //There was no collection with that name, so we will create it
	    if(!found)
	    {
	    	Database.establishDefaultConnection();
			ArrayList<ArrayList<String>> data = Database.getData(addData.getCollectionName());
	        final ObjectMapper mapper = new ObjectMapper();
	        
	        //we need to change data into the appropriate format
	        DatabaseMetaData md;
	        ResultSet columns;
	        
	        //if data size is zero, that means our database SQL database is empty, and there is no need to 
	        //contact the transformer, we just create a new collection normally and add a document to i
	        if (data.size() == 0)
	        {
	        	//CREATE NEW COLLECTION HERE, ADD DOCUMENT AND FINISH
	        	db.createCollection(addData.getCollectionName());
	        	Document document = new Document();
	    		//we go through our fields and put data
	    		for (ArangoDBField field : addData.getFields())
	    		{
	    			document.append(field.getName(), field.getValue());
	    		}
	    		db.collection(addData.getCollectionName()).insertDocument(document);
	    		return "SQL DATABASE WAS EMPTY. CREATED NEW MONGODB COLLECTION "+ addData.getCollectionName() + "AND ADDED DATA TO IT.";
	        }
	        
	        
	        
	        //The rest of the code is the same as in mongodb
	        
	        
	        
	        try
	        {
	        	md = Database.getEstablishedConnection().getMetaData();
	        	columns = md.getColumns("tim_401_6_si2019", null, currentName, null);
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        	return "Error connecting to SQL database!";
	        }
	        
	        String[][] dataMatrix = new String[data.size()][data.get(0).size()];
	        
	        int colnum = 0, rownum = 0;
	        //We create our data matrix
	        for (ArrayList<String> list:data)
	        {
	        	colnum = 0;
	        	for (String string : list)
	        	{
	        		dataMatrix[rownum][colnum] = string;
	        		colnum++;
	        	}
	        	rownum++;
	        }
	        
	   
	        HashMap<String, ArrayList<String>> dataMap = new HashMap<>();
	        int cntr = 0;
	        while(columns.next())
	        {
	        	ArrayList<String> help = new ArrayList<>();
	        	for (int i = 0; i < rownum; i++)
	        		help.add(dataMatrix[i][cntr]);
	        	
	        	cntr++;
	        	dataMap.put(columns.getString("COLUMN_NAME"), help);
	        }
	        
	        DataSet ds = new DataSet();
	        ds.setData(dataMap);
	        ds.setVertex(true);//ovo je cvor a ne ivica
	        
	        String HTTPmessage = mapper.writeValueAsString(ds); //we send this to localhost
	        
	        
	        System.out.println(HTTPmessage);
	        
	        
	       //Asking for JWT
			URL url1 = new URL("http://localhost:8080/authenticate");
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			String userName = "{\"username\":\"ditra\",\"password\":\"ditra\"}";
			System.out.println(userName);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = userName.getBytes("utf-8");
				os.write(input, 0, input.length);
			}


			String auth = "Bearer ";

			BufferedReader readerr;
			String linee;
			StringBuffer responseContett = new StringBuffer();
			int sta = con.getResponseCode();
			if (sta > 299) {
				readerr = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
			} else {
				readerr = new BufferedReader(new InputStreamReader(con.getInputStream()));

				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
				String s = responseContett.toString();
				String[] array = s.split("\"");
				auth = auth + array[3];
			}
	        
			System.out.println("THIS IS THE AUTHENTICATION WE ARE USING");
			System.out.println(auth);
	               
       
		    RestTemplateBuilder rtb = new RestTemplateBuilder();
			final RestTemplate restTemplate = rtb.build();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", auth); //adding jwt to header
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(HTTPmessage, headers);
			
			String url = "http://localhost:8080/transform/arangodb"; //the url i am targeting
		
			//Sending the message and getting a response
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
			
			//First we create a collection with that name
			db.createCollection(addData.getCollectionName());
			//Now we parse response and fill our database, which should be simple
			ArrayList<Document> docList = mapper.readValue(response.getBody(), new TypeReference<ArrayList<Document>>() { });
			for (Document d : docList)
			{
				System.out.println(d.toJson());
				BaseDocument doc = new BaseDocument();
				
				//We add all the fields
				for (String key : d.keySet())
				{
					doc.addAttribute(key, d.get(key));
				}
				
				db.collection(addData.getCollectionName()).insertDocument(doc);
			}   
	    }
	    
	    
	  //All the necessary tables and collections had been created, we only need to insert a new collection to our database and 
	  		//that is it
	  		BaseDocument document = new BaseDocument();
	  		//db.createCollection(addData.getCollectionName());
	  		
	  		
	  		//we go through our fields and put data
	  		for (ArangoDBField field : addData.getFields())
	  		{
	  			document.addAttribute(field.getName(), field.getValue());
	  		}
	  		db.collection(addData.getCollectionName()).insertDocument(document);

	  		
	  		return "ADDED DATA TO " + addData.getCollectionName();
	}
	
	@PutMapping(path = "/update-document")
	public String updateDocument(@RequestBody String addData)
	{
		return "";
	}
	
	@PutMapping(path = "/remove-document")
	public String removeDocument(@RequestBody String addData)
	{
		return "";
	}
	
	@PutMapping(path = "/testing")
	public String test(@RequestBody String name)
	{
	
		 ArangoDB arangodb = new ArangoDB.Builder().host("64.225.110.65",8529).user("tim_401_6_arango_si2019").password("sdnteMxe").build(); 
	     System.out.println("Test Start");
	     ArangoDatabase db = arangodb.db("tim_401_6_arango_si2019");
	     
	     db.createCollection(name);
	     
	     //Turns out we need to use this BaseDocument class
	     BaseDocument d = new BaseDocument();
	     d.addAttribute("some_key", 123);
	     db.collection(name).insertDocument(d);
	    
		System.out.println("We've arrived here!");
		
		return "SUCCESSFUL!";
	}
	
	@PutMapping(path = "/compare-subjects")
	public String compareSubjects(@RequestBody ArangoDBCompareSubjectsData subjectsData) throws SQLException, IOException
	{
			
		//COURSE je nas predmet
		//COMPETENCE je nasa kompetencija
		
		String[] vertexNames = {"COMPETENCE", "COURSE", "COMPETENCE_CATEGORY"}; // za svaki od ovih predmeta proveravamo da li postoji u nasoj arango bazi
		
		
		System.out.println("DOSAO SAM DOVDEE");
		//Invarijanta je seldece. Ako neki entitet ima entitete sa kojima je u relaciji, onda ce da se i ti entiteti zapisati 
		// u edge
		for (int i = 0; i <  vertexNames.length; i++)
		{
			populateVertex(vertexNames[i]);//ukoliko nisu postojali podaci za neku imena, napraviti ih
		}
		
		
		//ASd
		//sada cemo da kreiramo ivice
		//Should work now es
		 ArangoDB arangodb = new ArangoDB.Builder().host("64.225.110.65",8529).user("tim_401_6_arango_si2019").password("sdnteMxe").build(); 
	     System.out.println("Test Start");
	     ArangoDatabase db = arangodb.db("tim_401_6_arango_si2019");
	     
	     

	     
	     String currentName = "COURSE_COMPETENCE";

	     boolean found = false;
	     
	     Collection<CollectionEntity> c = db.getCollections();
	     for (CollectionEntity elem : c)
	    	 if (elem.getName().compareTo(currentName) == 0)
	    		 found = true;
	     
	    System.out.println(found);
	    
	    
	  
		
	   // Collection<GraphEntity> col = db.getGraphs();
	   // if (col.size() == 0) graph = db.graph("edge-graph");

		
		
	    
	    
	    //There was no collection with that name, so we will create it
	    if(!found)
	    {
	    	Database.establishDefaultConnection();
			ArrayList<ArrayList<String>> data = Database.getData(currentName);
	        final ObjectMapper mapper = new ObjectMapper();
	        
	        //we need to change data into the appropriate format
	        DatabaseMetaData md;
	        ResultSet columns;
	        
	        //if data size is zero, that means our database SQL database is empty, and there is no need to 
	        //contact the transformer, we just create a new collection normally and add a document to i
	        if (data.size() == 0)
	        {
	        	//CREATE NEW COLLECTION HERE, ADD DOCUMENT AND FINISH
	        	db.createCollection(currentName);
	        	Document document = new Document();
	    		return "SQL DATABASE WAS EMPTY. CREATED NEW MONGODB COLLECTION "+ currentName + "AND ADDED DATA TO IT.";
	        }
	        
	        
	        
	        //The rest of the code is the same as in mongodb
	        
	        
	        
	        try
	        {
	        	md = Database.getEstablishedConnection().getMetaData();
	        	columns = md.getColumns("tim_401_6_si2019", null, currentName, null);
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        	return "Error connecting to SQL database!";
	        }
	        
	        String[][] dataMatrix = new String[data.size()][data.get(0).size()];
	        
	        //poenta je sledeca, mi ne mozemo da tek tako ubacimo ono sto nam se nalazi u data
	        //moramo da odemo u nasu odgovarajucu kolekciju i da uporedimo 
	        
	        /** Ovo su podaci oblika
	         *    NP_PREDMET      KO_KOMPETENCIJA
	         *    predmet1       kompetencija1
	         *    predmet2       kompetencija2
	         */    
	        
	        

	        
	        data =  Database.getData("COURSE_COMPETENCE"); //pravim tabelu course competence!!!!!!!
	        
	        //sada iz kursa treba da izvucem 2 stvari, to ce mi biti NP_PREDMET na poziciji 2
	        //iz kompetence treba da izvucem KO_KOMPETENCIJA, na poziciji 4
	        
	        int colnum = 0, rownum = 0;
	        //We create our data matrix
	        
	        
	        
	        
	        
	        System.out.println("Sada cemo da ispisemo sve sto mozemo za kurseve");
	        ArrayList<HashMap<String, String>> cl = kljucevi.get("COURSE");
	        for (int i = 0; i < cl.size(); i++)
	        {
	        	Set<String> k = cl.get(i).keySet();
	        	for (String s: k)
	        	{
	        		System.out.println("ZA kljuc " + k  + " imamo vrednost " + cl.get(i).get(s));
	        	}
	        }
	        
	        
	        System.out.println("Sada cemo da ispisemo sve sto mozemo za kompetencije");
	        ArrayList<HashMap<String, String>> kl = kljucevi.get("COMPETENCE_TO_COURSE");
	        for (int i = 0; i < kl.size(); i++)
	        {
	        	Set<String> k = kl.get(i).keySet();
	        	for (String s: k)
	        	{
	        		System.out.println("ZA kljuc " + k  + " imamo vrednost " + kl.get(i).get(s));
	        	}
	        }
	        
	        
	        
	        
	        for (ArrayList<String> list:data)
	        {
	        	
	        	String NP_PREDMET = list.get(0);
	        	String KO_KOMPETENCIJA = list.get(1);
	        	
	        	//sada ove predmete treba da uporedimsa svim nasim 
	        	
	        	//za to cu koristit hesmapu kljuceva
	        	
	        	ArrayList<HashMap<String, String>> course_list = kljucevi.get("COURSE");
	        	ArrayList<HashMap<String, String>> competence_list = kljucevi.get("COMPETENCE_TO_COURSE");
	        	
	        	String _from = "", _to = "";
	        	
	        	for (int i = 0; i < course_list.size(); i++)
	        		if (course_list.get(i).containsKey(NP_PREDMET))
	        			_from = course_list.get(i).get(NP_PREDMET);
	        	
	        	
	        	for (int i = 0; i < competence_list.size(); i++)
	        		if (competence_list.get(i).containsKey(KO_KOMPETENCIJA))
	        			_to = competence_list.get(i).get(KO_KOMPETENCIJA);
	        	
	        	
	        	
	        	
	        	
	        	//nas data matrix ce sluziti da se napravi lista ivica u arango bazi
	        	//u prvoj kolini nalazice se _from atributi, a u drugoj koloni nalazice se _to atributi
	        	
	        	//mi sada moramo da uporedimo NP_PREDMET sa odgovarajucim
	        	dataMatrix[rownum][0] = "COURSE/"+_from;
	        	dataMatrix[rownum][1] = "COMPETENCE/" + _to;
	        		
	        		
	        	rownum++;
	        }
	        
	   
	        HashMap<String, ArrayList<String>> dataMap = new HashMap<>();
	        int cntr = 0;
	        while(columns.next())
	        {
	        	ArrayList<String> help = new ArrayList<>();
	        	for (int i = 0; i < rownum; i++)
	        		help.add(dataMatrix[i][cntr]);
	        	
	        	cntr++;
	        	dataMap.put(columns.getString("COLUMN_NAME"), help);
	        }
	        
	        DataSet ds = new DataSet();
	        ds.setData(dataMap);
	        ds.setVertex(false); //ovo je ivica, a ne cvor
	        
	        String HTTPmessage = mapper.writeValueAsString(ds); //we send this to localhost
	        
	        
	        System.out.println(HTTPmessage);
	        
	        
	       //Asking for JWT
			URL url1 = new URL("http://localhost:8080/authenticate");
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			String userName = "{\"username\":\"ditra\",\"password\":\"ditra\"}";
			System.out.println(userName);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = userName.getBytes("utf-8");
				os.write(input, 0, input.length);
			}


			String auth = "Bearer ";

			BufferedReader readerr;
			String linee;
			StringBuffer responseContett = new StringBuffer();
			int sta = con.getResponseCode();
			if (sta > 299) {
				readerr = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
			} else {
				readerr = new BufferedReader(new InputStreamReader(con.getInputStream()));

				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
				String s = responseContett.toString();
				String[] array = s.split("\"");
				auth = auth + array[3];
			}
	        
			System.out.println("THIS IS THE AUTHENTICATION WE ARE USING");
			System.out.println(auth);
	               
      
		    RestTemplateBuilder rtb = new RestTemplateBuilder();
			final RestTemplate restTemplate = rtb.build();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", auth); //adding jwt to header
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(HTTPmessage, headers);
			
			String url = "http://localhost:8080/transform/arangodb"; //the url i am targeting
		
			//Sending the message and getting a response
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
			
			//First we create a collection with that name
			//db.createCollection(currentName);

			//ovo je dobro
			db.createCollection(currentName, new CollectionCreateOptions().type(CollectionType.EDGES));
			//Now we parse response and fill our database, which should be simple
			ArrayList<Document> docList = mapper.readValue(response.getBody(), new TypeReference<ArrayList<Document>>() { });
			for (Document d : docList)
			{
				System.out.println(d.toJson());
				BaseEdgeDocument document = new BaseEdgeDocument(d.getString("_from"), d.getString("_to"));
				//We add all the fields
				System.out.println("FROM " + document.getFrom() + " TO " + document.getTo());
			
				
				
				//db.graph("EDGES").edgeCollection(currentName).insertEdge(document);
				
				db.collection(currentName).insertDocument(document);

			}   
	    }

	    //sada, ukoliko edge kolekcija COURSE_COMPETENCE nije postojala, kreirana je
	    //onda, proveriti da li postoji edge kolekcija COMPETENCE_COMPETENCE_CATEGORY
	    
	    
	    
	    
	    
	    
	    
	    currentName = "COMPETENCE_COMPETENCE_CATEGORY";
	     
	     found = false;
	     
	     //Collection<CollectionEntity> c = db.getCollections();
	     for (CollectionEntity elem : c)
	    	 if (elem.getName().compareTo(currentName) == 0)
	    		 found = true;
	     
	    System.out.println(found);
	    
	    
	    //There was no collection with that name, so we will create it
	    if(!found)
	    {
	    	//mi sada moramo izvuci podatke iz 2 tabele
	    	Database.establishDefaultConnection();
	    	ArrayList<ArrayList<String>> data_competence = Database.getData("COMPETENCE"); 
	    	ArrayList<ArrayList<String>> data_competence_category = Database.getData("COMPETENCE_CATEGORY"); 
	        final ObjectMapper mapper = new ObjectMapper();
	        
	        //we need to change data into the appropriate format
	        DatabaseMetaData md;

	        ResultSet columns_competence;
	        ResultSet columns_competence_category;
	        
	        //if data size is zero, that means our database SQL database is empty, and there is no need to 
	        //contact the transformer, we just create a new collection normally and add a document to i
	        if (data_competence.size() == 0 || data_competence_category.size() == 0)
	        {
	        	//CREATE NEW COLLECTION HERE, ADD DOCUMENT AND FINISH
	        	db.createCollection(currentName);
	        	Document document = new Document();
	    		return "SQL DATABASE WAS EMPTY. CREATED NEW ARANGODB COLLECTION "+ currentName + "AND ADDED DATA TO IT.";
	        }
	        
	        
	        
	        //The rest of the code is the same as in mongodb
	        
	        
	        
	        try
	        {
	        	md = Database.getEstablishedConnection().getMetaData();
	        	columns_competence = md.getColumns("tim_401_6_si2019", null, "COMPETENCE", null);
	        	columns_competence_category = md.getColumns("tim_401_6_si2019", null, "COMPETENCE_CATEGORY", null);
	        }
	        catch (Exception e)
	        {
	        	e.printStackTrace();
	        	return "Error connecting to SQL database!";
	        }
	        
	        String[][] dataMatrix = new String[data_competence.size()][2];
	        
	        
	        
	        
	        //crit        
            //KRITICNA ZONA, STAY AWAY
	        //trebaju mi podaci iz competence
	         data_competence = Database.getData("COMPETENCE"); 
	         
		        System.out.println("Sada cemo da ispisemo sve sto mozemo za kompetencije");
		        ArrayList<HashMap<String, String>> kkl = kljucevi.get("COMPETENCE_CATEGORY");
		        for (int i = 0; i < kkl.size(); i++)
		        {
		        	Set<String> k = kkl.get(i).keySet();
		        	for (String s: k)
		        	{
		        		System.out.println("ZA kljuc " + k  + " imamo vrednost " + kkl.get(i).get(s));
		        	}
		        }
		          	     
	        int colnum = 0, rownum = 0;
	        //We create our data matrix
	        for (ArrayList<String> list:data_competence)
	        {
	        	String KOMP_KATEGORIJA = list.get(3);
	        	
	        	//sada ove predmete treba da uporedimsa svim nasim 
	        	
	        	//za to cu koristit hesmapu kljuceva
	        	
	        	ArrayList<HashMap<String, String>> course_list = kljucevi.get("COMPETENCE_TO_COMPETENCE_CATEGORY");
	        	ArrayList<HashMap<String, String>> competence_to_competence_category_list = kljucevi.get("COMPETENCE_CATEGORY");
	        	
	        	String _from = "", _to = "";
	        	
	        	for (int i = 0; i < course_list.size(); i++)
	        		if (course_list.get(i).containsKey(KOMP_KATEGORIJA))
	        			_from = course_list.get(i).get(KOMP_KATEGORIJA);
	        	
	        	
	        	for (int i = 0; i < competence_to_competence_category_list.size(); i++)
	        		if (competence_to_competence_category_list.get(i).containsKey(KOMP_KATEGORIJA))
	        			_to = competence_to_competence_category_list.get(i).get(KOMP_KATEGORIJA);
	        	
	        	
	        	
	        	
	        	//nas data matrix ce sluziti da se napravi lista ivica u arango bazi
	        	//u prvoj kolini nalazice se _from atributi, a u drugoj koloni nalazice se _to atributi
	        	
	        	//mi sada moramo da uporedimo NP_PREDMET sa odgovarajucim
	        	dataMatrix[rownum][0] = "COMPETENCE/"+_from;
	        	dataMatrix[rownum][1] = "COMPETENCE_CATEGORY/" + _to;
	        		
	        		
	        	rownum++;
	        }
	        
	        
	     
	        
	        
	        //RADOVI U TOKU
	        
	        
	        
	        
	        
	   
	        HashMap<String, ArrayList<String>> dataMap = new HashMap<>();
	        int cntr = 0;
	        
	        //pravimo hashmapu koju transformer moze da parsira
	        ArrayList<String> help = new ArrayList<>();
        	for (int i = 0; i < rownum; i++)
        		help.add(dataMatrix[i][cntr]);
        	
        	cntr++;
        	dataMap.put("_from", help);
        	
        	
        	 help = new ArrayList<>();
         	for (int i = 0; i < rownum; i++)
         		help.add(dataMatrix[i][cntr]);
         	
         	cntr++;
         	dataMap.put("_to", help);
	        
	        
	        
	        
         	
         	//ovo dalje ostaje u principu isto
	        DataSet ds = new DataSet();
	        ds.setData(dataMap);
	        
	        ds.setVertex(false);//ovo je ivica, a ne cvor
	        
	        String HTTPmessage = mapper.writeValueAsString(ds); //we send this to localhost
	        
	        
	        System.out.println(HTTPmessage);
	        
	        
	       //Asking for JWT
			URL url1 = new URL("http://localhost:8080/authenticate");
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			String userName = "{\"username\":\"ditra\",\"password\":\"ditra\"}";
			System.out.println(userName);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			try (OutputStream os = con.getOutputStream()) {
				byte[] input = userName.getBytes("utf-8");
				os.write(input, 0, input.length);
			}


			String auth = "Bearer ";

			BufferedReader readerr;
			String linee;
			StringBuffer responseContett = new StringBuffer();
			int sta = con.getResponseCode();
			if (sta > 299) {
				readerr = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
			} else {
				readerr = new BufferedReader(new InputStreamReader(con.getInputStream()));

				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
				String s = responseContett.toString();
				String[] array = s.split("\"");
				auth = auth + array[3];
			}
	        
			System.out.println("THIS IS THE AUTHENTICATION WE ARE USING");
			System.out.println(auth);
	               
     
		    RestTemplateBuilder rtb = new RestTemplateBuilder();
			final RestTemplate restTemplate = rtb.build();
	        
	        HttpHeaders headers = new HttpHeaders();
	        headers.add("Authorization", auth); //adding jwt to header
			headers.setContentType(MediaType.APPLICATION_JSON);
			HttpEntity<String> entity = new HttpEntity<String>(HTTPmessage, headers);
			
			String url = "http://localhost:8080/transform/arangodb"; //the url i am targeting
		
			//Sending the message and getting a response
			ResponseEntity<String> response = restTemplate.exchange(url, HttpMethod.PUT, entity, String.class);
			
			//First we create a collection with that name
			db.createCollection(currentName, new CollectionCreateOptions().type(CollectionType.EDGES));

			//Now we parse response and fill our database, which should be simple
			ArrayList<Document> docList = mapper.readValue(response.getBody(), new TypeReference<ArrayList<Document>>() { });
			for (Document d : docList)
			{
				System.out.println(d.toJson());
				BaseDocument  doc = new BaseDocument();
				
				//We add all the fields
				
				doc.addAttribute("_from", d.get("_from"));
				doc.addAttribute("_to", d.get("_to"));
				
				//db.graph("EDGES").edgeCollection(currentName).insertEdge(doc);
				
				db.collection(currentName).insertDocument(doc);
			}   
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //ovde sada mozemo da kucamo aranogo kod <3

	    
	    
	    //OVAJ UPIT PRAVILNO PARSIRA VRACANJE 
	    String query = "FOR cour IN COURSE FILTER cour.NP_NAZIV_PREDMETA == ";
	    for (int i = 0; i < subjectsData.getNumSubjects(); i++)
	    {
	    	query += "\""+subjectsData.getSubjects().get(i).toString()+"\"";
	    	if (subjectsData.getNumSubjects() > 1 && i != subjectsData.getNumSubjects()-1)
	    		query += " OR cour.NP_NAZIV_PREDMETA == ";
	    }
	    
	    query += "         FOR link IN COURSE_COMPETENCE\r\n" + 
	    		"            FILTER cour._id == link._from\r\n" + 
	    		"            FOR comp IN COMPETENCE\r\n" + 
	    		"                FILTER comp._id == link._to\r\n" + 
	    		"                    RETURN {COMPETENCE: comp.KO_NAZIV, COURSE: cour.NP_NAZIV_PREDMETA}";
	    
	    System.out.println(query);
	    
	    
	    ArangoCursor<BaseDocument> cursor = db.query(
	    		  query,
	    		  null, null,
	    		  BaseDocument.class
	    		);

	    
	    
	    //U Kurosru se sada cuvaju predmeti i specificne kompetencije
	    HashMap<String, Set<String>> mapa = new HashMap<>();
	    while(cursor.hasNext())
	    {
	    	BaseDocument b = cursor.next();
	    	System.out.println("KLJUC JE " + b.getKey());
	    	Map<String, Object> m = b.getProperties();
	    	
	    	String COURSE = (String) m.get("COURSE");
	    	String COMPETENCE = (String) m.get("COMPETENCE");
		    
	    	
	    	System.out.println("COURSE  " + COURSE + " COMPETENCE " + COMPETENCE);
	    	
	    	if (mapa.containsKey(COURSE))
	    		mapa.get(COURSE).add(COMPETENCE);
	    	else
	    	{
	    		Set<String> skup= new HashSet<>();
	    		skup.add(COMPETENCE);
	    		mapa.put(COURSE, skup);
	    		
	    		
	    	}
	    }
	    
	    //nakon ovoga mozemo da pretvorimo set u array listu
	    HashMap<String, ArrayList<String>> subjectSpecificCompetency = new HashMap<>();

	    
	    for (String s:mapa.keySet())
	    {
	    	ArrayList<String> lst = new ArrayList<String> (mapa.get(s));
	    	subjectSpecificCompetency.put(s, lst);
	    }
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    
	    //OVAJ UPIT PRAVILNO PARSIRA VRACANJE 
	    query = "FOR cour IN COURSE FILTER cour.NP_NAZIV_PREDMETA == ";
	    for (int i = 0; i < subjectsData.getNumSubjects(); i++)
	    {
	    	query += "\""+subjectsData.getSubjects().get(i).toString()+"\"";
	    	if (subjectsData.getNumSubjects() > 1 && i != subjectsData.getNumSubjects()-1)
	    		query += " OR cour.NP_NAZIV_PREDMETA == ";
	    }
	    
	    query += "  FOR comp in COMPETENCE\r\n" + 
	    		"	            FOR link IN COURSE_COMPETENCE\r\n" + 
	    		"	                FILTER cour._id == link._from\r\n" + 
	    		"	                FILTER comp._id == link._to\r\n" + 
	    		"	                \r\n" + 
	    		"	                    FOR comp_cat IN COMPETENCE_CATEGORY\r\n" + 
	    		"	                        FOR ccc IN COMPETENCE_COMPETENCE_CATEGORY\r\n" + 
	    		"	                            FILTER comp._id == ccc._from\r\n" + 
	    		"	                            FILTER comp_cat._id == ccc._to\r\n" + 
	    		"	 \r\n" + 
	    		"	                                RETURN {COURSE: cour.NP_NAZIV_PREDMETA, COMPETENCE_CATEGORY: comp_cat.KOMP_NAZIV }";
	    
	    

	    
	    
	    
	    
	    
	    System.out.println(query);
	    
	    
	    cursor = db.query(
	    		  query,
	    		  null, null,
	    		  BaseDocument.class
	    		);
	    
	    
	    
	  //U Kurosru se sada cuvaju predmeti i generalne kompetencije
	    mapa = new HashMap<>();
	    while(cursor.hasNext())
	    {
	    	BaseDocument b = cursor.next();
	    	System.out.println("KLJUC JE " + b.getKey());
	    	Map<String, Object> m = b.getProperties();
	    	
	    	String COURSE = (String) m.get("COURSE");
	    	String COMPETENCE_CATEGORY = (String) m.get("COMPETENCE_CATEGORY");
		    
	    	
	    	System.out.println("COURSE  " + COURSE + " COMPETENCE_CATEGORY " + COMPETENCE_CATEGORY);
	    	
	    	if (mapa.containsKey(COURSE))
	    		mapa.get(COURSE).add(COMPETENCE_CATEGORY);
	    	else
	    	{
	    		Set<String> skup= new HashSet<>();
	    		skup.add(COMPETENCE_CATEGORY);
	    		mapa.put(COURSE, skup);	
	    	}
	    }
	    
	    
	    //nakon ovoga mozemo da pretvorimo set u array listu
	    HashMap<String, ArrayList<String>> subjectGeneralCompetency = new HashMap<>();

	    
	    for (String s:mapa.keySet())
	    {
	    	ArrayList<String> lst = new ArrayList<String> (mapa.get(s));
	    	subjectGeneralCompetency.put(s, lst);
	    }
	    
	    
	    //asdf
	    
	    
	    
	    
	    
	    
	    ArangoComplexDataSet acds = new ArangoComplexDataSet(subjectGeneralCompetency, subjectSpecificCompetency);
	    ObjectMapper mapper = new ObjectMapper();
		return mapper.writeValueAsString(acds);
	}

}
