package agendatelefonica;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class ContactsManager {

	private DatabaseConnection db = new DatabaseConnection();
	private List<Contacts> listContacts = new ArrayList<Contacts>();
	
	public ContactsManager(){
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
		Contacts contact = new Contacts(name, telefone);
		addContactToDatabase(contact);
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
	//ESTA COM DELAY
	public void showContacts() {
		if (listContacts.isEmpty()) {
			System.out.println("Nenhum Contato encotrado.");
		}else {
			System.out.println("\n=== Lista de Contatos ===");
			for (Contacts c : listContacts) {
				c.showContact();
			}
		}
	}

	public void removeContact() {

	}

	public void editContact() {

	}

	public void updatingContact() {

	}

}
