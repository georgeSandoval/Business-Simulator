package Service;

import java.util.List;
import Model.Employee;

public interface EmployeeService {
	public List<Employee> findAll();
	public Employee saveEmployee(Employee employee,String departmentCode);
	public boolean updateEmployee(Employee employee);
	public void deleteEmployee(Employee employee);	
}