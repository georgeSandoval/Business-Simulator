package Repository;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class GenericRepositoryImplements <T> implements GenericRepository<T> {	
	
	protected final File file ;
	
	public GenericRepositoryImplements(File file) {
		this.file = file;
	}

	public void verifyFile() {		
		try {			
			if(!file.exists()) {
				File _file = new File("./Data");
				_file.mkdirs();
				if(!file.exists()) {			 
					file.createNewFile();	
				}
			}			
		} catch (Exception e) {
			System.out.println("No s� pudo crear el archivo");
		}	
	}
	
	public void writerFile(List<String> list) {
		try {
			BufferedWriter bufferWriter= new BufferedWriter(new FileWriter(file));
			list.forEach(e->{
                            try{
					bufferWriter.write(e);
					bufferWriter.newLine();
				} catch (Exception e1) {					
					e1.printStackTrace();
				}				
			});
			bufferWriter.close();
		} catch (Exception e) {
			System.out.println("No se puede abrir el archivo de texto");
		}
	}
	
	@Override
	public List<String> findAll() {
		verifyFile();
		List<String> list= new ArrayList<>();
		try {
			BufferedReader buffer = new BufferedReader(new FileReader(file));
			String line ="";
			while ((line = buffer.readLine())!=null) {
				if(line.contains(";"))
					list.add(line);
			}
			buffer.close();			
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}
	
	@Override
	public String findById(int id) {		
		return  findById(findAll(),id);
	}
	
	@Override
	public String save(T Entity) {		
		List<String> list = findAll();
		String object = String.valueOf(list.size()+1)+";"+Entity.toString();
		list.add(object);
		writerFile(list);
		return object;
	}
	@Override
	public boolean update(int id, T Entity) {
		List<String> list = findAll();
		String _entity = findById(list,id);
		if(!_entity.isEmpty()) 
			list.remove(_entity);
		else
			return false;
		_entity=String.valueOf((list.size()==0?1:id))+";"+Entity.toString();
		list.add(_entity);
		writerFile(list);
		return true;
	}

	@Override
	public void delete(int id,T Entity) {
		List<String> list = findAll();
		String oldEntity= findById(list, id);
		if(!oldEntity.isEmpty())
			list.remove(oldEntity);
		else
			return;
		if(list.size()>0 || id==1) {
			list.add(String.valueOf(id)+";"+Entity.toString());
			writerFile(list);
		}
	}
	
	public String findById(List<String> list,int id) {
		String entity[];
		if(list.size()>0) {
			for (String e : list) { 
				if(e.contains(";")) {
					entity=e.split(";");
					if(entity[0].equals(String.valueOf(id)))
						return e;
				}
			}
		}
		return "";
	}
	
	public String generateNumberCode() {
		String number = String.valueOf((findAll().size()+1));
		if(number.length()==1)
			number="000"+number;
		else if (number.length()==2)
			number="00"+number;
		else if (number.length()==3)
			number="0"+number;
		return number;
	}
}