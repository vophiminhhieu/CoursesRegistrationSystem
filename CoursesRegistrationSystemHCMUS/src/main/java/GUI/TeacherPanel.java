package GUI;

import java.awt.Graphics;

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
    private JPanel leftCenterPanel = paintToPanel("image/Menu/leftCenterPanel.png");
    private JPanel rightCenterPanel = paintToPanel("image/Menu/right_centerPanel.png");

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
	private void prepareGUI() {
		setLayout(null);	setBounds(0,0,713,295);
		leftCenterPanel.setBounds(0,10,495,281);			rightCenterPanel.setBounds(520,10,183,280);
		account.setBounds(10, 10, 111, 111);	leftCenterPanel.add(account);
		subject.setBounds(134,10,111,111);		leftCenterPanel.add(subject);
		semester.setBounds(258,10,111,111);		leftCenterPanel.add(semester);
		classBtn.setBounds(382,10,111,111);		leftCenterPanel.add(classBtn);
	}
	
	public TeacherPanel() {
		prepareGUI();	add(leftCenterPanel);	add(rightCenterPanel);
	}
}
