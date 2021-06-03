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

public class teacherSubjectAdd extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon addIcon = new ImageIcon("image/Menu/Dashboard/userAccount/save.png");
	private JButton addBtn = new JButton(addIcon);
	private JLabel idLabel = new JLabel("Input Subject ID        : ");
	private JLabel nameLabel = new JLabel("Input Subject NAME     : ");
	private JLabel creditsLabel = new JLabel("Input Subject CREDITS : ");
	private JLabel error = new JLabel();
	private JTextField id = new JTextField();	private JPanel idPanel = new JPanel(new BorderLayout());
	private JTextField name = new JTextField();	private JPanel namePanel = new JPanel(new BorderLayout());
	private JTextField credits = new JTextField();	private JPanel creditsPanel = new JPanel(new BorderLayout());
	private SubjectDao subDao=new SubjectDao();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");	addBtn.setBounds(295,200,95,29);
		idPanel.setBounds(230,30,180,20);	idPanel.add(id);		idLabel.setBounds(30,30,200,20);
		namePanel.setBounds(230,60,180,20);	namePanel.add(name);	nameLabel.setBounds(30,60,200,20);
		creditsPanel.setBounds(230,90,180,20);	creditsPanel.add(credits);	creditsLabel.setBounds(30,90,200,20);
		error.setBounds(30,200,200,20);
	}

	private ActionListener actionChangeAdd = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(id.getText()==""||name.getText()==""||credits.getText()=="") {
				error.setText("NULL INPUT !!");
			}
			else {
				Subject sub = new Subject();
				sub = subDao.getSubject(id.getText());
				try {
					if(sub != null) {
						error.setText("Existed Subject !!");
					}
					else {
						sub=new Subject();
						sub.setCredits(Long.parseLong(credits.getText()));
						sub.setId(id.getText());
						sub.setName(name.getText());
						subDao.saveSubject(sub);
						error.setText("");
					}
	
				}
				catch(Exception exp) {
					error.setText("Number of credits is Integer !!");
				}
			}
		}
	};
	public teacherSubjectAdd(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(idPanel);	add(idLabel);	add(namePanel);	add(nameLabel);	add(creditsPanel);	add(creditsLabel);	
		add(addBtn);	add(error);	addBtn.addActionListener(actionChangeAdd);
	}
}
