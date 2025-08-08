package agendatelefonica;

public class AuthSystem {
	private User registeredUser;
	
	public void register(String email, String password) {
		registeredUser = new User (email,password);
		System.out.println("Cadastro concluido com sucesso!\n");
	}
	public boolean login(String email, String password) {
		if (registeredUser == null) {
			System.out.println("Nenhuma Usuário Cadastrado.");
		}
		if (registeredUser.getEmail().equals(email) && registeredUser.getPassword().equals(password)) {
			return true;
		}else {
			return false;
		}
	}
}