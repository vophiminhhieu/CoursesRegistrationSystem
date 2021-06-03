package DAL.POJO;


import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="class")

public class Classs {
	@Id
	@Column(name="id")
	private String id; 
	@Column(name="nameClass")
	private String name;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Classs() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Classs(String id, String name) {
		super();
		this.id = id;
		this.name = name;
	}
	
}
