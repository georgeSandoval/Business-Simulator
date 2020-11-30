package Views.Manager.Login;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXPasswordField;
import com.jfoenix.controls.JFXTextField;

import Util.Util;
import Views.Manager.Main.MainManager;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;

public class LoginManager {
    @FXML private JFXTextField userTextField;
    @FXML private JFXPasswordField passwordTextField;
    @FXML private JFXButton loginButton,closeButton;
    @FXML private ImageView avatarImage;
    @FXML private Label errorLabel;
    
    public void initializeAvatar() {
    	avatarImage.setImage(new Image(getClass().getClassLoader().getResource("Util/loginAvatar.png").toString()));
    }
    
    @FXML void loginButtonAction(Event event) {
    	login(event);
    }
    
    public void login(Event event) {
    	if(verifyLogin()) {
    		try {
    			if(event!=null)
    				Util.closeWindow(event);
				MainManager.showForm();
			} catch (Exception e) {
				e.printStackTrace();
			}
    	}else errorLabel.setVisible(true);
    }
    
    public boolean verifyLogin() {
    	if(userTextField.getText().trim().isEmpty()) return false;
    	if(!userTextField.getText().trim().toLowerCase().equals("admin")) return false;
    	if(passwordTextField.getText().trim().isEmpty()) return false;
    	if(!passwordTextField.getText().trim().toLowerCase().equals("admin")) return false;
    	return true;
    }
    
    @FXML void enterKey(KeyEvent event) {
    	if (event.getCode() == KeyCode.ENTER) {
    		login(event);
    	}
    }
    @FXML
    void closeLogin(Event event) {
    	Util.closeWindow(event);
    }
    @FXML public void initialize() {
    	errorLabel.setVisible(false);
    	initializeAvatar();
    }
}
