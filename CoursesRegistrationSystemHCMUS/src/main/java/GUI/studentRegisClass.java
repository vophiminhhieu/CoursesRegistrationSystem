package GUI;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Date;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import Application.Data;
import DAL.DAO.ClassDao;
import DAL.DAO.JoinClassDao;
import DAL.POJO.Classs;
import DAL.POJO.JoinClass;
import DAL.POJO.Study;



public class studentRegisClass extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private JoinClassDao joinClassDao = new JoinClassDao();
	private ClassDao classDao = new ClassDao();
	private Data data = new Data();
	private JLabel log = new JLabel("You have a activity class !!");
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
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
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");
		List<JoinClass> list = joinClassDao.getJoinClass();
		for(int i=0;i<list.size();i++) {
			if(list.get(i).getPk().getIdStudent()==data.id) {
				if(list.get(i).getDone()==true) {
					log.setBounds(40,50,200,25); add(log);
					return;
				}
			}
		}
		List<Classs> classs=classDao.getClasss();
		String[] classS = new String[classs.size()];
		for(int i=0;i<classs.size();i++) {
			classS[i]=classs.get(i).getId()+" - "+classs.get(i).getName();
		}
		JLabel title = new JLabel("Choose your activity class : "); title.setBounds(0,20,200,25); add(title);
		JComboBox combo = new JComboBox(classS); 
		JPanel box = new JPanel(new BorderLayout()); box.add(combo); box.setBounds(10,100,450,25); add(box);
		Icon saveIcon = new ImageIcon("image/Menu/Dashboard/myProfile/save.png");
		JButton saveBtn = new JButton(saveIcon); saveBtn.setBounds(350,20,95,29); add(saveBtn);
		saveBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				JoinClass.Pk jPk = new JoinClass.Pk();
				for(int i=0;i<classs.size();i++) {
					if(combo.getItemAt(combo.getSelectedIndex()).equals(classs.get(i).getId()+" - "+classs.get(i).getName())){
						jPk.setIdClass(classs.get(i).getId());
					}
				}
				jPk.setIdStudent(data.id);
				JoinClass j = new JoinClass(jPk,false);
				joinClassDao.saveJoinClass(j);
				JLabel success = new JLabel("Change Success !!");
				success.setBounds(50,175,150,25); add(success);
				
			}
		});
	}

	
	public studentRegisClass(ActionListener obj) {

		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		
	}
}
