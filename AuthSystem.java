package agendatelefonica;

public class AuthSystem {
	private User registeredUser;
	
	//registra o email e senha do metodo construtor User
	public void register(String email, String password) {
		registeredUser = new User (email,password);
		System.out.println("Cadastro concluido com sucesso!\n");
	}
	//metodo para criar variaveis que agem como boolean
	public enum LoginStatus{
		SUCESS,
		INVALID_PASSWORD,
		USUR_NOT_REGISTED
	}
	//verifica o estatus do login
	public LoginStatus login(String email, String password) {
		if (registeredUser == null) {
			System.out.println("Nenhum Usuário foi Cadastrado, Aperte Enter para tentar de novo!");
			return LoginStatus.USUR_NOT_REGISTED;
		}if (registeredUser.getEmail().equals(email) && 
				registeredUser.getPassword().equals(password)){
			return LoginStatus.SUCESS;
		}else {
			return LoginStatus.INVALID_PASSWORD;
		}
	}
}