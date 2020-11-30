package Model;

public class Employee extends BusinessSubject{
	private String type;
	private int id_department;	
	private String title;
	private String urlImage;
																																																	
	public Employee(int id,int id_department,String type, String urlImage,String title, String code,String created_at,String deleted_at,String firstname, String lastname, String fullname, String identitynumber, String phonenumber,String address, String email, String age, String country, String birthday, boolean state) {
		super(firstname, lastname, fullname, identitynumber, phonenumber, address, email, age, country, birthday,state,created_at,deleted_at,code);
		this.setId(id);
		this.type = type;
		this.id_department = id_department;
		this.title = title;
		this.urlImage = urlImage;
	}	
	public Employee(String type,String title, int id_department,String urlImage,String firstname, String lastname, String fullname, String identitynumber, String phonenumber,String address, String email, String age, String country, String birthday, boolean state) {
		super(firstname, lastname, fullname, identitynumber, phonenumber, address, email, age, country, birthday, state);
		this.urlImage=urlImage;
		this.title=title;
		this.type = type;
		this.id_department = id_department;
	}
	public Employee() {
		super();	
	}	
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getUrlImage() {
		return urlImage;
	}	
	public void setUrlImage(String urlImage) {
		this.urlImage = urlImage;
	}	
	public String getType() {
		return type;
	}	
	public void setType(String type) {
		this.type = type;
	}	
	public int getIdDepartment() {
		return id_department;
	}	
	public void setIdDepartment(int id_department) {
		this.id_department = id_department;
	}
	@Override public String toString() {
		return id_department+";"+type+";"+urlImage+";"+title+toStringBusinessSubject();
	}
}
