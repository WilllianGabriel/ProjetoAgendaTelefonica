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
}