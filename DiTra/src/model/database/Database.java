package model.database;

import java.awt.event.ActionEvent;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.*;

import enums.AtributeType;
import gui.MainFrame;
import model.Entity;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

public class Database implements IDatabase {
	private static Connection conn = null;

	private static HttpURLConnection httpConnection;

	public static Object[][] getData(Entity entity) {

		HttpURLConnection httpConnection;

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

		try {
			System.out.println("Trazim");
			URL url1 = new URL("http://localhost:8080/authenticate");
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			String userName = "{\"username\":\"ditra\",\"password\":\"ditra\"}";
			System.out.println(userName);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			try (OutputStream os = con.getOutputStream()) {
				System.out.println(userName);
				byte[] input = userName.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			System.out.println(con.getResponseCode());

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

			//Uzimanje podataka iz tabele
			String ss = "http://localhost:8080/sql_crud/table-content?tableName=" + entity.getName();
			//	System.out.println(ss);
			URL url = new URL(ss);
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setConnectTimeout(50000);
			httpConnection.setReadTimeout(50000);
			httpConnection.setRequestProperty("Authorization", auth);

			int status = httpConnection.getResponseCode();
			System.out.println(status);

			BufferedReader reader;
			String line;
			StringBuffer responseContet = new StringBuffer();

			if (status > 299) {
				reader = new BufferedReader(new InputStreamReader(httpConnection.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContet.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(httpConnection.getInputStream()));

				while ((line = reader.readLine()) != null) {
					responseContet.append(line);
				}
				reader.close();

				String s = responseContet.toString();

				JSONParser parser = new JSONParser();
				LinkedList l = (LinkedList) parser.parse(s, cf);

				int col = entity.getAttributes().size();
				System.out.println(col);
				if (l.size() != 0) {
					String ubicuSe = l.get(0).toString();
					String ubicuSeZivMiSve = ubicuSe.substring(1, ubicuSe.length() - 1);
					col = ubicuSeZivMiSve.length();
				}
				Object[][] data = new String[20][col];


				for (int i = 0; i < l.size(); i++) {
					String atributi = l.get(i).toString();
					atributi = atributi.substring(1, atributi.length() - 1);
					String[] nizAtributa = atributi.split(", ");
					System.out.println(atributi);
					for (int j = 0; j < nizAtributa.length; j++) {
						System.out.println(i + " " + j);
						data[i][j] = nizAtributa[j];
					}
				}
				return data;

			}


		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Neje good url");
			return null;
		} catch (IOException | ParseException e) {
			System.out.println("Ipak odje");
			e.printStackTrace();
			return null;
		}
		return null;
	}

	public static Object[][] getSortedData(Entity entity, String sql) {
		try {
			conn = getConnection();
			Statement stmt = conn.createStatement();
			ResultSet rs = stmt.executeQuery(sql);

			System.out.println(entity.getName());

			int col = entity.getAttributes().size();
			Object[][] data = new String[20][col];
			while (rs.next()) {
				for (int i = 0; i < col; i++) {
					data[rs.getRow() - 1][i] = rs.getString(i + 1);
				}
			}

			rs.close();
			stmt.close();
			return data;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}

	public static Connection getConnection() {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:jtds:sqlserver://147.91.175.155/ui-2017-tim201.2",
						"ui-2017-tim201.2", "tim201.2hjuh5");
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	//Method returns the established connection, wheather it be the defult one, or 
	//via credentials
	public static Connection getEstablishedConnection() {
		return conn;
	}

	public static Connection establishParametrizedConnection(String ip, String db, String user, String pass) {
		if (conn == null) {
			try {
				conn = DriverManager.getConnection("jdbc:jtds:sqlserver://" + ip + "/" + db,
						user, pass);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return conn;
	}

	public static HttpURLConnection establishDefaultConnection() {

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

		try {
			//Autorizacija
			URL url1 = new URL("http://localhost:8080/authenticate");
			HttpURLConnection con = (HttpURLConnection) url1.openConnection();
			String userName = "{\"username\":\"ditra\",\"password\":\"ditra\"}";
			System.out.println(userName);
			con.setRequestMethod("POST");
			con.setRequestProperty("Content-Type", "application/json; utf-8");
			con.setRequestProperty("Accept", "application/json");
			con.setDoOutput(true);
			try (OutputStream os = con.getOutputStream()) {
				System.out.println(userName);
				byte[] input = userName.getBytes("utf-8");
				os.write(input, 0, input.length);
			}

			System.out.println(con.getResponseCode());

			String auth = "Bearer ";

			BufferedReader reader;
			String line;
			StringBuffer responseContet = new StringBuffer();
			int sta = con.getResponseCode();
			if (sta > 299) {
				reader = new BufferedReader(new InputStreamReader(con.getErrorStream()));
				while ((line = reader.readLine()) != null) {
					responseContet.append(line);
				}
				reader.close();
			} else {
				reader = new BufferedReader(new InputStreamReader(con.getInputStream()));

				while ((line = reader.readLine()) != null) {
					responseContet.append(line);
				}
				reader.close();

				String s = responseContet.toString();

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


			//KonektovanjeNaBazu
			URL url = new URL("http://localhost:8080/sql_crud/database-metadata");
			httpConnection = (HttpURLConnection) url.openConnection();
			httpConnection.setRequestMethod("GET");
			httpConnection.setConnectTimeout(50000);
			httpConnection.setReadTimeout(50000);
			httpConnection.setRequestProperty ("Authorization", auth);

			int status = httpConnection.getResponseCode();
			System.out.println(status);
		} catch (MalformedURLException e) {
			e.printStackTrace();
			System.out.println("Neje good url");
		} catch (IOException e) {
			System.out.println("IOex");
			e.printStackTrace();
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return httpConnection;

	}
}
