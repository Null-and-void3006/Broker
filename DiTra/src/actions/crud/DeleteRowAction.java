package actions.crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.security.KeyStore.Entry.Attribute;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import actions.dataStorage.FetchNextBlockAction;
import enums.AtributeType;
import gui.dataStorage.DataStorageView;
import gui.dataStorage.DataTable;
import gui.dataStorage.EntityTab;
import model.Attribut;
import model.Entity;
import model.database.Database;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class DeleteRowAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent arg0) {
		System.out.println("Obrisi akcija");
		
		EntityTab entityTab = (EntityTab) DataStorageView.getInstance().getTabPane().getSelectedComponent();
		Entity entity = entityTab.getEntity();
		
		DataTable dataTable = entityTab.getTable();
		int row = dataTable.getSelectedRow();

		System.out.println(entity.getName());

		LinkedHashMap table = new LinkedHashMap();
		String tableName = '"' + entity.getName() + '"';
		table.put('"' + "tableName" + '"', tableName);

		JSONArray jsonArray = new JSONArray();
		/*

		String sql = "DELETE FROM " + entity.getName() + " WHERE ";
		
		boolean prvi = true;
		*/


		for(int i = 0 ; i < entity.getAttributes().size() ; i++) {



			Attribut attr = entity.getAttributes().get(i);
			LinkedHashMap attributeMap = new LinkedHashMap<>();
			String attributeName = attr.getName();
			attributeMap.put("name", attributeName);
			String attributeValue = null;
			if(dataTable.getValueAt(row, i) != null){
				attributeValue = dataTable.getValueAt(row, i).toString();
			}
			attributeMap.put("value", attributeValue);
			String attributeType = attr.getType().toString();
			attributeMap.put("type" , attributeType);

			jsonArray.add(attributeMap);


		}
		table.put('"' + "primaryKeys" + '"', jsonArray);

		try {
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
			//Autorizacija
			URL url1 = new URL("http://localhost:8080/authenticate");
			HttpURLConnection conn = (HttpURLConnection) url1.openConnection();
			String userName = "{\"username\":\"ditra\",\"password\":\"ditra\"}";
			System.out.println(userName);
			conn.setRequestMethod("POST");
			conn.setRequestProperty("Content-Type", "application/json; utf-8");
			conn.setRequestProperty("Accept", "application/json");
			conn.setDoOutput(true);
			try (OutputStream os = conn.getOutputStream()) {
				System.out.println(userName);
				byte[] input = userName.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			System.out.println(conn.getResponseCode());

			String auth = "Bearer ";

			BufferedReader readerr;
			String linee;
			StringBuffer responseContett = new StringBuffer();
			int sta = conn.getResponseCode();
			if (sta > 299) {
				readerr = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();
			} else {
				readerr = new BufferedReader(new InputStreamReader(conn.getInputStream()));

				while ((linee = readerr.readLine()) != null) {
					responseContett.append(linee);
				}
				readerr.close();

				String s = responseContett.toString();

				JSONParser parser = new JSONParser();

				LinkedHashMap l = (LinkedHashMap) parser.parse(s, cf);

				String sp = l.entrySet().toArray()[0].toString();
				//System.out.println(sp);
				String[] yeeeySplittingAgain = sp.split("=");

				auth = auth + yeeeySplittingAgain[1];

				System.out.println(auth);

				l.forEach((k,v)->{
					System.out.println(v);
					//auth = auth + v.toString();
				});
			}
			//Brisanje reda
			URL url = new URL ("http://localhost:8080/sql_crud/delete-row");
			HttpURLConnection con = (HttpURLConnection)url.openConnection();
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setRequestProperty("Authorization", auth);
			con.setDoOutput(true);
			System.out.println(table.toString());
			try(OutputStream os = con.getOutputStream()) {
				String str = table.toString().replace("=", ":");
				System.out.println(str);
				byte[] input = str.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			int status = con.getResponseCode();
			System.out.println(status);

			entityTab = DataStorageView.getInstance().getEntityTab(entity);
			dataTable = FetchNextBlockAction.getTableWithData(entity);
			DataStorageView.getInstance().refreshTab(entityTab, dataTable);
			DataStorageView.getInstance().updateParamsLabels();

		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

	}

}
