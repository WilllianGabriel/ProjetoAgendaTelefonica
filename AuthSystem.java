package agendatelefonica;

public class AuthSystem {
	
	private User registeredUser;
	
	//registra o email e senha do metodo construtor User
	public void register(String email, String password) {
		registeredUser = new User (email,password);
		System.out.println("Cadastro Concluido com Sucesso!\n");
	}
	//Método para criar variaveis que agem como boolean
	public enum LoginStatus{
		SUCESS,
		INVALID_PASSWORD,
		USUR_NOT_REGISTED
	}
	//Método para verifica o estatus do login
	public LoginStatus login(String email, String password) {
		if (registeredUser == null) {
			System.out.println("Nenhum Usuário foi Cadastrado");
			return LoginStatus.USUR_NOT_REGISTED;
		}if (registeredUser.getEmail().equals(email) && 
				registeredUser.getPassword().equals(password)){
			System.out.println("Login Feito com Sucesso");
			return LoginStatus.SUCESS;
		}else {
			System.out.println("Senha ou Email Invalidos");
			return LoginStatus.INVALID_PASSWORD;
		}
	}
}