package agendatelefonica;

public class Contatos {
	
	//void não pode ser reultilizado
	//return é só usado quando o valor precisa ser reutilizado
	
	private String name;
	private String telefone;
	
	public void showContacts() {
		System.out.println("Ainda em desenvolvimento!\n");
	}
	
	//setter e //getter
	public void setName(String name) {
		this.name = name;
	}
	
	public String getName() {
		return name;
	}
	
	public void setTelefone(String telefone) {
		this.telefone = telefone;
	}
	
	public String getTelefone() {
		if (telefone.length() == 11) {
			telefone = String.format("(%s) %s %s-%s",
					telefone.substring(0,2),
					telefone.substring(2,3),
					telefone.substring(3,7),
					telefone.substring(7,11));
		}		
		return telefone;
	}
}