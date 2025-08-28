package agendatelefonica;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ContactsManager {

	private ContactDAO contactDAO = new ContactDAO();
	private List<Contacts> contactList = new ArrayList<Contacts>();
	private Utils u = new Utils();

	// Método Construtor que vai chamar o metodo que carregar os contatos no banco
	// de dados
	public ContactsManager() {
		contactDAO.loadContact();
	}

	// Chama os metodos que salvam no banco de dados e na lista de contatos
	public void addContact(String name, String telefone) {
		Contacts contact = new Contacts(name, telefone);
		contactDAO.addContactToDatabase(contact);
	}
	
	// Chama o metodo que verifica se o contato existe no banco de dados
	public boolean contactExistsByName(String name) {
		if (contactDAO.contactExistsByName(name)) {
			return true;
		}
		return false;
	}
	
	public boolean contactExistsByPhone(String name) {
		if (contactDAO.contactExistsByPhone(name)) {
			return true;
		}
		return false;
	}
	// Chama o método para remover o contato do banco de dados
	public void removeContact(int id) {
		contactDAO.removeDatabaseContact(id);
	}
	// Chama o método que atualiza os dados de um contato do banco de dados
	public void updateContact(int id, String newName, String newTelefone) {
		contactDAO.updateContact(id, newName, newTelefone);
	}
	
	// Método que mostra todos os contatos no banco de dados
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

	// Método que buscar no banco de dados um contato e mostra seus dados
	public void searchContact(int id) {
		Contacts foundContact = null;
		foundContact = contactDAO.findContactById(id);
		if (foundContact != null) {
			System.out.println("\nContato encontrado: ");
			System.out.println("\nID: " + foundContact.getId());
			System.out.println("Nome: " + foundContact.getName());
			u.phoneFormat(foundContact.getTelefone());
		}
	}

	// Método para escolher se a busca vai ser usando o id ou nome
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
					return contactDAO.findContactById(id);
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
			return contactDAO.findContactByName(name);
		}
		default:
			u.verificationNumber(option);
		}
		return null;
	}
}