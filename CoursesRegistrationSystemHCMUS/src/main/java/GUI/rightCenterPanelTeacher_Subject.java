package GUI;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class rightCenterPanelTeacher_Subject extends JPanel {

	private ImageIcon background = new ImageIcon("image/Menu/right_centerPanel.png");
    private Icon subject_addIcon=new ImageIcon("image/Menu/Teacher/Subject/add.png");
    private JButton subject_add=new JButton(subject_addIcon);
    private Icon subject_editIcon=new ImageIcon("image/Menu/Teacher/Subject/edit.png");
    private JButton subject_edit=new JButton(subject_editIcon);
    private Icon subject_deleteIcon=new ImageIcon("image/Menu/Teacher/Subject/delete.png");
    private JButton subject_delete=new JButton(subject_deleteIcon);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	public void prepareGUI() {
		subject_add.setBounds(5,10,174,38);	add(subject_add);	subject_add.setActionCommand("add");
		subject_edit.setBounds(5,53,174,38);	add(subject_edit);	subject_edit.setActionCommand("edit");
		subject_delete.setBounds(5,96,174,38);		add(subject_delete);		subject_delete.setActionCommand("delete");
	}
	public rightCenterPanelTeacher_Subject(ActionListener obj) {
		setLayout(null);	setBounds(0,0,183,280);	prepareGUI();	
		subject_add.addActionListener(obj);
		subject_edit.addActionListener(obj);
		subject_delete.addActionListener(obj);
		
	}
}
