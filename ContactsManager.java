package agendatelefonica;

import java.util.ArrayList;

public class ContactsManager {
	
	private ArrayList<Contacts> contactList = new ArrayList<>();
	
	// Método para adicionar novo contato na lista
	public void addContact(String name, String telefone) {
		contactList.add(new Contacts(name,telefone));
		System.out.println("Contato Adicionado!");
		}
	//Mostrar o nome e telefone
	public void showContacts() {
		if (contactList.isEmpty()) {
			System.out.println("Nenhuma contato Cadastrado");
		}else {
			System.out.println("\n=== Lista de Contatos ===");
			for(Contacts c : contactList) {
				c.showContact();
			}
		}
	}
}