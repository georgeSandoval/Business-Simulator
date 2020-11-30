package Controller;

import java.util.List;

import Model.Department;
import Model.Employee;
import Service.EmployeeServiceImplements;

public class EmployeeController {
	private final EmployeeServiceImplements service;	
	public EmployeeController() {
		service = new EmployeeServiceImplements();
	}
	public List<Employee> findAll(){
		return service.findAll();
	}
	public Employee save(Employee employee,Department department) {
		return service.saveEmployee(employee,department.getCode());
	}
	public boolean update(Employee employee) {
		return service.updateEmployee(employee);
	}
	public void delete(Employee employee) {
		service.deleteEmployee(employee);
	}
}
