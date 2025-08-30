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
	public List<Contacts> contactList = new ArrayList<Contacts>();

	// Carrega os contatos salvos no banco de dados
	public List<Contacts> loadContact() {

		String sql = "SELECT * FROM contacts";
		try (Connection conn = db.connect();
				PreparedStatement stmt = conn.prepareStatement(sql);
				ResultSet rs = stmt.executeQuery();) {
			while (rs.next()) {
				Contacts contact = new Contacts();
				contact.setId(rs.getInt("id"));
				contact.setName(rs.getString("name"));
				contact.setTelefone(rs.getString("telefone"));
				contactList.add(contact);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao carregar os contatos no banco de dados: " + e.getMessage());
		}
		return contactList;
	}

	// Método para adicionar um novo contato no banco de dados
	public boolean addContactToDatabase(Contacts contact) {
		String sql = "INSERT INTO contacts (name, telefone) VALUES (?,?)";

		try (Connection conn = db.connect();
				PreparedStatement stmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);) {
			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getTelefone());
			int rowsInserted = stmt.executeUpdate();
			if (rowsInserted > 0) {
				try (ResultSet generatedKeys = stmt.getGeneratedKeys()) {
					if (generatedKeys.next()) {
						int generatedId = generatedKeys.getInt(1);
						contact.setId(generatedId);
						return true;
					}
				}
			}
			contactList.add(contact);
		} catch (SQLException e) {
			System.out.println("Erro ao salvar o contato no banco de dados: " + e.getMessage());
		}
		return false;
	}

	// Método que remove um contato do banco de dados
	public boolean removeDatabaseContact(int id) {
		String sql = "DELETE FROM contacts WHERE id = ?";
		try (Connection conn = db.connect();) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				return contactList.removeIf(Contacts -> Contacts.getId() == id);
			}
		} catch (SQLException e) {
			System.out.println("Erro ao remover contato: " + e.getMessage());
		}
		return false;
	}

	// Método que atualiza os dados de um contato do banco de dados
	public boolean updateContact(int id, String newName, String newTelefone) {
		String sql = "UPDATE contacts SET name = ?, telefone = ? WHERE id = ?";
		try (Connection conn = db.connect()) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, newName);
			stmt.setString(2, newTelefone);
			stmt.setInt(3, id);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				for (Contacts c : contactList) {
					if (c.getId() == id) {
						c.setName(newName);
						c.setTelefone(newTelefone);
						return true;
					}
				}
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar nome/telefone do contato: " + e.getMessage());
		}
		return false;
	}

	// Método que busca um contato pelo id ou nome especifico no banco de dados
	public Contacts findContact(String field, String value) {
		if (!field.equalsIgnoreCase("id") && !field.equalsIgnoreCase("name")) {
			throw new IllegalArgumentException("Campo inválido: " + field);
		}
		String sql = "SELECT id, name, telefone FROM contacts WHERE " + field + " = ?";

		try (Connection conn = db.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {
			if (field.equalsIgnoreCase("id")) {
				stmt.setInt(1, Integer.parseInt(value));
			} else {
				stmt.setString(1, value);
			}
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				return new Contacts(rs.getInt("id"), rs.getString("name"), rs.getString("telefone"));
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar contato por " + field + ": " + e.getMessage());
		}
		return null; // se não encontrou
	}

	// Método que verifica se o contato existe no banco de dados
	public boolean contactExists(String field, String value) {
		// Proteção contra SQL Injection: só permitimos "name" ou "phone"
		if (!field.equalsIgnoreCase("name") && !field.equalsIgnoreCase("telefone") && !field.equalsIgnoreCase("id")) {
			throw new IllegalArgumentException("Campo inválido: " + field);
		}

		String sql = "SELECT COUNT(*) FROM contacts WHERE " + field + " = ?";

		try (Connection conn = db.connect(); PreparedStatement stmt = conn.prepareStatement(sql)) {

			// Substitui o "?" pelo valor (nome ou telefone)
			stmt.setString(1, value);

			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				return rs.getInt(1) > 0; // retorna true se achou pelo menos 1
			}

		} catch (SQLException e) {
			System.out.println("Erro ao verificar contato por " + field + ": " + e.getMessage());
		}
		return false;
	}

}