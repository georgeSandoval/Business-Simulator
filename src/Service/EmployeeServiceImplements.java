package Service;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

import Model.Employee;
import Repository.GenericRepositoryImplements;

public class EmployeeServiceImplements implements EmployeeService {
	private final GenericRepositoryImplements<Employee> repository;
	
	public EmployeeServiceImplements() {
		super();
		this.repository = new GenericRepositoryImplements<Employee>(new File("Data/employees.txt"));
	}
	@Override public List<Employee> findAll() {
		return repository.findAll().stream().map(e->toEmployee(e)).filter(Objects::nonNull).collect(Collectors.toList());
	}
	@Override public Employee saveEmployee(Employee employee,String departmentCode) {
		employee.setCode(departmentCode+generateCode());
		return toEmployee(repository.save(employee));
	}
	@Override public boolean updateEmployee(Employee employee) {
		return repository.update(employee.getId(), employee);
	}
	@Override public void deleteEmployee(Employee employee) {
		employee.setState(false);
		repository.delete(employee.getId(), employee);
	}
	public Employee toEmployee(String line) {
		String[] data= line.split(";");
		if(data.length==19 && data[18].charAt(0)=='+')
			return new Employee(Integer.parseInt(data[0]), Integer.parseInt(data[1]),data[2],data[3],data[4],data[5],data[6],data[7],data[8],data[9],data[10],data[11],data[12],data[13],data[14],data[15],data[16],data[17],true);
		return null;
	}	
	public String generateCode() {
		return "EMP"+repository.generateNumberCode();
	}
}
