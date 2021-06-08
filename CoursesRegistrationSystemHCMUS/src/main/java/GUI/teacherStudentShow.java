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

import DAL.DAO.ClassDao;
import DAL.DAO.JoinClassDao;
import DAL.DAO.TeacherDao;
import DAL.POJO.Subject;


public class teacherStudentShow extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon searchIcon = new ImageIcon("image/Menu/Teacher/Student/search.png");
	private JButton search = new JButton(searchIcon);
	private JFrame frame = new JFrame();
	private JLabel label = new JLabel("Input ID Class : ");
	private JTextField id = new JTextField();	private JPanel idPanel = new JPanel(new BorderLayout());
	private JoinClassDao dao = new JoinClassDao();	private ClassDao classDao = new ClassDao();
	private JLabel error = new JLabel("Not Found !!");
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");
		idPanel.setBounds(150,30,180,20);	idPanel.add(id);		label.setBounds(30,30,120,20);
		search.setBounds(350,30,95,29);	error.setBounds(30,200,200,20);
		frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocation(200,80);
        frame.setTitle("List of Student");
        frame.pack();
        frame.setSize(750,550);
	}
	private void on() {
		String[] columnName= {"ID","Name","BirthDate","BirthPlace","Sex","Email","Address","Phone","Major","StartDate"};
		JTable table = new JTable(dao.getStringStudent(id.getText()),columnName);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(200);
		table.getColumnModel().getColumn(2).setPreferredWidth(70);
		table.getColumnModel().getColumn(3).setPreferredWidth(200);
		table.getColumnModel().getColumn(4).setPreferredWidth(35);
		table.getColumnModel().getColumn(5).setPreferredWidth(300);
		table.getColumnModel().getColumn(6).setPreferredWidth(450);
		table.getColumnModel().getColumn(7).setPreferredWidth(120);
		table.getColumnModel().getColumn(8).setPreferredWidth(160);
		table.getColumnModel().getColumn(9).setPreferredWidth(70);
		JPanel panel =new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(750,550));
		JScrollPane sp=new JScrollPane(table);
		panel.add(sp,BorderLayout.CENTER);
		panel.setOpaque(true);

        frame.setContentPane(panel);
        frame.setVisible(true);
		
	}
	
	private ActionListener actionChangeShow = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(classDao.getClasss(id.getText())!=null) {
				on();	error.setVisible(false);
			}
			else {
				frame.setVisible(false);
				error.setVisible(true);
			}
		}
	};
	
	public teacherStudentShow(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(label);	add(idPanel);	add(search);	add(error);	search.addActionListener(actionChangeShow);
	}
}
