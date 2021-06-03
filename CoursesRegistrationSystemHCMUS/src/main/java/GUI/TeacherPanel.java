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
    private JPanel rightCenterPanel = paintToPanel("image/Menu/right_centerPanel.png");
    private JPanel teacher_Account = new JPanel();
    private JPanel teacher_Subject = new JPanel();
    private JPanel teacher_Semester = new JPanel();
    private JPanel teacher_Class = new JPanel();
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
			if(e.getActionCommand().equals("Back")) {	remove(teacher_Account);	add(leftCenterPanel);			}
			else if(e.getActionCommand().equals("display")) {	remove(leftCenterPanel);	remove(teacher_Account);
				teacher_Account = new teacherAccountDisplay(actionChangeAccount);	add(teacher_Account);			}
			else if(e.getActionCommand().equals("search")) {remove(leftCenterPanel);	remove(teacher_Account);
				teacher_Account = new teacherAccountSearch(actionChangeAccount);		add(teacher_Account);			}
			else if(e.getActionCommand().equals("add")) {	remove(leftCenterPanel);				remove(teacher_Account);
				teacher_Account = new teacherAccountAdd(actionChangeAccount);	add(teacher_Account);			}
			setVisible(false);		setVisible(true);
		}
	};
	private ActionListener actionChangeSubject = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Back")) {	remove(teacher_Subject);	add(leftCenterPanel);	}
			else if(e.getActionCommand().equals("add")) {	remove(leftCenterPanel);	remove(teacher_Subject);
				teacher_Subject = new teacherSubjectAdd(actionChangeSubject);	add(teacher_Subject); }
			else if(e.getActionCommand().equals("edit")) {	remove(leftCenterPanel);	remove(teacher_Subject);
				teacher_Subject=new teacherSubjectEdit(actionChangeSubject);	add(teacher_Subject);}
			else if(e.getActionCommand().equals("delete")) {	remove(leftCenterPanel);	remove(teacher_Subject);
			teacher_Subject=new teacherSubjectDelete(actionChangeSubject);	add(teacher_Subject);				}
			setVisible(false);		setVisible(true);
		}
	};
	

	private ActionListener actionChangeSemester = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Back")) {	remove(teacher_Semester);	add(leftCenterPanel);	}
			else if(e.getActionCommand().equals("show")) {	remove(leftCenterPanel);	remove(teacher_Semester);
			teacher_Semester = new teacherSemesterShow(actionChangeSemester);	add(teacher_Semester); }
			else if(e.getActionCommand().equals("add")) {	remove(leftCenterPanel);	remove(teacher_Semester);
			teacher_Semester=new teacherSemesterAdd(actionChangeSemester);	add(teacher_Semester);}
			else if(e.getActionCommand().equals("set")) {	remove(leftCenterPanel);	remove(teacher_Semester);
			teacher_Semester=new teacherSemesterSet(actionChangeSemester);	add(teacher_Semester);				}
			setVisible(false);		setVisible(true);
		}
	};

	private ActionListener actionChangeClass = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Back")) {	remove(teacher_Class);	add(leftCenterPanel);	}
			else if(e.getActionCommand().equals("show")) {	remove(leftCenterPanel);	remove(teacher_Class);
			teacher_Class = new teacherClassShow(actionChangeClass);	add(teacher_Class); }
			else if(e.getActionCommand().equals("add")) {	remove(leftCenterPanel);	remove(teacher_Class);
			teacher_Class = new teacherClassAdd(actionChangeClass);		add(teacher_Class);}
			else if(e.getActionCommand().equals("edit")) {	remove(leftCenterPanel);	remove(teacher_Class);
			teacher_Class = new teacherClassEdit(actionChangeClass);	add(teacher_Class);	}
			setVisible(false);		setVisible(true);
		}
	};
	

    private JPanel rightCenterPanelAccount = new rightCenterPanelTeacher_Account(actionChangeAccount);
    private JPanel rightCenterPanelSubject = new rightCenterPanelTeacher_Subject(actionChangeSubject);
    private JPanel rightCenterPanelSemester = new rightCenterPanelTeacher_Semester(actionChangeSemester);
    private JPanel rightCenterPanelClass = new rightCenterPanelTeacher_Class(actionChangeClass);
	private ActionListener actionChangeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Account")) {
				removeAllPanel();	rightCenterPanel.add(rightCenterPanelAccount);
			}
			else if(e.getActionCommand().equals("Subject")) {
				removeAllPanel();	rightCenterPanel.add(rightCenterPanelSubject);
			}
			else if(e.getActionCommand().equals("Semester")) {
				removeAllPanel();	rightCenterPanel.add(rightCenterPanelSemester);
			}
			else if(e.getActionCommand().equals("Class")) {
				removeAllPanel();	rightCenterPanel.add(rightCenterPanelClass);
			}
			rightCenterPanel.setVisible(false);
			rightCenterPanel.setVisible(true);
		}
	};
	private void removeAllPanel() {
		rightCenterPanel.remove(rightCenterPanelAccount);
		rightCenterPanel.remove(rightCenterPanelSubject);
		rightCenterPanel.remove(rightCenterPanelSemester);
		rightCenterPanel.remove(rightCenterPanelClass);
	}
	private void prepareGUI() {
		setLayout(null);	setBounds(0,0,713,295);	
		leftCenterPanel.setBounds(0,10,495,281);	rightCenterPanel.add(rightCenterPanelAccount);	rightCenterPanel.setBounds(520,10,183,280);
		account.setBounds(10, 10, 111, 111);	leftCenterPanel.add(account);	account.setActionCommand("Account");	account.addActionListener(actionChangeButton);
		subject.setBounds(134,10,111,111);		leftCenterPanel.add(subject);	subject.setActionCommand("Subject");	subject.addActionListener(actionChangeButton);
		semester.setBounds(258,10,111,111);		leftCenterPanel.add(semester);	semester.setActionCommand("Semester");	semester.addActionListener(actionChangeButton);
		classBtn.setBounds(382,10,111,111);		leftCenterPanel.add(classBtn);	classBtn.setActionCommand("Class");		classBtn.addActionListener(actionChangeButton);
		studentBtn.setBounds(10,130,111,111);		leftCenterPanel.add(studentBtn);	studentBtn.setActionCommand("Student");	studentBtn.addActionListener(actionChangeButton);
		registration.setBounds(134,130,111,111);		leftCenterPanel.add(registration);	registration.setActionCommand("Registration");	registration.addActionListener(actionChangeButton);
		course.setBounds(258,130,111,111);		leftCenterPanel.add(course);	course.setActionCommand("Course");	course.addActionListener(actionChangeButton);
	}
	
	public TeacherPanel() {
		prepareGUI();	add(leftCenterPanel);	add(rightCenterPanel);
	}
}
