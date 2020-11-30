package Service;

import java.util.List;

import Model.Department;

public interface DepartmentService {
	public List<Department> findAll();
	public Department saveDepartment(Department department);
	public boolean updateDepartment(Department department);
	public void deleteDepartment(Department department);	
	public boolean findById(int id);
}
