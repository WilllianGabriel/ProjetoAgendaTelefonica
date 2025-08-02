package agendatelefonica;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		
		// Objeto que permite o usuario escrever na tela.
		Scanner s = new Scanner(System.in);
		// Objeto da classe Contatos que permite usar objetos e atributos de la.
		Contatos c = new Contatos();
		
		// \n = quebra de linha
		System.out.println("Bem vindo a Agenda Telefônica!\n\n"
				+ "Digite um Número: \n\n"
				+ "1- Mostrar seus Contatos! \n"
				+ "2- Adicione, Delete ou Edite um Contato! \n"
				+ "3- Sair!");
		
		int number = s.nextInt();
		//Usar nextInt ou varianes buga o buffer, fazer um logo após nextLine desbuga!
		s.nextLine();
		
		switch (number) {
		case 1: {
			c.mostrar();
			break;
		}
		case 2: {
			
			
			
			System.out.println("Digite o nome do Contato:");
			String name1 = s.nextLine();
			c.setName(name1);
			
			System.out.println("Digite o número do Contato:");
			String telefone1 = s.nextLine();
			c.setTelefone(telefone1);
			
			System.out.println("\n CONTATO SALVO COM SUCESSO!\n\n "
					+ "Nome do Contato: "+c.getName()
					+ "\n Número de Telefone: "+c.getTelefone()+"\n\n");
			
			break;
		}
		case 3: {
			break;
		}
		default:
			System.out.println("ERRO!, Digito Invalido!, aperte qualquer botão para voltar ao menu!");
			break;
		}
		
		System.out.println("Fim do Programa!");
		
		
		
		
		s.close();
	}
}