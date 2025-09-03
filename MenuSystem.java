package agendatelefonica;

import java.util.Scanner;
import agendatelefonica.AuthSystem.LoginStatus;

public class MenuSystem {

	private final Scanner sc = new Scanner(System.in);
	private final AuthSystem auth = new AuthSystem();
	private ContactsManager manager;
	private User currentUser;
	private Utils u = new Utils();

	// Método para chamar o metodo responsavel por executar o Menu de cadastro e
	// login
	public void start() {
		showLoginMenu();
	}

	// Método para executar o menu de cadastro e login
	private void showLoginMenu() {
		while (true) {

			System.out.println("\n === Cadastro / Login ===");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Login");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma Opção: ");
			String n1 = sc.nextLine();

			switch (n1) {
			case "1": {
				register();
				break;
			}
			case "2": {
				currentUser = login();
				if (currentUser != null) {
					manager = new ContactsManager();
					showMainMenu();
				}
				break;
			}
			case "0": {
				System.out.println("Fim do Programa!");
				return;
			}
			default:
				u.verificationNumber(n1);
				sc.nextLine();
				u.clear();
				break;
			}
		}
	}

	// Método para casdastrar o email e senha do usuário
	private void register() {
		System.out.println("\n=== Cadastro ===");
		System.out.println("Digite seu email:");
		String email = sc.nextLine();
		System.out.println("Digite seu senha:");
		String password = sc.nextLine();
		if (email == null || password == null) {
			System.out.println("Email e Senha não podem ser vazios");
			System.out.println("\nAperte Enter para voltar ao menu!");
			sc.nextLine();
			u.clear();
			return;
		}
		auth.register(email, password);
		System.out.println("\nAperte Enter para continuar!");
		sc.nextLine();
		u.clear();
	}

	// Método para verificar a auteticação do login
	private User login() {

		while (true) {
			System.out.println("\n=== Login ===");
			System.out.println("Digite seu email:");
			String emailLogin = sc.nextLine();
			System.out.println("Digite seu senha:");
			String passwordLogin = sc.nextLine();
			LoginStatus status = auth.login(emailLogin, passwordLogin);

			switch (status) {
			case LoginStatus.SUCESS: {
				System.out.println("\nAperte Enter para continuar!");
				sc.nextLine();
				u.clear();
				return new User(emailLogin, passwordLogin);
			}
			case LoginStatus.INVALID_PASSWORD: {
				System.out.println("\nAperte Enter para tentar de novo!");
				sc.nextLine();
				u.clear();
				break;
			}
			default:
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				return null;
			}
		}
	}

	// Método para executar o Menu principal
	private void showMainMenu() {

		while (true) {
			System.out.println("\n=== Bem vindo, A Agenda de Contatos! ===\n");
			System.out.println("1 - Adicionar um Contato");
			System.out.println("2 - Lista de Contatos");
			System.out.println("3 - Buscar contato");
			System.out.println("4 - Editar contato");
			System.out.println("5 - Remover contato");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma Opção: ");
			String n2 = sc.nextLine();

			switch (n2) {
			case "1": {
				promptAddContact(sc, manager, u);
				break;
			}
			case "2": {
				showContacts();
				break;
			}
			case "3": {
				searchContact();
				break;
			}
			case "4": {
				updateContact();
				break;
			}
			case "5": {
				removeContact();
				break;
			}
			case "0": {
				System.out.println("Saindo!");
				return;
			}
			default:
				u.verificationNumber(n2);
				sc.nextLine();
				u.clear();
				break;
			}
		}
	}

	// Chama os metodos responsaveis por verificar se o cotato já existe, e adiciona
	// esse novo contato na lista de contatos e no banco de dados
	private static void promptAddContact(Scanner sc, ContactsManager manager, Utils u) {

		System.out.println("Digite o nome do Contato:");
		String name = sc.nextLine();
		if (manager.contactExists("name", name)) {
			System.out.println("\nAperte Enter para Voltar ao menu!");
			sc.nextLine();
			u.clear();
			return;
		}

		while (true) {
			System.out.println("Digite o número do Contato:");
			String telefone = sc.nextLine();
			if (manager.contactExists("telefone", telefone)) {
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				return;
			}
			if (telefone.matches("\\d+")) {
				manager.addContact(name, telefone);
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
			} else {
				System.out.println("Digite Apenas Números");
				System.out.println("\nAperte Enter para tentar de novo!");
				sc.nextLine();
				u.clear();
			}
		}
	}

	// Chama o método responsável por mostrar os contatos ja
	// adicionados
	private void showContacts() {
		manager.showContacts();
		System.out.println("\nAperte Enter para Voltar ao menu!");
		sc.nextLine();
		u.clear();
	}

	// Chama o método responsável por buscar um contato especifico e mostrar seus
	// dados
	private void searchContact() {
		System.out.println("\nBuscar contato por: \n");
		System.out.println("1 - ID");
		System.out.println("2 - Nome");
		System.out.print("\nEscolha uma opção: \n");
		String option = sc.nextLine();
		switch (option) {
		case "1": {
			System.out.println("Digite o ID do contato: ");
			String id = sc.nextLine();
			if (manager.contactExists("id", id)) {
				manager.searchContact("id", id);
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
			} else {
				System.out.println("contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
			}
			break;
		}
		case "2": {
			System.out.println("Digite o Nome do contato: ");
			String name = sc.nextLine();
			if (manager.contactExists("name", name)) {
				manager.searchContact("name", name);
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
			} else {
				System.out.println("contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
			}
			break;
		}
		default: {
			u.verificationNumber(option);
		}
		}
	}

	// Chama o método responsável por editar os dados de um contato especifico
	private void updateContact() {
		Contacts foundContact = null;
		System.out.println("\nBuscar contato por: \n");
		System.out.println("1 - ID");
		System.out.println("2 - Nome");
		System.out.print("\nEscolha uma opção: \n");
		String option = sc.nextLine();
		switch (option) {
		case "1": {
			System.out.println("Digite o ID do contato: ");
			String id = sc.nextLine();
			if (manager.contactExists("id", id)) {
				foundContact = manager.findContact("id", id);
			}else {
				System.out.println("Contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
			}
			break;
		}
		case "2": {
			System.out.println("DIgite o Nome do contato: ");
			String name = sc.nextLine();
			if (manager.contactExists("name", name)) {
				foundContact = manager.findContact("name", name);
			}else {
				System.out.println("Contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
			}
			break;
		}
		default: {
			u.verificationNumber(option);
		}
		}

		if (foundContact != null) {
			System.out.println("\nContato encontrado: \n");
			System.out.println(foundContact);

			while (true) {
				System.out.println("\nDeseja Atualizar esse contato?\n");
				System.out.println("1 - Sim");
				System.out.println("2 - Não");
				System.out.println("\nEscolha uma opção: ");
				String option1 = sc.nextLine();
				switch (option1) {
				case "1": {
					System.out.println("\nDigite o novo nome para esse Contato: ");
					String newName = sc.nextLine();
					System.out.println("Digite o novo telefone para esse Contato: ");
					String newTelefone = sc.nextLine();
					manager.updateContact(foundContact.getId(), newName, newTelefone);
					System.out.println("\nAperte Enter para voltar ao menu");
					sc.nextLine();
					u.clear();
					return;
				}
				case "2": {
					return;
				}
				default:
					u.verificationNumber(option);
				}
			}
		} else {
			System.out.println("Contato não existente!");
			System.out.println("\nAperte Enter para voltar ao menu");
			sc.nextLine();
			u.clear();
		}
	}

	// Chama o método responsável por remover um contato especifico
	private void removeContact() {
		Contacts foundContact = null;
		System.out.println("\nBuscar contato por: \n");
		System.out.println("1 - ID");
		System.out.println("2 - Nome");
		System.out.print("\nEscolha uma opção: \n");
		String option = sc.nextLine();
		switch (option) {
		case "1": {
			System.out.println("Digite o ID do contato: ");
			String id = sc.nextLine();
			if (manager.contactExists("id", id)) {
				foundContact = manager.findContact("id", id);
			}else {
				System.out.println("Contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
			}
			break;
		}
		case "2": {
			System.out.println("DIgite o Nome do contato: ");
			String name = sc.nextLine();
			if (manager.contactExists("name", name)) {
				foundContact = manager.findContact("name", name);
			}else {
				System.out.println("Contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
			}
			break;
		}
		default: {
			u.verificationNumber(option);
		}
		}
		if (foundContact != null) {
			System.out.println("\nContato encontrado: ");
			System.out.println(foundContact);

			while (true) {
				System.out.println("\nDeseja realmente remover esse contato?\n");
				System.out.println("1 - Sim");
				System.out.println("2 - Não");
				System.out.println("\nEscolha uma opção: ");
				String option1 = sc.nextLine();
				switch (option1) {
				case "1": {
					manager.removeContact(foundContact.getId());
					System.out.println("\nAperte Enter para voltar ao menu");
					sc.nextLine();
					u.clear();
					return;
				}
				case "2": {
					return;
				}
				default:
					u.verificationNumber(option);
				}
			}
		} else {
			System.out.println("\nAperte Enter para voltar ao menu");
			sc.nextLine();
			u.clear();
		}
	}
}