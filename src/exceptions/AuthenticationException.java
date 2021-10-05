package exceptions;

public class AuthenticationException extends Exception {
	private AuthError reason;

	public AuthenticationException(AuthError reason) {
		this.reason = reason;
	}

	@Override
	public String getMessage() {
		String message = null;

		switch (this.reason) {
			case NOT_FOUND:
				message = "Usuário não encontrado";
				break;
			case WRONG_PASSWORD:
				message = "Senha incorreta";
				break;
			case INTERNAL:
				message = "Erro interno, tente novamente";
				break;
		}

		return message;
	}
}
