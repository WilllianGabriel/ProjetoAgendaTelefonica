package agendatelefonica;

import java.util.List;

public class ContactsManager {

	private ContactDAO contactDAO = new ContactDAO();
	
	// Chama os metodos que salvam no banco de dados e na lista de contatos
	public void addContact(int id, String name, String telefone) {
		Contacts contact = new Contacts(name, telefone);
		
		if (contactDAO.addContactToDatabase(id, contact)) {
			System.out.println("Contato Salvo com sucesso.");
		}else {
			System.out.println("Não foi possivel salvar o contato!");
		}
	}

	// Chama o metodo que verifica se o contato existe no banco de dados
	public boolean contactExists(String field, String value) {
		if (contactDAO.contactExists(field, value)) {
			System.out.println("Já existe um contato com esse nome.");
			return true;
		}
		return false;
	}

	// Chama o método para remover o contato do banco de dados
	public void removeContact(int id) {
		if (contactDAO.removeDatabaseContact(id)) {
			System.out.println("Contato removido com sucesso!");
		} else {
			System.out.println("Nenhum contato encontrado com esse nome.");
		}
	}

	// Chama o método que atualiza os dados de um contato do banco de dados
	public void updateContact(int id, String newName, String newTelefone) {
		if (contactDAO.updateContact(id, newName, newTelefone)) {
			System.out.println("Contato Atualizado com sucesso.");
		} else {
			System.out.println("Contato não encontrado.");
		}
	}

	// Método que mostra todos os contatos no banco de dados
	public void showContacts(int id) {
		List<Contacts> contacts = contactDAO.getContacts(id);
		if (contacts.isEmpty()) {
			System.out.println("Nenhum Contato encotrado.");
		} else {
			System.out.println("\n=== Lista de Contatos ===");
			for (Contacts c : contacts) {
				System.out.println(c);
			}
		}
	}

	// Método que buscar no banco de dados um contato e mostra seus dados
	public void searchContact(String field, String value) {
		Contacts foundContact = null;
		foundContact = contactDAO.findContact(field, value);
		if (foundContact != null) {
			System.out.println("\nContato encontrado: ");
			System.out.println(foundContact);
		}
	}

	public Contacts findContact(String field, String value) {
		return contactDAO.findContact(field, value);
	}
}