package Service;

import java.io.File;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;
import Model.Department;
import Repository.GenericRepositoryImplements;

public class DepartmentServiceImplements implements DepartmentService {
	private GenericRepositoryImplements<Department> repository;
	
	public DepartmentServiceImplements() {
		repository= new GenericRepositoryImplements<Department>(new File("Data/department.txt"));
	}
	@Override
	public List<Department> findAll() {
		return repository.findAll().stream().map(e->toDepartment(e)).filter(Objects::nonNull).collect(Collectors.toList());
	}
	@Override public Department saveDepartment(Department department) {
		department.setCode(generateCode());
		return toDepartment(repository.save(department));	
	}
	@Override public boolean updateDepartment(Department department) {
		return repository.update(department.getId(),department);		
	}
	@Override public void deleteDepartment(Department department) {
		department.setState(false);
		repository.delete(department.getId(), department);		
	}
	@Override
	public boolean findById(int id) {
		return !repository.findById(id).isEmpty();
	}
	public Department toDepartment(String line) {
		String[] data = line.split(";");
		if(data[15].charAt(0)=='+' && data.length==16) 
			return new Department(Integer.parseInt(data[0]),Integer.parseInt(data[1]), data[2], data[3], data[4], data[5], data[6], data[7], data[8], data[9], data[10], data[11],data[12],data[13],data[14],true);
		return null;
	}
	
	public String generateCode() {
		return "DEP"+repository.generateNumberCode();
	}
}
