package GUI;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAL.DAO.RegistrationDao;
import DAL.DAO.SemesterDao;
import DAL.DAO.SemesterStaticDao;
import DAL.DAO.SubjectDao;
import DAL.DAO.TeacherDao;
import DAL.DAO.UserDao;
import DAL.POJO.Semester;
import DAL.POJO.Subject;
import DAL.POJO.Teacher;
import DAL.POJO.User;
import DAL.POJO.Semester.Pk;
import DAL.POJO.Registration;
import DAL.POJO.SemesterStatic;

public class teacherSemesterSet extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon editIcon = new ImageIcon("image/Menu/Dashboard/myProfile/edit.png");
	private JButton editBtn = new JButton(editIcon);
	private Icon removeIcon = new ImageIcon("image/Menu/Teacher/Account/removesmall.png");
	private JButton removeBtn = new JButton(removeIcon);
	private Icon setIcon = new ImageIcon("image/Menu/Teacher/Semester/setsmall.png");
	private JButton setBtn = new JButton(setIcon);
	private JLabel nameLabel = new JLabel("Input Semester Name: ");
	private JLabel yearLabel = new JLabel("Input Semester Year: ");
	private JLabel startDateLabel = new JLabel();
	private JLabel endDateLabel = new JLabel();
	private JTextField name= new JTextField();	private JPanel namePanel = new JPanel(new BorderLayout());
	private JTextField year= new JTextField();	private JPanel yearPanel = new JPanel(new BorderLayout());
	private JLabel error = new JLabel();
	private SemesterDao semesterDao = new SemesterDao();
	private Semester semester = new Semester();
	private SemesterStaticDao semesterStaticDao = new SemesterStaticDao();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");	editBtn.setBounds(295,200,95,29);
		namePanel.setBounds(230,30,180,20);	namePanel.add(name);		nameLabel.setBounds(30,30,200,20);
		yearPanel.setBounds(230,60,180,20);	yearPanel.add(year);		yearLabel.setBounds(30,60,200,20);
		startDateLabel.setBounds(30,90,300,20);		endDateLabel.setBounds(30,120,300,20);		error.setBounds(30,200,200,20);
		removeBtn.setBounds(400,160,67,21);	setBtn.setBounds(280,160,108,21);
	}

	private ActionListener actionChangeEdit = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(name.getText()==""||year.getText()=="") {
				error.setText("Null Input !!");
			}
			else {
				Pk pk =new Pk();
				try {
					pk.setYear(Long.parseLong(year.getText()));
					pk.setName(name.getText());
				}
				catch(Exception exp) {
					error.setText("Error Input !!");
					return;
				}
				semester = semesterDao.getSemester(pk);
				if(semester == null) {
					error.setText("Not Found Semester !!");
				}
				else {
					startDateLabel.setText("Start date for this Semester : "+semester.getStart_Date());
					endDateLabel.setText("End date for this Semester : "+semester.getEnd_Date());	error.setText("");
					showAll(true);
				}
			}
		}
	};
	private ActionListener actionChangeRemove = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			SemesterStatic semest = semesterStaticDao.getSemesterStatic().get(0);
			if(
				semest.getPk().getName().equals(semester.getPk().getName()) &&
				semest.getPk().getYear() == semester.getPk().getYear()) {
				error.setText("This semester is now !!");
			}
			else {
				RegistrationDao resDao = new RegistrationDao();
				Registration.Pk resPk = new Registration.Pk();
				resPk.setName(semester.getPk().getName());
				resPk.setYear(semester.getPk().getYear());
				resDao.deleteRegistration(resPk);
				semesterDao.deleteSemester(semester.getPk());
				error.setText("Remove Success !!");
			}
		}
	};
	private ActionListener actionChangeSet = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			semesterStaticDao.deleteSemesterStatic(semesterStaticDao.getSemesterStatic().get(0).getPk());
			SemesterStatic semesterStatic = new SemesterStatic(semester.getPk(),semester.getStart_Date(),semester.getEnd_Date());
			semesterStaticDao.saveSemesterStatic(semesterStatic);
			RegistrationDao daoRe = new RegistrationDao();
			daoRe.offAll();
			error.setText("Set now semester Success !!");
			
		}
	};
	void showAll(boolean show) {
		removeBtn.setVisible(show);	setBtn.setVisible(show);
	}
	public teacherSemesterSet(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		editBtn.addActionListener(actionChangeEdit);	add(namePanel);	add(nameLabel);	add(yearPanel);	add(yearLabel);
		add(startDateLabel);	add(endDateLabel);	add(editBtn);	add(error);	showAll(false); add(removeBtn);	add(setBtn);
		setBtn.addActionListener(actionChangeSet);	removeBtn.addActionListener(actionChangeRemove);
	}
}
