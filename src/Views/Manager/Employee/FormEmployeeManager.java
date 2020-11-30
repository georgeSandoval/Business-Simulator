package Views.Manager.Employee;


import java.io.File;
import java.time.LocalDate;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXDatePicker;
import Controller.EmployeeController;
import com.jfoenix.controls.JFXTextField;
import Model.Employee;
import Views.Manager.Main.MainManager;
import Util.Util;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class FormEmployeeManager {
	private String urlImage;
    private EmployeeController controller;
    private Employee employee;
	private MainManager mainManager;	
	@FXML private Button btnAddEmployee,btnCancelEmployee;
	@FXML private JFXButton btnFileChooser;
    @FXML private JFXTextField txtNames,txtLastName,txtIdentityNumber,txtPhoneNumber,txtAddress,txtEmail,txtAge,txtCountry,txtTitle;
    @FXML private ImageView imgPrev;
    @FXML private JFXDatePicker birthdayDatePicker;


    public FormEmployeeManager(MainManager mainManager,EmployeeController controller) {
    	this.mainManager=mainManager;
    	this.controller=controller;
    	this.employee=null;
	}
    
    public FormEmployeeManager(MainManager mainManager,EmployeeController controller,Employee employee) {
    	this.mainManager=mainManager;
    	this.controller=controller;
    	this.employee=employee;
    }

    public FormEmployeeManager(MainManager mainManager) {
        controller = new EmployeeController();
        this.employee = new Employee();
    	this.mainManager = mainManager;
	}
    
    private void loadForm() {
    	boolean action = (employee==null);
    	if (action) {
    		employee=new Employee();
    		employee.setType("Empleado");
    	}    		
        employee.setFirstname(txtNames.getText().trim());
        employee.setLastname(txtLastName.getText().trim());
        employee.setFullname(txtNames.getText().trim()+" "+txtLastName.getText().trim());
        employee.setIdentitynumber(txtIdentityNumber.getText().trim());
        employee.setPhonenumber(txtPhoneNumber.getText().trim());
        employee.setAddress(txtAddress.getText().trim());
        employee.setEmail(txtEmail.getText().trim());
        employee.setAge(txtAge.getText().trim());
        employee.setCountry(txtCountry.getText().trim());
        employee.setBirthday(birthdayDatePicker.getValue().toString());
        employee.setTitle(txtTitle.getText().trim());
        employee.setIdDepartment(mainManager.department.getId());	
        employee.setUrlImage(urlImage);
        employee.setState(true);
    }
    
    public boolean verifyData() {
        if(txtNames.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Name no debe estar vacio");
            return false;
        }
        if(!txtNames.getText().matches("[a-zA-Z ]+")) {
        Util.showAlert(AlertType.ERROR, "Error el nombre no debe tener numeros");
        return false;
        }
        if(txtLastName.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Apellido no debe estar vacio");
            return false;      
        }
        if(!txtLastName.getText().matches("[a-zA-Z ]+")) {
        Util.showAlert(AlertType.ERROR, "Error el apellido no debe tener numeros");
        return false;
        }
        if(txtIdentityNumber.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Dni no debe estar vacio");
            return false;
        }
        if(!txtIdentityNumber.getText().matches("[0-9 ]+")) {
        Util.showAlert(AlertType.ERROR, "Error el DNI no debe tener letras");
        return false;
        }
        if(txtPhoneNumber.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Numero de telefono no debe estar vacio");
            return false;
        }
        if(!txtPhoneNumber.getText().matches("[0-9 ]+")) {
        Util.showAlert(AlertType.ERROR, "Error el telefono no debe tener letras");
        return false;
        }
        if(txtAddress.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Direccion no debe estar vacio");
            return false;
        }
        if(txtEmail.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Email no debe estar vacio");
            return false;
        }
        if(txtAge.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Edad no debe estar vacio");
            return false;
        }
        if(!txtAge.getText().matches("[0-9 ]+")) {
        Util.showAlert(AlertType.ERROR, "Error el anyoo no debe tener letras");
        return false;
        }
//        if(txtAge.getText().trim().length()>=3) {
//            Util.showAlert(AlertType.ERROR, "Error el anyoo no debe tener letras");
//            return false;
//            }
        if(txtCountry.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Pais no debe estar vacio");
            return false;
        }
        if(!txtCountry.getText().matches("[a-zA-Z ]+")) {
        Util.showAlert(AlertType.ERROR, "Error el pais no debe tener numeros");
        return false;
        }
        if(txtTitle.getText().trim().isEmpty()){
            Util.showAlert(AlertType.ERROR, "Titulo no debe estar vacio");
            return false;
        }
        if(!txtTitle.getText().matches("[a-zA-Z ]+")) {
        Util.showAlert(AlertType.ERROR, "Error el titulo no debe tener numeros");
        return false;
        }
        if(birthdayDatePicker.getValue()==null){
            Util.showAlert(AlertType.ERROR, "Error el campo cumpleayos esta vacio");
            return false;
        }
            if(birthdayDatePicker.getValue().compareTo(LocalDate.now())>0){
        Util.showAlert(AlertType.ERROR, "Error el campo fecha de nacimiento no debe ser mayor a la fecha actual");
            return false;
        }
       
        return true;
    }
    

    @FXML void addEmployee(Event event) {
    	if(verifyData()) {    		
    		boolean isNull=employee==null; 
    		loadForm();    		
    		if(isNull) employee=controller.save(employee, mainManager.department);
    		else controller.update(employee);    		
    		if(employee!=null) mainManager.addEmployeeToTable(employee, true,true);
    		else Util.showAlert(AlertType.ERROR,"Error al guardar empleado, porfavor comuniquese con el desarollador");
    		Util.closeWindow(event);
    	}
    }

    
    @FXML void btnFileChooserAction(Event event) {
    	FileChooser fileChooser= new FileChooser();
        fileChooser.getExtensionFilters().addAll(
                new FileChooser.ExtensionFilter("JPG", "*.jpg"),
                new FileChooser.ExtensionFilter("PNG", "*.png")
        );
        validImgFile(fileChooser.showOpenDialog(new Stage()));
    }
    
    public void validImgFile(File _file) {
    	urlImage = (_file==null?"":_file.toURI().toString());
    	if(_file==null || !_file.exists()) urlImage=getClass().getClassLoader().getResource("Util/defaultImageUser.png").toString();
    	openImage();
    }
    
    public void openImage() {
    	try {
    		Image img = new Image(urlImage);
            imgPrev.setImage(img);
		} catch (Exception e) {
			validImgFile(null);
		}
	}
    
    public void loadData() {
    	txtNames.setText(employee.getFirstname());
    	txtLastName.setText(employee.getLastname());
    	txtIdentityNumber.setText(employee.getIdentitynumber());
    	txtPhoneNumber.setText(employee.getPhonenumber());
    	txtAddress.setText(employee.getAddress());
    	txtEmail.setText(employee.getEmail());
    	txtAge.setText(employee.getAge());
    	txtCountry.setText(employee.getCountry());
    	birthdayDatePicker.setValue(Util.stringToLocalDate(employee.getBirthday()));
    	txtTitle.setText(employee.getTitle());
    	urlImage=employee.getUrlImage();
    	openImage();
    }
    
    @FXML void cancelEmployee(Event event) {
    	if(employee!=null)
    		mainManager.addEmployeeToTable(employee, true,true);
    	Util.closeWindow(event);
    }
    
    @FXML public void initialize() {
    	if(employee!=null) {
    		mainManager.addEmployeeToTable(employee, false,false);
    		loadData();
    	}
    	else {
    		urlImage="null";
    		openImage();
    	}
    }
}
