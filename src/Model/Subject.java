package Model;

public class Subject {
	private int id;
	private String firstname;
	private String lastname;
	private String fullname;
	private String identitynumber;
    private String phonenumber;
    private String address;
    private String email;
    private String age;
    private String country;
    private String birthday;
	private boolean state;
	
	public Subject() {
	}
	
	public Subject( String firstname, String lastname, String fullname, String identitynumber,
			String phonenumber, String address, String email, String age, String country, String birthday,
			boolean state) {
		this.firstname = firstname;
		this.lastname = lastname;
		this.fullname = fullname;
		this.identitynumber = identitynumber;
		this.phonenumber = phonenumber;
		this.address = address;
		this.email = email;
		this.age = age;
		this.country = country;
		this.birthday = birthday;
		this.state = state;
	}
	
	public Subject(int id, String firstname, String lastname, String fullname, String identitynumber,
			String phonenumber, String address, String email, String age, String country, String birthday,
			boolean state) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.fullname = fullname;
		this.identitynumber = identitynumber;
		this.phonenumber = phonenumber;
		this.address = address;
		this.email = email;
		this.age = age;
		this.country = country;
		this.birthday = birthday;
		this.state = state;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getFullname() {
		return fullname;
	}
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	public String getIdentitynumber() {
		return identitynumber;
	}
	public void setIdentitynumber(String identitynumber) {
		this.identitynumber = identitynumber;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public void setPhonenumber(String phonenumber) {
		this.phonenumber = phonenumber;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getAge() {
		return age;
	}
	public void setAge(String age) {
		this.age = age;
	}
	public String getCountry() {	
		return country;
	}
	public void setCountry(String country) {
		this.country = country;
	}
	public String getBirthday() {
		return birthday;
	}
	public void setBirthday(String birthday) {
		this.birthday = birthday;
	}	
	
	public boolean getState() {
		return state;
	}	
	public void setState(boolean state) {
		this.state = state;
	}

	public String toStringSubject() {
		return ";"+firstname+";"+lastname+";"+fullname+";"+identitynumber+";"+phonenumber+";"+address+";"+email+";"+age+";"+country+";"+birthday+";"+(state?"+":"-");
	}
	
}
