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

import DAL.DAO.SubjectDao;
import DAL.DAO.TeacherDao;
import DAL.DAO.UserDao;
import DAL.POJO.Subject;
import DAL.POJO.Teacher;
import DAL.POJO.User;

public class teacherSubjectEdit extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon editIcon = new ImageIcon("image/Menu/Dashboard/myProfile/edit.png");
	private JButton editBtn = new JButton(editIcon);
	private Icon saveIcon = new ImageIcon("image/Menu/Dashboard/userAccount/save.png");
	private JButton saveBtn = new JButton(saveIcon);
	private JLabel idLabel = new JLabel("Input Subject ID        : ");
	private JLabel nameLabel = new JLabel();
	private JLabel creditsLabel = new JLabel();
	private JLabel error = new JLabel();
	private JTextField id = new JTextField();	private JPanel idPanel = new JPanel(new BorderLayout());
	private JTextField name = new JTextField();	private JPanel namePanel = new JPanel(new BorderLayout());
	private JTextField credits = new JTextField();	private JPanel creditsPanel = new JPanel(new BorderLayout());
	private SubjectDao subDao=new SubjectDao();	private Subject sub = new Subject();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void setAll(boolean set) {
		namePanel.setVisible(set);	creditsPanel.setVisible(set);	nameLabel.setVisible(set);	creditsLabel.setVisible(set);	saveBtn.setVisible(set);
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");	editBtn.setBounds(380,30,95,29);
		idPanel.setBounds(180,30,180,20);	idPanel.add(id);		idLabel.setBounds(30,30,150,20);
		namePanel.setBounds(265,60,180,20);	namePanel.add(name);	nameLabel.setBounds(30,60,230,20);
		creditsPanel.setBounds(265,90,180,20);	creditsPanel.add(credits);	creditsLabel.setBounds(30,90,230,20);
		error.setBounds(30,200,200,20);		setAll(false);	saveBtn.setBounds(295,200,95,29);
	}

	private ActionListener actionChangeEdit = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			sub = subDao.getSubject(id.getText());
			if(sub==null) {
				error.setText("NOT FOUND!!");
			}
			else {
				nameLabel.setText(sub.getName()); 	creditsLabel.setText(""+sub.getCredits());		setAll(true);	error.setText("");
			}
		}
	};

	private ActionListener actionChangeSave = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(name.getText()==""||credits.getText()=="") {
				error.setText("NULL INPUT !!");
			}
			else {
				try {
					sub.setCredits(Long.parseLong(credits.getText()));
					sub.setName(name.getText());
					subDao.deleteSubject(sub.getId());
					subDao.saveSubject(sub);
					error.setText("");
				}
				catch(Exception exp) {
					error.setText("Number of credits is Integer !!");
				}
			}
		}
	};
	public teacherSubjectEdit(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(idPanel);	add(idLabel);	add(namePanel);	add(nameLabel);	add(creditsPanel);	add(creditsLabel);	
		add(editBtn);	add(error);	editBtn.addActionListener(actionChangeEdit);	add(saveBtn);	saveBtn.addActionListener(actionChangeSave);
	}
}
