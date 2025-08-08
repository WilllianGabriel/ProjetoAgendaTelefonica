package agendatelefonica;

public class Contacts {
	private String name;
	private String telefone;
	
	public Contacts (String name, String telefone) {
		this.name = name;
		this.telefone = telefone;
	}
	public void showContacts() {
		System.out.println("Nome: "+name);
		if (telefone.length() == 11) {
			telefone = String.format("(%s) %s %s-%s",
					telefone.substring(0,2),
					telefone.substring(2,3),
					telefone.substring(3,7),
					telefone.substring(7,11));
		}
		System.out.println("Telefone: "+telefone);
	}
	
	public String getName() {
		return name;
	}
	
	public String getTelefone() {
		if (telefone.length() >= 11) {
			telefone = String.format("(%s) %s %s-%s",
					telefone.substring(0,2),
					telefone.substring(2,3),
					telefone.substring(3,7),
					telefone.substring(7,11));
		}		
		return telefone;
	}
}
