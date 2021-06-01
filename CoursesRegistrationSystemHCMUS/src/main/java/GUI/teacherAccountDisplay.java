package GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.WindowConstants;

import com.sun.tools.javac.util.Position;

import DAL.DAO.StudentDao;
import DAL.DAO.TeacherDao;

public class teacherAccountDisplay extends JPanel {
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
		backBtn.setBounds(220, 250, 95, 29);
		backBtn.setActionCommand("Back");

		frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocation(200,80);
        frame.setTitle("List of Teacher");
        frame.pack();
        frame.setSize(750,550);
	}
	private void on() {
		TeacherDao stdDao = new TeacherDao();
		String[] columnName= {"ID","Name","BirthDate","BirthPlace","Sex","Email","Address","Phone","Major","StartDate"};
		JTable table = new JTable(stdDao.getListTeacher(),columnName);
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
		//frame.removeAll();

        frame.setContentPane(panel);
        frame.setVisible(true);
		
	}
	public teacherAccountDisplay(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();		backBtn.addActionListener(obj);	on();
		setBounds(0, 10, 495, 281);
	}

}
