package agendatelefonica;

import java.io.*;
import java.util.ArrayList;

public class ContactsManager {

	private ArrayList<Contacts> contactList = new ArrayList<>();
	private final String FILE_NAME = "data/Contacts.txt";

	public ContactsManager() {
		loadContacts();
	}

	// Método para adicionar novo contato na lista
	public void addContact(String name, String telefone) {
		Contacts contact = new Contacts(name, telefone);
		contactList.add(contact);
		saveContactToFile(contact);
		System.out.println("Contato Adicionado!");
	}

	// Método que mostra todos os contatos
	public void showContacts() {
		if (contactList.isEmpty()) {
			System.out.println("Nenhuma contato Cadastrado");
		} else {
			System.out.println("\n=== Lista de Contatos ===");
			for (Contacts c : contactList) {
				c.showContact();
			}
		}
	}

	// Método para salvar no arquivo
	private void saveContactToFile(Contacts contact) {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME, true))) {
			writer.write(contact.getName() + "," + contact.getTelefone());
			writer.newLine();
		} catch (IOException e) {
			System.out.println("❌ Erro ao salvar o contato: " + e.getMessage());
		}
	}

	// Método para carregar o dados do arquivo
	private void loadContacts() {
		File file = new File(FILE_NAME);
		if (!file.exists())
			return;

		try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
			String line;
			while ((line = reader.readLine()) != null) {
				String[] parts = line.split(",");
				if (parts.length == 2) {
					Contacts contact = new Contacts(parts[0], parts[1]);
					contactList.add(contact);
				}
			}
		} catch (IOException e) {
			System.out.println(" Erro ao carregar contatos: " + e.getMessage());
		}
	}

	// Método para procurar o contato pelo nome e remover
	public void removeContact(String name) {
		boolean removed = false;
		for (int i = 0; i < contactList.size(); i++) {
			if (contactList.get(i).getName().equalsIgnoreCase(name)) {
				contactList.remove(i);
				removed = true;
				System.out.println(" Contato removido com sucesso.");
				break;
			}
		}
		if (removed) {
			updateFile();
		} else {
			System.out.println("Contato não encontrado.");
		}
	}

	// Método para subreescrever o arquivo, tanto para remover quanto editar os contatos
	private void updateFile() {
		try (BufferedWriter writer = new BufferedWriter(new FileWriter(FILE_NAME))) {
			for (Contacts c : contactList) {
				writer.write(c.getName() + "," + c.getTelefone());
				writer.newLine();
			}
		} catch (IOException e) {
			System.out.println("❌ Erro ao atualizar o arquivo: " + e.getMessage());
		}
	}

	// Método para procurar um contato pelo nome e mostra os dados
	public void searchContact(String name) {
		boolean found = false;

		for (Contacts c : contactList) {
			if (c.getName().equalsIgnoreCase(name)) {
				System.out.println("Contato Encontrado: ");
				c.showContact();
				found = true;
				break;
			}
		}
		if (!found) {
			System.out.println("Contato não encontrado.");
		}
	}
	// Método para procurar um contato pelo nome e editar seus dados
	public void editContact(String oldName, String newName, String newTelefone) {
		boolean updating = false;
		for (Contacts c : contactList) {
			if (c.getName().equalsIgnoreCase(oldName)) {
				c.setName(newName);
				c.setTelefone(newTelefone);
				updating = true;
				System.out.println("Contato Atualizado com sucesso.");
				break;
			}
		}
		if (updating) {
			updateFile();
		} else {
			System.out.println("Contato não encontrado para edição");
		}
	}
}