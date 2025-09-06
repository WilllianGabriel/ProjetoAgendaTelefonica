package agendatelefonica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDAO {

	private DatabaseConnection db = new DatabaseConnection();

	// Cadastrar novo usuário
	public boolean register(String name, String email, String password) {
		String sql = "INSERT INTO users (name, email, password) VALUES (?,?,?)";

		try (Connection conn = db.connect();
				PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, name);
			stmt.setString(2, email);
			stmt.setString(3, password);
			stmt.executeUpdate();
			return true;
		} catch (SQLException e) {
			System.out.println("Erro ao tentar registrar usuário no banco de dados: " + e.getMessage());
			return false;
		}
	}

	// Fazer login
	public User login(String email, String password) {
		String sql = "SELECT * FROM users WHERE email = ?  AND password = ?";

		try (Connection conn = db.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			stmt.setString(1, email);
			stmt.setString(2, password);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new User(rs.getInt("id"),
						rs.getString("name"),
						rs.getString("email"),
						rs.getString("password"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao tentar logar no banco de dados: " + e.getMessage());
		}
		return null;
	}

}
