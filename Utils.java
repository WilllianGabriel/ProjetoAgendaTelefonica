package agendatelefonica;

public class Utils {
	
	//pula varias linhas, para "limpar a tela"
	public void clear() {
		for (int i = 0; i<28;i++) {
			System.out.println();
		}
	}
	//verifica se o que foi digitado, é um número
	public boolean verificationNumber(String text) {
		return text.matches("\\d+");
	}
}