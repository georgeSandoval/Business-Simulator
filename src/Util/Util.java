package Util;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.time.LocalDate;

import javafx.event.Event;
import javafx.fxml.FXMLLoader;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class Util {
	public boolean isNumber(String number) {
		return number.matches("[0-9]+");
	}
	public boolean isAlphabet(String text) {
		return text.matches("/^[a-zA-Z ]*$/");
	}
	public static void showAlert(AlertType alertType,String text) {
		Alert alert = new Alert(alertType,text);
		alert.showAndWait();
	}	
	public static void closeWindow(Event event) {
		Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
		stage.close();
	}	
	public static void showForm(Event event,String nameFxml,Object controller,String title) {	
		Scene scene = null;
		try{
			Stage stage = new Stage();
			scene = setViewScene(scene, Util.class.getClassLoader().getResource(searchUrlByNameFxml(Util.class.getResourceAsStream("fxml"), nameFxml)), controller);
			stage.initStyle(StageStyle.UNDECORATED);
			stage.initModality(Modality.APPLICATION_MODAL);
			if(!title.isEmpty())
				stage.setTitle(title);
			if(event!=null)
				stage.initOwner(((Node)event.getSource()).getScene().getWindow());
			stage.centerOnScreen();
			stage.setResizable(false);
			stage.setScene(scene);
			stage.show();
		} catch (Exception e) {
			showAlert(AlertType.CONFIRMATION,"La vista no Existe");
		}		
	}
	
    public Integer stringToNumber(String txt){
        try {
            Integer number = Integer.parseInt(txt);
            return number;
        } catch (Exception e) {
            return null;    
        }            
    }
        
	public static String searchUrlByNameFxml(InputStream file,String nameFxml) {
		String line="",read[];
		try {
			BufferedReader buffer = new BufferedReader(new InputStreamReader(file));
			while((line = buffer.readLine())!=null) {
				if(line.contains("->")) {
					read=line.split("->");	
					if(read[0].equals(nameFxml)) 
						return read[1];										
				}
			}	
			buffer.close();
		} catch (Exception e) {		
			showAlert(AlertType.CONFIRMATION,"No se encuentra archivo txt");
		}
		return "";
	}
	
	public static Scene setViewScene(Scene _scene,URL _url,Object controller) {
		try {
			FXMLLoader loader = new FXMLLoader(_url);
			loader.setController(controller);
			_scene = new Scene((Pane) loader.load());
			_scene.getStylesheets().add("bootstrapfx.css");
		} catch (Exception e) {				
            e.printStackTrace();
		}
		return _scene;
	}
	public static LocalDate stringToLocalDate(String value) {
		try {
			return LocalDate.parse(value);
		} catch (Exception e) {
			showAlert(AlertType.ERROR,"La fecha es invalida,, actualizelo a una valida");
			return LocalDate.now();
		}
	}
}
