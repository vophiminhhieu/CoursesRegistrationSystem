package GUI;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class rightCenterPanelStudent_Regis extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/right_centerPanel.png");
    private Icon Regis_regisIcon=new ImageIcon("image/Menu/Student/Regis/regis.png");
    private JButton Regis_regis=new JButton(Regis_regisIcon);
    private Icon Regis_classIcon=new ImageIcon("image/Menu/Student/Regis/regisClass.png");
    private JButton Regis_class=new JButton(Regis_classIcon);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	
	public void prepareGUI() {
		Regis_regis.setBounds(5,10,174,38);	add(Regis_regis);	Regis_regis.setActionCommand("regis");
		Regis_class.setBounds(5,53,174,38);	add(Regis_class);	Regis_class.setActionCommand("class");
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
	}
	public rightCenterPanelStudent_Regis(ActionListener obj) {
		setLayout(null);	setBounds(0,0,183,280);	prepareGUI();	
		Regis_regis.addActionListener(obj);
		Regis_class.addActionListener(obj);
	}
}
