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
@Table(name="regisCourse")

public class regisCourse {
	  @Embeddable
	  public static class Pk implements Serializable {
		    private static final long serialVersionUID = 1L;
		    @Column(name = "idStudent")
		    private long idStudent;
		    @Column(name = "idCourse")
		    private String idCourse;
			public Pk() {
				super();
				// TODO Auto-generated constructor stub
			}
			public Pk(long idStudent, String idCourse) {
				super();
				this.idStudent = idStudent;
				this.idCourse = idCourse;
			}
			public long getIdStudent() {
				return idStudent;
			}
			public void setIdStudent(long idStudent) {
				this.idStudent = idStudent;
			}
			public String getIdCourse() {
				return idCourse;
			}
			public void setIdCourse(String idCourse) {
				this.idCourse = idCourse;
			}
		    
	  }
		@EmbeddedId
		private Pk pk;
		public regisCourse() {
			super();
			// TODO Auto-generated constructor stub
		}
		public regisCourse(Pk pk) {
			super();
			this.pk = pk;
		}
		public Pk getPk() {
			return pk;
		}
		public void setPk(Pk pk) {
			this.pk = pk;
		}
		
	
	  
	
	  
	
}





