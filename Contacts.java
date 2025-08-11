package agendatelefonica;

public class Contacts {
	private String name;
	private String telefone;

	// metodo Construtor para registrar o nome e telefone do contato
	public Contacts(String name, String telefone) {
		this.name = name;
		this.telefone = telefone;
	}
	// Mostrar o nome e telefone
	public void showContact() {
		System.out.println("\nNome: "+name);
		if (telefone.length() == 11) {
			telefone = String.format("(%s) %s %s-%s",
					telefone.substring(0,2),
					telefone.substring(2,3),
					telefone.substring(3,7),
					telefone.substring(7,11));
		}
		System.out.println("Telefone: "+telefone);
	}
	// retorna o valor do nome
	public String getName() {
		return name;
	}

	// retorna o valor do telefone
	public String getTelefone() {
		return telefone;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
}