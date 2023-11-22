/**
 * Project: CRM
 * Auther: Vu Kim Khoi
 */
package entity;

public class Status {
	private int id;
	private String name;
	
	public Status() {
		super();
	}

	public Status(int id, String name) {
		super();
		this.id = id;
		this.name = name;
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
}
