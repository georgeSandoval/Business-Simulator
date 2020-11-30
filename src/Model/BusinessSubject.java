package Model;

public class BusinessSubject extends Subject {
	private String code;
	private String created_at;
    private String deleted_at;   
    
    public BusinessSubject(String firstname, String lastname, String fullname, String identitynumber,String phonenumber, String address, String email, String age, String country, String birthday,boolean state,String created_at,String deleted_at,String code) {
		super(firstname, lastname, fullname,  identitynumber,phonenumber, address, email,  age,  country,  birthday,state);
		this.code=code;
		this.created_at=created_at;
		this.deleted_at=deleted_at;
	}
    
    public BusinessSubject(String firstname, String lastname, String fullname, String identitynumber,String phonenumber, String address, String email, String age, String country, String birthday,boolean state) {
		super(firstname, lastname, fullname,  identitynumber,phonenumber, address, email,  age,  country,  birthday,state);
		code="";
		created_at="";
		deleted_at="";
	}
    
	public BusinessSubject() {
		super();
	}

	public String getCode() {
		return code;
	}
	public void setCode(String code) {
		this.code = code;
	}
	public String getCreated_at() {
		return created_at;
	}
	public void setCreated_at(String created_at) {
		this.created_at = created_at;
	}
	public String getDeleted_at() {
		return deleted_at;
	}
	public void setDeleted_at(String deleted_at) {
		this.deleted_at = deleted_at;
	}
	
	public String toStringBusinessSubject() {
		return ";"+code+";"+created_at+";"+deleted_at+toStringSubject();
	}
}
