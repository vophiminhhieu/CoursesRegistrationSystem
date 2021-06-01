package BUS;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;

import Application.Data;
import DAL.DAO.StudentDao;
import DAL.DAO.TeacherDao;
import DAL.POJO.Student;
import DAL.POJO.Teacher;

public class saveGenerateInf {
	private Data data=new Data();
	private String name;
	private String birthDate;
	private String sex;
	private String birthPlace;
	private String address;
	private String phone;
	private String email;
	private String major;
	private String startDate;
	public saveGenerateInf(String name, String birthDate, String sex, String birthPlace, String address, String phone,
			String email, String major, String startDate) {
		super();
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
	
	public String getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(String birthDate) {
		this.birthDate = birthDate;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public saveGenerateInf() {
		super();
		// TODO Auto-generated constructor stub
	}

	public boolean checkBirthDate() {
		try {		
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	public boolean checkStartDate() {
		try {		
			Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	public boolean checkYear() {
		try {		
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(birthDate);
			Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(startDate);
			if(date2.getYear()-date1.getYear()<18) {
				return false;
			}
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	public void save() {
		if(data.getRole().equals("Sinh vien")) {
			StudentDao stdDao = new StudentDao();
			Student student = new Student(data.id,name,birthDate,sex,birthPlace,address,phone,email,major,startDate);
			stdDao.deleteStudent(data.id);
			stdDao.saveStudent(student);
			data.setStudent(student);
		}
		else if(data.getRole().equals("Giao vien")) {
			TeacherDao tchDao= new TeacherDao();
			Teacher teacher =new Teacher (data.id,name,birthDate,sex,birthPlace,address,phone,email,major,startDate);
			tchDao.deleteTeacher(data.id);
			tchDao.saveTeacher(teacher);
			data.setTeacher(teacher);
		}
	}
}
