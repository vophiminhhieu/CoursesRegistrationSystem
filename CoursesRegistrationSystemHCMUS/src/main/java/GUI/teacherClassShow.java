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


public class teacherClassShow extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
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
		frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocation(200,80);
        frame.setTitle("List of Semester");
        frame.pack();
        frame.setSize(750,550);
	}
	private void on() {
		ClassDao dao = new ClassDao();
		String[] columnName = {"ID","Name","Number of Male","Number of Female","Number of Student"};
		JTable table = new JTable(dao.getListClass(),columnName);
		table.setAutoResizeMode(JTable.AUTO_RESIZE_OFF);
		table.getColumnModel().getColumn(0).setPreferredWidth(80);
		table.getColumnModel().getColumn(1).setPreferredWidth(220);
		table.getColumnModel().getColumn(2).setPreferredWidth(144);
		table.getColumnModel().getColumn(3).setPreferredWidth(144);
		table.getColumnModel().getColumn(4).setPreferredWidth(144);
		JPanel panel =new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(750,550));
		JScrollPane sp=new JScrollPane(table);
		panel.add(sp,BorderLayout.CENTER);
		panel.setOpaque(true);
        frame.setContentPane(panel);
        frame.setVisible(true);
		
	}
	public teacherClassShow(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj); on();	setBounds(0, 10, 495, 281);
		
	}
}
