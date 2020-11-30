package Controller;

import java.util.List;

import Model.Department;
import Service.DepartmentServiceImplements;

public class ControllerDepartment {
	private static DepartmentServiceImplements service;
	
	public ControllerDepartment() {
		service = new DepartmentServiceImplements();
	}
	public List<Department> findAll(){
		return service.findAll();
	}
	public Department save(Department department) {
		return service.saveDepartment(department);
	}
	public boolean update(Department department) {
		return service.updateDepartment(department);
	}
	public void delete(Department department) {
		service.deleteDepartment(department);
	}
	public boolean findById(int id) {
		return service.findById(id);
	}
}
