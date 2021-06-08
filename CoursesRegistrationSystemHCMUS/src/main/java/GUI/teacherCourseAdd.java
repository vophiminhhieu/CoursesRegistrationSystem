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




public class teacherCourseAdd extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon addIcon = new ImageIcon("image/Menu/Dashboard/userAccount/save.png");
	private JButton addBtn = new JButton(addIcon);
	private JLabel idSubjectLabel = new JLabel("Input Subject ID: ");
	private JLabel idTeacherLabel = new JLabel("Input Subject's Teacher ID: ");
	private JLabel nameTeacherLabel = new JLabel("Input This Teacher Name: ");
	private JLabel nameClassLabel = new JLabel("Input Study Class: ");
	private JLabel dayLabel = new JLabel("Choose Day Study: ");
	private JLabel timeLabel =  new JLabel("Choose Time Study: ");
	private JTextField idSubject= new JTextField();	private JPanel idSubjectPanel = new JPanel(new BorderLayout());
	private JTextField idTeacher= new JTextField();	private JPanel idTeacherPanel = new JPanel(new BorderLayout());
	private JTextField nameTeacher= new JTextField();	private JPanel nameTeacherPanel = new JPanel(new BorderLayout());
	private JTextField nameClass= new JTextField();	private JPanel nameClassPanel = new JPanel(new BorderLayout());
	String []dayChoice = {"Monday","Tuesday","Wednesday","Thursday","Friday","Saturday","Sunday"};
	private JComboBox day = new JComboBox(dayChoice);	private JPanel dayPanel = new JPanel(new BorderLayout());
	String []timeChoice = {"7:30 - 9:30","9:30 - 11:30","13:30 - 15:30","15:30 - 17:30"};
	private JComboBox time = new JComboBox(timeChoice);	private JPanel timePanel = new JPanel(new BorderLayout());
	private JLabel error = new JLabel();
	private SemesterStaticDao smtDao = new SemesterStaticDao();
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
		backBtn.setBounds(400, 220, 95, 29);	backBtn.setActionCommand("Back");	addBtn.setBounds(295,220,95,29);
		idSubjectPanel.setBounds(230,30,180,20);	idSubjectPanel.add(idSubject);		idSubjectLabel.setBounds(30,30,200,20);
		idTeacherPanel.setBounds(230,60,180,20);	idTeacherPanel.add(idTeacher);		idTeacherLabel.setBounds(30,60,200,20);
		nameTeacherPanel.setBounds(230,90,180,20);	nameTeacherPanel.add(nameTeacher);		nameTeacherLabel.setBounds(30,90,200,20);
		nameClassPanel.setBounds(230,120,180,20);	nameClassPanel.add(nameClass);		nameClassLabel.setBounds(30,120,200,20);
		dayPanel.setBounds(230,150,180,20);	dayPanel.add(day);		dayLabel.setBounds(30,150,200,20);
		timePanel.setBounds(230,180,180,20);	timePanel.add(time);		timeLabel.setBounds(30,180,200,20);
		error.setBounds(30,220,200,20);
	}
	void showAll(boolean show) {
		addBtn.setVisible(show);
		idSubjectPanel.setVisible(show);
		idSubjectLabel.setVisible(show);
		idTeacherPanel.setVisible(show);
		idTeacherLabel.setVisible(show);
		nameTeacherPanel.setVisible(show);
		nameTeacherLabel.setVisible(show);
		nameClassLabel.setVisible(show);
		nameClassPanel.setVisible(show);
		dayPanel.setVisible(show);
		dayLabel.setVisible(show);
		timePanel.setVisible(show);
		timeLabel.setVisible(show);
	}
	private ActionListener actionChangeAdd = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(idSubject.getText().equals("")||idTeacher.getText().equals("")||nameTeacher.getText().equals("")
					||nameClass.getText().equals("")) {
				error.setText("NULL INPUT !!");
			}
			else {
				SubjectDao subDao= new SubjectDao();
				Subject t = subDao.getSubject(idSubject.getText());
				if(t==null) {
					error.setText("Not Found Subject !!");
				}
				else {
					CourseDao courseDao = new CourseDao();
					Course course = courseDao.getCourse(idSubject.getText());
					if(course!=null) {
						error.setText("Existed Course !!");
					}
					else {
						Subject sbj = subDao.getSubject(idSubject.getText());
						Course courseSave = new Course();
						courseSave.setNameSubject(sbj.getName());
						courseSave.setCredits(sbj.getCredits());
						courseSave.setIdSubject(idSubject.getText());
						courseSave.setIdTeacher(idTeacher.getText());
						courseSave.setNameTeacher(nameTeacher.getText());
						courseSave.setNameClass(nameClass.getText());
						courseSave.setDay(String.valueOf(day.getItemAt(day.getSelectedIndex())));
						if(String.valueOf(time.getItemAt(time.getSelectedIndex())).equals("7:30 - 9:30")) {
							courseSave.setTime("ca1");
						}
						else if(String.valueOf(time.getItemAt(time.getSelectedIndex())).equals("9:30 - 11:30")) {
							courseSave.setTime("ca2");
						}
						else if(String.valueOf(time.getItemAt(time.getSelectedIndex())).equals("13:30 - 15:30")) {
							courseSave.setTime("ca3");
						}
						else if(String.valueOf(time.getItemAt(time.getSelectedIndex())).equals("15:30 - 17:30")) {
							courseSave.setTime("ca4");
						}
						courseDao.saveCourse(courseSave);
						error.setText("Saved Success !!");
					}
				}
			}
		}
	};
	public teacherCourseAdd(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		addBtn.addActionListener(actionChangeAdd);	add(idSubjectPanel);	add(idSubjectLabel);
		add(addBtn);	add(error);	add(idTeacherPanel); add(idTeacherLabel); add(nameTeacherPanel); add(nameTeacherLabel);
		add(nameClassPanel); add(nameClassLabel); add(dayPanel); add(dayLabel); add(timePanel); add(timeLabel);
		add(error);
	}
}
