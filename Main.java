package agendatelefonica;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {

		// Objeto que permite o usuario escrever na tela.
		Scanner s = new Scanner(System.in);
		// Objeto da classe Contatos que permite usar objetos e atributos de la.
		Contatos c = new Contatos();
		// Objeto da classe Utils que permite usar objetos e atributos de la.
		Utils u = new Utils();

		int option = 0;
		while (option != 3) {
			
			// \n = quebra de linha
			System.out.println("Bem vindo a Agenda Telefônica!\n\n" +
			    "Digite um Número: \n\n" +
				"1- Mostrar seus Contatos! \n" +
			    "2- Adicione, Delete ou Edite um Contato! \n" +
				"3- Sair!");
			
			String n1 = s.nextLine(); 
			u.verificationNumber(n1);
			
			switch (n1) {
			case "1": {
				c.mostrar();
				System.out.println("Aperte Enter para Voltar ao menu!");
				s.nextLine();
				u.clear();
				break;
			}
			case "2": {

				System.out.println("Digite o nome do Contato:");
				String name1 = s.nextLine();
				c.setName(name1);
				
				int t = 0;
				while (t != 1) {
					
					System.out.println("Digite o número do Contato:");
					String telefone1 = s.nextLine();
					if (u.verificationNumber(telefone1)) {
						c.setTelefone(telefone1);
						t = 1;
					}else {
						System.out.println("\nERRO!, Digito Invalido!, Aperte Enter para Voltar ao "
								+ "menu!");
						s.nextLine();
						u.clear();
					}
				}

				System.out.println("\n CONTATO SALVO COM SUCESSO!\n\n " +
				"Nome do Contato: " + c.getName() +
				"\n Número de Telefone: " + c.getTelefone() + "\n\n");
				
				System.out.println("Aperte Enter para Voltar ao menu!");
				s.nextLine();
				u.clear();
				break;
			}
			case "3": {
				option = 3;
				break;
			}
			default:
				System.out.println("\nERRO!, Digito Invalido!, Aperte Enter para Voltar ao menu!");
				s.nextLine();
				u.clear();
				break;
			}
		}
		
		System.out.println("\n Fim do Programa!");

		s.close();
	}
}