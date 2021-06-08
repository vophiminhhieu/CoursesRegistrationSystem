package DAL.UTIL;


import java.util.Properties;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import DAL.POJO.JoinClass;
import DAL.POJO.Registration;
import DAL.POJO.Classs;
import DAL.POJO.Course;
import DAL.POJO.Semester;
import DAL.POJO.SemesterStatic;
import DAL.POJO.Student;
import DAL.POJO.Study;
import DAL.POJO.Subject;
import DAL.POJO.Teacher;
import DAL.POJO.User;
public class UserUtil {

	private static SessionFactory sessionFactory;
	public static SessionFactory getSessionFactory() {
		if (sessionFactory == null) {
			try {
				Configuration configuration = new Configuration();

				Properties settings = new Properties();
				settings.put(Environment.DRIVER, "com.mysql.cj.jdbc.Driver");
				settings.put(Environment.URL, "jdbc:mysql://localhost:3306/portal?useSSL=false");
				settings.put(Environment.USER, "root");
				settings.put(Environment.PASS, "992412snsn");
				settings.put(Environment.DIALECT, "org.hibernate.dialect.MySQL5Dialect");

				settings.put(Environment.SHOW_SQL, "true");

				settings.put(Environment.CURRENT_SESSION_CONTEXT_CLASS, "thread");

				settings.put(Environment.HBM2DDL_AUTO, "update");

				configuration.setProperties(settings);

				configuration.addAnnotatedClass(User.class);
				configuration.addAnnotatedClass(Student.class);
				configuration.addAnnotatedClass(Teacher.class);
				configuration.addAnnotatedClass(Subject.class);
				configuration.addAnnotatedClass(Semester.class);
				configuration.addAnnotatedClass(SemesterStatic.class);
				configuration.addAnnotatedClass(Classs.class);
				configuration.addAnnotatedClass(JoinClass.class);
				configuration.addAnnotatedClass(Study.class);
				configuration.addAnnotatedClass(Registration.class);
				configuration.addAnnotatedClass(Course.class);

				ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder()
						.applySettings(configuration.getProperties()).build();
				
				sessionFactory = configuration.buildSessionFactory(serviceRegistry);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		return sessionFactory;
	}
}
