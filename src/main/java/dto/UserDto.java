/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package dto;

public class UserDto {
	private int id; 
	private String email;
	private String password; 
	private String fullname;
	private String firstname;
	private String lastname;
	private String phone; 	
	private String address; 
	private String avatar; 
	private int role_id;
	private String roleName;
	
	public UserDto(int id, String email, String password, String fullname, String phone, String address, String avatar,
			int role_id, String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.fullname = fullname;
		this.phone = phone;
		this.address = address;
		this.avatar = avatar;
		this.role_id = role_id;
		this.roleName = roleName;
	}
	
	public UserDto(int id, String email, String password, String firstname, String lastname, String phone, String address, String avatar,
			int role_id, String roleName) {
		super();
		this.id = id;
		this.email = email;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phone = phone;
		this.address = address;
		this.avatar = avatar;
		this.role_id = role_id;
		this.roleName = roleName;
	}

	public int getRole_id() {
		return role_id;
	}

	public void setRole_id(int role_id) {
		this.role_id = role_id;
	}

	public UserDto() {
	
	}
	
	public int getId() {
		return id;
	}
	
	public void setId(int id) {
		this.id = id;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getFullname() {
		return fullname;
	}
	
	public void setFullname(String fullname) {
		this.fullname = fullname;
	}
	
	public String getFirstname() {
		return firstname;
	}
	
	public void setFirstname(String fullname) {	
		int idx = fullname.lastIndexOf(' ');
		if (idx == -1) {
			this.firstname = fullname;
		}else {
			this.firstname = fullname.substring(idx + 1);
		}
	}
	
	public String getLastname() {
		return lastname;
	}
	
	public void setLastname(String fullname) {
		int idx = fullname.lastIndexOf(' ');
		if (idx == -1) {
			this.lastname = "N/A";
		}else {
			this.lastname = fullname.substring(0, idx);
		}
	}
	
	public String getPhone() {
		return phone;
	}
	
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	public String getAddress() {
		return address;
	}
	
	public void setAddress(String address) {
		this.address = address;
	}
	
	public String getAvatar() {
		return avatar;
	}
	
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	
	public String getRoleName() {
		return roleName;
	}
	
	public void setRoleName(String roleName) {
		this.roleName = roleName;
	}
}
