package DTO.POJO;

public class Student extends Person {
	private String faculty;
	private String startOfLearn;
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getStartOfLearn() {
		return startOfLearn;
	}
	public void setStartOfLearn(String startOfLearn) {
		this.startOfLearn = startOfLearn;
	}
}
