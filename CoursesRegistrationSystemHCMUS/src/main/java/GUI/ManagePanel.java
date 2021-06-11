package GUI;

import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import BUS.Login;

public class ManagePanel extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/centerPanel.png");
    private JPanel leftCenterPanel = paintToPanel("image/Menu/fea.png");
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
		leftCenterPanel.setBounds(0,10,495,281);	rightCenterPanel.setBounds(520,10,183,280);
		}
	
	public ManagePanel() {
		prepareGUI();	add(leftCenterPanel);	add(rightCenterPanel);
		JFrame f = (JFrame) SwingUtilities.getAncestorOfClass(JFrame.class, this);
		
	}
}
