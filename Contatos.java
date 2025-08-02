package agendatelefonica;

public class Contatos {
	
	//void não pode ser reultilizado.
	//return é só usado quando o valor precisa ser reutilizado.
	
	private String name;
	private String telefone;
	
	public void mostrar() {
		System.out.println("Você ainda não tem nenhum Contato!");
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
		return telefone;
	}
	
}