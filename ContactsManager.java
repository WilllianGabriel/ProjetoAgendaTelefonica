package agendatelefonica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {

	private DatabaseConnection db = new DatabaseConnection();
	private List<Contacts> contactList = new ArrayList<Contacts>();
	private Utils u = new Utils();

	public ContactsManager() {
		loadContact();
	}

	private List<Contacts> loadContact() {

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

	public void addContact(String name, String telefone) {
		Contacts contact = new Contacts(name, telefone);
		addContactToDatabase(contact);
		contactList.add(contact);
	}

	private void addContactToDatabase(Contacts contact) {
		String sql = "INSERT INTO contacts (name, telefone) VALUES (?,?)";

		try (Connection conn = db.connect()) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, contact.getName());
			stmt.setString(2, contact.getTelefone());
			stmt.executeUpdate();
			System.out.println("Contato Salvo no banco de dados com sucesso.");
		} catch (SQLException e) {
			System.out.println("Erro ao salvar o contato no banco de dados: " + e.getMessage());
		}
	}

	public void showContacts() {
		if (contactList.isEmpty()) {
			System.out.println("Nenhum Contato encotrado.");
		} else {
			System.out.println("\n=== Lista de Contatos ===");
			for (Contacts c : contactList) {
				c.showContact();
			}
		}
	}

	public void removeContact(int id) {
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
			System.out.println("Erro ao atualizar o contato: " + e.getMessage());
		}
	}

	public void searchContact(int id) {
		Contacts foundContact = null;
		foundContact = findContactById(id);;
		if (foundContact != null) {
			System.out.println("\nContato encontrado: ");
			System.out.println("\nID: " + foundContact.getId());
			System.out.println("Nome: " + foundContact.getName());
			u.phoneFormat(foundContact.getTelefone());
		}
	}

	public Contacts findContact(String option) {
		Scanner sc = new Scanner(System.in);
		switch (option) {
		case "1": {
			while (true) {
				System.out.println("\nDigite o ID do contato: ");
				String input = sc.nextLine();
				if (input.matches("\\d+")) {
					int id = Integer.parseInt(input);
					sc.nextLine();
					return findContactById(id);
				} else {
					System.out.println("Digite Apenas Números, tente novamente.");
					System.out.println("\nAperte Enter para tentar de novo!");
					sc.nextLine();
					u.clear();
				}
			}
		}
		case "2": {
			System.out.println("\nDigite o nome do contato: ");
			String name = sc.nextLine();
			return findContactByName(name);
		}
		default:
			u.verificationNumber(option);
		}
		return null;
	}

	private Contacts findContactByName(String name) {
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

	private Contacts findContactById(int id) {
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

	public boolean contactExists(String name) {
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
			System.out.println("Erro verificar existência do contato: " + e.getMessage());
		}
		return false;
	}

}
