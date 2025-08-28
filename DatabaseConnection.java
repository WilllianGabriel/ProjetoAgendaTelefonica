package agendatelefonica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {
	
	private static final String URL = "jdbc:mysql://localhost:3306/contacts_db";
	private static final String USER = "root";
	private static final String PASSWORD = "";
	
	// Método que conecta esse projeto java no banco de dados
	public Connection connect() {
		try {
			return DriverManager.getConnection(URL, USER, PASSWORD);
		} catch (SQLException e) {
			System.out.println("Erro ao conectar: "+e.getMessage());
			return null;
		}
	}
}