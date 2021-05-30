package GUI;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

import Application.Data;

public class MenuFrame extends JFrame {
	private Data data=new Data();
    private JPanel contentPanel = paintToPanel("image/Menu/background.png");
    private JPanel CenterPanel= paintToPanel("image/Menu/centerPanel.png");
    private JPanel dashboardCenterPanel = new DashboardPanel();
    private JPanel teacherCenterPanel = new TeacherPanel();
    private JPanel studentCenterPanel = new StudentPanel();
    private JPanel manageCenterPanel = new ManagePanel();
    private JPanel dashboardPanel = paintToPanel("image/Menu/dashboardIcon.png");
    private JPanel teacherPanel = paintToPanel("image/Menu/teacherIcon.png");
    private JPanel studentPanel = paintToPanel("image/Menu/studentIcon.png");
    private JPanel managePanel = paintToPanel("image/Menu/manageIcon.png");
    private Icon dashboardBtnImage=new ImageIcon("image/Menu/buttonDashboard.png");
    private Icon dashboardBtnImageLight=new ImageIcon("image/Menu/buttonDashboardLight.jpg");
    private JButton dashboardBtn = new JButton(dashboardBtnImage);
    private Icon teacherBtnImage=new ImageIcon("image/Menu/buttonTeacher.png");
    private Icon teacherBtnImageLight=new ImageIcon("image/Menu/buttonTeacherLight.jpg");
    private JButton teacherBtn = new JButton(teacherBtnImageLight);
    private Icon studentBtnImage=new ImageIcon("image/Menu/buttonStudent.png");
    private Icon studentBtnImageLight=new ImageIcon("image/Menu/buttonStudentLight.jpg");
    private JButton studentBtn = new JButton(studentBtnImageLight);
    private Icon manageBtnImage=new ImageIcon("image/Menu/buttonManage.png");
    private Icon manageBtnImageLight=new ImageIcon("image/Menu/buttonManageLight.jpg");
    private JButton manageBtn = new JButton(manageBtnImageLight);
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
    private void setFullLight() {
    	dashboardBtn.setIcon(dashboardBtnImageLight);	dashboardPanel.setVisible(false);
    	teacherBtn.setIcon(teacherBtnImageLight);		teacherPanel.setVisible(false);
    	studentBtn.setIcon(studentBtnImageLight);		studentPanel.setVisible(false);
    	manageBtn.setIcon(manageBtnImageLight);			managePanel.setVisible(false);
    }
    private void removeCenterPanel() {
    	CenterPanel.remove(dashboardCenterPanel);	CenterPanel.remove(teacherCenterPanel);
    	CenterPanel.remove(studentCenterPanel);		CenterPanel.remove(manageCenterPanel);
    }
    private ActionListener LightDarkButton = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand()=="dashboard") {
				removeCenterPanel();	CenterPanel.add(dashboardCenterPanel);	setFullLight();
				dashboardBtn.setIcon(dashboardBtnImage);	dashboardPanel.setVisible(true);
			}
			else if(e.getActionCommand()=="teacher") {
				if(data.getRole().equals("Giao vien")){
					removeCenterPanel();	CenterPanel.add(teacherCenterPanel);	setFullLight();
					teacherBtn.setIcon(teacherBtnImage);		teacherPanel.setVisible(true);
				}
			}
			else if(e.getActionCommand()=="student") {
				removeCenterPanel();	CenterPanel.add(studentCenterPanel);
				setFullLight();	studentBtn.setIcon(studentBtnImage);		studentPanel.setVisible(true);
			}
			else if(e.getActionCommand()=="manage") {
				removeCenterPanel();	CenterPanel.add(manageCenterPanel);
				setFullLight();	manageBtn.setIcon(manageBtnImage);			managePanel.setVisible(true);
			}
			CenterPanel.setVisible(false);	CenterPanel.setVisible(true);
		}
	};
    public void eventLightButton(ActionListener obj) {
    	dashboardBtn.addActionListener(obj);
    	teacherBtn.addActionListener(obj);
    	studentBtn.addActionListener(obj);
    	manageBtn.addActionListener(obj);
    }
	private void prepareGUI() {
		CenterPanel.setBounds(45,237,713,295);
        setTitle("CoursesRegistrationSystem"); setSize(800,600); setLayout(null);	
        setLocation(200,50); setResizable(false); setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/icon.png");  setIconImage(icon);	
		dashboardBtn.setBounds(60, 95, 128, 80);		dashboardBtn.setActionCommand("dashboard");
		teacherBtn.setBounds(190,95,128,80);			teacherBtn.setActionCommand("teacher");
		studentBtn.setBounds(320,95,128,80);			studentBtn.setActionCommand("student");
		manageBtn.setBounds(450,95,128,80);				manageBtn.setActionCommand("manage");
		dashboardPanel.setBounds(29, 188, 300, 48);		teacherPanel.setBounds(29, 188, 300, 48);
		studentPanel.setBounds(29, 188, 300, 48);		managePanel.setBounds(29, 188, 300, 48);
		
	}
	public MenuFrame() {
		prepareGUI();
		setContentPane(contentPanel);	CenterPanel.add(dashboardCenterPanel);	add(CenterPanel);
		add(dashboardBtn);		add(teacherBtn);	add(studentBtn);	add(manageBtn); 	
		add(dashboardPanel);	add(teacherPanel);	add(studentPanel);	add(managePanel);
		eventLightButton(LightDarkButton);
		setVisible(true);
	}
}
