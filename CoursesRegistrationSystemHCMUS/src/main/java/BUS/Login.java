package BUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;

import Application.Data;
import DAL.DAO.ClassDao;
import DAL.DAO.JoinClassDao;
import DAL.DAO.SemesterDao;
import DAL.DAO.StudentDao;
import DAL.DAO.SubjectDao;
import DAL.DAO.TeacherDao;
import DAL.DAO.UserDao;
import DAL.POJO.Semester;
import DAL.POJO.Semester.Pk;
import DAL.POJO.Student;
import DAL.POJO.Subject;
import DAL.POJO.Teacher;
import DAL.POJO.User;
import GUI.LoginFrame;
import GUI.MenuFrame;

public class Login {
	LoginFrame fr;
	UserDao userDao=new UserDao();
	Data data = new Data();
	private ActionListener actionListenerLogin = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!fr.isRobotIsCheck) {
				fr.refreshCenterPanelWithRobot();
			}
			else {
				if(checkLogin(fr.getUsername(),fr.getPassword())==1){
					fr.setVisible(false);
					MenuFrame mnfr=new MenuFrame();
					data.id=Long.parseLong(fr.getUsername());
					data.pass=fr.getPassword();
					ClassDao dao = new ClassDao();
					dao.deleteClasss("CTT1");
				}
			}
			
		}
	};
	private ActionListener actionListenerMissing = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			fr.refreshCenterPanelWithMissing();
		}
	};
	public int checkLogin(String username,String password) {
		long _username;
		try {
			_username=Long.parseLong(username);
		}
		catch(Exception e) {
			fr.refreshCenterPanelWithUsername();
			return 0;
		}
		User test=userDao.getUser(_username);
		if(test==null) {
			fr.refreshCenterPanelWithUsername();
			return 0;
		}
		if(test.getPassword().equals(password)) {
			StudentDao studentDao =new StudentDao();
			TeacherDao teacherDao =new TeacherDao();
			Student student = studentDao.getStudent(_username);
			Teacher teacher = teacherDao.getTeacher(_username);
			if(student==null&&teacher==null) {
				System.out.println("ERROR !!");
			}
			else if(student!=null) {
				data.setStudent(student);
				data.setRole("Sinh vien");
			}
			else if(teacher!=null) {
				data.setTeacher(teacher);
				data.setRole("Giao vien");
			}
			return 1;
		}
		else {
			fr.refreshCenterPanelWithPassword();
			return 0;
		}
	}
	public Login() {
		userDao.prepare();
		fr=new LoginFrame();
		fr.eventLoginButton(actionListenerLogin);
		fr.eventMissingButton(actionListenerMissing);
	}
}
