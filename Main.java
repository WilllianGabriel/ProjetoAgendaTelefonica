package agendatelefonica;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Objeto que permite o usuario escrever na tela
		Scanner sc = new Scanner(System.in);
		// Objeto da classe Contatos que permite usar objetos e atributos da classe
		// Contatos
		Contatos c = new Contatos();
		// Objeto da classe Utils que permite usar objetos e atributos da classe Utils
		Utils u = new Utils();
		// Objeto da classe AuthSystem que permite usar objetos e atributos da classe
		// AuthSystem
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
			u.verificationNumber(n1);

			switch (n1) {
			case "1": {

				System.out.println("\n=== Cadastro ===");
				System.out.println("Digite seu email:");
				String email = sc.nextLine();
				System.out.println("Digite seu senha:");
				String password = sc.nextLine();
				auth.register(email, password);

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
						n1 = "197854263";
					}else if(auth.login(emailLogin, passwordLogin) == false){
						System.out.println("Email ou senha invalidos, tente novamente!");
						System.out.println("\nAperte Enter para Voltar ao menu!");
						sc.nextLine();
					}
				}
			}
			case "0": {
				optionAuth = 0;
				//optionMenu = 3;
				break;
			} 
			case "197854263":{
				
				
				
			}
			default:
				// Caso o usuario não digite um número no menu, aqui vai ativar o loop e volta
				// ao menu
				System.out.println("\nERRO!, Digito Invalido!, Aperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
			}
		}
		
		// Aqui ja diz o que é!
			System.out.println("\nComo deseja ser Chamado(a)?");
			String userName = sc.nextLine();
			
		// while para fazer loop do menu Principal
		while (optionMenu != 3) {
			
			// Aqui é o Menu!
			System.out.println("\n=== Bem vindo, " + userName + ", A Agenda Telefônica! ===\n\n"
					+ "Digite um Número: \n\n" + "1- Mostrar meus Contatos \n" + "2- Adicionar um Contato \n"
					+ "3- Deletar um Contato \n" + "4- Editar um Contato \n" + "5- Sair");

			String n2 = sc.nextLine();
			// objeto que verifica se o que foi digitado foi um número
			u.verificationNumber(n2);

			// switch para realizar as ações escolhidas pelo usuario
			switch (n2) {
			case "1": {

				// mostrar = objeto que mostra os contatos salvos, e seus dados como o nome e
				// telefone
				c.showContacts();
				System.out.println("Aperte Enter para Voltar ao menu!");
				sc.nextLine();
				// clear = é para pular varias linhas e parecer que limpou a tela
				u.clear();
				break;
			}
			case "2": {

				// Cadastro de Contatos!
				System.out.println("Digite o nome do Contato:");
				String name1 = sc.nextLine();
				// setName e retorna o valor do Name
				c.setName(name1);

				// while para fazer um loop, caso o que foi digitado não seja número
				int t = 0;
				while (t != 1) {

					System.out.println("Digite o número do Contato:");
					String telefone1 = sc.nextLine();
					if (u.verificationNumber(telefone1)) {
						// setTelefone retorna o valor do telefone
						c.setTelefone(telefone1);
						t = 1;
					} else {
						System.out.println("\nERRO!, Digito Invalido!, Aperte Enter para Voltar ao "
					+ "menu!");
						sc.nextLine();
						u.clear();
					}
				}

				// getName e getTelefone chama os valores
				System.out.println("\nCONTATO SALVO COM SUCESSO!\n\n " 
				+ "Nome do Contato: " + c.getName()
				+ "\nNúmero de Telefone: " + c.getTelefone());
				System.out.println("\nAperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
			}
			case "3": {
				optionMenu = 3;
				break;
			}
			default:
				// Caso o usuario não digite um número no menu, aqui vai ativar o loop e volta
				// ao menu
				System.out.println("\nERRO!, Digito Invalido!, Aperte Enter para Voltar ao menu!");
				sc.nextLine();
				u.clear();
				break;
			}
		}
		
		// Aqui ja diz o que é!
		System.out.println("\nFim do Programa!");

		sc.close();
	}
}