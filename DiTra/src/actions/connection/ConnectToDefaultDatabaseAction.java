package actions.connection;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.*;

import controller.MainController;
import controller.TreeController;
import enums.AtributeType;
import enums.InfResourceType;
import gui.MainFrame;
import gui.SelectDatabaseDialog;
import model.Attribut;
import model.Entity;
import model.InfResource;
import model.Relation;
import model.database.Database;
import model.factory.AttributeFactory;
import model.factory.EntityFactory;
import model.factory.InfResourceFactory;
import model.factory.RelationFactory;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.swing.*;

public class ConnectToDefaultDatabaseAction implements ActionListener{
	private SelectDatabaseDialog dialog;
	
	public ConnectToDefaultDatabaseAction(SelectDatabaseDialog dialog)
	{
		this.dialog = dialog;
	}
	
	
	private EntityFactory ef = new EntityFactory();
	private AttributeFactory af = new AttributeFactory();
	private InfResourceFactory irf = new InfResourceFactory();
	private RelationFactory rf = new RelationFactory();
	
	
	
	private static Connection conn = null;
	private static HttpURLConnection httpConnection;

	@Override
	public void actionPerformed (ActionEvent arg0) {
		// TODO Auto-generated method stub

		ContainerFactory cf = new ContainerFactory() {
			@Override
			public Map createObjectContainer() {
				return new LinkedHashMap<>();
			}

			@Override
			public List creatArrayContainer() {
				return new LinkedList<>();
			}
		};



		httpConnection = Database.establishDefaultConnection();
		try{
			BufferedReader reader;
			String line;
			StringBuffer responseContet = new StringBuffer();
			int status = httpConnection.getResponseCode();
			if(status > 299) {
				reader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContet.append(line);
				}
				reader.close();
			}else {
				reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

				while ((line = reader.readLine()) != null) {
					responseContet.append(line);
				}
				reader.close();

				String s = responseContet.toString();

				JSONParser parser = new JSONParser();

				if(s.length() == 0){
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Nema podataka, doslo je od greske");
					return;
				}

				LinkedList l = (LinkedList)parser.parse(s, cf);

				System.out.println(l.get(0));

				ArrayList<Entity> entities = new ArrayList<>();


				//Dodavanje entiteta

				for(int i = 0 ; i < l.size() ; i++){
					String tabela = l.get(i).toString();
					String[] bla = tabela.split("]");
					String[] poZarezu = bla[0].split("}, \\{");
					String[] firstPart = poZarezu[0].split(", ", 2);

					//Odvajanje imena tabele
					String[] tableName = firstPart[0].split("=");
					Entity curr = (Entity)ef.getNode(tableName[1]);
			//		System.out.println(firstPart[0]);
			//		System.out.println(firstPart[1]);

					//Odvajanje prvog atributa
					String[] prviAtribut = firstPart[1].split("=", 2);
					String[] parametriAtributa = prviAtribut[1].split(", ");
					String[] at = parametriAtributa[0].split("=");
					Attribut currAttr = (Attribut)af.getNode(at[1]);
					for(int j = 1 ; j < parametriAtributa.length ; j++){
						at = parametriAtributa[j].split("=");
						if(j == 4){
							//System.out.println(at[1]);
							currAttr.setType(AtributeType.valueOf(at[1]));
						}else if(j == 2 && at[1].compareTo("true") == 0){
							currAttr.setMandatory(true);
						}
					}
				//	System.out.println(currAttr);
					curr.addAttribute(currAttr);

					//Odvajanje ostalih atributa

				//	System.out.println(prviAtribut[1]);
					for(int j = 1 ; j < poZarezu.length ; j++){
				//		System.out.println(poZarezu[j]);
						parametriAtributa = poZarezu[j].split(", ");
						at = parametriAtributa[0].split("=");
						Attribut ca = (Attribut)af.getNode(at[1]);
					//	System.out.println(poZarezu[j]);
						for(int k = 1 ; k < parametriAtributa.length ; k++){
							at = parametriAtributa[k].split("=");
							if(k == 4){
								//System.out.println(at[1]);
								ca.setType(AtributeType.valueOf(at[1]));
							}else if(k == 2 && at[1].compareTo("true") == 0){
					//			System.out.println(at[1]);
							ca.setMandatory(true);
						}
						}
						curr.addAttribute(ca);
					}
					entities.add(curr);
				}


				//Dodavanje relacija

				for(int i = 0 ; i < l.size() ; i++){
					String tabela = l.get(i).toString();
					String[] bla = tabela.split("]");
		//			System.out.println("Sledeci");
					for(int b = 0 ; b < bla.length ; b++){
		//				System.out.println(bla[b]);
					}
					String[] onlyRelations = bla[1].split("\\[");
					if(onlyRelations.length < 2) continue;
					String[] seperateRelations = onlyRelations[1].split("}}, ");
					for(int j = 0 ; j < seperateRelations.length ; j++){
						String[] e = seperateRelations[j].split(", ", 2); //0 - imena entiteta, 1 - atributi
						String[] entiteti = e[0].split("="); // 1 - imena entiteta
						String[] entitetiPonaosob = entiteti[1].split(" -> ");
						Entity fromEntity = entities.get(i);
						int pos = 0;
						while(!entities.get(pos).getName().equals(entitetiPonaosob[1]))pos++;
						Entity toEntity = entities.get(pos);

						String[] atributi = e[1].split("=\\{"); //0 - djubre, 1 - fromAtribut, 2 - toAtribut

						//From atribut
						String[] fAtribut = atributi[1].split(", ", 2);
						String[] fromAtributName = fAtribut[0].split("="); // 0 - djubre, 1 - atribut
						pos = 0;
						while(!fromEntity.getAttributes().get(pos).getName().equals(fromAtributName[1]))pos++;
						Attribut fromAttribut = fromEntity.getAttributes().get(pos);

						//To atribut
						String[] tAtribut = atributi[2].split(", ", 2);
						String[] toAtributName = fAtribut[0].split("="); // 0 - djubre, 1 - atribut
						pos = 0;
						while(!fromEntity.getAttributes().get(pos).getName().equals(toAtributName[1]))pos++;
						Attribut toAttribut = fromEntity.getAttributes().get(pos);


						Relation r = rf.getNode(fromEntity.getName() + " -> " + toEntity.getName() );
						r = rf.setParams(r, r.getName(), fromEntity, toEntity, fromAttribut, toAttribut);


						fromEntity.addRelation(r);
						entities.set(i, fromEntity);

					}
				}


				InfResource database = (InfResource) irf.getNode("tim_401_6_si2019");
				database = irf.setParams(database, database.getName(), "Kratak opis", "Eksterna", null, InfResourceType.mssql);
				for (int i = 0; i < entities.size(); i++)
				{
					database.add(entities.get(i));
				}
				MainController.getInstance().addInfResource(database);


			}



		}catch(MalformedURLException e){
			e.printStackTrace();
		}catch (IOException e){
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		} finally {
			httpConnection.disconnect();
		}






		this.dialog.dispose();
	}

}
