package json;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import enums.InfResourceType;
import model.Attribut;
import model.Entity;
import model.InfResource;
import model.Package;
import model.Relation;
import model.dataStorage.AttributeObject;

public class JsonExport {
     
	private InfResource infResource;
	private String path;
	
	public JsonExport(InfResource infResource) {
		super();
		this.infResource = infResource;
		path=infResource.getMetaSchemaLocation();
	}

	public void exportEverything() throws IOException
	{ 
        
		JSONObject JInfResource = new JSONObject();
		 
		JSONArray JPackages = new JSONArray();
		
		for (Package paket:infResource.getPackage())
		{
		     JSONObject JPackage =  exportPackage(paket);
		     JPackages.add(JPackage);
		}
		JInfResource.put("location", infResource.getLocation());
		JInfResource.put("name", infResource.getName());
		JInfResource.put("description", infResource.getDescription());
		JInfResource.put("type", infResource.getType().toString());
		
		JInfResource.put("packages", JPackages);
		
		String jsonString = JInfResource.toString();
		
		infResource.setJsonString(jsonString);
		
		try {
			
			PrintWriter pw = new PrintWriter(path);
			pw.close();
			
			BufferedWriter writer = new BufferedWriter(new FileWriter(path, true));
			writer.write(jsonString);
			
			writer.close();
		} catch (IOException e) {
			System.out.println("Doslo do greske pri upisu u JSON");
		}
		
		
		return;
	}
	
	public JSONObject exportPackage(Package paket)
	{
		JSONObject JPackage = new JSONObject();
		
		JSONArray JEntities = new JSONArray();
		
		for (Entity entity: paket.getEntities())
		{
			JSONObject JEntity = exportEntity(entity);
			JEntities.add(JEntity);
		}
		
		JPackage.put("name", paket.getName());
		JPackage.put("type",paket.getType().toString());
		JPackage.put("entities",JEntities);
		try {
			JPackage.put("relations", exportRelations(paket));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return JPackage;
	}
	
	public  JSONObject exportEntity(Entity entity)
	{  
//		JSONObject JEntity = new JSONObject();
//		
//		JSONArray JAttributes = new JSONArray();
//		
//		for (Attribut attribute : entity.getAttributes())
//		{
//			 JSONObject JAttribute = exportAttribute(attribute);
//			 JAttributes.add(JAttribute);
//		}
//		
//		JEntity.put("name", entity.getName());
//		if (entity.getType()==EntityType.serial){
//		
//			String jsonUrl=entity.getUrl().substring(20);
//			jsonUrl = jsonUrl.substring(0,jsonUrl.length()-3)+"ser";
//			JEntity.put("url", jsonUrl);
//
//		} else
//		if (entity.getType()==EntityType.sequential){
//			
//			String jsonUrl=entity.getUrl().substring(20);
//			jsonUrl = jsonUrl.substring(0,jsonUrl.length()-4)+".sek";
//			JEntity.put("url", jsonUrl);
//		
//		} else {
//			  
//			String jsonUrl=entity.getUrl().substring(20);
//			jsonUrl = jsonUrl.substring(0,jsonUrl.length()-4)+".ind";
//			JEntity.put("url", jsonUrl);
//		}
//		JEntity.put("type", entity.getType().toString());
//		JEntity.put("attributes",JAttributes);
//	
//		return JEntity;
		return null;
		
	}
	
	public JSONObject exportAttribute(Attribut attribute) 
	{
		 JSONObject JAttribute = new JSONObject();
		 
		 JAttribute.put("name", attribute.getName());
		 JAttribute.put("type", attribute.getType().toString());
		 JAttribute.put("length", Integer.toString(attribute.getLenght()));
		 JAttribute.put("primaryKey", Boolean.toString(attribute.isPrimaryKey()));
		 JAttribute.put("group", Integer.toString(attribute.getGroup()));
		 JAttribute.put("mandatory",Boolean.toString(attribute.isMandatory()));
		 
		return JAttribute;
	}
	
	public JSONArray exportRelations(Package paket) throws IOException
	{   
		JSONArray JRelations = new JSONArray();
		
		for (Entity entity:paket.getEntities())
		{
			Entity fromEntity = entity;
			
			for (Relation relation : entity.getRelations())
			{
				System.out.println(relation+" " +fromEntity);
				JSONObject JRelation = exportSingleRelation(relation,fromEntity);
				JRelations.add(JRelation);
			}
		}
		;
		 return JRelations;
	}
	
	public JSONObject exportSingleRelation(Relation relation, Entity fromEntity) 
	{   
		JSONObject JRelation = new JSONObject();
		
		JRelation.put("name", relation.getName());
		JRelation.put("fromEntity", fromEntity.getName());
		JRelation.put("toEntity", relation.getToEntity().getName());
		JRelation.put("fromAttribute", relation.getFromAttribute().getName());
		JRelation.put("toAttribute", relation.getToAttribute().getName());
		
		return JRelation;
	}
	

	public InfResource getInfResource() {
		return infResource;
	}

	public void setInfResource(InfResource infResource) {
		this.infResource = infResource;
		path=infResource.getMetaSchemaLocation();
	}
	
}
