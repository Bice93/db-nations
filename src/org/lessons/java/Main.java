package org.lessons.java;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws SQLException {
		//Creare la connessione al db
		String url = "jdbc:mysql://localhost:3306/nations";
		String user = "root";
		String password = "root";
		
		Scanner s = new Scanner(System.in);
		System.out.println("Inserisci una stringa di ricerca");
		String inputUser = s.nextLine();
		s.close();
		
		try (Connection con = DriverManager.getConnection(url, user, password)) {
			System.out.println("Connessione al database effettuata");

			//Scrivere la query sql
			String sql = "SELECT c.name AS name_country, c.country_id AS id_country, r.name AS name_region, c2.name AS name_continent\r\n"
					+ "FROM countries c \r\n"
					+ "INNER JOIN regions r \r\n"
					+ "	ON r.region_id = c.region_id \r\n"
					+ "INNER JOIN continents c2 \r\n"
					+ "	ON c2.continent_id = r.continent_id\r\n"
					+ "WHERE c.name LIKE ?\r\n"
					+ "ORDER BY c.name";
			
			try (PreparedStatement ps=con.prepareStatement(sql)){
				//posso utilizzare il preparestatement (istruzione che devo eseguire)
				
				ps.setString(1, "%"+inputUser+"%");

					try(ResultSet rs = ps.executeQuery()) {
						//posso utilizzare resultSet (risultato dell'istruzione)
						
						while (rs.next()) { //fintanto c'è qualcosa da leggere
							
							String nameCountry = rs.getString("name_country");
							int idCountry = rs.getInt("id_country");
							String nameRegion = rs.getString("name_region");
							String nameContinent = rs.getString("name_continent");
							System.out.println("Id_Country: " + idCountry + "\t Country: " + nameCountry + "\t Region: " + nameRegion + "\t Continet: " + nameContinent + "\n");
						
						}
					}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		}
		
		
		
	/*
		 * 	BONUS
	*/
		
		// tutte le lingue parlate in quella country
		
	/*	try (Connection con = DriverManager.getConnection(url, user, password)) {
			System.out.println("Connessione al database effettuata");
			
			Scanner s = new Scanner(System.in);
			System.out.println("Inserisci l'id di un paese: ");
			int inputUser = Integer.parseInt(s.nextLine());
			s.close();
			
			//Scrivere la query sql
			String sql = "SELECT l.`language` \r\n"
					+ "FROM countries c \r\n"
					+ "INNER JOIN country_languages cl \r\n"
					+ "	ON cl.country_id = c.country_id \r\n"
					+ "INNER JOIN languages l \r\n"
					+ "	ON l.language_id = cl.language_id\r\n"
					+ "WHERE c.country_id = ?";
			
			try (PreparedStatement ps=con.prepareStatement(sql)){
				//posso utilizzare il preparestatement (istruzione che devo eseguire)
				
				ps.setInt(1, inputUser);

					try(ResultSet rs = ps.executeQuery()) {
						//posso utilizzare resultSet (risultato dell'istruzione)

						while (rs.next()) { //fintanto c'è qualcosa da leggere
							String lenguage = rs.getString(1);
							System.out.println("Lenguage: " + lenguage + "\t");
						
						}
					}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} */ 
		
		
		// le statistiche più recenti per quella country
		
	/*	try (Connection con = DriverManager.getConnection(url, user, password)) {
			System.out.println("Connessione al database effettuata");
			
			Scanner s = new Scanner(System.in);
			System.out.println("Inserisci l'id di un paese: ");
			int inputUser = Integer.parseInt(s.nextLine());
			s.close();
			
			//Scrivere la query sql
			String sql = "SELECT c.country_id, c.name, cs.population, cs.gdp, cs.`year` AS anno\r\n"
					+ "FROM countries c \r\n"
					+ "INNER JOIN country_stats cs \r\n"
					+ "ON cs.country_id = c.country_id \r\n"
					+ "WHERE c.country_id = ? \r\n"
					+ "ORDER BY anno DESC\r\n"
					+ "LIMIT 2;";
			
			try (PreparedStatement ps=con.prepareStatement(sql)){
				//posso utilizzare il preparestatement (istruzione che devo eseguire)
				
				ps.setInt(1, inputUser);

					try(ResultSet rs = ps.executeQuery()) {
						//posso utilizzare resultSet (risultato dell'istruzione)
						
						while (rs.next()) { //fintanto c'è qualcosa da leggere
							
							int idCountry = rs.getInt(1);
							String nameCountry = rs.getString(2);
							int population = rs.getInt(3);
							BigDecimal gdp = rs.getBigDecimal(4);
							int year = rs.getInt(5);
							System.out.println("Statistiche: " + nameCountry);
							System.out.println("Id_Country: " + idCountry + "\t Country: " + nameCountry + "\t Population: " + population + "\t Gdp: " + gdp + "\t year: " + year + "\n");
						
						}
					}
			}
			
		} catch (SQLException ex) {
			ex.printStackTrace();
		} */
	}
	
}
