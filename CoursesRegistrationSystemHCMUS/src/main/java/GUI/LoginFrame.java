package GUI;
/*
         800
    |----------|
600 |          | 600    ===>  Location: (200,50)
    |----------|
         800
*/


import java.awt.BorderLayout;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.UIManager;

public class LoginFrame extends JFrame{
    private JPanel contentPanel = paintToPanel("image/Login/background.png");
    private JPanel centerPanel   = paintToPanel("image/Login/CenterPanel.png");
    private JPanel WithoutRobot = paintToPanel("image/Login/CenterPanelIsNotRobot.png");
    private JPanel WithoutUsername = paintToPanel("image/Login/CenterPanelIsNotUsername.png");
    private JPanel WithoutPassword = paintToPanel("image/Login/CenterPanelIsNotPassword.png");
    private JPanel WithoutMissing = paintToPanel("image/Login/CenterPanelIsNotMissing.png");
    private JPanel panelUsername = new JPanel(new BorderLayout());
    private JPanel panelPassword = new JPanel(new BorderLayout());
    private JTextField username = new JTextField();
    private JPasswordField password = new JPasswordField();
    private Icon loginButtonImage = new ImageIcon("image/Login/loginButton.png");
    private JButton loginButton = new JButton(loginButtonImage);
    private Icon missingButtonImage = new ImageIcon("image/Login/missingpass.png");
    private JButton missingButton = new JButton(missingButtonImage);
    private Icon isRobotImage = new ImageIcon("image/Login/isRobot.png");
    private Icon notIsRobotImage = new ImageIcon("image/Login/youarepeople.png");
    private JCheckBox isRobot = new JCheckBox(isRobotImage);
    
    public boolean isRobotIsCheck=false;
    
    private void prepareGUI(){
        setTitle("CoursesRegistrationSystem");
        setSize(800,600);
        setLayout(null);
        setLocation(200,50);
        setResizable(false);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        Image icon = Toolkit.getDefaultToolkit().getImage("image/icon.png");  
        setIconImage(icon);
        panelUsername.add(username);
        panelUsername.setBounds(35,110,250,30);
        panelPassword.add(password);
        panelPassword.setBounds(35,190,250,30);
        loginButton.setBounds(195,325,97,25);
        missingButton.setBounds(31,325,144,25);
        isRobot.setBounds(30,251,40,40);
    }
    private void eventRobot(){
        isRobot.addItemListener(new ItemListener(){
            public void itemStateChanged(ItemEvent e){
                isRobot.setIcon(notIsRobotImage);
                isRobotIsCheck=true;
            }
        });
    }
    public void eventLoginButton(ActionListener obj) {
    	loginButton.addActionListener(obj);
    }
    public void eventMissingButton(ActionListener obj) {
    	missingButton.addActionListener(obj);
    }
    public String getUsername() {
    	return username.getText();
    }
    public String getPassword() {
    	return password.getText();
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
    public void refreshCenterPanelWithRobot() {
    	WithoutRobot.setBounds(40,360,252,25);
    	centerPanel.remove(WithoutUsername);
    	centerPanel.remove(WithoutMissing);
    	centerPanel.remove(WithoutPassword);
    	centerPanel.add(WithoutRobot);
    	centerPanel.setVisible(false);
    	centerPanel.setVisible(true);
    }
    public void refreshCenterPanelWithUsername() {
    	WithoutUsername.setBounds(40,360,252,25);
    	centerPanel.remove(WithoutRobot);
    	centerPanel.remove(WithoutPassword);
    	centerPanel.remove(WithoutMissing);
    	centerPanel.add(WithoutUsername);
    	centerPanel.setVisible(false);
    	centerPanel.setVisible(true);
    }
    public void refreshCenterPanelWithPassword() {
    	WithoutPassword.setBounds(40,360,252,25);
    	centerPanel.remove(WithoutRobot);
    	centerPanel.remove(WithoutUsername);
    	centerPanel.remove(WithoutMissing);
    	centerPanel.add(WithoutPassword);
    	centerPanel.setVisible(false);
    	centerPanel.setVisible(true);
    }
    public void refreshCenterPanelWithMissing() {
    	WithoutMissing.setBounds(40,360,252,25);
    	centerPanel.remove(WithoutRobot);
    	centerPanel.remove(WithoutUsername);
    	centerPanel.remove(WithoutPassword);
    	centerPanel.add(WithoutMissing);
    	centerPanel.setVisible(false);
    	centerPanel.setVisible(true);
    }
    public LoginFrame(){
        prepareGUI();
        contentPanel.add(centerPanel);
        centerPanel.setBounds(237,100,325,400);
        setContentPane(contentPanel);
        centerPanel.add(panelUsername);
        centerPanel.add(panelPassword);
        centerPanel.add(loginButton);
        centerPanel.add(missingButton);
        centerPanel.add(isRobot);
        eventRobot();
        setVisible(true);
    }
}
