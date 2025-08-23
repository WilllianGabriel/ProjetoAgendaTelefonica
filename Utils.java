package agendatelefonica;

public class Utils {
	
	// pular varias linhas e simula que limpou a tela
	public void clear() {
		for (int i = 0; i < 20; i++) {
			System.out.println();
		}
	}

	// verifica se o que foi digitado, é um número
	public boolean verificationNumber(String text) {
		if (!text.matches("\\d+")) {
			System.out.println("\nDigite Apenas Números!");
		}
		return text.matches("\\d+");
	}
	public void phoneFormat(String telefone) {
		if (telefone.length() == 11) {
			telefone = String.format("(%s) %s %s-%s",
					telefone.substring(0, 2),
					telefone.substring(2, 3),
					telefone.substring(3, 7),
					telefone.substring(7, 11));
		}
		System.out.println("Telefone: " + telefone);
	}
}