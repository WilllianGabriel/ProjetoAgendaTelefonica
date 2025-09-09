package agendatelefonica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

	private DatabaseConnection db = new DatabaseConnection();
	
	// Método para adicionar um novo contato no banco de dados
	public boolean addContact(int user_id, Contacts contact) {
		String sql = "INSERT INTO contacts (user_id, name, phone) VALUES (?,?,?)";

		try (Connection conn = db.connect();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setInt(1, user_id);
			stmt.setString(2, contact.getName());
			stmt.setString(3, contact.getPhone());
			int rowsInserted = stmt.executeUpdate();
			// só retorna true se conseguir salvar E recuperar o id
			if (rowsInserted > 0) {
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int generatedId = generatedKeys.getInt(1);
						contact.setId(generatedId);
						return true;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro SQL ("+ sql +"): " + e.getMessage());
		}
		return false;
	}
	
	// Carrega os contatos salvos no banco de dados temporariamente
		public List<Contacts> getContacts(int user_id) {
			List<Contacts> list = new ArrayList<>();

			String sql = "SELECT * FROM contacts WHERE user_id = ?";
			try (Connection conn = db.connect();
					PreparedStatement stmt = conn.prepareStatement(sql);) {
				stmt.setInt(1, user_id);
				ResultSet rs = stmt.executeQuery();
				while (rs.next()) {
					Contacts contact = new Contacts(
							rs.getInt("id"), 
							rs.getString("name"), 
							rs.getString("phone"));
					list.add(contact);
				}
			} catch (SQLException e) {
				System.out.println("Erro SQL ("+ sql +"): " + e.getMessage());
			}
			return list;
		}

	// Método que remove um contato do banco de dados
	public boolean removeContact(int contactId, int user_id) {
		String sql = "DELETE FROM contacts WHERE id = ? AND user_id = ?";
		try (Connection conn = db.connect();) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, contactId);
			stmt.setInt(2, user_id);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro SQL ("+ sql +"): " + e.getMessage());
		}
		return false;
	}

	// Método que atualiza os dados de um contato do banco de dados
	public boolean updateContact(int contactId, int user_id, String newName, String newPhone) {
		String sql = "UPDATE contacts SET name = ?, phone = ? WHERE id = ? AND user_id = ?";
		try (Connection conn = db.connect()) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, newName);
			stmt.setString(2, newPhone);
			stmt.setInt(3, contactId);
			stmt.setInt(4, user_id);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				return true;
			}
		} catch (SQLException e) {
			System.out.println("Erro SQL ("+ sql +"): " + e.getMessage());
		}
		return false;
	}

	// Método que busca um contato pelo id ou nome especifico no banco de dados
	public Contacts findContact(String field, int user_id, String value) {
		if (!field.equalsIgnoreCase("id") && !field.equalsIgnoreCase("name")) {
			throw new IllegalArgumentException("Campo inválido: " + field);
		}
		String sql = "SELECT * FROM contacts WHERE " + field + " = ? AND user_id = ?";

		try (Connection conn = db.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			if (field.equalsIgnoreCase("id")) {
				stmt.setInt(1, Integer.parseInt(value));
			} else {
				stmt.setString(1, value);
			}
			stmt.setInt(2, user_id);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Contacts(rs.getInt("id"), rs.getString("name"), rs.getString("phone"));
			}
		} catch (SQLException e) {
			System.out.println("Erro SQL ("+ sql +"): " + e.getMessage());
		}
		return null; // se não encontrou
	}

	// Método que verifica se o contato existe no banco de dados
	public boolean contactExists(String field, int user_id, String value) {
		// Proteção contra SQL Injection: só permitimos "name", "phone" ou "id"
		if (!field.equalsIgnoreCase("name") && !field.equalsIgnoreCase("phone")
				&& !field.equalsIgnoreCase("id")) {
			throw new IllegalArgumentException("Campo inválido: " + field);
		}

		String sql = "SELECT COUNT(*) FROM contacts WHERE " + field + " = ? AND user_id = ?";

		try (Connection conn = db.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			
			// Substitui o "?" pelo valor (nome, telefone ou id)
			if (field.equalsIgnoreCase("id")) {
				stmt.setInt(1, Integer.parseInt(value));
			} else {
				stmt.setString(1, value);
			}
			stmt.setInt(2, user_id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // retorna true se achou pelo menos 1
			}	

		} catch (SQLException e) {
			System.out.println("Erro SQL ("+ sql +"): " + e.getMessage());
		}
		return false;
	}

}