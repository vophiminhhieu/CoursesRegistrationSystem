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

import DAL.POJO.Semester.Pk;


@Entity
@Table(name="joinclass")

public class JoinClass {

	  @Embeddable
	  public static class Pk implements Serializable {
		    private static final long serialVersionUID = 1L;
		    @Column(name = "idClass")
		    private String idClass;
		    @Column(name = "idStudent")
		    private long idStudent;
			public String getIdClass() {
				return idClass;
			}
			public void setIdClass(String idClass) {
				this.idClass = idClass;
			}
			public long getIdStudent() {
				return idStudent;
			}
			public void setIdStudent(long idStudent) {
				this.idStudent = idStudent;
			}
			public Pk(String idClass, long idStudent) {
				super();
				this.idClass = idClass;
				this.idStudent = idStudent;
			}
			public Pk() {
				super();
				// TODO Auto-generated constructor stub
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
		public JoinClass(Pk pk, boolean done) {
			super();
			this.pk = pk;
			this.done = done;
		}
		public JoinClass() {
			super();
			// TODO Auto-generated constructor stub
		}
		  
	
}
