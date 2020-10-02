package model;
 
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.security.KeyStore.Entry.Attribute;
import java.util.ArrayList;
 
import controller.MainController;
import enums.AtributeType;
import model.dataStorage.EntityObject;
 
public class Entity extends InfNode {  
    protected ArrayList<Attribut> attributes;
    protected CountAttribute countAttribute;
    protected ArrayList<Relation> relations;
   
    private ArrayList<EntityObject> objects;
    private String url;
   
    
    public Entity(String name){
   
        super(name);
 
        setAttributes(new ArrayList<>());
        setRelations(new ArrayList<>());  
       
        countAttribute = new CountAttribute();
    }
   
   
    public Entity(String name, String url) throws IOException {
        super(name);
        this.url = url;
       
        setAttributes(new ArrayList<>());
        setRelations(new ArrayList<>());
       
       
        BufferedReader reader = new BufferedReader(new FileReader(url));
       
        String line="";
        if (url.substring(url.length()-3).equals("ind"))
        {  
            reader.readLine();
            reader.readLine();
        }
        reader.readLine();
        int byteLine=0;
        while((line=reader.readLine())!=null)
        {    
             String split[] = line.split("/");
             String attributName = split[1];
             int length = Integer.parseInt(split[3]);
             byteLine+=length;
             String typeString = split[2].substring(5, 6)+ split[2].substring(6).toLowerCase();
             
             AtributeType attributeType = AtributeType.valueOf(typeString);
             Boolean primaryKey = Boolean.parseBoolean(split[4]);
             Boolean mandatory = true;
             int group =1;
             Attribut attribut = new Attribut(attributName, length, attributeType , primaryKey, mandatory, group);
             this.addAttribute(attribut);
        }

       
        // Atribut koji vodi racuna koliko je bilo pristupa
        countAttribute = new CountAttribute();
    }
 
    public void addAttribute(Attribut atr){
        getAttributes().add(atr);
        add(atr);
    }
   
    public void removeAttribute(Attribut atr){
        getAttributes().remove(atr);
        remove(atr);
    }
   
    public void addRelation(Relation rel){
        getRelations().add(rel);
    }
   
    public void removeRelation(Relation rel){
        getRelations().remove(rel);
    }
 
    public ArrayList<Relation> getRelations() {
        return relations;
    }
 
    public void setRelations(ArrayList<Relation> relations) {
        this.relations = relations;
    }
 
    public ArrayList<Attribut> getAttributes() {
        return attributes;
    }
 
    public void setAttributes(ArrayList<Attribut> atributes) {
        this.attributes = atributes;
    }
   
    public String getUrl() {
        return url;
    }
 
    public ArrayList<EntityObject> getObjects() {
        return objects;
    }
 
   
    public CountAttribute getCountAttribute() {
        return countAttribute;
    }
 
    public void incCountAttribute() {
        countAttribute.incConut();
    }
 
    @Override
    public String toString(){
        return name;
    }
   
 
    public void testPrint() {
        System.out.println("    /----" + this.name + "----\\");
        System.out.println("    *attributes*");
        for (Attribut a: attributes) {
            a.testPrint();
        }
        System.out.println("    *relations*");
        for (Relation r: relations) {
            r.testPrint();
        }
        System.out.println("    \\----" + this.name + "----/");
    }
}