package GUI;

import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import BUS.savePasswordChange;

import javax.swing.JLabel;

public class ChangePassword extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private JPanel oldPasswordPanel = new JPanel(new BorderLayout());
	private JPanel newPasswordPanel = new JPanel(new BorderLayout());
	private JPanel re_typeNewPasswordPanel = new JPanel(new BorderLayout());
	private JPasswordField oldPassword = new JPasswordField();
	private JPasswordField newPassword = new JPasswordField();
	private JPasswordField re_typeNewPassword = new JPasswordField();
	private JLabel oldPasswordLabel = new JLabel("Current            :");
	private JLabel newPasswordLabel = new JLabel("New                  :");
	private JLabel re_typeNewPasswordLabel = new JLabel("Re-type new   :");
	private JLabel comment = new JLabel();
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private Icon saveIcon = new ImageIcon("image/Menu/Dashboard/userAccount/save.png");
	private JButton backBtn = new JButton(backIcon);
	private JButton saveBtn = new JButton(saveIcon);

	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}

	private void prepareGUI() {
		oldPasswordPanel.add(oldPassword);
		oldPasswordPanel.setBounds(220, 35, 208, 20);
		newPasswordPanel.add(newPassword);
		newPasswordPanel.setBounds(220, 75, 208, 20);
		re_typeNewPasswordPanel.add(re_typeNewPassword);
		re_typeNewPasswordPanel.setBounds(220, 115, 208, 20);
		oldPasswordLabel.setBounds(100, 35, 100, 20);
		newPasswordLabel.setBounds(100, 75, 100, 20);
		re_typeNewPasswordLabel.setBounds(100, 115, 100, 20);
		backBtn.setBounds(220, 160, 95, 29);
		saveBtn.setBounds(330, 160, 95, 29);
		backBtn.setActionCommand("Back");
	}

	private ActionListener actionListenerSave = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			savePasswordChange saveChange = new savePasswordChange(oldPassword.getText(),newPassword.getText(),re_typeNewPassword.getText());
			int check=saveChange.checkSave();
			if(check==1) {
				
			}
			else if(check==2) {
				
			}
			else if(check==3) {
				
			}
			else {
				System.out.print("save success");
				saveChange.save();
			}

		}
	};

	public ChangePassword(ActionListener actionChangePassword) {
		setLayout(null);
		prepareGUI();
		add(oldPasswordPanel);
		add(newPasswordPanel);
		add(re_typeNewPasswordPanel);
		add(oldPasswordLabel);
		add(newPasswordLabel);
		add(re_typeNewPasswordLabel);
		add(backBtn);
		add(saveBtn);
		backBtn.addActionListener(actionChangePassword);
		saveBtn.addActionListener(actionListenerSave);
		setBounds(0, 10, 495, 281);
	}
}
