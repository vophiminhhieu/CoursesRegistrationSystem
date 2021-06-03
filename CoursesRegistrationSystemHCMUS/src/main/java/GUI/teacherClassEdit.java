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

import DAL.DAO.ClassDao;
import DAL.DAO.JoinClassDao;
import DAL.POJO.Classs;
import DAL.POJO.JoinClass;



public class teacherClassEdit extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon editIcon = new ImageIcon("image/Menu/Dashboard/myProfile/edit.png");
	private JButton editBtn = new JButton(editIcon);
	private Icon removeIcon = new ImageIcon("image/Menu/Teacher/Account/removesmall.png");
	private JButton removeBtn = new JButton(removeIcon);
	private JLabel idLabel = new JLabel("Input Class ID   : ");
	private JLabel nameLabel = new JLabel();
	private JLabel numberMaleLabel = new JLabel();
	private JLabel numberFemaleLabel = new JLabel();
	private JLabel totalLabel = new JLabel();
	private JTextField id= new JTextField();	private JPanel idPanel = new JPanel(new BorderLayout());
	private JLabel error = new JLabel();	private ClassDao dao = new ClassDao();	private JoinClassDao daoJoin = new JoinClassDao();
	private Classs _class = new Classs();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");	editBtn.setBounds(295,200,95,29);
		idPanel.setBounds(230,30,180,20);	idPanel.add(id);		idLabel.setBounds(30,30,200,20);
		nameLabel.setBounds(30,60,300,20);	numberMaleLabel.setBounds(30,90,200,20);	
		numberFemaleLabel.setBounds(30,120,200,20);		totalLabel.setBounds(30,150,200,20);
		error.setBounds(30,200,200,20);		removeBtn.setBounds(400,160,67,21);	
	}

	private ActionListener actionChangeEdit = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(id.getText()=="") {
				error.setText("Null Input !!");
				showAll(false);
			}
			else {
				_class=dao.getClasss(id.getText());
				if(_class==null) {
					error.setText("Not Found !!");
					showAll(false);
				}
				else {
					error.setText("");
					nameLabel.setText("Name class: "+_class.getName());
					int[] n = daoJoin.getNumberStudent(_class.getId());
					numberMaleLabel.setText("Numbers of male: "+String.valueOf(n[0]));
					numberFemaleLabel.setText("Numbers of female: "+String.valueOf(n[1]));
					totalLabel.setText("Total: "+String.valueOf(n[0]+n[1]));
					showAll(true);
				}
			}
		}
	};
	private ActionListener actionChangeRemove = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			daoJoin.deleteJoinClass(_class.getId());
			dao.deleteClasss(_class.getId());
			error.setText("Remove success !!");
		}
	};
	void showAll(boolean show) {
		removeBtn.setVisible(show);	nameLabel.setVisible(show);  numberMaleLabel.setVisible(show);
		numberFemaleLabel.setVisible(show);	totalLabel.setVisible(show);
	}
	public teacherClassEdit(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		editBtn.addActionListener(actionChangeEdit);	add(idPanel);	add(idLabel);
		add(editBtn);	add(error);	showAll(false); add(removeBtn);	add(nameLabel);	add(numberMaleLabel);
		removeBtn.addActionListener(actionChangeRemove);	add(numberFemaleLabel);	add(totalLabel);
	}
}
