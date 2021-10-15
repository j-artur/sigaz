package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;

import exceptions.*;
import model.bo.*;
import model.vo.*;
import view.Master;
import view.ViewMode;

public class AuthController {
	@FXML
	private Label error;
	@FXML
	private TextField email;
	@FXML
	private PasswordField password;

	private PrincipalBO principalBo = new PrincipalBO();
	private ProfessorBO professorBo = new ProfessorBO();
	private StudentBO studentBo = new StudentBO();

	public void authenticate(ActionEvent event) {
		if (this.error.getText().length() != 0) {
			this.error.setText("");
		}

		System.out.println(this.email.getText());
		System.out.println(this.password.getText());

		UserVO user = new UserVO() {
		};

		try {
			user.setEmail(this.email.getText());
			user.setPassword(this.password.getText());

			try {
				this.principalBo.authenticate(new PrincipalVO(user));
				Master.setViewMode(ViewMode.PRINCIPAL);
				Master.home();
				return;
			} catch (AuthenticationException e) {
				if (e.getReason() != AuthError.NOT_FOUND)
					throw e;
			}

			try {
				this.professorBo.authenticate(new ProfessorVO(user));
				Master.setViewMode(ViewMode.PROFESSOR);
				Master.home();
				return;
			} catch (AuthenticationException e) {
				if (e.getReason() != AuthError.NOT_FOUND)
					throw e;
			}

			try {
				this.studentBo.authenticate(new StudentVO(user));
				Master.setViewMode(ViewMode.STUDENT);
				Master.home();
				return;
			} catch (AuthenticationException e) {
				if (e.getReason() != AuthError.NOT_FOUND)
					throw e;
			}

			throw new AuthenticationException(AuthError.NOT_FOUND);
		} catch (Exception e) {
			this.error.setText(e.getMessage());
		}
	}
}
