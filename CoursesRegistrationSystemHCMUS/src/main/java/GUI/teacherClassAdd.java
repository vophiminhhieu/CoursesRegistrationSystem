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
import DAL.POJO.Classs;


public class teacherClassAdd extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon addIcon = new ImageIcon("image/Menu/Dashboard/userAccount/save.png");
	private JButton addBtn = new JButton(addIcon);
	private JLabel idLabel = new JLabel("Input Class ID: ");
	private JLabel nameLabel = new JLabel("Input Class Name: ");
	private JTextField id= new JTextField();	private JPanel idPanel = new JPanel(new BorderLayout());
	private JTextField name= new JTextField();	private JPanel namePanel = new JPanel(new BorderLayout());
	private JLabel error = new JLabel();
	private Classs _class = new Classs();	private ClassDao dao = new ClassDao();
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
		namePanel.setBounds(230,60,180,20);	namePanel.add(name);		nameLabel.setBounds(30,60,200,20);
		error.setBounds(30,200,200,20);
	}

	private ActionListener actionChangeAdd = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(id.getText()==""||name.getText()=="") {
				error.setText("Null Input !!");
			}
			else {
				_class=dao.getClasss(id.getText());
				if(_class!=null) {
					error.setText("Existed ID !!");
				}
				else {
					_class = new Classs(id.getText(),name.getText());
					dao.saveClasss(_class);
					error.setText("Succsess Saved !!");
				}
			}
		}
	};
	public teacherClassAdd(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		addBtn.addActionListener(actionChangeAdd);	add(idPanel);	add(idLabel);	add(namePanel);	add(nameLabel);
		add(addBtn);	add(error);
	}
}
