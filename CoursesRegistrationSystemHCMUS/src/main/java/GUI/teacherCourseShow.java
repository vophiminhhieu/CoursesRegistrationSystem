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

import DAL.DAO.ClassDao;
import DAL.DAO.CourseDao;
import DAL.DAO.RegistrationDao;
import DAL.DAO.SemesterStaticDao;
import DAL.POJO.Registration;


public class teacherCourseShow extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private JLabel error = new JLabel();
	private JFrame frame = new JFrame();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");
		error.setBounds(100,200,195,29);
		frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocation(200,80);
        frame.setTitle("List of Semester");
        frame.pack();
        frame.setSize(750,550);
	}
	private void on() {
		CourseDao courseDao = new CourseDao();
		String[][] listCourse = courseDao.getListCourse();
		RegistrationDao resDao = new RegistrationDao();
		List<Registration> res = resDao.getRegistration();
		boolean a =false;
		for(int i=0;i<res.size();i++) {
			if(res.get(i).getCurrent()==true) {
				a=true;
			}
		}
		if(a==false) {
			error.setText("You don't have registration");
		}
		else {
		String[] columnName = {"ID Subject","Name Subject","Credits","ID Teacher","Name Teacher","Name Class","Day","Time"};
		JTable table = new JTable(courseDao.getListCourse(),columnName);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(100);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setPreferredWidth(144);
		table.getColumnModel().getColumn(3).setPreferredWidth(144);
		table.getColumnModel().getColumn(4).setPreferredWidth(224);
		table.getColumnModel().getColumn(5).setPreferredWidth(144);
		table.getColumnModel().getColumn(6).setPreferredWidth(144);
		table.getColumnModel().getColumn(7).setPreferredWidth(224);
		JPanel panel =new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(750,550));
		JScrollPane sp=new JScrollPane(table);
		panel.add(sp,BorderLayout.CENTER);
		panel.setOpaque(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
		}
		
	}
	public teacherCourseShow(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj); on();	setBounds(0, 10, 495, 281);
		add(error);
		
	}
}
