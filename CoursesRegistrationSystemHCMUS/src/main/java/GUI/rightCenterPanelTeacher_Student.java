package GUI;


import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class rightCenterPanelTeacher_Student extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/right_centerPanel.png");
    private Icon student_showIcon=new ImageIcon("image/Menu/Teacher/Student/display.png");
    private JButton student_show=new JButton(student_showIcon);
    private Icon student_addIcon=new ImageIcon("image/Menu/Teacher/Student/addStudent.png");
    private JButton student_add=new JButton(student_addIcon);
    private Icon student_subjectIcon=new ImageIcon("image/Menu/Teacher/Student/subject.png");
    private JButton student_subject=new JButton(student_subjectIcon);
    private Icon student_editIcon=new ImageIcon("image/Menu/Teacher/Student/edit.png");
    private JButton student_edit=new JButton(student_editIcon);
    private Icon student_passIcon=new ImageIcon("image/Menu/Teacher/Student/pass.png");
    private JButton student_pass=new JButton(student_passIcon);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	public void prepareGUI() {
		student_show.setBounds(5,10,174,38);	add(student_show);	student_show.setActionCommand("show");
		student_add.setBounds(5,53,174,38);	add(student_add);	student_add.setActionCommand("add");
		student_subject.setBounds(5,96,174,38);		add(student_subject);		student_subject.setActionCommand("set");
		student_edit.setBounds(5,139,174,38);		add(student_edit);		student_edit.setActionCommand("edit");
		student_pass.setBounds(5,182,174,38);		add(student_pass);		student_pass.setActionCommand("pass");
	}
	public rightCenterPanelTeacher_Student(ActionListener obj) {
		setLayout(null);	setBounds(0,0,183,280);	prepareGUI();	
		student_show.addActionListener(obj);
		student_add.addActionListener(obj);
		student_subject.addActionListener(obj);
		student_edit.addActionListener(obj);
		student_pass.addActionListener(obj);
	}
}
