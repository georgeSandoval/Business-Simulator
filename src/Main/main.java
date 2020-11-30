package Main;

import javafx.application.Application;
import javafx.stage.Stage;
import Util.Util;
import Views.Manager.Login.LoginManager;

public class main extends Application{

	@Override
	public void start(Stage primaryStage) throws Exception {	
			Util.showForm(null, "loginFxml", new LoginManager(), "Login");
	}	
	
	public static void main(String args[]) {
		launch(args);
	}
	
}
