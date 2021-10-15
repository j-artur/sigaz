package view;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Master extends Application {
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
		primaryStage.show();
		login();
	}

	private static void reset() throws Exception {
		primaryStage.setResizable(true);
		primaryStage.setTitle("SIGAZ");
	}

	public static void login() throws Exception {
		reset();
		Parent root = FXMLLoader.load(Master.class.getResource("xml/login.fxml"));
		primaryStage.setResizable(false);
		primaryStage.setTitle("SIGAZ - login");
		primaryStage.setScene(new Scene(root));
	}

	public static void home() throws Exception {
		reset();
		// Parent root = FXMLLoader.load(Master.class.getResource("xml/home.fxml"));
		// primaryStage.setScene(new Scene(root));
	}
}
