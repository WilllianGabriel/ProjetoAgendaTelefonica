package agendatelefonica;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Objeto que permite o usuario escrever na tela
		Scanner sc = new Scanner(System.in);
		// Objeto da classe Utils que permite usar objetos e atributos da classe Utils
		Utils u = new Utils();
		// Objeto da classe AuthSystem que permite usar objetos e atributos da classe AuthSystem
		AuthSystem auth = new AuthSystem();

		int optionAuth = 1;
		int optionMenu = 0;

		while (optionAuth != 0) {

			System.out.println("\n === Menu ===");
			System.out.println("1 - Cadastrar");
			System.out.println("2 - Login");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma Opção: ");
			String n1 = sc.nextLine();
			// objeto que verifica se o que foi digitado foi um número
			u.verificationNumber(n1);

			switch (n1) {
			case "1": {

				System.out.println("\n=== Cadastro ===");
				System.out.println("Digite seu email:");
				String email = sc.nextLine();
				System.out.println("Digite seu senha:");
				String password = sc.nextLine();
				auth.register(email, password);
				// clear = é para pular varias linhas e parecer que limpou a tela
				u.clear();
				break;
			}
			case "2": {

				int loopLogin = 0;
				while (loopLogin != 1) {

					System.out.println("\n=== Login ===");
					System.out.println("Digite seu email:");
					String emailLogin = sc.nextLine();
					System.out.println("Digite seu senha:");
					String passwordLogin = sc.nextLine();
					auth.login(emailLogin, passwordLogin);
					if (auth.login(emailLogin, passwordLogin) == true) {
						System.out.println("Login feito com sucesso");
						loopLogin = 1;
						optionAuth = 0;
					} else if (auth.login(emailLogin, passwordLogin) == false) {
						System.out.println("Email ou senha invalidos, tente novamente!");
						System.out.println("\nAperte Enter para Voltar ao menu!");
						sc.nextLine();
					}
				}
				u.clear();
				break;
			}
			case "0": {
				optionAuth = 0;
				optionMenu = 5;
				break;
			}
			default:
				System.out.println("\nERRO!, Digito Invalido!, Aperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
			}
		}

		// while para fazer loop do menu Principal
		while (optionMenu != 5) {

			// Aqui é o Menu!
			System.out.println("\n=== Bem vindo, A Agenda de Contatos! ===\n");
			System.out.println("1 - Adicionar um Contato");
			System.out.println("2 - Deletar um Contato");
			System.out.println("3 - Editar um Contato");
			System.out.println("4 - Mostrar meus Contatos");
			System.out.println("0 - Sair");
			System.out.println("Escolha uma Opção: ");
			String n2 = sc.nextLine();
			u.verificationNumber(n2);
			u.clear();

			// switch para realizar as ações escolhidas pelo usuario
			switch (n2) {
			case "1": {
				
				System.out.println("Digite o nome do Contato:");
				String name = sc.nextLine();
				
				int t = 0;
				while (t != 1) {
					
					System.out.println("Digite o número do Contato:");
					String telefone = sc.nextLine();
					if (u.verificationNumber(telefone)) {
						t = 1;
						Contacts c = new Contacts(name, telefone);
						System.out.println("\nCONTATO SALVO COM SUCESSO!\n");
						c.showContacts();
						System.out.println("\nAperte qualquer botão para Voltar ao menu!");
						sc.nextLine();
					} else {
						System.out.println("\nERRO!, Digito Invalido!, Aperte qualquer botão para Voltar ao menu!");
						sc.nextLine();
					}
				}
				u.clear();
				break;
			}
			case "2": {
				
				System.out.println("Ainda em desenvolvimento 2");
				System.out.println("\nAperte qualquer botão para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
				
			}
			case "3": {
				
				System.out.println("Ainda em desenvolvimento 3");
				System.out.println("\nAperte qualquer botão para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
				
			}
			case "4": {
				// mostra os contatos salvos, e seus dados como o nome e telefone
				
				System.out.println("Aperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
			}
			case "0": {
				optionMenu = 5;
				break;
			}
			default:
				System.out.println("\nERRO!, Digito Invalido!, Aperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
			}
		}

		System.out.println("\nFim do Programa!");
		sc.close();
	}
}