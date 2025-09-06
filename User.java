package agendatelefonica;

public class User {

	private int id;
	private String name;
	private String email;
	private String password;

	// Metodo Construtor para registrar o nome, email e senha do usuário
	public User(int id, String name, String email, String password) {
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
	}

	// Metodo Construtor para registrar o email e senha do usuário
	public User(String email, String password) {
		this.email = email;
		this.password = password;
	}
	
	// Metodo Construtor para retornar nada 
		public User() {}

	// Método para retorna o valor do ID do usuario
	public int getId() {
		return id;
	}

	// Método para retorna o valor do nome do usuario
	public String getName() {
		return name;
	}

	// Método para retorna o valor do email usuario
	public String getEmail() {
		return email;
	}

	// Método para retorna o valor da senha usuario
	public String getPassword() {
		return password;
	}

}