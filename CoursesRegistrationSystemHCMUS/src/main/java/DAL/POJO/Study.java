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
@Table(name="study")

public class Study {
	  @Embeddable
	  public static class Pk implements Serializable {
		    private static final long serialVersionUID = 1L;
		    @Column(name = "idStudent")
		    private long idStudent;
		    @Column(name = "idSubject")
		    private String idSubject;
			public Pk(long idStudent, String idSubject) {
				super();
				this.idStudent = idStudent;
				this.idSubject = idSubject;
			}
			public Pk() {
				super();
				// TODO Auto-generated constructor stub
			}
			public long getIdStudent() {
				return idStudent;
			}
			public void setIdStudent(long idStudent) {
				this.idStudent = idStudent;
			}
			public String getIdSubject() {
				return idSubject;
			}
			public void setIdSubject(String idSubject) {
				this.idSubject = idSubject;
			}
		    
	  }

		@EmbeddedId
		private Pk pk;
		@Column(name="done")
		private boolean done;
		public Pk getPk() {
			return pk;
		}
		public void setPk(Pk pk) {
			this.pk = pk;
		}
		public boolean getDone() {
			return done;
		}
		public void setDone(boolean done) {
			this.done = done;
		}
		public Study(Pk pk, boolean done) {
			super();
			this.pk = pk;
			this.done = done;
		}
		public Study() {
			super();
			// TODO Auto-generated constructor stub
		}
		
	
	  
	
}





