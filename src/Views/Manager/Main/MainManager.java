package Views.Manager.Main;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;

import Controller.ControllerDepartment;
import Controller.EmployeeController;
import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.collections.transformation.FilteredList;
import javafx.collections.transformation.SortedList;
import javafx.event.ActionEvent;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Label;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import Util.ActionButtonTableCell;
import Util.Util;
import Views.Manager.Department.FormatDepartmentManager;
import Views.Manager.Employee.FormEmployeeManager;
import Model.Department;
import Model.Employee;

public class MainManager {
	public Department department;
	private ObservableList<Department> listDepartments;
	private String lowerCaseFilter;
	private List<Department> departments;
    private ControllerDepartment departmentController;
    @FXML private TableView<Department> departmentTableView;
    @FXML private TableColumn<Department, String> fullNameDepartmentColum;
    @FXML private TableColumn<Department, JFXButton> openColumnDepartment,deleteColumnDepartment,editColumnDepartment;
    @FXML private JFXButton agregarDepButton,AddEmployeeButton; 

    @FXML private JFXTextField departmentSearchTextField,employeesSearchTextField;
    //lado derecho
    private EmployeeController employeeController;
    private List<Employee> employees;
    private ObservableList<Employee> listEmployees;
    private Employee employee;
    @FXML private TableView<Employee> employeesTableView;
    @FXML private TableColumn<Employee, String> employeeTableColum,TipoTableColum,ageColum,tituloColum;
    @FXML private TableColumn<Employee, JFXButton> doBossEmployeeColumn,editEmployeeColumn,deleteEmployeColumn;
    @FXML private ImageView imagenPng, managerImagenView, titleImageView, ageImageView, emailImageView;
    @FXML private Label nameDepartmentLabel,employeeDirectorLabel,titleEmployeLabel,ageEmployeeLabel,labelName,correoLabel,labelDirector,labelTitle,labelAge,searchEmployeeLabel;
    
    public MainManager() {
    	department = new Department();
    	employees=new ArrayList<>();
    	employeeController= new EmployeeController();
    	departmentController= new ControllerDepartment();
    	employee=null;
	}    
    
    public static void showForm()throws Exception {
      Stage primaryStage = new Stage();
      FXMLLoader loader = new FXMLLoader(MainManager.class.getClassLoader().getResource("Views/View/Main/MainFrom.fxml"));
      loader.setController(new MainManager());        
      Scene root= new Scene((Pane)loader.load());
      root.getStylesheets().add("bootstrapfx.css");
      root.getStylesheets().add("tableView.css");
      primaryStage.setScene(root);
      primaryStage.show();
    }
    
    @FXML void AgregarEmpleados(ActionEvent event){Util.showForm(null, "formEmployeeFxml", new FormEmployeeManager(this,employeeController),"");}
    
    public boolean addDepartmentToTable(Department department,boolean isAdd,boolean chargeData){
    	if(isAdd) departments.add(department);
    	else departments.remove(department);
    	if(chargeData) loadTableDepartment();
    	return this.department.equals(department);
	}
    public void setDepartmentLabel(String departmentFullName) {
    	nameDepartmentLabel.setText(departmentFullName);
    }
    
    public void addDepartmentToTable(Department department) {
        departmentTableView.getItems().add(department);
    }


    @FXML
    void agregarDepartamento(Event event) {
        Util.showForm(null, "formatDepartmentFxml", new FormatDepartmentManager(this), "");

    }
    public void addEmployeeToTable(Employee employe,boolean isAdd,boolean chargeData){
    	if(isAdd) employees.add(employe);
    	else employees.remove(employe);
    	if(chargeData) {
    		fillTableEmployees();
    		fillDataDirector();
    	}
	
    }
    

        
    public void initTableViewDepartment(){
        fullNameDepartmentColum.setCellValueFactory(new PropertyValueFactory<>("fullname"));
        openColumnDepartment.setCellFactory(ActionButtonTableCell.<Department>forTableColumn("view","",(Department e)->{
        	department=e;
        	fillDepartment();
    		return e;
    	}));

        editColumnDepartment.setCellFactory(ActionButtonTableCell.<Department>forTableColumn("edit","",(Department e)->{
        	Util.showForm(null,"formatDepartmentFxml",new FormatDepartmentManager(this,e),"");
            return e;
        }));
        deleteColumnDepartment.setCellFactory(ActionButtonTableCell.<Department>forTableColumn("delete","",(Department e)->{
        	department=e;
        	departmentController.delete(department);
            addDepartmentToTable(department,false,true);
            return e;
        }));
    }
    
    public void assignBoss() {
    	Employee _employee = null;
    	for (Employee e: employeesTableView.getItems()) {
			if(e.getType().toLowerCase().equals("jefe")) {
				_employee=e;
				break;
			}
		}
    	if(_employee!=null) {
    		employees.remove(_employee);
    		_employee.setType("Empleado");
    		employeeController.update(_employee);
    		employees.add(_employee);
    	}
    	employees.remove(employee);
    	employee.setType("Jefe");
    	if(employeeController.update(employee)) employees.add(0,employee);
    	else Util.showAlert(AlertType.ERROR, "No se pudo actualizar al empleado");
    	fillTableEmployees();
    }
    
    public void initTableViewEmployee() {
    	employeeTableColum.setCellValueFactory(new PropertyValueFactory<>("fullname"));
    	TipoTableColum.setCellValueFactory(new PropertyValueFactory<>("type"));
    	ageColum.setCellValueFactory(new PropertyValueFactory<>("age"));
    	tituloColum.setCellValueFactory(new PropertyValueFactory<>("title"));
    	doBossEmployeeColumn.setCellFactory(ActionButtonTableCell.<Employee>forTableColumn("add","",(Employee e)->{
    		employee=e;
    		assignBoss();
            return e;
        }));
    	editEmployeeColumn.setCellFactory(ActionButtonTableCell.<Employee>forTableColumn("edit","",(Employee e)->{
    		employee=e;
    		Util.showForm(null,"formEmployeeFxml",new FormEmployeeManager(this,employeeController,employee),"");
            return e;
        }));
    	deleteEmployeColumn.setCellFactory(ActionButtonTableCell.<Employee>forTableColumn("delete","",(Employee e)->{
    		employeeController.delete(e);
    		addEmployeeToTable(e,false,true);
            return e;
        }));
    }
    
    public void fillDataDirector() {
    	employeesTableView.getItems().forEach(e->{
			if(e.getType().equals("Jefe")) {
				employee=e;    				
				return;
			}    			
		});	
    	cleanAndInvisibleLabel(!(employee==null),employeeDirectorLabel,titleEmployeLabel,ageEmployeeLabel,correoLabel);
    	if(employee!=null) {
    		employeeDirectorLabel.setText(employee.getFullname());
    		titleEmployeLabel.setText(employee.getTitle());
    		ageEmployeeLabel.setText(employee.getAge());
                correoLabel.setText(employee.getEmail());
    		imagenPng.setVisible(true);
                managerImagenView.setVisible(true);
                titleImageView.setVisible(true);
                ageImageView.setVisible(true);
                emailImageView.setVisible(true);
    		openImage(employee.getUrlImage());
    	}else{  
            imagenPng.setVisible(false); 
            managerImagenView.setVisible(false);
            titleImageView.setVisible(false);
            ageImageView.setVisible(false);
            emailImageView.setVisible(false);
            employee=null;
        }
    }
   
    public void openImage(String urlImage) {
    	try {
    		Image img = new Image(urlImage);
    		imagenPng.setImage(img);
		} catch (Exception e) {
			openImage(getClass().getClassLoader().getResource("Util/defaultImageUser.png").toString());
		}
	}
    
    public void cleanAndInvisibleLabel(boolean isVisible,Label... labels) {
    	for (Label e : labels) {
			e.setVisible(isVisible);
		}
    }
    
    public void fillDepartment() {
        
    	if(departmentController.findById(department.getId())) {
         
    		labelName.setVisible(true);
    		nameDepartmentLabel.setVisible(true);
    		AddEmployeeButton.setVisible(true);
    		nameDepartmentLabel.setText(department.getFullname());
    		employeesTableView.setVisible(true);
    		employeesTableView.setItems(FXCollections.observableArrayList(new ArrayList()));
    		fillTableEmployees();
    		nameDepartmentLabel.setText(department.getFullname());
    		employee=null;
    	}
    }
    
    public void fillTableEmployees() {
    	List<Employee> filter= new ArrayList<>();
    	filter = employees.stream().map(e->{
    		if(e.getIdDepartment()==department.getId())
				return e;
    		return null;
    	}).filter(Objects::nonNull).collect(Collectors.toList());
    	listEmployees= FXCollections.observableArrayList(filter);
    	
        //searchEmployeeLabel.setVisible(filter.size()>0);
    	employeesSearchTextField.setVisible(filter.size()>0);
    	
        employeesTableView.setItems(listEmployees);
    	employeesTableView.refresh();
    	employeesTableView.setVisible(filter.size()>0);
        
    	fillDataDirector();
    	searchTableEmployees();
    }    
    
    private void searchTableDeparment() {
    	FilteredList<Department> filteredData=new FilteredList<>(listDepartments, b -> true);
    	departmentSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    		filteredData.setPredicate(object -> {
    			if(newValue == null || newValue.isEmpty()) return true;    				
    			lowerCaseFilter = newValue.toLowerCase();
    			if(object.getFullname().toLowerCase().contains(lowerCaseFilter)) return true;
    			return false;
    		});
    	});
    	SortedList<Department> sortedData = new SortedList<>(filteredData);
    	sortedData.comparatorProperty().bind(departmentTableView.comparatorProperty());
    	departmentTableView.setItems(sortedData);
    }
    
    private void searchTableEmployees() {
    	FilteredList<Employee> filteredData=new FilteredList<>(listEmployees, b -> true);
    	employeesSearchTextField.textProperty().addListener((observable, oldValue, newValue) -> {
    		filteredData.setPredicate(object -> {
    			if(newValue == null || newValue.isEmpty()) return true;    				
    			lowerCaseFilter = newValue.toLowerCase().trim();
    			if(object.getFullname().toLowerCase().contains(lowerCaseFilter) || object.getTitle().toLowerCase().contains(lowerCaseFilter)) return true;
    			return false;
    		});
    	});
    	SortedList<Employee> sortedData = new SortedList<>(filteredData);
    	sortedData.comparatorProperty().bind(employeesTableView.comparatorProperty());
    	employeesTableView.setItems(sortedData);
    }
    public void loadTableDepartment() {    	
    	listDepartments=FXCollections.observableArrayList(departments);
        departmentTableView.setItems(listDepartments);
        departmentTableView.refresh();
        searchTableDeparment();
    }
    @FXML public void initialize(){
        initTableViewDepartment();
        initTableViewEmployee();
        departments=departmentController.findAll();
        loadTableDepartment();
        employees=employeeController.findAll();  
        employeesTableView.setVisible(false); 
        imagenPng.setVisible(false);
        AddEmployeeButton.setVisible(false);
        labelName.setVisible(false);
        
        managerImagenView.setVisible(false);
        titleImageView.setVisible(false);
        ageImageView.setVisible(false);
        emailImageView.setVisible(false);
        //searchEmployeeLabel.setVisible(false);
        employeesSearchTextField.setVisible(false);
        //cleanAndInvisibleLabel(false,nameDepartmentLabel,employeeDirectorLabel,titleEmployeLabel,ageEmployeeLabel,labelDirector,labelTitle,labelAge,correoLabel);
        cleanAndInvisibleLabel(false,nameDepartmentLabel,employeeDirectorLabel,titleEmployeLabel,ageEmployeeLabel,correoLabel);
    }
}
