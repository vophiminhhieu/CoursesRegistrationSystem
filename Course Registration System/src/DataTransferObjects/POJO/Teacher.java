package DataTransferObjects.POJO;

public class Teacher extends Person {
	private String faculty;
	private String startDateForTeach;
	public String getFaculty() {
		return faculty;
	}
	public void setFaculty(String faculty) {
		this.faculty = faculty;
	}
	public String getStartDateForTeach() {
		return startDateForTeach;
	}
	public void setStartDateForTeach(String startDateForTeach) {
		this.startDateForTeach = startDateForTeach;
	}
}
