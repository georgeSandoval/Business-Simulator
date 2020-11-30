package Views.Manager.Department;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import javafx.event.Event;
import javafx.fxml.FXML;
import Util.Util;
import Views.Manager.Main.MainManager;
import Model.Department;
import Controller.ControllerDepartment;
import com.jfoenix.controls.JFXDatePicker;
import java.time.LocalDate;

import javafx.scene.control.Alert.AlertType;

public class FormatDepartmentManager {
    private MainManager mainManager;
    private Department department;    
    private ControllerDepartment controller;
    private boolean _isNull;
    @FXML private JFXTextField nameText;
    @FXML private JFXButton DepartmentCancelButton,DepartmentAddButton;
    @FXML private JFXTextField PhoneNumberText,AddresText,EmailText,CountryText,CodeText;
    @FXML private JFXDatePicker birthdayDatePicker;
    
    public FormatDepartmentManager(MainManager mainManager){
        controller = new ControllerDepartment();
        this.department = null;
        this.mainManager = mainManager;
        _isNull=false;
    }
    
    public FormatDepartmentManager(MainManager mainManager,Department department){
        controller = new ControllerDepartment();
        this.department = department;
        this.mainManager = mainManager;
        _isNull=true;
    }
    
    private void loadForm() {//A�ades todos los datos
    	department.setFullname(nameText.getText().trim());
        department.setPhonenumber(PhoneNumberText.getText().trim());
        department.setAddress(AddresText.getText().trim());
        department.setEmail(EmailText.getText().trim());
        department.setCountry(CountryText.getText().trim());
        department.setBirthday(birthdayDatePicker.getValue().toString());
        department.setIdentitynumber(CodeText.getText().trim());
        department.setState(true);
    }
    
    private void loadData(){
    	nameText.setText(department.getFullname());
    	PhoneNumberText.setText(department.getPhonenumber());
    	AddresText.setText(department.getAddress());
    	EmailText.setText(department.getEmail());
    	CountryText.setText(department.getCountry());
    	birthdayDatePicker.setValue(Util.stringToLocalDate(department.getBirthday()));
    	CodeText.setText(department.getIdentitynumber());
    }
    
    private boolean verifyData() {
		if(nameText.getText().trim().isEmpty()){
			Util.showAlert(AlertType.ERROR, "Error el campo nombre esta vacio");
			return false;
		}
		if(!nameText.getText().matches("[a-zA-Z ]+")) {
			Util.showAlert(AlertType.ERROR, "Error el nombre no debe tener numeros");
			return false;
		}
                if(PhoneNumberText.getText().trim().isEmpty()){
			Util.showAlert(AlertType.ERROR, "Error el campo Telefono esta vacio");
			return false;
		}
                if(!PhoneNumberText.getText().matches("[0-9 ]+")) {
			Util.showAlert(AlertType.ERROR, "Error el telefono no debe tener letras");
			return false;
		}
                if(AddresText.getText().trim().isEmpty()){
			Util.showAlert(AlertType.ERROR, "Error el campo direccion esta vacio");
			return false;
		}
                if(EmailText.getText().trim().isEmpty()){
			Util.showAlert(AlertType.ERROR, "Error el campo correo esta vacio");
			return false;
		}
                if(CountryText.getText().trim().isEmpty()){
			Util.showAlert(AlertType.ERROR, "Error el campo pais esta vacio");
			return false;
		}
                if(!CountryText.getText().matches("[a-zA-Z ]+")) {
			Util.showAlert(AlertType.ERROR, "Error el nombre del pais no debe tener numeros");
			return false;
		}
        if(birthdayDatePicker.getValue()==null){
			Util.showAlert(AlertType.ERROR, "Error el campo aniversario esta vacio");
			return false;
		}
                if(birthdayDatePicker.getValue().compareTo(LocalDate.now())>0){
			Util.showAlert(AlertType.ERROR, "Error el campo fecha de creacion no debe ser mayor a la fecha actual");
			return false;
		}
                if(CodeText.getText().trim().isEmpty()){
			Util.showAlert(AlertType.ERROR, "Error el campo codigo a esta vacio");
			return false;
		}
                if(!CodeText.getText().matches("[0-9 ]+")) {
			Util.showAlert(AlertType.ERROR, "Error el codigo no debe tener letras");
			return false;
		}
		return true;
	}
    
    @FXML void DeparmentAddButtonAction(Event event){
    	if(verifyData()) {
    		boolean isChange =false;
    		if(_isNull)
    			isChange=mainManager.addDepartmentToTable(department,false,false); // elimina de la tabla xd
    		else department= new Department();
    		loadForm();
    		if(!_isNull)
    			department=controller.save(department);
    		else 
    			controller.update(department);
    		if(isChange) mainManager.setDepartmentLabel(department.getFullname());
    		
        	mainManager.addDepartmentToTable(department,true,true);
        	Util.closeWindow(event);
    	}  	
    }
    
    @FXML void DepartmentCancelButtonAction(Event event){
    	Util.closeWindow(event);
    }
    
    @FXML public void initialize() {
    	if(department!=null) loadData();
    		
    }
}
