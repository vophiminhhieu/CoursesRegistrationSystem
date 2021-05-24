package DTO.POJO;

public class Classroom {
	private String name;
	private String nameSemester;
	private int yearSemester;
	private int numbersOfMale;
	private int numbersOfFemale;
	
	public String getNameSemester() {
		return nameSemester;
	}
	public void setNameSemester(String nameSemester) {
		this.nameSemester = nameSemester;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getYearSemester() {
		return yearSemester;
	}
	public void setYearSemester(int yearSemester) {
		this.yearSemester = yearSemester;
	}
	public int getNumbersOfMale() {
		return numbersOfMale;
	}
	public void setNumbersOfMale(int numbersOfMale) {
		this.numbersOfMale = numbersOfMale;
	}
	public int getNumbersOfFemale() {
		return numbersOfFemale;
	}
	public void setNumbersOfFemale(int numbersOfFemale) {
		this.numbersOfFemale = numbersOfFemale;
	}
}
