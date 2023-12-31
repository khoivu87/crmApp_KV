/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package dto;

public class RoleDto {
	private int id;
	private String name;
	private String desc;
	
	public RoleDto(int id, String name, String desc) {
		super();
		this.id = id;
		this.name = name;
		this.desc = desc;
	}

	public RoleDto() {
		super();
	}
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDesc() {
		return desc;
	}
	public void setDesc(String desc) {
		this.desc = desc;
	}
}
