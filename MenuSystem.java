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

			// MENU DE CADASTRO
			System.out.println("\n === Cadastro / Login ===");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Login");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma Opção: ");
			String n1 = sc.nextLine();
			u.verificationNumber(n1);

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
				System.out.println("Saindo!");
				return;
			}
			default:
				System.out.println("\nDigito Invalido!, Aperte Enter para tentar de novo!");
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
		auth.register(email, password);
		System.out.println("Aperte Enter para continuar!");
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
				showContact();
				break;
			}
			case "3": {
				searchContact();
				break;
			}
			case "4": {
				editContact();
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
				System.out.println("\nDigito Invalido!, Aperte Enter para tentar de novo!");
				sc.nextLine();
				u.clear();
				break;
			}
		}
	}

	// Chama o método responsável por adicionar novo contato na lista de contatos, e
	// antes verifica se o cotato já existe
	private static void promptAddContact(Scanner sc, ContactsManager manager, Utils u) {

		System.out.println("Digite o nome do Contato:");
		String name = sc.nextLine();
		if (manager.contactExists(name)) {
			System.out.println("Já existe um contato com esse nome.");
			System.out.println("\nAperte Enter para Voltar ao menu!");
			sc.nextLine();
			u.clear();
			return;
		}

		while (true) {
			System.out.println("Digite o número do Contato:");
			String telefone = sc.nextLine();
			if (u.verificationNumber(telefone)) {
				manager.addContact(name, telefone);
				break;
			} else {
				System.out.println("\nAperte Enter para tentar de novo!");
				sc.nextLine();
				u.clear();
			}
		}
		System.out.println("\nAperte Enter para Voltar ao menu!");
		sc.nextLine();
		u.clear();
	}

	// Chama o método responsável por mostrar os contatos ja
	// adicionados
	private void showContact() {
		manager.showContacts();
		System.out.println("\nAperte Enter para Voltar ao menu!");
		sc.nextLine();
		u.clear();
	}
	
	//Chama o método responsável por buscar um contato especifico e mostrar seus dados
	private void searchContact() {
		System.out.println("Digite o nome do contato que deseja buscar: ");
		String searchName = sc.nextLine();
		manager.searchContact(searchName);
		System.out.println("\nAperte Enter para voltar ao menu");
		sc.nextLine();
		u.clear();
	}
	
	//Chama o método responsável por editar os dados de um contato especifico
	private void editContact() {
		System.out.println("Digite o nome do contato que deseja editar: ");
		String oldName = sc.nextLine();
		System.out.println("Digite o novo nome para esse Contato: ");
		String newName = sc.nextLine();
		System.out.println("Digite o novo telefone para esse Contato: ");
		String newTelefone = sc.nextLine();
		manager.editContact(oldName, newName, newTelefone);
		System.out.println("\nAperte Enter para voltar ao menu");
		sc.nextLine();
		u.clear();
	}
	
	//Chama o método responsável por remover um contato especifico
	private void removeContact() {
		System.out.println("Digite o nome do contato que deseja remover: ");
		String removeName = sc.nextLine();
		manager.removeContact(removeName);
		System.out.println("\nAperte Enter para voltar ao menu");
		sc.nextLine();
		u.clear();
	}
}