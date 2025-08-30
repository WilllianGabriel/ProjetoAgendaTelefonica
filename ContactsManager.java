package agendatelefonica;

public class ContactsManager {

	private ContactDAO contactDAO = new ContactDAO();
	private Utils u = new Utils();

	// Método Construtor que vai chamar o metodo que carregar os contatos no banco
	// de dados
	public ContactsManager() {
		contactDAO.loadContact();
	}

	// Chama os metodos que salvam no banco de dados e na lista de contatos
	public void addContact(String name, String telefone) {
		Contacts contact = new Contacts(name, telefone);
		if (contactDAO.addContactToDatabase(contact)) {
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
	public void showContacts() {
		if (contactDAO.contactList.isEmpty()) {
			System.out.println("Nenhum Contato encotrado.");
		} else {
			System.out.println("\n=== Lista de Contatos ===");
			for (Contacts c : contactDAO.contactList) {
				c.showContact();
			}
		}
	}

	// Método que buscar no banco de dados um contato e mostra seus dados
	public void searchContact(String field, String value) {
		Contacts foundContact = null;
		foundContact = contactDAO.findContact(field, value);
		if (foundContact != null) {
			System.out.println("\nContato encontrado: ");
			System.out.println("\nID: " + foundContact.getId());
			System.out.println("Nome: " + foundContact.getName());
			u.phoneFormat(foundContact.getTelefone());
		}
	}

	public Contacts findContact(String field, String value) {
		return contactDAO.findContact(field, value);
	}
}