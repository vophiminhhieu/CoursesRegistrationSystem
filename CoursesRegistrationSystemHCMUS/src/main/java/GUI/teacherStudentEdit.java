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
import java.util.List;

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

import DAL.DAO.StudentDao;
import DAL.DAO.StudyDao;
import DAL.POJO.Student;
import DAL.POJO.Study;
import DAL.POJO.Subject;


public class teacherStudentEdit extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon searchIcon = new ImageIcon("image/Menu/Teacher/Account/searchicon.png");
	private JButton searchBtn = new JButton(searchIcon);
	private Icon saveIcon = new ImageIcon("image/Menu/Dashboard/myProfile/save.png");
	private JButton saveBtn = new JButton(saveIcon);
	private JTextField text = new JTextField();	private JPanel textPanel = new JPanel(new BorderLayout());
	private JTextField name = new JTextField();	private JPanel namePanel= new JPanel(new BorderLayout());
	private JTextField birthDate =  new JTextField();	private JPanel birthDatePanel = new JPanel (new BorderLayout());
	private JTextField birthPlace = new JTextField(); 	private JPanel birthPlacePanel = new JPanel(new BorderLayout());
	private JTextField sex = new JTextField();	private JPanel sexPanel = new JPanel(new BorderLayout());
	private JTextField phone = new JTextField();	private JPanel phonePanel = new JPanel(new BorderLayout());
	private JTextField email = new JTextField();	private JPanel emailPanel = new JPanel(new BorderLayout());
	private JTextField address = new JTextField();	private JPanel addressPanel = new JPanel(new BorderLayout());
	private JTextField major = new JTextField(); 	private JPanel majorPanel = new JPanel(new BorderLayout());
	private JTextField startDate = new JTextField(); private JPanel startDatePanel = new JPanel(new BorderLayout());
	private JLabel idLabel = new JLabel("Input Student ID : ");
	private JLabel nameLabel = new JLabel();	private JLabel birthDateLabel = new JLabel();
	private JLabel birthPlaceLabel = new JLabel();	private JLabel sexLabel = new JLabel();
	private JLabel phoneLabel = new JLabel();	private JLabel emailLabel = new JLabel();
	private JLabel addressLabel = new JLabel();	
	private JLabel majorLabel = new JLabel();
	private JLabel startDateLabel = new JLabel();
	private JLabel err = new JLabel();
	private JLabel err1 = new JLabel("*");
	private JLabel err2 = new JLabel("*");
	private StudentDao studentDao =new StudentDao();
	private String _id_ = "";
	private StudyDao studyDao = new StudyDao();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(265, 25, 95, 29);	backBtn.setActionCommand("Back");	
		err.setBounds(250,200,95,29);	saveBtn.setBounds(370, 25, 95, 29);
		textPanel.setBounds(120,30,100,20);	textPanel.add(text);	idLabel.setBounds(0,30,150,20);
		searchBtn.setBounds(230,30,20,20); namePanel.add(name);	birthDatePanel.add(birthDate);
		birthPlacePanel.add(birthPlace);	sexPanel.add(sex);	phonePanel.add(phone);
		emailPanel.add(email);	addressPanel.add(address); majorPanel.add(major);	startDatePanel.add(startDate);
		nameLabel.setBounds(0,60,250,20);		namePanel.setBounds(260,60,220,20);
		birthDateLabel.setBounds(0,85,250,20);	birthDatePanel.setBounds(260,85,220,20); err1.setBounds(490,85,5,20);
		birthPlaceLabel.setBounds(0,110,250,20);	birthPlacePanel.setBounds(260,110,220,20);
		sexLabel.setBounds(0,135,250,20);		sexPanel.setBounds(260,135,220,20);
		phoneLabel.setBounds(0,160,250,20);		phonePanel.setBounds(260,160,220,20);
		emailLabel.setBounds(0,185,250,20);		emailPanel.setBounds(260,185,220,20);
		addressLabel.setBounds(0,210,250,20);	addressPanel.setBounds(260,210,220,20);
		majorLabel.setBounds(0,235,250,20);		majorPanel.setBounds(260,235,220,20);
		startDateLabel.setBounds(0,260,250,20);	startDatePanel.setBounds(260,260,220,20);	err2.setBounds(490,85,5,20);
		showAll(false);
	
	}
	void showAll(boolean show) {
		nameLabel.setVisible(show);		birthDateLabel.setVisible(show);		birthPlaceLabel.setVisible(show);
		sexLabel.setVisible(show);		phoneLabel.setVisible(show);		emailLabel.setVisible(show);
		addressLabel.setVisible(show);		majorLabel.setVisible(show);		startDateLabel.setVisible(show);
		namePanel.setVisible(show);		birthDatePanel.setVisible(show);		birthPlacePanel.setVisible(show);
		sexPanel.setVisible(show);		phonePanel.setVisible(show);		emailPanel.setVisible(show);
		addressPanel.setVisible(show);		majorPanel.setVisible(show);		startDatePanel.setVisible(show);
		saveBtn.setVisible(show);	err.setVisible(!show); err2.setVisible(false); err1.setVisible(false);
	}
	private ActionListener actionChangeSearch = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(text.getText()=="") {
				err.setText("NULL INPUT !!");
			}
			else {
				long id_std;
				try{
				 id_std =Long.parseLong(text.getText());
				}
				catch(Exception ex) {
					err.setText("ERROR INPUT !!");
					return;
				}
				Student _t = studentDao.getStudent(id_std);
				if(_t==null) {
					err.setText("NOT FOUND !!");
				}
				else {
					_id_ = text.getText();
					nameLabel.setText("Name: "+_t.getName());
					birthDateLabel.setText("BirthDate: "+_t.getBirthDate());
					birthPlaceLabel.setText("BirthPlace: "+_t.getBirthPlace());
					sexLabel.setText("Sex: "+_t.getSex());
					phoneLabel.setText("Phone: "+_t.getPhone());
					emailLabel.setText("Email: "+_t.getEmail());
					addressLabel.setText("Address: "+_t.getAddress());
					majorLabel.setText("Major: "+_t.getMajor());
					startDateLabel.setText("Start Date: "+_t.getStartDate());
					showAll(true);
				}
			}
		}
	};

	private boolean checkBirthDate() {
		try {		
			Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(birthDate.getText());
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}
	private boolean checkStartDate() {
		try {		
			Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(startDate.getText());
			return true;
		}
		catch(Exception ex) {
			return false;
		}
	}

	private ActionListener actionChangeSave = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(checkBirthDate()==false) {
				err1.setVisible(true);
			}
			else if(checkStartDate()==false) {
				err2.setVisible(true);
			}
			else {
				err1.setVisible(false);
				err2.setVisible(false);
				Student k = new Student();
				k.setId(Long.parseLong(_id_));
				k.setName(name.getText());
				k.setBirthDate(birthDate.getText());
				k.setBirthPlace(birthPlace.getText());
				k.setSex(sex.getText());
				k.setPhone(phone.getText());
				k.setEmail(email.getText());
				k.setAddress(address.getText());
				k.setMajor(major.getText());
				k.setStartDate(startDate.getText());
				studentDao.updateStudent(k);
				err.setText("Edit Success !!");
				showAll(false);
			}
		}
	};
	public teacherStudentEdit(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(searchBtn);	add(textPanel);	add(idLabel);	 add(nameLabel);	add(addressLabel);
		add(birthDateLabel);	add(birthPlaceLabel);	 add(sexLabel);	add(phoneLabel);	add(emailLabel);
		add(majorLabel);	add(startDateLabel);	add(namePanel);	add(birthDatePanel);	add(birthPlacePanel);
		add(sexPanel);	add(addressPanel);	add(phonePanel);	add(emailPanel);	add(majorPanel);	add(startDatePanel);
		add(saveBtn);	searchBtn.addActionListener(actionChangeSearch);	saveBtn.addActionListener(actionChangeSave);
		
	}
}
