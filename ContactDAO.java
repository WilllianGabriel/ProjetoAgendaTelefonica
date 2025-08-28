package agendatelefonica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactDAO {

	private DatabaseConnection db = new DatabaseConnection();
	private List<Contacts> contactList = new ArrayList<Contacts>();

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
	public void addContactToDatabase(Contacts contact) {
		String sql = "INSERT INTO contacts (name, telefone) VALUES (?,?)";

		try (Connection conn = db.connect()) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getTelefone());
			stmt.executeUpdate();
			contactList.add(contact);
			System.out.println("Contato Salvo no banco de dados com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar o contato no banco de dados: " + e.getMessage());
		}
	}

	// Método para remover o contato do banco de dados
	public void removeDatabaseContact(int id) {
		String sql = "DELETE FROM contacts WHERE id = ?";
		try (Connection conn = db.connect();) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				contactList.removeIf(Contacts -> Contacts.getId() == id);
				System.out.println("Contato removido com sucesso!");
			} else {
				System.out.println("Nenhum contato encontrado com esse nome.");
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover contato: " + e.getMessage());
		}
	}

	// Método que atualiza os dados de um contato do banco de dados
	public void updateContact(int id, String newName, String newTelefone) {
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
						break;
					}
				}
				System.out.println("Contato Atualizado com sucesso.");
			} else {
				System.out.println("Contato não encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar nome/telefone do contato: " + e.getMessage());
		}
	}

	// Método que encontra o contato no banco de dados pelo id
	public Contacts findContactByName(String name) {
		String sql = "SELECT * FROM contacts WHERE name = ?";
		try (Connection conn = db.connect()) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int id = rs.getInt("id");
				String dbname = rs.getString("name");
				String telefone = rs.getString("telefone");
				return new Contacts(id, dbname, telefone);
			} else {
				System.out.println("Contato com nome: " + name + " não encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar contato por nome: " + e.getMessage());
		}
		return null;
	}

	// Método que encontra o contato no banco de dados pelo nome
	public Contacts findContactById(int id) {
		String sql = "SELECT * FROM contacts WHERE id = ?";
		try (Connection conn = db.connect()) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setInt(1, id);
			ResultSet rs = stmt.executeQuery();
			if (rs.next()) {
				int dbId = rs.getInt("id");
				String name = rs.getString("name");
				String telefone = rs.getString("telefone");
				return new Contacts(dbId, name, telefone);
			} else {
				System.out.println("Contato com ID: " + id + " não encontrado.");
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar contato por ID: " + e.getMessage());
		}
		return null;
	}

	// Método que verifica se o contato existe no banco de dados
	public boolean contactExistsByName(String name) {
		String sql = "SELECT COUNT(*) FROM contacts WHERE name = ?";
		try (Connection conn = db.connect()) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			ResultSet rs = stmt.executeQuery();

			if (rs.next()) {
				int count = rs.getInt(1);
				return count > 0;
			}

		} catch (SQLException e) {
			System.out.println("Erro ao buscar contato pelo nome: " + e.getMessage());
		}
		return false;
	}
	
	// Método que verifica se o contato existe no banco de dados
	public boolean contactExistsByPhone(String telefone) {
	    String sql = "SELECT COUNT(*) FROM contacts WHERE telefone = ?";
	    try (Connection conn = db.connect();
	         PreparedStatement stmt = conn.prepareStatement(sql)) {

	        stmt.setString(1, telefone);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            return rs.getInt(1) > 0;
	        }

	    } catch (SQLException e) {
	        System.out.println("Erro ao buscar contato pelo telefone: " + e.getMessage());
	    }
	    return false;
	}

}