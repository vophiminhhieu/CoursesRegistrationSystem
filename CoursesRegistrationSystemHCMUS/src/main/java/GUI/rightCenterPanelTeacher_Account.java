package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class rightCenterPanelTeacher_Account extends JPanel {

	private ImageIcon background = new ImageIcon("image/Menu/right_centerPanel.png");

    private Icon account_displayIcon=new ImageIcon("image/Menu/Teacher/account/display.png");
    private JButton account_display=new JButton(account_displayIcon);
    private Icon account_searchIcon=new ImageIcon("image/Menu/Teacher/account/search.png");
    private JButton account_search=new JButton(account_searchIcon);
    private Icon account_addIcon=new ImageIcon("image/Menu/Teacher/account/add.png");
    private JButton account_add=new JButton(account_addIcon);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	public void prepareGUI() {
		account_display.setBounds(5,10,174,38);	add(account_display);	account_display.setActionCommand("display");
		account_search.setBounds(5,53,174,38);	add(account_search);	account_search.setActionCommand("search");
		account_add.setBounds(5,96,174,38);		add(account_add);		account_add.setActionCommand("add");
	}
	public rightCenterPanelTeacher_Account(ActionListener obj) {
		setLayout(null);	setBounds(0,0,183,280);	prepareGUI();	
		account_display.addActionListener(obj);
		account_search.addActionListener(obj);
		account_add.addActionListener(obj);
		
	}
}
