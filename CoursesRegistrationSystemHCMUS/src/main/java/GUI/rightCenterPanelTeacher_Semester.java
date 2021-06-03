package GUI;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class rightCenterPanelTeacher_Semester extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/right_centerPanel.png");
    private Icon semester_showIcon=new ImageIcon("image/Menu/Teacher/Semester/display.png");
    private JButton semester_show=new JButton(semester_showIcon);
    private Icon semester_addIcon=new ImageIcon("image/Menu/Teacher/Semester/add.png");
    private JButton semester_add=new JButton(semester_addIcon);
    private Icon semester_setIcon=new ImageIcon("image/Menu/Teacher/Semester/set.png");
    private JButton semester_set=new JButton(semester_setIcon);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	public void prepareGUI() {
		semester_show.setBounds(5,10,174,38);	add(semester_show);	semester_show.setActionCommand("show");
		semester_add.setBounds(5,53,174,38);	add(semester_add);	semester_add.setActionCommand("add");
		semester_set.setBounds(5,96,174,38);		add(semester_set);		semester_set.setActionCommand("set");
	}
	public rightCenterPanelTeacher_Semester(ActionListener obj) {
		setLayout(null);	setBounds(0,0,183,280);	prepareGUI();	
		semester_show.addActionListener(obj);
		semester_add.addActionListener(obj);
		semester_set.addActionListener(obj);
		
	}
}
