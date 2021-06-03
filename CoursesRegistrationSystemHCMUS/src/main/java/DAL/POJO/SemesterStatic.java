package DAL.POJO;


import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="semesterstatic")

public class SemesterStatic {
	  @Embeddable
	  public static class Pk implements Serializable {
		    private static final long serialVersionUID = 1L;
		    @Column(name = "nameSemester")
		    private String name;
		    @Column(name = "yearSemester")
		    private long year;
		    
		    public Pk(String name, long year) {
				super();
				this.name = name;
				this.year = year;
			}
			public Pk() {
				super();
				// TODO Auto-generated constructor stub
			}
			public String getName() {
				return name;
			}
			public void setName(String name) {
				this.name = name;
			}
			public long getYear() {
				return year;
			}
			public void setYear(long year) {
				this.year = year;
			}
		    @Override
		    public String toString() {
		      return "Pk [nameSemester=" + name + ", yearSemester=" + year + "]";
		    }
	  }
	  

		@EmbeddedId
		private Pk pk;
		@Column(name="startDate")
		private String start_Date;
		@Column(name="endDate")
		private String end_Date;
	public SemesterStatic() {
		super();
		// TODO Auto-generated constructor stub
	}
	public SemesterStatic(Pk pk, String start_Date, String end_Date) {
		super();
		this.pk = pk;
		this.start_Date = start_Date;
		this.end_Date = end_Date;
	}
	public SemesterStatic(Semester.Pk pk, String start_Date, String end_Date) {
		super();
		this.pk = new Pk(pk.getName(),pk.getYear());
		this.start_Date = start_Date;
		this.end_Date = end_Date;
	}
	public Pk getPk() {
		return pk;
	}
	public void setPk(Pk pk) {
		this.pk = pk;
	}
	public String getStart_Date() {
		return start_Date;
	}
	public void setStart_Date(String start_Date) {
		this.start_Date = start_Date;
	}
	public String getEnd_Date() {
		return end_Date;
	}
	public void setEnd_Date(String end_Date) {
		this.end_Date = end_Date;
	}
	  
	
	  
	
}





