package agendatelefonica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {

	private DatabaseConnection db = new DatabaseConnection();
	private List<Contacts> listContacts = new ArrayList<Contacts>();

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
				listContacts.add(contact);
			}

		} catch (SQLException e) {
			System.out.println("Erro ao carregar os contatos no banco de dados: " + e.getMessage());
		}
		return listContacts;
	}

	public void addContact(String name, String telefone) {
		if (contactExists(name)) {
			System.out.println("ja existe um contato com esse nome!");
			return;
		}
		Contacts contact = new Contacts(name, telefone);
		addContactToDatabase(contact);
		listContacts.add(contact);
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
		if (listContacts.isEmpty()) {
			System.out.println("Nenhum Contato encotrado.");
		} else {
			System.out.println("\n=== Lista de Contatos ===");
			for (Contacts c : listContacts) {
				c.showContact();
			}
		}
	}

	public void removeContact(String name) {
		String sql = "DELETE FROM contacts WHERE name = ?";
		try (Connection conn = db.connect();) {
			PreparedStatement stmt = conn.prepareStatement(sql);

			stmt.setString(1, name);
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				System.out.println("Contato removido com sucesso!");
			} else {
				System.out.println("Nenhum contato encontrado com esse nome.");
			}

		} catch (SQLException e) {
			System.out.println("Erro ao remover contato: " + e.getMessage());
		}
	}

	public boolean updateContact(int id, String newName, String newTelefone) {
		String sql = "UPDATE contacts SET name = ?, telefone = ? WHERE id = ?";
		try (Connection conn = db.connect()) {
			PreparedStatement stmt = conn.prepareStatement(sql);
			
			stmt.setString(1, newName);
			stmt.setString(2, newTelefone);
			stmt.setInt(3, id);
			
			int rowsAffected = stmt.executeUpdate();
			if (rowsAffected > 0) {
				for (Contacts c : listContacts) {
					if (c.getId() == id) {
						c.setName(newName);
						c.setTelefone(newTelefone);
						break;
					}
				}
				System.out.println("Contato Atualizado com sucesso.");
				return true;
			} else {
				System.out.println("Contato não encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao atualizar o contato: " + e.getMessage());
		}
		return false;
	}

	public void searchContact(String opcao) {
		Scanner sc = new Scanner(System.in);
		Utils u = new Utils();
		Contacts foundContact = null;
		switch (opcao) {
		case "1": {
			while (true) {
				System.out.println("\nDigite o ID do contato: ");
				String imput = sc.nextLine();
				if (u.verificationNumber(imput)) {
					int id = Integer.parseInt(imput);
					foundContact = findContactById(id);
					break;
				} else {
					System.out.println("\nAperte Enter para tentar de novo!");
					sc.nextLine();
					u.clear();
				}
			}
			break;
		}
		case "2": {
			System.out.println("\nDigite o nome do contato: ");
			String name = sc.nextLine();
			foundContact = findContactByName(name);
			break;
		}
		default:
			if (opcao.matches("\\d+")) {
				System.out.println("\nDigito Invalido!, tente novamente.");
			} else {
				System.out.println("Digite Apenas Números, tente novamente.");
			}
		}
		if (foundContact != null) {
			System.out.println("\nContato encontrado: ");
			System.out.println("\nID: " + foundContact.getId());
			System.out.println("Nome: " + foundContact.getName());
			u.phoneFormat(foundContact.getTelefone());
			System.out.println("\nAperte Enter para voltar ao menu");
			sc.nextLine();
			u.clear();
		}
	}

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
				return new Contacts(id,dbname, telefone);
			} else {
				System.out.println("Contato com nome: " + name + " não encontrado.");
			}
		} catch (SQLException e) {
			System.out.println("Erro ao buscar contato por nome: " + e.getMessage());
		}
		return null;
	}

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

	private boolean contactExists(String name) {
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
