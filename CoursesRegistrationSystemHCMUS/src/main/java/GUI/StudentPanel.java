package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class StudentPanel extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/centerPanel.png");
    private Icon regisIcon=new ImageIcon("image/Menu/Student/regis.png");
    private JButton regis=new JButton(regisIcon);
    private Icon yourIcon=new ImageIcon("image/Menu/Student/your.png");
    private JButton your=new JButton(yourIcon);
    private JPanel leftCenterPanel = paintToPanel("image/Menu/leftCenterPanel.png");
    private JPanel rightCenterPanel = paintToPanel("image/Menu/right_centerPanel.png");
    private JPanel Student_Regis = new JPanel();
    private JPanel Student_Your = new JPanel();
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
	private ActionListener actionChangeRegis = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Back")) {	remove(Student_Regis);	add(leftCenterPanel);	}
			else if(e.getActionCommand().equals("regis")) {	remove(leftCenterPanel);	remove(Student_Regis);
			Student_Regis = new studentRegisRegis(actionChangeRegis);	add(Student_Regis); }
			//else if(e.getActionCommand().equals("add")) {	remove(leftCenterPanel);	remove(teacher_Course);
			//teacher_Course = new teacherCourseAdd(actionChangeCourse);		add(teacher_Course);}
			//else if(e.getActionCommand().equals("find")) {	remove(leftCenterPanel);	remove(teacher_Course);
			//teacher_Course = new teacherCourseFind(actionChangeCourse);		add(teacher_Course);}
			setVisible(false);		setVisible(true);
		
		}
	};
	private ActionListener actionChangeYour = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
		}
	};
	

    private JPanel rightCenterPanelRegis = new rightCenterPanelStudent_Regis(actionChangeRegis);
    //private JPanel rightCenterPanelSubject = new rightCenterPanelTeacher_Subject(actionChangeSubject);
	private ActionListener actionChangeButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Regis")) {
				removeAllPanel();	rightCenterPanel.add(rightCenterPanelRegis);
			}
			else if(e.getActionCommand().equals("Your")) {
				removeAllPanel();	//rightCenterPanel.add(rightCenterPanelSubject);
			}			
			rightCenterPanel.setVisible(false);
			rightCenterPanel.setVisible(true);
		}
	};
	private void removeAllPanel() {
		rightCenterPanel.remove(rightCenterPanelRegis);
		//rightCenterPanel.remove(rightCenterPanelSubject);
	}
	private void prepareGUI() {
		setLayout(null);	setBounds(0,0,713,295);	
		leftCenterPanel.setBounds(0,10,495,281);	rightCenterPanel.add(rightCenterPanelRegis);	rightCenterPanel.setBounds(520,10,183,280);
		regis.setBounds(10, 10, 111, 111);	leftCenterPanel.add(regis);	regis.setActionCommand("Regis");	regis.addActionListener(actionChangeButton);
		your.setBounds(134,10,111,111);		leftCenterPanel.add(your);	your.setActionCommand("Your");	your.addActionListener(actionChangeButton);
	}
	
	public StudentPanel() {
		prepareGUI();	add(leftCenterPanel);	add(rightCenterPanel);
	}
}
