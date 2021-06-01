package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class TeacherPanel extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/centerPanel.png");
    private Icon accountIcon=new ImageIcon("image/Menu/Teacher/account.png");
    private JButton account=new JButton(accountIcon);
    private Icon subjectIcon=new ImageIcon("image/Menu/Teacher/subject.png");
    private JButton subject=new JButton(subjectIcon);
    private Icon semesterIcon=new ImageIcon("image/Menu/Teacher/semester.png");
    private JButton semester=new JButton(semesterIcon);
    private Icon classIcon=new ImageIcon("image/Menu/Teacher/class.png");
    private JButton classBtn=new JButton(classIcon);
    private Icon studentIcon=new ImageIcon("image/Menu/Teacher/student.png");
    private JButton studentBtn=new JButton(studentIcon);
    private Icon registrationIcon=new ImageIcon("image/Menu/Teacher/registration.png");
    private JButton registration=new JButton(registrationIcon);
    private Icon courseIcon=new ImageIcon("image/Menu/Teacher/courses.png");
    private JButton course=new JButton(courseIcon);
    private JPanel leftCenterPanel = paintToPanel("image/Menu/leftCenterPanel.png");
    private JPanel rightCenterPanel = new JPanel();
    private JPanel teacher_Account = new JPanel();
	@Override
    protected void paintComponent(Graphics g){
		super.paintComponent(g);
	    if(background!=null){
	        g.drawImage(background.getImage(), 0, 0, getWidth(),getHeight(),null);
	    }
	}
	private JPanel paintToPanel(String namepath){
        ImageIcon background = new ImageIcon(namepath);
        JPanel panel = new JPanel(){
            @Override
            protected void paintComponent(Graphics g){
                super.paintComponent(g);
                if(background!=null){
                    g.drawImage(background.getImage(), 0, 0, getWidth(),getHeight(),null);
                }
            }
        };
        panel.setLayout(null);
        return panel;
    }
	private ActionListener actionChangeAccount = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Back")) {
				remove(teacher_Account);
				add(leftCenterPanel);
			}
			else if(e.getActionCommand().equals("display")) {
				remove(leftCenterPanel);
				remove(teacher_Account);
				teacher_Account = new teacherAccountDisplay(actionChangeAccount);
				add(teacher_Account);
			}
			else if(e.getActionCommand().equals("search")) {
				remove(leftCenterPanel);
				remove(teacher_Account);
				teacher_Account = new teacherAccountSearch(actionChangeAccount);
				add(teacher_Account);
			}
			else if(e.getActionCommand().equals("add")) {
				remove(leftCenterPanel);
				remove(teacher_Account);
				teacher_Account = new teacherAccountAdd(actionChangeAccount);
				add(teacher_Account);
			}
			setVisible(false);
			setVisible(true);
		}
	};
	private void prepareGUI() {
		setLayout(null);	setBounds(0,0,713,295);	
		leftCenterPanel.setBounds(0,10,495,281);	rightCenterPanel = new rightCenterPanelTeacher_Account(actionChangeAccount);
		account.setBounds(10, 10, 111, 111);	leftCenterPanel.add(account);
		subject.setBounds(134,10,111,111);		leftCenterPanel.add(subject);
		semester.setBounds(258,10,111,111);		leftCenterPanel.add(semester);
		classBtn.setBounds(382,10,111,111);		leftCenterPanel.add(classBtn);
		studentBtn.setBounds(10,130,111,111);		leftCenterPanel.add(studentBtn);
		registration.setBounds(134,130,111,111);		leftCenterPanel.add(registration);
		course.setBounds(258,130,111,111);		leftCenterPanel.add(course);
	}
	
	public TeacherPanel() {
		prepareGUI();	add(leftCenterPanel);	add(rightCenterPanel);
	}
}
