package GUI;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAL.DAO.CourseDao;
import DAL.DAO.RegistrationDao;
import DAL.DAO.SemesterStaticDao;
import DAL.DAO.SubjectDao;
import DAL.POJO.Course;
import DAL.POJO.Registration;
import DAL.POJO.SemesterStatic;
import DAL.POJO.Subject;




public class teacherCourseFind extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon searchIcon = new ImageIcon("image/Menu/Teacher/Course/search.png");
	private JButton searchBtn = new JButton(searchIcon);
	private Icon deleteIcon = new ImageIcon("image/Menu/Teacher/Course/delete.png");
	private JButton deleteBtn = new JButton(deleteIcon);
	private JLabel idSubjectLabel = new JLabel("Input Subject ID of Course : ");
	private JTextField idSubject= new JTextField();	private JPanel idSubjectPanel = new JPanel(new BorderLayout());
	private JLabel error = new JLabel();
	private SemesterStaticDao smtDao = new SemesterStaticDao();
	private CourseDao courseDao = new CourseDao();
	private JLabel nameSubject= new JLabel();
	private JLabel credits = new JLabel();
	private JLabel idTeacher = new JLabel();
	private JLabel nameTeacher = new JLabel();
	private JLabel nameClass = new JLabel();
	private JLabel day = new JLabel();
	private JLabel time = new JLabel();
	private String id;
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		RegistrationDao resDao = new RegistrationDao();
		List<Registration> res = resDao.getRegistration();
		boolean a =false;
		for(int i=0;i<res.size();i++) {
			if(res.get(i).getCurrent()==true) {
				a=true;
			}
		}
		if(a==false) {
			error.setText("You don't have registration");
			showAll(false);
			return;
		}
		backBtn.setBounds(400, 220, 95, 29);	backBtn.setActionCommand("Back");	searchBtn.setBounds(400,30,95,29);
		idSubjectPanel.setBounds(210,30,180,20);	idSubjectPanel.add(idSubject);		idSubjectLabel.setBounds(0,30,200,20);
		deleteBtn.setBounds(400,180,95,29); nameSubject.setBounds(10,55,400,20); credits.setBounds(10,80,400,20);
		idTeacher.setBounds(10,105,400,20); nameTeacher.setBounds(10,130,400,20); nameClass.setBounds(10,155,400,20);
		day.setBounds(10,180,200,20); time.setBounds(10,205,200,20);
		error.setBounds(30,220,200,20);
	}
	void showAll(boolean show) {
		searchBtn.setVisible(show);
		idSubjectPanel.setVisible(show);
		idSubjectLabel.setVisible(show);
	}
	private ActionListener actionChangeSearch = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			Course course = courseDao.getCourse(idSubject.getText());
			if(course == null) {
				error.setText("NOT FOUND !!");
			}
			else {
				nameSubject.setText("Name Subject: "+course.getNameSubject());
				credits.setText("Credits: "+String.valueOf(course.getCredits()));
				idTeacher.setText("ID Teacher: "+course.getIdTeacher());
				nameTeacher.setText("Name Teacher: "+course.getNameTeacher());
				nameClass.setText("Name Class: "+course.getNameClass());
				day.setText("Day Study: "+course.getDay());
				if(course.getTime().equals("ca1")) {
					time.setText("Time study: 7:30 - 9:30");
				}else if(course.getTime().equals("ca2")) {
					time.setText("Time study: 9:30 - 11:30");
				}else if(course.getTime().equals("ca3")) {
					time.setText("Time study: 13:30 - 15:30");
				}else if(course.getTime().equals("ca4")) {
					time.setText("Time study: 15:30 - 17:30");
				}
				id=course.getIdSubject();
				deleteBtn.setVisible(true);
			}
		}
	};
	private ActionListener actionChangeDelete = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			courseDao.deleteCourse(id);
			error.setText("Success !!!");
		}
	};
	
	public teacherCourseFind(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		searchBtn.addActionListener(actionChangeSearch);	add(idSubjectPanel);	add(idSubjectLabel);
		add(searchBtn);	add(error);	add(deleteBtn); deleteBtn.setVisible(false); add(nameSubject); add(credits);
		add(idTeacher); add(nameTeacher); add(nameClass); add(day); add(time);	add(error); deleteBtn.addActionListener(actionChangeDelete);
	}
}
