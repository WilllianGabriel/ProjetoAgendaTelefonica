package agendatelefonica;

public class Contacts {
	private String name;
	private String telefone;
	private int id;

	// Método Construtor para registrar o nome e telefone do contato
	public Contacts(String name, String telefone) {
		this.name = name;
		this.telefone = telefone;
	}
	// Método Construtor vazio, para criar um novo objeto sem precisar inserir dados nele
	public Contacts() {}

	// Método para mostrar o nome e telefone
	public void showContact() {
		System.out.println("\nId: " + id);
		System.out.println("Nome: " + name);
		if (telefone.length() == 11) {
			telefone = String.format("(%s) %s %s-%s",
					telefone.substring(0, 2),
					telefone.substring(2, 3),
					telefone.substring(3, 7),
					telefone.substring(7, 11));
		}
		System.out.println("Telefone: " + telefone);
	}

	// Método para retorna o valor do nome
	public String getName() {
		return name;
	}

	// Método para receber o valor do nome
	public void setName(String name) {
		this.name = name;
	}

	// Método para retorna o valor do telefone
	public String getTelefone() {
		return telefone;
	}

	// Método para receber o valor do telefone
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}

	// Método para retorna o valor do id
	public int getId() {
		return id;
	}

	// Método para receber o valor do id
	public void setId(int id) {
		this.id = id;
	}

}