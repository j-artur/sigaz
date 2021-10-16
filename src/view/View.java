package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class View extends Application {
	private static Stage primaryStage;
	private static ViewMode viewMode = ViewMode.RESTRICTED;

	public static ViewMode getViewMode() {
		return viewMode;
	}

	public static void setViewMode(ViewMode mode) {
		viewMode = mode;
	}

	@Override
	public void start(Stage stage) throws Exception {
		primaryStage = stage;
		primaryStage.setTitle("SIGAZ");
		primaryStage.setResizable(false);
		login();
		primaryStage.show();
	}

	public static void login() throws Exception {
		Parent root = FXMLLoader.load(View.class.getResource("xml/login.fxml"));
		primaryStage.setTitle("SIGAZ - login");
		primaryStage.setScene(new Scene(root));
		primaryStage.centerOnScreen();
	}

	public static void home() throws Exception {
		primaryStage.setTitle("SIGAZ");
		Parent root = FXMLLoader.load(View.class.getResource("xml/home.fxml"));
		primaryStage.setScene(new Scene(root));
		primaryStage.centerOnScreen();
	}
}
