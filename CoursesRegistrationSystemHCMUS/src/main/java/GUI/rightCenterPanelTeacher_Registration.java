package GUI;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class rightCenterPanelTeacher_Registration extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/right_centerPanel.png");
    private Icon registration_showIcon=new ImageIcon("image/Menu/Teacher/Registration/show.png");
    private JButton registration_show=new JButton(registration_showIcon);
    private Icon registration_addIcon=new ImageIcon("image/Menu/Teacher/Registration/add.png");
    private JButton registration_add=new JButton(registration_addIcon);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	public void prepareGUI() {
		registration_show.setBounds(5,10,174,38);	add(registration_show);	registration_show.setActionCommand("show");
		registration_add.setBounds(5,53,174,38);	add(registration_add);	registration_add.setActionCommand("add");
	}
	public rightCenterPanelTeacher_Registration(ActionListener obj) {
		setLayout(null);	setBounds(0,0,183,280);	prepareGUI();	
		registration_show.addActionListener(obj);
		registration_add.addActionListener(obj);
	}
}
