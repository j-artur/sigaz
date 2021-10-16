package controller;

import javafx.event.ActionEvent;

import view.*;

public class HomeController {
	public void logout(ActionEvent event) throws Exception {
		View.setViewMode(ViewMode.RESTRICTED);
		View.login();
	}
}
