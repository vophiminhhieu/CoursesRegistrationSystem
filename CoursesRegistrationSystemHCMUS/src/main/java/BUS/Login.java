package BUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import DAL.DAO.UserDao;
import DAL.POJO.User;
import GUI.LoginFrame;

public class Login {
	LoginFrame fr;
	UserDao userDao=new UserDao();
	private ActionListener actionListenerLogin = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(!fr.isRobotIsCheck) {
				fr.refreshCenterPanelWithRobot();
			}
			else {
				if(checkLogin(fr.getUsername(),fr.getPassword())==1){
					System.out.print("Success");
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
		User test=userDao.getStudent(_username);
		if(test==null) {
			fr.refreshCenterPanelWithUsername();
			return 0;
		}
		if(test.getPassword().equals(password)) {
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
		userDao.prepare();
	}
}
