package GUI;



import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import DAL.DAO.RegistrationDao;
import DAL.DAO.SemesterDao;
import DAL.DAO.SemesterStaticDao;
import DAL.DAO.SubjectDao;
import DAL.DAO.TeacherDao;
import DAL.DAO.UserDao;
import DAL.POJO.Registration;
import DAL.POJO.Semester;
import DAL.POJO.SemesterStatic;
import DAL.POJO.Subject;
import DAL.POJO.Teacher;
import DAL.POJO.User;
import DAL.POJO.Semester.Pk;

public class teacherRegistrationAdd extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private Icon addIcon = new ImageIcon("image/Menu/Dashboard/userAccount/save.png");
	private JButton addBtn = new JButton(addIcon);
	private JLabel startDateLabel = new JLabel("Input Registration Start Date: ");
	private JLabel endDateLabel = new JLabel("Input Registration End Date: ");
	private JTextField startDate= new JTextField();	private JPanel startDatePanel = new JPanel(new BorderLayout());
	private JTextField endDate= new JTextField();	private JPanel endDatePanel = new JPanel(new BorderLayout());
	private JLabel error = new JLabel();
	private JLabel log = new JLabel();
	private RegistrationDao resDao = new RegistrationDao();
	private SemesterStaticDao ssDao = new SemesterStaticDao();
	private List<SemesterStatic> ss = ssDao.getSemesterStatic();
	private SemesterStatic ssNow = ss.get(0);
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		if (background != null) {
			g.drawImage(background.getImage(), 0, 0, getWidth(), getHeight(), null);
		}
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");	addBtn.setBounds(295,200,95,29);
		startDatePanel.setBounds(230,30,180,20);	startDatePanel.add(startDate);		startDateLabel.setBounds(30,30,200,20);
		endDatePanel.setBounds(230,60,180,20);	endDatePanel.add(endDate);		endDateLabel.setBounds(30,60,200,20);
		error.setBounds(30,200,350,20);  check(); log.setBounds(30,170,350,20);
	}
	private void showAll(boolean show) {
		startDateLabel.setVisible(show);
		endDateLabel.setVisible(show);
		startDatePanel.setVisible(show);
		endDatePanel.setVisible(show);
		addBtn.setVisible(show);
		log.setVisible(show);
	}
	private void check() {
		if(ssNow==null){
			error.setText("You don't have current semester !!");
			showAll(false);
			return;
		}
		List<Registration> listRes = resDao.getRegistration();
		for(int i=0;i<listRes.size();i++) {
			if(listRes.get(i).getCurrent()==true) {
				error.setText("You had current registration !!!");
				showAll(false);
				return;
			}
		}
		log.setText("Current Semester: "+ssNow.getPk().getName()+"-"+ssNow.getPk().getYear()+": "+ssNow.getStart_Date()+"-"+ssNow.getEnd_Date());
		showAll(true);
	}
	
	private ActionListener actionChangeAdd = new ActionListener() {
		public void actionPerformed(ActionEvent e) {
			if(startDate.getText()==""||endDate.getText()=="") {
				error.setText("NULL INPUT !!! ");
			}
			else {
				try {		
					Date date1=new SimpleDateFormat("dd/MM/yyyy").parse(startDate.getText());
					Date date2=new SimpleDateFormat("dd/MM/yyyy").parse(endDate.getText());
					if(date1.compareTo(date2)>0) {
						error.setText("StartDate after EndDate !!!");
						return;
					}
					else {
						Date date3 = new SimpleDateFormat("dd/MM/yyyy").parse(ssNow.getStart_Date());
 						if(date2.compareTo(date3)>0) {
 							error.setText("EndDate of Registration after StartDate of Semester !!");
 							return;
 						}
 						else  {
 							Registration.Pk resPk= new Registration.Pk();
 							resPk.setName(ssNow.getPk().getName());
 							resPk.setYear(ssNow.getPk().getYear());
 							resDao.deleteRegistration(resPk);
 							Registration res = new Registration(resPk,startDate.getText(),endDate.getText(),true);
 							resDao.saveRegistration(res);
 							showAll(false);
 							error.setText("Success !!");
 						}
					}
				}
				catch(Exception ex) {
					error.setText("Error Input !!");
				}
			}
			
		}
	};
	public teacherRegistrationAdd(ActionListener obj) {
		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		addBtn.addActionListener(actionChangeAdd);	add(log);
		add(startDatePanel);	add(startDateLabel);	add(endDatePanel);	add(endDateLabel);	add(addBtn);	add(error);
	}
}
