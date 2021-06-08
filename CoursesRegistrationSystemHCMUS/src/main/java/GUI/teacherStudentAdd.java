package GUI;


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Scrollbar;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

import DAL.DAO.ClassDao;
import DAL.DAO.JoinClassDao;
import DAL.DAO.SemesterDao;
import DAL.DAO.StudentDao;
import DAL.POJO.Classs;
import DAL.POJO.JoinClass;
import DAL.POJO.JoinClass.Pk;
import DAL.POJO.Student;

public class teacherStudentAdd extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon searchIcon = new ImageIcon("image/Menu/Teacher/Student/search.png");
	private JButton searchBtn = new JButton(searchIcon);
	private JFrame frame = new JFrame();
	private JPanel inScroll = new JPanel();
	private List<JoinClass> join = new ArrayList<JoinClass>();
	private StudentDao stdDao = new StudentDao();
	private JoinClassDao jclDao=new JoinClassDao();
	private JTextField text = new JTextField();	private JPanel textPanel = new JPanel(new BorderLayout());
	private ClassDao clDao = new ClassDao();
	private JLabel lab = new JLabel("Input Class ID : ");
	private JLabel err = new JLabel();
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");
		err.setBounds(250,200,95,29);
		textPanel.setBounds(170,30,150,20);	textPanel.add(text);	lab.setBounds(0,30,150,20);
		searchBtn.setBounds(350,30,95,29); 
		searchBtn.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(text.getText()=="") {
					err.setText("Null Input !!");
				}
				else {
					Classs clss = clDao.getClasss(text.getText());
					if(clss==null) {
						err.setText("Not Found !!");
					}
					else {
						on(text.getText());
					}
				}
			}
		});
		frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocation(200,80);
        frame.setTitle("List of Student");
        frame.pack();
        frame.setSize(550,550);
	}
	private void on(String idClass) {
		List<JoinClass> joinCl = jclDao.getJoinClass(idClass);
		inScroll.setPreferredSize(new Dimension(500,25*(joinCl.size())));
		inScroll.setLayout(null);
		JPanel panel =new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(750,550));
		JScrollPane sp=new JScrollPane();
		sp.setViewportView(inScroll);
		panel.add(sp,BorderLayout.CENTER);
		panel.setOpaque(true);
		panel.setLayout(null);
		List<JPanel> panelCell = new ArrayList<JPanel>();
		for(int i=0;i<joinCl.size();i++) {
			JPanel cell = new JPanel();
			cell.setLayout(null);
			cell.setBounds(10,30*i,480,25);
			Student std = stdDao.getStudent(joinCl.get(i).getPk().getIdStudent());
			JLabel id = new JLabel(String.valueOf(std.getId()));
			JLabel name = new JLabel(std.getName());
			id.setBounds(0,0,120,25);
			name.setBounds(150,0,300,25);
			Icon ic = new ImageIcon("image/Menu/Teacher/Student/check.png");
			JButton btn = new JButton(ic);
			btn.setBounds(460,5,14,14);
			cell.add(id);
			cell.add(name);
			cell.add(btn);
			panel.add(cell);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pk pk = new Pk();
					pk.setIdClass(idClass);
					pk.setIdStudent(std.getId());
					jclDao.deleteJoinClass(pk);
					JoinClass jc = new JoinClass(pk,true);
					jclDao.saveJoinClass(jc);
					on(idClass);
					frame.setVisible(false);
					frame.setVisible(true);
				}
			});
		}
		
        frame.setContentPane(panel);
        frame.setVisible(true);
		
	}
	private ActionListener actionChangeSearch = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			
		}
	};
	public teacherStudentAdd(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI(); 	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(searchBtn);	add(lab);	add(textPanel); add(err);
	}
}
