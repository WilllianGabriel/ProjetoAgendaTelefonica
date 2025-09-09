package agendatelefonica;

import java.util.List;

public class ContactsManager {

	private ContactDAO contactDAO = new ContactDAO();
	
	// Chama os metodos que salvam no banco de dados e na lista de contatos
	public void addContact(int user_id, String name, String telefone) {
		Contacts contact = new Contacts(name, telefone);
		
		if (contactDAO.addContact(user_id, contact)) {
			System.out.println("Contato Salvo com sucesso.");
		}else {
			System.out.println("Não foi possivel salvar o contato!");
		}
	}

	// Chama o metodo que verifica se o contato existe no banco de dados
	public boolean contactExists(String field, int user_id, String value) {
		if (contactDAO.contactExists(field, user_id, value)) {
			return true;
		}
		return false;
	}

	// Chama o método para remover o contato do banco de dados
	public void removeContact(int contactId, int user_id) {
		if (contactDAO.removeContact(contactId, user_id)) {
			System.out.println("Contato removido com sucesso!");
		} else {
			System.out.println("Nenhum contato encontrado com esse nome.");
		}
	}

	// Chama o método que atualiza os dados de um contato do banco de dados
	public void updateContact(int contactId, int user_id, String newName, String newTelefone) {
		if (contactDAO.updateContact(contactId, user_id, newName, newTelefone)) {
			System.out.println("Contato Atualizado com sucesso.");
		} else {
			System.out.println("Contato não encontrado.");
		}
	}

	// Método que mostra todos os contatos no banco de dados
	public void showContacts(int contactId) {
		List<Contacts> contacts = contactDAO.getContacts(contactId);
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
	public void searchContact(String field, int user_id, String value) {
		Contacts foundContact = null;
		foundContact = contactDAO.findContact(field, user_id,value);
		if (foundContact != null) {
			System.out.println("\nContato encontrado: ");
			System.out.println(foundContact);
		}
	}

	public Contacts findContact(String field, int user_id, String value) {
		return contactDAO.findContact(field, user_id, value);
	}
}