package Application;

import DAL.POJO.Student;
import DAL.POJO.Teacher;

public class Data {
	public static long id;
	public static String pass;
	private static String role;
	private static Student student;
	private static Teacher teacher;
	public static String getRole() {
		return role;
	}
	public static void setRole(String role) {
		Data.role = role;
	}
	public static Student getStudent() {
		return student;
	}
	public static void setStudent(Student student) {
		Data.student = student;
	}
	public static Teacher getTeacher() {
		return teacher;
	}
	public static void setTeacher(Teacher teacher) {
		Data.teacher = teacher;
	}
	
}
