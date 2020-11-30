package Repository;

import java.util.List;

public interface GenericRepository <T> {	
	public List<String> findAll();
	public String findById(int id);
	public String save(T Entity);
	public boolean update(int id,T Entity);
	public void delete(int id,T Entity);	
}
