package agendatelefonica;

public class UserManager {
	
	private UserDAO userDAO = new UserDAO();
	
	public void register(String name, String email, String password) {
		if (userDAO.register(name, email, password)) {
			System.out.println("Cadastro efetuado com sucesso!");
		} else {
			System.out.println("\nNão foi possivel efetuar seu cadastro.");
		}
	}
	
	public User login(String email, String password) {
		return userDAO.login(email, password);
	}
	
}
