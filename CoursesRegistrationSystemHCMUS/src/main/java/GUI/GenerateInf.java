package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;

import Application.Data;
import BUS.saveGenerateInf;
import BUS.savePasswordChange;
import DAL.POJO.Student;
import DAL.POJO.Teacher;

public class GenerateInf extends JPanel {
	private Data data = new Data();
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private JLabel roleLabel 		= new JLabel("Role       : ");
	private JLabel idLabel 			= new JLabel("Id                 : ");
	private JLabel nameLabel 		= new JLabel("Name         : ");
	private JLabel birthDateLabel 	= new JLabel("Birthdate   : ");
	private JLabel birthPlaceLabel  = new JLabel("Birthplace : ");
	private JLabel sexLabel 		= new JLabel("Sex             : ");
	private JLabel addressLabel 	= new JLabel("Address    : ");
	private JLabel phoneLabel 		= new JLabel("Phone        : ");
	private JLabel emailLabel 		= new JLabel("Email          : ");
	private JLabel majorLabel 		= new JLabel("Major          : ");
	private JLabel startDateLabel 	= new JLabel("Start Date  : ");
	private JLabel error 			= new JLabel("*");
	private JLabel error1 			= new JLabel("*");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/myProfile/back.png");
	private Icon saveIcon = new ImageIcon("image/Menu/Dashboard/myProfile/save.png");
	private Icon editIcon = new ImageIcon("image/Menu/Dashboard/MyProfile/edit.png");
	private JButton backBtn = new JButton(backIcon);
	private JButton saveBtn = new JButton(saveIcon);
	private JButton editBtn = new JButton(editIcon);
	private JTextField nameEdit = new JTextField();			private JPanel nameEditPanel = new JPanel(new BorderLayout());
	private JTextField birthDateEdit = new JTextField();	private JPanel birthDateEditPanel = new JPanel(new BorderLayout());
	private JTextField birthPlaceEdit = new JTextField();	private JPanel birthPlaceEditPanel = new JPanel(new BorderLayout());
	private JTextField sexEdit = new JTextField();			private JPanel sexEditPanel = new JPanel(new BorderLayout());
	private JTextField addressEdit = new JTextField();		private JPanel addressEditPanel = new JPanel(new BorderLayout());
	private JTextField phoneEdit = new JTextField();		private JPanel phoneEditPanel = new JPanel(new BorderLayout());
	private JTextField emailEdit = new JTextField();		private JPanel emailEditPanel = new JPanel(new BorderLayout());
	private JTextField majorEdit = new JTextField();		private JPanel majorEditPanel = new JPanel(new BorderLayout());
	private JTextField startDateEdit = new JTextField();	private JPanel startDateEditPanel = new JPanel(new BorderLayout());
	private boolean editCheck=false;
	private saveGenerateInf saveGenerate = new saveGenerateInf();
	private void Init() {
		if(data.getRole().equals("Sinh vien")) {
			Student user = data.getStudent();					roleLabel.setText("Student");	roleLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
			idLabel.setText("Id                 : "+user.getId());	nameLabel.setText("Name         : "+user.getName());
			birthDateLabel.setText("Birthdate   : "+user.getBirthDate());
			birthPlaceLabel.setText("Birthplace : "+user.getBirthPlace());
			sexLabel.setText(sexLabel.getText()+user.getSex());	addressLabel.setText(addressLabel.getText()+user.getAddress());
			phoneLabel.setText(phoneLabel.getText()+user.getPhone());
			emailLabel.setText(emailLabel.getText()+user.getEmail());
			majorLabel.setText(majorLabel.getText()+user.getMajor());
			startDateLabel.setText(startDateLabel.getText()+user.getStartDate());
		}
		else if(data.getRole().equals("Giao vien")) {
			Teacher user = data.getTeacher();					roleLabel.setText("Teacher");	roleLabel.setFont(new Font("Verdana", Font.PLAIN, 24));
			idLabel.setText("Id                 : "+user.getId());	nameLabel.setText("Name         : "+user.getName());
			birthDateLabel.setText("Birthdate   : "+user.getBirthDate());
			birthPlaceLabel.setText("Birthplace : "+user.getBirthPlace());
			sexLabel.setText(sexLabel.getText()+user.getSex());	addressLabel.setText(addressLabel.getText()+user.getAddress());
			phoneLabel.setText(phoneLabel.getText()+user.getPhone());
			emailLabel.setText(emailLabel.getText()+user.getEmail());
			majorLabel.setText(majorLabel.getText()+user.getMajor());
			startDateLabel.setText(startDateLabel.getText()+user.getStartDate());
		}
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	public void prepareGUI() {
		error.setForeground(Color.RED);				error1.setForeground(Color.RED);	error.setBounds(473,70,5,20);	error1.setBounds(473,210,5,20);
		idLabel.setBounds(10,30,300,20);			nameLabel.setBounds(10,50,300,20);	birthDateLabel.setBounds(10,70,300,20);
		birthPlaceLabel.setBounds(10,90,300,20);	sexLabel.setBounds(10,110,300,20);	addressLabel.setBounds(10,130,300,20);
		phoneLabel.setBounds(10,150,300,20);		emailLabel.setBounds(10,170,300,20);	majorLabel.setBounds(10,190,300,20);
		startDateLabel.setBounds(10,210,300,20);	backBtn.setBounds(100,250,95,29);	editBtn.setBounds(230,250,95,29);
		saveBtn.setBounds(360, 250, 95, 29);		roleLabel.setBounds(230,2,300,20);
		nameEditPanel.setBounds(320,50,150,20);				birthDateEditPanel.setBounds(320,70,150,20);
		birthPlaceEditPanel.setBounds(320,90,150,20);		sexEditPanel.setBounds(320,110,150,20);		addressEditPanel.setBounds(320,130,150,20);
		phoneEditPanel.setBounds(320,150,150,20);			emailEditPanel.setBounds(320,170,150,20);	majorEditPanel.setBounds(320,190,150,20);
		startDateEditPanel.setBounds(320,210,150,20);
		nameEditPanel.add(nameEdit);	birthDateEditPanel.add(birthDateEdit);	
		birthPlaceEditPanel.add(birthPlaceEdit);							sexEditPanel.add(sexEdit);	
		addressEditPanel.add(addressEdit);	phoneEditPanel.add(phoneEdit);	emailEditPanel.add(emailEdit);
		majorEditPanel.add(majorEdit);		startDateEditPanel.add(startDateEdit);
		backBtn.setActionCommand("Back");			editBtn.setActionCommand("Edit"); 	saveBtn.setActionCommand("Save");
		Init();
	}
	private void setEditPanelVisible(boolean set) {
		nameEditPanel.setVisible(set);		birthDateEditPanel.setVisible(set);		birthPlaceEditPanel.setVisible(set);
		sexEditPanel.setVisible(set);		addressEditPanel.setVisible(set);		phoneEditPanel.setVisible(set);
		emailEditPanel.setVisible(set);		majorEditPanel.setVisible(set);			startDateEditPanel.setVisible(set);
	}
	private ActionListener actionListenerEdit = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(editCheck==false) {
				editCheck=true;
			}
			else {
				editCheck=false;
			}
			setEditPanelVisible(editCheck);
		}
	};
	
	private ActionListener actionListenerSave = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(editCheck=true) {
				saveGenerate = new saveGenerateInf(nameEdit.getText(),birthDateEdit.getText(),sexEdit.getText(),birthPlaceEdit.getText(),
						addressEdit.getText(),phoneEdit.getText(),emailEdit.getText(),majorEdit.getText(),startDateEdit.getText());
				if(saveGenerate.checkBirthDate()==false) {
					error1.setVisible(false);
					error.setVisible(true);
				}
				else if(saveGenerate.checkStartDate()==false) {
					error1.setVisible(true);
					error.setVisible(false);
				}
				else if(saveGenerate.checkYear()==false) {
					error1.setVisible(true);
					error1.setVisible(true);
				}
				else {
					saveGenerate.save();
					error1.setVisible(false);
					error.setVisible(false);
					editCheck=false;
					setEditPanelVisible(editCheck);
					Init();
					setVisible(false);
					setVisible(true);
				}
			}
		}
	};
	public GenerateInf(ActionListener obj) {
		setEditPanelVisible(false);		error.setVisible(false); 	error1.setVisible(false);
		setLayout(null);
		prepareGUI();
		add(idLabel);		add(nameLabel);		add(birthDateLabel);	add(birthPlaceLabel);	add(sexLabel);	
		add(phoneLabel);	add(emailLabel);	add(majorLabel);		add(startDateLabel);	add(addressLabel);
		add(saveBtn);		add(editBtn);		add(backBtn);			add(roleLabel);			add(error);		add(error1);
		add(nameEditPanel);		add(birthDateEditPanel);		add(birthPlaceEditPanel);		add(startDateEditPanel);
		add(sexEditPanel);		add(addressEditPanel);			add(phoneEditPanel);			add(emailEditPanel);		add(majorEditPanel);
		backBtn.addActionListener(obj);			editBtn.addActionListener(actionListenerEdit);	saveBtn.addActionListener(actionListenerSave);
		setBounds(0, 10, 495, 281);
	}

}
