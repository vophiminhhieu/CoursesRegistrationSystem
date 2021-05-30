package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;

public class DashboardPanel extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/centerPanel.png");
    private Icon userAccountIcon=new ImageIcon("image/Menu/Dashboard/userAccount.png");
    private JButton userAccount=new JButton(userAccountIcon);
    private Icon myProfileIcon=new ImageIcon("image/Menu/Dashboard/myProfile.png");
    private JButton myProfile=new JButton(myProfileIcon);
    private Icon userAccount_changePasswordIcon=new ImageIcon("image/Menu/Dashboard/userAccount/thaydoimatkhau.png");
    private JButton userAccount_changePassword=new JButton(userAccount_changePasswordIcon);
    private Icon myProfile_generateInfIcon=new ImageIcon("image/Menu/Dashboard/myProfile/thongtinchung.png");
    private JButton myProfile_generateInf=new JButton(myProfile_generateInfIcon);
    private JPanel leftCenterPanel = paintToPanel("image/Menu/leftCenterPanel.png");
    private JPanel rightCenterPanel = paintToPanel("image/Menu/right_centerPanel.png");
    private JPanel newLeftChangePassword = new JPanel();
    private JPanel newLeftGenerateInf = new JPanel();
    
	@Override
    protected void paintComponent(Graphics g){
		super.paintComponent(g);
	    if(background!=null){
	        g.drawImage(background.getImage(), 0, 0, getWidth(),getHeight(),null);
	    }
	}
	private ActionListener actionChangePassword = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Back")) {
				remove(newLeftChangePassword);
				add(leftCenterPanel);
			}
			else {
				remove(leftCenterPanel);
				newLeftChangePassword = new ChangePassword(actionChangePassword);
				add(newLeftChangePassword);
			}
			setVisible(false);
			setVisible(true);
		}
	};
	private ActionListener actionGenerateInf = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("Back")) {
				remove(newLeftGenerateInf);
				add(leftCenterPanel);
			}
			else {
				remove(leftCenterPanel);
				newLeftGenerateInf = new GenerateInf(actionGenerateInf);
				add(newLeftGenerateInf);
			}
			setVisible(false);
			setVisible(true);
		}
	};
	
	private ActionListener dashboardAction = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(e.getActionCommand().equals("userAccount")) {
				rightCenterPanel.removeAll();
				rightCenterPanel.add(userAccount_changePassword);
			}
			else if(e.getActionCommand().equals("myProfile")) {
				rightCenterPanel.removeAll();
				rightCenterPanel.add(myProfile_generateInf);
			}
			rightCenterPanel.setVisible(false);
			rightCenterPanel.setVisible(true);
		}
	};
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
		userAccount.setBounds(10, 10, 111, 111);			userAccount_changePassword.setBounds(5,10,174,38);
		myProfile.setBounds(135,10,111,111); 				myProfile.setActionCommand("myProfile");
		leftCenterPanel.add(userAccount);					rightCenterPanel.add(userAccount_changePassword);
		leftCenterPanel.add(myProfile); 					userAccount.setActionCommand("userAccount");
		userAccount.addActionListener(dashboardAction);		myProfile_generateInf.setBounds(5,10,174,38);
		myProfile.addActionListener(dashboardAction);
	}
	
	public DashboardPanel() {
		prepareGUI();
		add(leftCenterPanel);
		add(rightCenterPanel);
		userAccount_changePassword.addActionListener(actionChangePassword);
		myProfile_generateInf.addActionListener(actionGenerateInf);
	}
    
}
