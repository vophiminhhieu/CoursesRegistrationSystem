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

import DAL.DAO.SemesterDao;
import DAL.DAO.SubjectDao;
import DAL.DAO.TeacherDao;
import DAL.DAO.UserDao;
import DAL.POJO.Semester;
import DAL.POJO.Subject;
import DAL.POJO.Teacher;
import DAL.POJO.User;
import DAL.POJO.Semester.Pk;

public class teacherSemesterAdd extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon addIcon = new ImageIcon("image/Menu/Dashboard/userAccount/save.png");
	private JButton addBtn = new JButton(addIcon);
	private JLabel nameLabel = new JLabel("Input Semester Name: ");
	private JLabel yearLabel = new JLabel("Input Semester Year: ");
	private JLabel startDateLabel = new JLabel("Input Semester Start Date: ");
	private JLabel endDateLabel = new JLabel("Input Semester End Date: ");
	private JTextField name= new JTextField();	private JPanel namePanel = new JPanel(new BorderLayout());
	private JTextField year= new JTextField();	private JPanel yearPanel = new JPanel(new BorderLayout());
	private JTextField startDate= new JTextField();	private JPanel startDatePanel = new JPanel(new BorderLayout());
	private JTextField endDate= new JTextField();	private JPanel endDatePanel = new JPanel(new BorderLayout());
	private JLabel error = new JLabel();
	private SemesterDao semesterDao = new SemesterDao();
	private Semester semester = new Semester();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");	addBtn.setBounds(295,200,95,29);
		namePanel.setBounds(230,30,180,20);	namePanel.add(name);		nameLabel.setBounds(30,30,200,20);
		yearPanel.setBounds(230,60,180,20);	yearPanel.add(year);		yearLabel.setBounds(30,60,200,20);
		startDatePanel.setBounds(230,90,180,20);	startDatePanel.add(startDate);		startDateLabel.setBounds(30,90,200,20);
		endDatePanel.setBounds(230,120,180,20);	endDatePanel.add(endDate);		endDateLabel.setBounds(30,120,200,20);
		error.setBounds(30,200,200,20);
	}

	private ActionListener actionChangeAdd = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(name.getText()==""||year.getText()==""||startDate.getText()==""||endDate.getText()=="") {
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
				try {
					Date test = new SimpleDateFormat("dd/MM/yyyy").parse(startDate.getText());
					test = new SimpleDateFormat("dd/MM/yyyy").parse(endDate.getText());
				}
				catch(Exception ex) {
					error.setText("Error Input !!");
					return;
				}
				semester = semesterDao.getSemester(pk);
				if(semester !=null) {
					error.setText("Existed Semester !!");
				}
				else {
					semester = new Semester(pk,startDate.getText(),endDate.getText());
					semesterDao.saveSemester(semester);	error.setText("Success !!!");
				}
			}
		}
	};
	public teacherSemesterAdd(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		addBtn.addActionListener(actionChangeAdd);	add(namePanel);	add(nameLabel);	add(yearPanel);	add(yearLabel);
		add(startDatePanel);	add(startDateLabel);	add(endDatePanel);	add(endDateLabel);	add(addBtn);	add(error);
	}
}
