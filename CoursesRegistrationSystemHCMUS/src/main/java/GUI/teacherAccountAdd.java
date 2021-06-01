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

import DAL.DAO.TeacherDao;
import DAL.DAO.UserDao;
import DAL.POJO.Teacher;
import DAL.POJO.User;

public class teacherAccountAdd extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	

	private JTextField textSearch = new JTextField();
	private JPanel textPanel = new JPanel(new BorderLayout());
	private JLabel label = new JLabel("Input Teacher ID : ");
	private Icon searchIcon = new ImageIcon("image/Menu/Teacher/Account/searchicon.png");
	private JButton searchBtn = new JButton(searchIcon);
	private Icon saveIcon = new ImageIcon("image/Menu/Dashboard/myProfile/save.png");
	private JButton saveBtn = new JButton(saveIcon);		
	private JLabel nameLabel 		= new JLabel("Name : ");	private JLabel birthDateLabel 	= new JLabel("BirthDate : ");
	private JLabel birthPlaceLabel  = new JLabel("BirthPlace : ");	private JLabel sexLabel 		= new JLabel("Sex : ");
	private JLabel addressLabel 	= new JLabel("Address : ");	private JLabel phoneLabel 		= new JLabel("Phone : ");
	private JLabel emailLabel 		= new JLabel("Email : ");	private JLabel majorLabel 		= new JLabel("Major :");
	private JLabel startDateLabel 	= new JLabel("Start Date : ");	private JLabel error 			= new JLabel("*");
	private JLabel error1 			= new JLabel("*");	private JLabel errorMain = new JLabel();
	private TeacherDao teacher = new TeacherDao();	private Teacher JTeacher = new Teacher();
	private JTextField nameEdit = new JTextField();			private JPanel nameEditPanel = new JPanel(new BorderLayout());
	private JTextField birthDateEdit = new JTextField();	private JPanel birthDateEditPanel = new JPanel(new BorderLayout());
	private JTextField birthPlaceEdit = new JTextField();	private JPanel birthPlaceEditPanel = new JPanel(new BorderLayout());
	private JTextField sexEdit = new JTextField();			private JPanel sexEditPanel = new JPanel(new BorderLayout());
	private JTextField addressEdit = new JTextField();		private JPanel addressEditPanel = new JPanel(new BorderLayout());
	private JTextField phoneEdit = new JTextField();		private JPanel phoneEditPanel = new JPanel(new BorderLayout());
	private JTextField emailEdit = new JTextField();		private JPanel emailEditPanel = new JPanel(new BorderLayout());
	private JTextField majorEdit = new JTextField();		private JPanel majorEditPanel = new JPanel(new BorderLayout());
	private JTextField startDateEdit = new JTextField();	private JPanel startDateEditPanel = new JPanel(new BorderLayout());
	private UserDao userDao = new UserDao();	private User user = new User();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");	searchBtn.setBounds(260,10,20,20);	error1.setForeground(Color.red);	error.setForeground(Color.red);
		textPanel.add(textSearch);	textPanel.setBounds(155,10,100,20);			label.setFont(new Font("Verdana", Font.PLAIN, 14));	label.setBounds(0,10,150,20);
		nameLabel.setBounds(10,60,100,20);	birthDateLabel.setBounds(10,85,100,20);	birthPlaceLabel.setBounds(10,110,100,20);
		sexLabel.setBounds(10,135,100,20);	addressLabel.setBounds(10,160,100,20);	phoneLabel.setBounds(10,185,100,20);	emailLabel.setBounds(10,210,100,20);
		majorLabel.setBounds(10,235,100,20); startDateLabel.setBounds(10,260,100,20);	nameEditPanel.setBounds(110,60,280,20);	birthDateEditPanel.setBounds(110,85,280,20);
		birthPlaceEditPanel.setBounds(110,110,280,20);		sexEditPanel.setBounds(110,135,280,20);		addressEditPanel.setBounds(110,160,280,20);	error.setBounds(315,85,15,20);
		phoneEditPanel.setBounds(110,185,280,20);			emailEditPanel.setBounds(110,210,280,20);	majorEditPanel.setBounds(110,235,280,20);
		startDateEditPanel.setBounds(110,260,280,20);		error1.setBounds(315,260,15,20);	errorMain.setBounds(285, 10, 100, 20);
		nameEditPanel.add(nameEdit);	birthDateEditPanel.add(birthDateEdit);	
		birthPlaceEditPanel.add(birthPlaceEdit);							sexEditPanel.add(sexEdit);	
		addressEditPanel.add(addressEdit);	phoneEditPanel.add(phoneEdit);	emailEditPanel.add(emailEdit);
		majorEditPanel.add(majorEdit);		startDateEditPanel.add(startDateEdit);		saveBtn.setBounds(400,240,95,29);
	
	}

	private void showAll(boolean show) {
		nameEditPanel.setVisible(show);	birthDateEditPanel.setVisible(show);	birthPlaceEditPanel.setVisible(show);
		sexEditPanel.setVisible(show);	phoneEditPanel.setVisible(show);	emailEditPanel.setVisible(show);
		addressEditPanel.setVisible(show);	majorEditPanel.setVisible(show);	startDateEditPanel.setVisible(show);
		saveBtn.setVisible(show);
	}
	private ActionListener actionChangeSearch = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			try {
				JTeacher.setId(Long.parseLong(textSearch.getText()));
				JTeacher = teacher.getTeacher(JTeacher.getId());
				if(JTeacher == null) {
					errorMain.setText("");
					errorMain.setVisible(false);
					showAll(true);
				}
				else {
					errorMain.setText("Existed ID !!");
					errorMain.setVisible(true);
				}
				JTeacher = new Teacher();
				JTeacher.setId(Long.parseLong(textSearch.getText()));
			}
			catch(Exception exp) {
				errorMain.setText("Error ID");
				errorMain.setVisible(true);
			}
		}
	};
	private void save() {
		JTeacher.setName(nameEdit.getText());		JTeacher.setBirthDate(birthDateEdit.getText());		JTeacher.setBirthPlace(birthPlaceEdit.getText());
		JTeacher.setSex(sexEdit.getText());		JTeacher.setPhone(phoneEdit.getText());		JTeacher.setEmail(emailEdit.getText());
		JTeacher.setAddress(addressEdit.getText());		JTeacher.setMajor(majorEdit.getText());		JTeacher.setStartDate(startDateEdit.getText());
	}
	private boolean checkBirthDate() {
		try {		
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(birthDateEdit.getText());
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	private boolean checkStartDate() {
		try {		
			Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(startDateEdit.getText());
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	private boolean checkYear() {
		try {		
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(birthDateEdit.getText());
			Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(startDateEdit.getText());
			if(date2.getYear()-date1.getYear()<18) {
				return false;
			}
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	private ActionListener actionChangeSave = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			save();
			user.setId(JTeacher.getId());
			user.setPassword("123456");
			userDao.saveUser(user);
			teacher.saveTeacher(JTeacher);
			showAll(false);
		}
	};
	public teacherAccountAdd(ActionListener obj) {

		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(textPanel);	add(label);	add(searchBtn); add(nameLabel); add(birthDateLabel);	add(birthPlaceLabel);
		add(sexLabel);	add(phoneLabel);	add(emailLabel);	add(majorLabel);	add(startDateLabel); add(addressLabel);
		searchBtn.addActionListener(actionChangeSearch);	add(saveBtn); saveBtn.addActionListener(actionChangeSave);
		add(nameEditPanel); add(birthDateEditPanel); add(birthPlaceEditPanel); add(sexEditPanel); add(phoneEditPanel);
		add(addressEditPanel); add(emailEditPanel); add(startDateEditPanel); add(majorEditPanel);	showAll(false);
		add(error);	add(error1); error.setVisible(false);error1.setVisible(false);	add(errorMain);
	
	}
}
