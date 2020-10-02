package actions.crud;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import actions.dataStorage.FetchNextBlockAction;
import enums.AtributeType;
import gui.MainFrame;
import gui.crud.AddRowDialog;
import gui.dataStorage.DataStorageView;
import gui.dataStorage.DataTable;
import gui.dataStorage.EntityTab;
import model.Attribut;
import model.Entity;
import model.database.Database;
import org.json.simple.JSONArray;
import org.json.simple.parser.ContainerFactory;
import org.json.simple.parser.JSONParser;
import state.AddState;

public class SaveAddRowAction implements ActionListener{


	@Override
	public void actionPerformed(ActionEvent e) {
		System.out.println("Akcija adovanja");

		AddRowDialog dialog = AddRowDialog.getCurrentInstance();
		EntityTab selectedEntityTab = dialog.getEntityTab();
		Entity selectedEntity = dialog.getEntity();

		JTextField[] textFields = dialog.getTextFields();
		JLabel[] labels = dialog.getLabels();


		boolean inserted = false;

		boolean insertIntoDatabase = true;

		try {

			LinkedHashMap table = new LinkedHashMap();
			String tableName = '"' + selectedEntity.getName() + '"';
			table.put('"' + "tableName" + '"', tableName);

			JSONArray jsonArray = new JSONArray();

			for(int i = 0 ; i < selectedEntity.getAttributes().size() ; i++) {
				Attribut atribut = selectedEntity.getAttributes().get(i);

				if(inserted) {
				//	sql = sql + ',';
				}

				if(atribut.isMandatory() && textFields[i].getText().length() == 0) {
					JOptionPane.showMessageDialog(MainFrame.getInstance(), "Obavezna polja moraju biti popunjena");
					insertIntoDatabase = false;
				}else if(textFields[i].getText().length() == 0){
					continue;
				}

				Attribut attr = selectedEntity.getAttributes().get(i);
				LinkedHashMap attributeMap = new LinkedHashMap<>();
				String attributeName = attr.getName();
				attributeMap.put("name", attributeName);
				String attributeValue = textFields[i].getText();
				attributeMap.put("value", attributeValue);
				String attributeType = attr.getType().toString();
				attributeMap.put("type" , attributeType);

				jsonArray.add(attributeMap);

				if(atribut.getType() == AtributeType.INT) {
					try {
						Integer.parseInt(textFields[i].getText());
					} catch(NumberFormatException ee) {
						insertIntoDatabase = false;
						JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "mora biti ceo broj");
					} catch(NullPointerException ee) {
					}
				}else if(atribut.getType() == AtributeType.NUMERIC) {
					try {
						Float.parseFloat(textFields[i].getText());
					} catch(NumberFormatException ee) {
						insertIntoDatabase = false;
						JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "mora biti broj");
					} catch(NullPointerException ee) {
					}
				}else if(atribut.getType() == AtributeType.CHAR) {if(textFields[i].getText().length() != 1) {
					insertIntoDatabase = false;
					JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "mora biti karakter");}
				}else if(atribut.getType() == AtributeType.DATE) {
					System.out.println("DATE JE");
					boolean baciGresku = false;
					String[] date = textFields[i].getText().split("-");
					System.out.println(date.length);
					if(date.length<3) {
						baciGresku = true;
					}else if(date[0].length() != 4 || date[1].length() != 2 || date[2].length() != 2) {
						baciGresku = true;
					}else {
						try {
							int year = Integer.parseInt(date[0]);
							int month = Integer.parseInt(date[1]);
							int day = Integer.parseInt(date[2]);
							if(month < 1 || month > 12) {
								baciGresku = true;
							}else {
								switch(month){
									case 1:
									case 3:
									case 5:
									case 7:
									case 8:
									case 10:
									case 12:{
										if(day < 1 || day > 31) {
											baciGresku = true;
										}
										break;
									}
									case 2:{
										if(year%400 == 0 || ((year%100 !=0) &&(year%4 == 0))) {
											if(day < 1 || day > 29) {
												baciGresku = true;
											}
										}else{
											if(day < 1 || day > 28) {
												baciGresku = true;
											}
										}
										break;
									}
									case 4:
									case 6:
									case 9:
									case 11:{
										if(day < 1 || day > 30) {
											baciGresku = true;
										}
									}
								}
							}
						} catch (Exception e2) {
							baciGresku = true;
						}
					}
					if(baciGresku) {
						insertIntoDatabase = false;
						JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "Datum mora biti u formatu YYYY-MM-DD");
					}

				}else if(atribut.getType() == AtributeType.DATETIME) {
					System.out.println("DATETIME JE");
					boolean baciGresku = false;
					String[] dt = textFields[i].getText().split(" ");
					if(dt.length < 2) {
						baciGresku = true;
					}else{
						String[] date = dt[0].split("-");
						String[] time = dt[1].split(":");
						if(date.length<3 || time.length<3) {
							baciGresku = true;
						}else if(date[0].length() != 4 || date[1].length() != 2 || date[2].length() != 2 || time[0].length() < 2 || time[1].length() < 2 || time[2].length() < 2) {
							baciGresku = true;
						}else {
							try {
								int year = Integer.parseInt(date[0]);
								int month = Integer.parseInt(date[1]);
								int day = Integer.parseInt(date[2]);
								int hours = Integer.parseInt(time[0]);
								int minutes = Integer.parseInt(time[1]);
								int seconds = Integer.parseInt(time[2]);
								if(hours < 0 || hours > 23 || minutes < 0 || minutes > 60 || seconds < 0 || seconds > 60) {
									baciGresku = false;
								}
								else if(month < 1 || month > 12) {
									baciGresku = true;
								}else {
									switch(month){
										case 1:
										case 3:
										case 5:
										case 7:
										case 8:
										case 10:
										case 12:{
											if(day < 1 || day > 31) {
												baciGresku = true;
											}
											break;
										}
										case 2:{
											if(year%400 == 0 || ((year%100 !=0) &&(year%4 == 0))) {
												if(day < 1 || day > 29) {
													baciGresku = true;
												}
											}else{
												if(day < 1 || day > 28) {
													baciGresku = true;
												}
											}
											break;
										}
										case 4:
										case 6:
										case 9:
										case 11:{
											if(day < 1 || day > 30) {
												baciGresku = true;
											}
										}
									}
								}
							} catch (Exception e2) {
								baciGresku = true;
							}
						}
						if(baciGresku) {
							insertIntoDatabase = false;
							JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "mora biti u formatu YYYY-MM-DD HH:MM:SS");
						}
					}
				}else if(atribut.getType() == AtributeType.SMALLINT) {
					try {
						Integer.parseInt(textFields[i].getText());
					} catch(NumberFormatException ee) {
						insertIntoDatabase = false;
						JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "mora biti ceo mali ceo broj");
					} catch(NullPointerException ee) {
					}
				}else if(atribut.getType() == AtributeType.DECIMAL) {
					try {
						Integer.parseInt(textFields[i].getText());
					} catch(NumberFormatException ee) {
						insertIntoDatabase = false;
						JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "mora biti realan broj");
					} catch(NullPointerException ee) {
					}
				}else if(atribut.getType() == AtributeType.BIGINT) {
					try {
						Integer.parseInt(textFields[i].getText());
					} catch(NumberFormatException ee) {
						insertIntoDatabase = false;
						JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "mora biti ceo broj");
					} catch(NullPointerException ee) {
					}
				}else if(atribut.getType() == AtributeType.BIT) {
					if(!textFields[i].getText().contentEquals("0") && !textFields[i].getText().contentEquals("1")) {
						insertIntoDatabase = false;
						JOptionPane.showMessageDialog(MainFrame.getInstance(), labels[i].getText() + "mora biti bit");
					}
				}
				inserted = true;
			}

			inserted = false;

			for(int i = 0 ; i < selectedEntity.getAttributes().size() ; i++) {
				Attribut atribut = selectedEntity.getAttributes().get(i);


				if(textFields[i].getText().length() == 0){
					continue;
				}

				inserted = true;
			}


			table.put('"' + "columns" + '"', jsonArray);

			if(insertIntoDatabase) {
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

				//Dodavanje reda
				URL url = new URL ("http://localhost:8080/sql_crud/add-row");
				HttpURLConnection con = (HttpURLConnection)url.openConnection();
				con.setRequestMethod("PUT");
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

				selectedEntityTab = DataStorageView.getInstance().getEntityTab(selectedEntity);
				DataTable dataTable = FetchNextBlockAction.getTableWithData(selectedEntity);
				DataStorageView.getInstance().refreshTab(selectedEntityTab, dataTable);
				DataStorageView.getInstance().updateParamsLabels();
				dialog.dispose();
			}
		}catch(Exception ex) {
			ex.printStackTrace();
		}



	}
}
