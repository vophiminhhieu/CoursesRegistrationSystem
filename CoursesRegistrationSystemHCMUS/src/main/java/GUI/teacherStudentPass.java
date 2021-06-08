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
import DAL.DAO.UserDao;
import DAL.POJO.Student;
import DAL.POJO.Study;
import DAL.POJO.Subject;
import DAL.POJO.User;


public class teacherStudentPass extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon searchIcon = new ImageIcon("image/Menu/Teacher/Student/reset.png");
	private JButton searchBtn = new JButton(searchIcon);
	private JTextField text = new JTextField();	private JPanel textPanel = new JPanel(new BorderLayout());
	private JLabel idLabel = new JLabel("Input Student ID : ");
	private JLabel err = new JLabel();
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
		backBtn.setBounds(335, 25, 95, 29);	backBtn.setActionCommand("Back");	
		err.setBounds(150,200,295,29);	
		textPanel.setBounds(120,30,100,20);	textPanel.add(text);	idLabel.setBounds(0,30,150,20);
		searchBtn.setBounds(230,25,95,29); 
	
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
					err.setText("Reset Password Success !!! Password is 99 ");
					User us = new User();
					us.setId(id_std);
					us.setPassword("99");
					UserDao usDao = new UserDao();
					usDao.updateUser(us);
				}
			}
		}
	};


	public teacherStudentPass(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(searchBtn);	add(textPanel);	add(idLabel);	 add(err);
		searchBtn.addActionListener(actionChangeSearch);	
		
	}
}
