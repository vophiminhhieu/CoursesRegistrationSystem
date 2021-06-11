package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import BUS.saveGenerateInf;
import DAL.DAO.TeacherDao;
import DAL.DAO.UserDao;
import DAL.POJO.Teacher;
import DAL.POJO.User;


public class teacherAccountSearch extends JPanel {

	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Teacher/Account/backsmall.png");
	private JButton backBtn = new JButton(backIcon);
	private JTextField textSearch = new JTextField();
	private JPanel textPanel = new JPanel(new BorderLayout());
	private JLabel label = new JLabel("Input Teacher ID : ");
	private Icon searchIcon = new ImageIcon("image/Menu/Teacher/Account/searchicon.png");
	private JButton searchBtn = new JButton(searchIcon);
	private Icon editIcon = new ImageIcon("image/Menu/Teacher/Account/editsmall.png");
	private JButton editBtn = new JButton(editIcon);
	private Icon removeIcon = new ImageIcon("image/Menu/Teacher/Account/removesmall.png");
	private JButton removeBtn = new JButton(removeIcon);
	private Icon resetIcon = new ImageIcon("image/Menu/Teacher/Account/resetsmall.png");
	private JButton resetBtn = new JButton(resetIcon);
	private JLabel id = new JLabel();				
	private JLabel nameLabel 		= new JLabel();	private JLabel birthDateLabel 	= new JLabel();
	private JLabel birthPlaceLabel  = new JLabel();	private JLabel sexLabel 		= new JLabel();
	private JLabel addressLabel 	= new JLabel();	private JLabel phoneLabel 		= new JLabel();
	private JLabel emailLabel 		= new JLabel();	private JLabel majorLabel 		= new JLabel();
	private JLabel startDateLabel 	= new JLabel();	private JLabel error 			= new JLabel("*");
	private JLabel error1 			= new JLabel("*");
	private JLabel log = new JLabel("Reset success. Pass: 123456");
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
	private boolean isShow=false;	
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(290, 10, 67, 21);		backBtn.setActionCommand("Back");		searchBtn.setBounds(260,10,20,20);	error1.setForeground(Color.red);	error.setForeground(Color.red);
		textPanel.add(textSearch);	textPanel.setBounds(155,10,100,20);			label.setFont(new Font("Verdana", Font.PLAIN, 14));	label.setBounds(0,10,150,20);
		id.setBounds(10,35,300,20);	nameLabel.setBounds(10,60,300,20);	birthDateLabel.setBounds(10,85,300,20);	birthPlaceLabel.setBounds(10,110,300,20);
		sexLabel.setBounds(10,135,300,20);	addressLabel.setBounds(10,160,300,20);	phoneLabel.setBounds(10,185,300,20);	emailLabel.setBounds(10,210,300,20);
		majorLabel.setBounds(10,235,300,20); startDateLabel.setBounds(10,260,300,20);	nameEditPanel.setBounds(315,60,150,20);	birthDateEditPanel.setBounds(315,85,150,20);
		birthPlaceEditPanel.setBounds(315,110,150,20);		sexEditPanel.setBounds(315,135,150,20);		addressEditPanel.setBounds(315,160,150,20);	error.setBounds(480,85,15,20);
		phoneEditPanel.setBounds(315,185,150,20);			emailEditPanel.setBounds(315,210,150,20);	majorEditPanel.setBounds(315,235,150,20);
		startDateEditPanel.setBounds(315,260,150,20);		error1.setBounds(490,260,15,20);
		nameEditPanel.add(nameEdit);	birthDateEditPanel.add(birthDateEdit);	
		birthPlaceEditPanel.add(birthPlaceEdit);							sexEditPanel.add(sexEdit);	
		addressEditPanel.add(addressEdit);	phoneEditPanel.add(phoneEdit);	emailEditPanel.add(emailEdit);
		majorEditPanel.add(majorEdit);		startDateEditPanel.add(startDateEdit);		editBtn.setBounds(370,10,67,21);
		removeBtn.setBounds(290,35,67,21); 	resetBtn.setBounds(370,35,89,21);
	}
	private void setTextAllNull() {
		id.setText("");	nameLabel.setText("");	birthDateLabel.setText("");	birthPlaceLabel.setText("");	sexLabel.setText("");
		phoneLabel.setText(""); 	emailLabel.setText(""); 	phoneLabel.setText(""); 	majorLabel.setText("");		startDateLabel.setText("");
	}
	public void set(String key) {
		long _key;
		try {
			_key=Long.parseLong(key);
		}
		catch(Exception e) {
			setTextAllNull();			showAll(false);			return;
		}
		JTeacher=teacher.getTeacher(_key);
		if(JTeacher==null) {
			setTextAllNull();	showAll(false);			return;
		}
		else {
			id.setText("Id : "+key); 	nameLabel.setText("Name : "+JTeacher.getName()); 	birthDateLabel.setText("BirthDate : "+JTeacher.getBirthDate());
			birthPlaceLabel.setText("BirthPlace : "+JTeacher.getBirthPlace()); sexLabel.setText("Sex : "+JTeacher.getSex());
			addressLabel.setText("Address : "+JTeacher.getAddress()); 	emailLabel.setText("Email : "+JTeacher.getEmail()); 	phoneLabel.setText("Phone : "+JTeacher.getPhone());
			majorLabel.setText("Major : "+JTeacher.getMajor()); 	startDateLabel.setText("StartDate : "+JTeacher.getStartDate());	showAll(true);
		}
	}
	private void showAll(boolean show) {
		nameEditPanel.setVisible(show);	birthDateEditPanel.setVisible(show);	birthPlaceEditPanel.setVisible(show);
		sexEditPanel.setVisible(show);	phoneEditPanel.setVisible(show);	emailEditPanel.setVisible(show);
		addressEditPanel.setVisible(show);	majorEditPanel.setVisible(show);	startDateEditPanel.setVisible(show);
		editBtn.setVisible(show);
	}
	private ActionListener actionChangeSearch = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			set(textSearch.getText());
			log.setVisible(false);
		}
	};
	private ActionListener actionChangeRemove = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			long _key;
			try {
				_key=Long.parseLong(textSearch.getText());
			}
			catch(Exception exp) {return;
			}
			JTeacher=teacher.getTeacher(_key);
			if(JTeacher!=null) {
				teacher.deleteTeacher(_key);
				UserDao userDao=new UserDao();
				userDao.deleteUser(_key);
				showAll(false);
			}
			log.setVisible(false);
		}
	};
	private ActionListener actionChangeReset = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			long _key;
			try {
				_key=Long.parseLong(textSearch.getText());
			}
			catch(Exception exp) {return;
			}
			UserDao userDao = new UserDao();
			User user = new User();
			user=userDao.getUser(_key);
			if(user!=null) {
				user.setPassword("123456");
			}
			Teacher tmp = teacher.getTeacher(_key);
			teacher.deleteTeacher(_key);
			userDao.deleteUser(_key);
			userDao.saveUser(user);
			teacher.saveTeacher(tmp);
			log.setBounds(155,35,100,20);
			log.setVisible(true);
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
	private ActionListener actionChangeEdit = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(checkBirthDate()==false) {
				error.setVisible(true);	error1.setVisible(false);
			}
			else if(checkStartDate()==false) {
				error.setVisible(false); error1.setVisible(true);
			}
			else if(checkYear()==false) {
				error.setVisible(true); error1.setVisible(true);
			}
			else {
				save();	teacher.deleteTeacher(JTeacher.getId());
				teacher.saveTeacher(JTeacher);	set(""+JTeacher.getId());	setVisible(false);	setVisible(true);
			}
			log.setVisible(false);
		}
	};
	public teacherAccountSearch(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(textPanel);	add(label);	add(searchBtn);	add(id); add(nameLabel); add(birthDateLabel);	add(birthPlaceLabel);
		add(sexLabel);	add(phoneLabel);	add(emailLabel);	add(majorLabel);	add(startDateLabel); add(addressLabel);
		searchBtn.addActionListener(actionChangeSearch);	add(editBtn); editBtn.addActionListener(actionChangeEdit);
		add(nameEditPanel); add(birthDateEditPanel); add(birthPlaceEditPanel); add(sexEditPanel); add(phoneEditPanel);
		add(addressEditPanel); add(emailEditPanel); add(startDateEditPanel); add(majorEditPanel);	showAll(false);
		add(error);	add(error1); error.setVisible(false);error1.setVisible(false);	add(removeBtn);	add(resetBtn);
		removeBtn.addActionListener(actionChangeRemove); resetBtn.addActionListener(actionChangeReset); add(log);
	}
}
