package Model;

public class Department extends BusinessSubject {
	private int id_manager;		
	
	public Department(int id,int id_manager,String code,String created_at,String deleted_at,String firstname,String lastname,
			String fullname,String identitynumber,String phonenumber,String address,String email,String age,String country,String birthday,boolean state) {
		this.setId(id);
		this.id_manager=id_manager;
		this.setCode(code);
		this.setCreated_at(created_at);
		this.setDeleted_at(deleted_at);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setFullname(fullname);
		this.setIdentitynumber(identitynumber);
		this.setPhonenumber(phonenumber);
		this.setAddress(address);
		this.setEmail(email);
		this.setAge(age);
		this.setCountry(country);
		this.setBirthday(birthday);
		this.setState(state);
	}
	public Department(int id,int id_manager,
			String firstname, String lastname, String fullname, 
			String identitynumber, String phonenumber,String address,
			String email, String age, String country, String birthday, 
			boolean state) {
		super();
		this.id_manager=id_manager;
		this.setId(id);
		this.setFirstname(firstname);
		this.setLastname(lastname);
		this.setFullname(fullname);
		this.setIdentitynumber(identitynumber);
		this.setPhonenumber(phonenumber);
		this.setAddress(address);
		this.setEmail(email);
		this.setAge(age);
		this.setCountry(country);
		this.setBirthday(birthday);
	}

	public Department() {
	}
 
	public int getId_manager() {
		return id_manager;
	}

	public void setId_manager(int id_manager) {
		this.id_manager = id_manager;
	}
	@Override 
	public String toString() {
		return id_manager+toStringBusinessSubject();
	}
}
