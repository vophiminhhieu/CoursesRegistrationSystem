package DAL.POJO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="subject")
public class Subject {
	@Id
	@Column(name="id")
	private String id;
	
	@Column(name="nameSubject")
	private String name;
	
	@Column(name="credits")
	private long credits;

	public Subject(String id, String name, long credits) {
		super();
		this.id = id;
		this.name = name;
		this.credits = credits;
	}

	
	public Subject() {
		super();
		// TODO Auto-generated constructor stub
	}


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

	public long getCredits() {
		return credits;
	}

	public void setCredits(long credits) {
		this.credits = credits;
	}
	
	
	
	

}
