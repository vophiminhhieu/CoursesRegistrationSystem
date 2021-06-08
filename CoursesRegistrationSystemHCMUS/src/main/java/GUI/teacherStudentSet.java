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

import DAL.DAO.StudentDao;
import DAL.DAO.StudyDao;
import DAL.DAO.SubjectDao;
import DAL.POJO.Student;
import DAL.POJO.Study;
import DAL.POJO.Subject;
import DAL.POJO.Study.Pk;



public class teacherStudentSet extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon searchIcon = new ImageIcon("image/Menu/Teacher/Student/search.png");
	private JButton searchBtn = new JButton(searchIcon);
	private JFrame frame = new JFrame();
	private JPanel inScroll = new JPanel();
	private JTextField text = new JTextField();	private JPanel textPanel = new JPanel(new BorderLayout());
	private JLabel lab = new JLabel("Input Student ID : ");
	private JLabel err = new JLabel();
	private StudentDao stdDao=new StudentDao();
	private StudyDao studyDao = new StudyDao();
	private SubjectDao sbjDao = new SubjectDao();
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
					long idStd;
					try {
						idStd=Long.parseLong(text.getText());
					}
					catch(Exception exp) {
						err.setText("Error Input !!");
						return;
					}
					Student std = stdDao.getStudent(idStd);
					if(std==null) {
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
        frame.setSize(650,550);
	}
	private void on(String idStudent) {
		List<Study> stdList = studyDao.getStudy(idStudent);
		inScroll.setPreferredSize(new Dimension(500,25*(stdList.size())));
		inScroll.setLayout(null);
		JPanel panel =new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(750,550));
		JScrollPane sp=new JScrollPane();
		sp.setViewportView(inScroll);
		panel.add(sp,BorderLayout.CENTER);
		panel.setOpaque(true);
		panel.setLayout(null);
		Student _std = stdDao.getStudent(Long.parseLong(idStudent));
		List<JPanel> panelCell = new ArrayList<JPanel>();
		for(int i=0;i<stdList.size();i++) {
			JPanel cell = new JPanel();
			cell.setLayout(null);
			cell.setBounds(10,30*i,650,25);
			JLabel id = new JLabel(String.valueOf(_std.getId()));
			JLabel name = new JLabel(_std.getName());
			Subject sbj = sbjDao.getSubject(stdList.get(i).getPk().getIdSubject());
			JLabel idSubject = new JLabel(sbj.getId());
			JLabel nameSubject = new JLabel(sbj.getName());
			id.setBounds(0,0,100,25);
			name.setBounds(110,0,200,25);
			idSubject.setBounds(320,0,100,25);
			nameSubject.setBounds(430,0,150,25);
			Icon ic = new ImageIcon("image/Menu/Teacher/Student/check.png");
			JButton btn = new JButton(ic);
			btn.setBounds(600,5,14,14);
			cell.add(id);
			cell.add(name);
			cell.add(idSubject);
			cell.add(nameSubject);
			cell.add(btn);
			panel.add(cell);
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					Pk pk = new Pk();
					pk.setIdStudent(Long.parseLong(idStudent));
					pk.setIdSubject(sbj.getId());
					studyDao.deleteStudy(pk);
					Study std_ = new Study(pk,true);
					studyDao.saveStudy(std_);
					on(idStudent);
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
	public teacherStudentSet(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI(); 	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		add(searchBtn);	add(lab);	add(textPanel); add(err);
	}
}
