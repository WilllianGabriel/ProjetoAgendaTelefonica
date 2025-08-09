package agendatelefonica;

import java.util.Scanner;

import agendatelefonica.AuthSystem.LoginStatus;

public class Main {

	public static void main(String[] args) {

		Scanner sc = new Scanner(System.in);
		Utils u = new Utils();
		AuthSystem auth = new AuthSystem();
		ContactsManager manager = new ContactsManager();

		int optionAuth = 1;
		int optionMenu = 1;

		while (optionAuth != 0) {
			
			//MENU DE CADASTRO
			System.out.println("\n === Menu ===");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Login");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma Opção: ");
			String n1 = sc.nextLine();
			if (!u.verificationNumber(n1)) {
				System.out.println("\nDigite Apenas Números!");
				sc.nextLine();
				u.clear();
			}
			switch (n1) {
			case "1": {

				System.out.println("\n=== Cadastro ===");
				System.out.println("Digite seu email:");
				String email = sc.nextLine();
				System.out.println("Digite seu senha:");
				String password = sc.nextLine();
				auth.register(email, password);
				u.clear();
				break;
			}
			case "2": {

				int loopLogin = 1;
				while (loopLogin != 0) {

					System.out.println("\n=== Login ===");
					System.out.println("Digite seu email:");
					String emailLogin = sc.nextLine();
					System.out.println("Digite seu senha:");
					String passwordLogin = sc.nextLine();
					LoginStatus status = auth.login(emailLogin, passwordLogin);
					
					switch (status) {
					case LoginStatus.SUCESS: {
						System.out.println("\nlogin feito com sucesso, Aperte Enter para continuar!");
						sc.nextLine();
						u.clear();
						loopLogin = 0;
						optionAuth = 0;
						break;
					}
					case LoginStatus.INVALID_PASSWORD:{
						System.out.println("\nDigito Invalido!, Aperte Enter para tentar de novo!");
						sc.nextLine();
						u.clear();
						break;
					}
					default:
						loopLogin = 0;
						sc.nextLine();
						u.clear();
						break;
					}
				}
				u.clear();
				break;
			}
			case "0": {
				optionAuth = 0;
				optionMenu = 0;
				break;
			}
			default:
				System.out.println("\nDigito Invalido!, Aperte Enter para tentar de novo!");
				sc.nextLine();
				u.clear();
				break;
			}
		}
		while (optionMenu != 0) {
			
			//MENU PRINCIPAL
			System.out.println("\n=== Bem vindo, A Agenda de Contatos! ===\n");
			System.out.println("1 - Adicionar um Contato");
			System.out.println("2 - Lista de Contatos");
			System.out.println("3 - Deleter um Contato");
			System.out.println("4 - Editar um Contato");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma Opção: ");
			String n2 = sc.nextLine();
			if (!u.verificationNumber(n2)) {
				System.out.println("\nDigite Apenas Números!");
			}
			switch (n2) {
			case "1": {
				System.out.println("Digite o nome do Contato:");
				String name = sc.nextLine();
				
				int t = 0;
				while (t != 1) {
					System.out.println("Digite o número do Contato:");
					String telefone = sc.nextLine();
					if (u.verificationNumber(telefone)) {
						manager.addContact(name, telefone);
						t = 1;
						System.out.println("\nAperte Enter para Voltar ao menu!");
						sc.nextLine();
					} else {
						System.out.println("\nDigite Apenas Números!, Aperte Enter para tentar de novo!");
						sc.nextLine();
					}
				}
				u.clear();
				break;
			}
			case "2": {
				manager.showContacts();
				System.out.println("\nAperte Enter para voltar ao menu");
				sc.nextLine();
				u.clear();
				break;
			}
			case "3":{
				System.out.println("Ainda em desenvolvimento 3");
				System.out.println("\nAperte Enter para voltar ao menu");
				sc.nextLine();
				u.clear();
				break;
			}
			case "4":{
				System.out.println("Ainda em desenvolvimento 4");
				System.out.println("\nAperte Enter para tentar de novo!");
				sc.nextLine();
				u.clear();
				break;
			}
			case "0": {
				optionMenu = 0;
				break;
			}
			default:
				System.out.println("\nDigito Invalido!, Aperte Enter para tentar de novo!");
				sc.nextLine();
				u.clear();
				break;
			}
		}

		System.out.println("\nFim do Programa!");
		sc.close();
	}
}