package agendatelefonica;

public class User {
	
	private String email;
	private String password;
	
	//metodo Construtor para registrar o email e senha do usuário
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	//retorna o valor do email
	public String getEmail() {
		return email;
	}
	//retorna o valor da senha
	public String getPassword() {
		return password;
	}
}