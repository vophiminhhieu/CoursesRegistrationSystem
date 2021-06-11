package BUS;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import Application.Data;
import DAL.DAO.UserDao;
import DAL.POJO.User;

public class savePasswordChange {
	private String oldPassword;
	private String newPassword;
	private String retypeNewPassword;
	private Data data = new Data();
	UserDao userDao=new UserDao();

	public void save() {
		userDao.updateDao(data.id, newPassword);
	}
	public savePasswordChange(String _old,String _new,String _retype){
		oldPassword=_old;
		newPassword=_new;
		retypeNewPassword=_retype;
	}
	public int checkSave() {
		if(!oldPassword.equals(data.pass)) {
			return 1;
		}
		else if(!newPassword.equals(retypeNewPassword)) {
			return 2;
		}
		else if(newPassword.equals(oldPassword)) {
			return 3;
		}
		return 0;
	}
}
