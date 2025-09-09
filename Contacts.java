package agendatelefonica;

public class Contacts {
	
	private int id;
	private String name;
	private String phone;
	
	private final Utils u = new Utils();

	// Método Construtor para registrar o id, nome e telefone do contato
	public Contacts(int id, String name, String phone) {
		this.id = id;
		this.name = name;
		this.phone = phone;
	}

	// Método Construtor para registrar o nome e telefone do contato
	public Contacts(String name, String phone) {
		this.name = name;
		this.phone = phone;
	}

	// Método para retorna o valor do id
	public int getId() {
		return id;
	}

	// Método para receber o valor do id
	public void setId(int id) {
		this.id = id;
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
	public String getPhone() {
		return phone;
	}

	// Método para receber o valor do telefone
	public void setPhone(String phone) {
		this.phone = phone;
	}

	// Método para mostrar o ID, Nome e Telefone
	public String toString() {
		return "\nID: " + id + "\nName: " + name + u.phoneFormat(phone);
	}
}