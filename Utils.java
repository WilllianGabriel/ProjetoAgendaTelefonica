package agendatelefonica;

public class Utils {
	
	// pular varias linhas e simula que limpou a tela
	public void clear() {
		for (int i = 0; i < 28; i++) {
			System.out.println();
		}
	}

	// verifica se o que foi digitado, é um número
	public void verificationNumber(String text) {
		if (text == null){
			System.out.println("Não pode ser vazio, tente novamente.");
		}else if (text.matches("\\d+")) {
			System.out.println("\nDigito Invalido!, tente novamente.");
		}else{
			System.out.println("Digite Apenas Números, tente novamente.");
		}
	}
	
	// Método que o número inserido fique na forma de número de telefone
	public String phoneFormat(String telefone) {
		if (telefone.length() == 11) {
			telefone = String.format("(%s) %s %s-%s",
					telefone.substring(0, 2),
					telefone.substring(2, 3),
					telefone.substring(3, 7),
					telefone.substring(7, 11));
		}
		return "\nTelefone: " + telefone;
	}
}