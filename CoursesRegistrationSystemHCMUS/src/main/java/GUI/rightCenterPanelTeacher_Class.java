package GUI;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class rightCenterPanelTeacher_Class extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/right_centerPanel.png");
    private Icon class_showIcon=new ImageIcon("image/Menu/Teacher/Class/display.png");
    private JButton class_show=new JButton(class_showIcon);
    private Icon class_addIcon=new ImageIcon("image/Menu/Teacher/Class/add.png");
    private JButton class_add=new JButton(class_addIcon);
    private Icon class_editIcon=new ImageIcon("image/Menu/Teacher/Class/edit.png");
    private JButton class_edit=new JButton(class_editIcon);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	public void prepareGUI() {
		class_show.setBounds(5,10,174,38);	add(class_show);	class_show.setActionCommand("show");
		class_add.setBounds(5,53,174,38);	add(class_add);	class_add.setActionCommand("add");
		class_edit.setBounds(5,96,174,38);		add(class_edit);		class_edit.setActionCommand("edit");
	}
	public rightCenterPanelTeacher_Class(ActionListener obj) {
		setLayout(null);	setBounds(0,0,183,280);	prepareGUI();	
		class_show.addActionListener(obj);
		class_add.addActionListener(obj);
		class_edit.addActionListener(obj);
		
	}
}
