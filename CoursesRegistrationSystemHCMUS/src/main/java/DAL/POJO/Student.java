package DAL.POJO;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="student")

public class Student {
	@Id
	@Column(name="id")
	private long id;
	@Column(name="nameStudent")
	private String name;
	@Column(name="birthdate")
	private String birthDate;
	@Column(name="sex")
	private String sex;
	@Column(name="birthplace")
	private String birthPlace;
	@Column(name="address")
	private String address;
	@Column(name="phone")
	private String phone;
	@Column(name="email")
	private String email;
	@Column(name="major")
	private String major;
	@Column(name="startDate")
	private String startDate;

	public Student() {
		super();
		// TODO Auto-generated constructor stub
	}
	public Student(long id, String name, String birthDate, String sex, String birthPlace, String address, String phone,
			String email, String major, String startDate) {
		super();
		this.id = id;
		this.name = name;
		this.birthDate = birthDate;
		this.sex = sex;
		this.birthPlace = birthPlace;
		this.address = address;
		this.phone = phone;
		this.email = email;
		this.major = major;
		this.startDate = startDate;
	}
	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getBirthDate() {
		return birthDate;
	}
	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}
	public String getSex() {
		return sex;
	}
	public void setSex(String sex) {
		this.sex = sex;
	}
	public String getBirthPlace() {
		return birthPlace;
	}
	public void setBirthPlace(String birthPlace) {
		this.birthPlace = birthPlace;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public String getMajor() {
		return major;
	}
	public void setMajor(String major) {
		this.major = major;
	}
	public String getStartDate() {
		return startDate;
	}
	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}
	
}
