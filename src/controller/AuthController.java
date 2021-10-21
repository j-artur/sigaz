package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

// import exceptions.*;
// import model.bo.*;
// import model.vo.*;
import view.*;

public class AuthController {
	@FXML
	private Label error;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;

	// private IUserBO<PrincipalVO> principalBo = new PrincipalBO();
	// private IUserBO<ProfessorVO> professorBo = new ProfessorBO();
	// private IUserBO<StudentVO> studentBo = new StudentBO();

	public void authenticate(ActionEvent event) {
		if (error.getText().length() != 0) {
			error.setText("");
		}

		try {
			// UserVO user = new UserVO() {};

			// user.setEmail(email.getText());
			// user.setPassword(password.getText());

			View.closeSecondaryWindow();
			View.home();

			// try {
			// principalBo.authenticate(new PrincipalVO(user));
			// View.setViewMode(ViewMode.PRINCIPAL);
			// View.home();
			// return;
			// } catch (AuthenticationException e) {
			// if (e.getReason() != AuthError.NOT_FOUND)
			// throw e;
			// }

			// try {
			// professorBo.authenticate(new ProfessorVO(user));
			// View.setViewMode(ViewMode.PROFESSOR);
			// View.home();
			// return;
			// } catch (AuthenticationException e) {
			// if (e.getReason() != AuthError.NOT_FOUND)
			// throw e;
			// }

			// try {
			// studentBo.authenticate(new StudentVO(user));
			// View.setViewMode(ViewMode.STUDENT);
			// View.home();
			// return;
			// } catch (AuthenticationException e) {
			// if (e.getReason() != AuthError.NOT_FOUND)
			// throw e;
			// }

			// throw new AuthenticationException(AuthError.NOT_FOUND);
		} catch (Exception e) {
			error.setText(e.getMessage());
		}
	}
}
