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
	public String phoneFormat(String phone) {
		if (phone.length() == 11) {
			phone = String.format("(%s) %s %s-%s",
					phone.substring(0, 2),
					phone.substring(2, 3),
					phone.substring(3, 7),
					phone.substring(7, 11));
		}
		return "\nTelefone: " + phone;
	}
}