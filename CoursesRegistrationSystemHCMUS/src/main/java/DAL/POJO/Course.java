package DAL.POJO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="course")
public class Course {
	@Id
	@Column(name="idSubject")
	private String idSubject;
	
	@Column(name="nameSubject")
	private String nameSubject;
	
	@Column(name="credits")
	private long credits;

	@Column(name="idTeacher")
	private String idTeacher;
	
	@Column(name="nameTeacher")
	private String nameTeacher;
	
	@Column(name="nameClass")
	private String nameClass;
	
	@Column(name="dayInWeek")
	private String day;
	
	@Column(name="timeInDay")
	private String time;

	public Course(String idSubject, String nameSubject, long credits, String idTeacher, String nameTeacher,
			String nameClass, String day, String time) {
		super();
		this.idSubject = idSubject;
		this.nameSubject = nameSubject;
		this.credits = credits;
		this.idTeacher = idTeacher;
		this.nameTeacher = nameTeacher;
		this.nameClass = nameClass;
		this.day = day;
		this.time = time;
	}

	public Course() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIdSubject() {
		return idSubject;
	}

	public void setIdSubject(String idSubject) {
		this.idSubject = idSubject;
	}

	public String getNameSubject() {
		return nameSubject;
	}

	public void setNameSubject(String nameSubject) {
		this.nameSubject = nameSubject;
	}

	public long getCredits() {
		return credits;
	}

	public void setCredits(long credits) {
		this.credits = credits;
	}

	public String getIdTeacher() {
		return idTeacher;
	}

	public void setIdTeacher(String idTeacher) {
		this.idTeacher = idTeacher;
	}

	public String getNameTeacher() {
		return nameTeacher;
	}

	public void setNameTeacher(String nameTeacher) {
		this.nameTeacher = nameTeacher;
	}

	public String getNameClass() {
		return nameClass;
	}

	public void setNameClass(String nameClass) {
		this.nameClass = nameClass;
	}

	public String getDay() {
		return day;
	}

	public void setDay(String day) {
		this.day = day;
	}

	public String getTime() {
		return time;
	}

	public void setTime(String time) {
		this.time = time;
	}
	
	
	

}
