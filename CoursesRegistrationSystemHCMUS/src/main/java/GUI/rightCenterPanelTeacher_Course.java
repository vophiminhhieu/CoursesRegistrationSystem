package GUI;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class rightCenterPanelTeacher_Course extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/right_centerPanel.png");
    private Icon course_showIcon=new ImageIcon("image/Menu/Teacher/Course/show.png");
    private JButton course_show=new JButton(course_showIcon);
    private Icon course_addIcon=new ImageIcon("image/Menu/Teacher/Course/add.png");
    private JButton course_add=new JButton(course_addIcon);
    private Icon course_findIcon=new ImageIcon("image/Menu/Teacher/Course/find.png");
    private JButton course_find=new JButton(course_findIcon);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	public void prepareGUI() {
		course_show.setBounds(5,10,174,38);	add(course_show);	course_show.setActionCommand("show");
		course_add.setBounds(5,53,174,38);	add(course_add);	course_add.setActionCommand("add");
		course_find.setBounds(5,96,174,38);	add(course_find);	course_find.setActionCommand("find");
	}
	public rightCenterPanelTeacher_Course(ActionListener obj) {
		setLayout(null);	setBounds(0,0,183,280);	prepareGUI();	
		course_show.addActionListener(obj);
		course_add.addActionListener(obj);
		course_find.addActionListener(obj);
	}
}
