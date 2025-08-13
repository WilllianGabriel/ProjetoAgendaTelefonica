package agendatelefonica;

public class User {
	
	private String email;
	private String password;

	// Metodo Construtor para registrar o email e senha do usuário
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}

	// Método para retorna o valor do email
	public String getEmail() {
		return email;
	}

	// Método para retorna o valor da senha
	public String getPassword() {
		return password;
	}
}