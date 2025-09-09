package agendatelefonica;

import java.util.Scanner;

public class MenuSystem {
	
	private final Scanner sc = new Scanner(System.in);
	private UserManager userM = new UserManager();
	private ContactsManager contactsM;
	private User currentUser;
	private final Utils u = new Utils();

	// Chamar o metodo responsavel pelo Menu de cadastro e login
	public void start() {
		showLoginMenu();
	}

	// Menu de cadastro e login
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
					contactsM = new ContactsManager();
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
				break;
			}
		}
	}

	// Método para casdastrar o email e senha do usuário
	private void register() {
		System.out.println("\n=== Cadastro ===");
		System.out.println("Digite seu Nome:");
		String name = sc.nextLine();
		System.out.println("Digite seu Email:");
		String email = sc.nextLine();
		System.out.println("Digite sua Senha:");
		String password = sc.nextLine();
		if (name == null || email == null || password == null) {
			System.out.println("Nome, Email e Senha não podem ser vazios");
			System.out.println("\nAperte Enter para voltar ao menu!");
			sc.nextLine();
			u.clear();
			return;
		}
		userM.register(name, email, password);
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
			if (userM.login(emailLogin, passwordLogin) != null) {
				System.out.println("Login efetuado com sucesso!");
			} else {
				System.out.println("\nSenha ou Email, não condiz com a nenhum cadastro.");
				return null;
			}
			u.clear();
			return userM.login(emailLogin, passwordLogin);
		}
	}

	// Menu principal
	private void showMainMenu() {

		while (true) {
			u.clear();
			System.out.println("\n=== Bem vindo(a), "+currentUser.getName()+", A Agenda de Contatos! ===\n");
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
				promptAddContact();
				u.clear();
				break;
			}
			case "2": {
				showContacts();
				u.clear();
				break;
			}
			case "3": {
				searchContact();
				u.clear();
				break;
			}
			case "4": {
				updateContact();
				u.clear();
				break;
			}
			case "5": {
				removeContact();
				u.clear();
				break;
			}
			case "0": {
				System.out.println("Saindo!");
				return;
			}
			default:
				u.verificationNumber(n2);
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
			}
		}
	}
	
	//Verifica se o contato existe, e depois chama o método para adicionar o contato no banco de dados
	private void promptAddContact() {

		System.out.println("Digite o nome do Contato:");
		String name = sc.nextLine();
		if (contactsM.contactExists("name", currentUser.getId(), name)) {
			System.out.println("Já existe um contato com esse nome.");
			System.out.println("\nAperte Enter para Voltar ao menu!");
			sc.nextLine();
			return;
		}

		while (true) {
			System.out.println("Digite o número do Contato:");
			String phone = sc.nextLine();
			if (contactsM.contactExists("phone", currentUser.getId(), phone)) {
				System.out.println("Já existe um contato com esse telefone.");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				return;
			}
			if (phone.matches("\\d+")) {
				contactsM.addContact(currentUser.getId(), name, phone);
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
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
		contactsM.showContacts(currentUser.getId());
		System.out.println("\nAperte Enter para Voltar ao menu!");
		sc.nextLine();
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
			if (contactsM.contactExists("id", currentUser.getId(), id)) {
				contactsM.searchContact("id", currentUser.getId(), id);
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
			} else {
				System.out.println("contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
			}
			break;
		}
		case "2": {
			System.out.println("Digite o Nome do contato: ");
			String name = sc.nextLine();
			if (contactsM.contactExists("name", currentUser.getId(), name)) {
				contactsM.searchContact("name", currentUser.getId(), name);
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
			} else {
				System.out.println("contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
			}
			break;
		}
		default: {
			u.verificationNumber(option);
			System.out.println("\nAperte Enter para Voltar ao menu!");
			sc.nextLine();
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
			if (contactsM.contactExists("id", currentUser.getId(), id)) {
				foundContact = contactsM.findContact("id", currentUser.getId(), id);
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
			if (contactsM.contactExists("name", currentUser.getId(), name)) {
				foundContact = contactsM.findContact("name", currentUser.getId(), name);
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
			System.out.println("\nAperte Enter para Voltar ao menu!");
			sc.nextLine();
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
					String newPhone = sc.nextLine();
					contactsM.updateContact(foundContact.getId(), currentUser.getId(), newName, newPhone);
					System.out.println("\nAperte Enter para voltar ao menu");
					sc.nextLine();
					return;
				}
				case "2": {
					return;
				}
				default:
					u.verificationNumber(option);
					System.out.println("\nAperte Enter para Voltar ao menu!");
					sc.nextLine();
				}
			}
		} else {
			System.out.println("Contato não existente!");
			System.out.println("\nAperte Enter para voltar ao menu");
			sc.nextLine();
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
			if (contactsM.contactExists("id", currentUser.getId(), id)) {
				foundContact = contactsM.findContact("id", currentUser.getId(), id);
			}else {
				System.out.println("Contato não existente!");
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
			}
			break;
		}
		case "2": {
			System.out.println("DIgite o Nome do contato: ");
			String name = sc.nextLine();
			if (contactsM.contactExists("name", currentUser.getId(), name)) {
				foundContact = contactsM.findContact("name", currentUser.getId(), name);
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
					contactsM.removeContact(foundContact.getId(), currentUser.getId());
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
			return;
		}
	}
}