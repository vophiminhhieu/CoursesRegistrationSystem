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
import DAL.DAO.CourseDao;
import DAL.DAO.RegistrationDao;
import DAL.DAO.StudyDao;
import DAL.DAO.SubjectDao;
import DAL.POJO.Course;
import DAL.POJO.Registration;
import DAL.POJO.Student;
import DAL.POJO.Study;
import DAL.POJO.Subject;
import DAL.POJO.User;
import DAL.POJO.Study.Pk;

public class studentRegisRegis extends JPanel {
	private ImageIcon background = new ImageIcon("image/Menu/leftCenterPanel.png");
	private Icon backIcon = new ImageIcon("image/Menu/Dashboard/userAccount/back.png");
	private JButton backBtn = new JButton(backIcon);
	private JFrame frame = new JFrame();
	private JPanel inScroll = new JPanel();
	private StudyDao studyDao= new StudyDao();
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
	private void checkTime() {
		Date date = new Date();
		LocalDate localDate = date.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
		RegistrationDao resDao = new RegistrationDao();
		List<Registration> res = resDao.getRegistration();
		String end="";
		boolean a =false;
		for(int i=0;i<res.size();i++) {
			if(res.get(i).getCurrent()==true) {
				a=true;
				end=res.get(i).getEnd_Date();
			}
		}
		if(a==false) {
			 background = new ImageIcon("image/Menu/expired.png");
			 frame.setVisible(false);
			 setVisible(false);
			 setVisible(true);
		}
		else {
			try {
				Date datee = new SimpleDateFormat("dd/MM/yyyy").parse(end);
				if(datee.compareTo(date)<0) {
					 background = new ImageIcon("image/Menu/expired.png");
					 frame.setVisible(false);
					 setVisible(false);
					 setVisible(true);
					 return;
				}
				
			}
			catch(Exception ex){
			}
			on();
		}
		
	}
	private void prepareGUI() {
		backBtn.setBounds(400, 200, 95, 29);	backBtn.setActionCommand("Back");
		frame.setLayout(null);
        frame.setDefaultCloseOperation(WindowConstants.HIDE_ON_CLOSE);
        frame.setLocation(200,80);
        frame.setTitle("Registration Course");
        frame.pack();
        frame.setSize(800,600);
		checkTime();
	}

	private void on() {
		Data data = new Data();
		List<Study> stdList = studyDao.getStudyFull(String.valueOf(data.id));
		List<Course> courList = new ArrayList<Course>();		CourseDao courDao = new CourseDao();
		JPanel topScroll = new JPanel();		topScroll.setPreferredSize(new Dimension(785,600)); //3333333333333
		topScroll.setLayout(null);		inScroll.setPreferredSize(new Dimension(785,25*(stdList.size())));
		inScroll.setLayout(null);		JPanel panel =new JPanel(new BorderLayout());
		panel.setPreferredSize(new Dimension(785,300));		JScrollPane sp=new JScrollPane();
		sp.setViewportView(inScroll);		panel.add(sp,BorderLayout.CENTER);	panel.setOpaque(true);			
		panel.setLayout(null);		panel.setLocation(0,300);	
		JLabel title = new JLabel("                     ------ Course -------"); title.setBounds(100, 0, 300, 20);
		panel.add(title);
		List<Course> resCour = courDao.getCourse();
		Study.Pk stdPk = new Pk();
		stdPk.setIdStudent(data.id);
		int leg=0;
		for(int i=0;i<resCour.size();i++) {
			String timeM = resCour.get(i).getTime();
			String timeD = resCour.get(i).getDay();
			String IDS = resCour.get(i).getIdSubject();
			stdPk.setIdSubject(IDS);
			if(studyDao.getStudy(stdPk)==null) {
				JPanel cell = new JPanel();	cell.setLayout(null); cell.setBounds(10,30*leg+20,785,25); leg++;
				Icon ic = new ImageIcon("image/Menu/Teacher/Student/check.png"); JButton btn = new JButton(ic);	btn.setBounds(0,5,14,14);
				btn.addActionListener(new ActionListener() {
					public void actionPerformed(ActionEvent e) {
						checkTime();
						if(stdList.size()==8) {
							JOptionPane.showMessageDialog(frame," Not More Than 8 Course !!");
						}
						else {
							boolean check =false;
							for(int j=0;j<stdList.size();j++) {
								Course tmp = courDao.getCourse(stdList.get(j).getPk().getIdSubject());
								if(tmp.getTime().equals(timeM)&&tmp.getDay().equals(timeD)) {
									check=true;
									System.out.println(tmp.getTime()+")))))))))))");
									System.out.println(resCour.get(j).getTime()+")))))))))))");
									System.out.println(tmp.getDay()+")))))))))))");
									System.out.println(resCour.get(j).getDay()+")))))))))))");
								}
							}
							if(check==false) {
								 Study std = new Study();
								 Study.Pk stPk = new Study.Pk();
								 stPk.setIdSubject(IDS);
								 stPk.setIdStudent(data.id);
								 std.setPk(stPk);
								 std.setDone(false);
								 studyDao.saveStudy(std);
								 System.out.print("-------------------------"+IDS);
								 on();
							}
							else {
								JOptionPane.showMessageDialog(frame," Coincident Time !! ");
							}
						}
					}
				});
				cell.add(btn);
				JLabel id = new JLabel(resCour.get(i).getIdSubject()); id.setBounds(20,0,60,25);
				JLabel name = new JLabel(resCour.get(i).getNameSubject()); name.setBounds(85,0,200,25);
				JLabel credits = new JLabel(String.valueOf(resCour.get(i).getCredits())); credits.setBounds(290,0,20,25);
				JLabel nameClass = new JLabel(resCour.get(i).getNameClass()); nameClass.setBounds(315,0,50,25);
				JLabel idTeacher = new JLabel(resCour.get(i).getIdTeacher()); idTeacher.setBounds(370,0,60,25);
				JLabel nameTeacher = new JLabel(resCour.get(i).getNameTeacher()); nameTeacher.setBounds(435,0,140,25);
				JLabel day = new JLabel(resCour.get(i).getDay()); day.setBounds(580,0,100,25);
				JLabel time = new JLabel();
				if(resCour.get(i).getTime().equals("ca1")) {
					time.setText("7:30 - 9:30");
				}
				else if(resCour.get(i).getTime().equals("ca2")) {
					time.setText("9:30 - 11:30");
				}
				else if(resCour.get(i).getTime().equals("ca3")) {
					time.setText("13:30 - 15:30");
				}
				else if(resCour.get(i).getTime().equals("ca4")) {
					time.setText("15:30 - 17:30");
				}
				time.setBounds(685,0,100,25);
				cell.add(id); cell.add(nameClass); cell.add(nameTeacher); cell.add(name); cell.add(credits);
				cell.add(idTeacher); cell.add(day); cell.add(time); 
				panel.add(cell);
			}
		}
		JLabel text = new JLabel("   -------------------- Your Course ------------------------");
		text.setBounds(70,30*leg + 20,400,25); leg++; panel.add(text);
		for(int i=0;i<stdList.size();i++) {
			courList.add(courDao.getCourse(stdList.get(i).getPk().getIdSubject()));
			JPanel cell = new JPanel();
			cell.setLayout(null);
			cell.setBounds(10,30*i+30*leg+20,785,25);
			JLabel id = new JLabel(stdList.get(i).getPk().getIdSubject());
			JLabel name = new JLabel(courList.get(i).getNameSubject());
			JLabel credits = new JLabel(String.valueOf(courList.get(i).getCredits()));
			
			JLabel nameClass = new JLabel(courList.get(i).getNameClass());
			JLabel idTeacher = new JLabel(courList.get(i).getIdTeacher());
			JLabel nameTeacher = new JLabel(courList.get(i).getNameTeacher());
			JLabel day = new JLabel(courList.get(i).getDay());
			JLabel time = new JLabel();
			if(courList.get(i).getTime().equals("ca1")) {
				time.setText("7:30 - 9:30");
			}
			else if(courList.get(i).getTime().equals("ca2")) {
				time.setText("9:30 - 11:30");
			}
			else if(courList.get(i).getTime().equals("ca3")) {
				time.setText("13:30 - 15:30");
			}
			else if(courList.get(i).getTime().equals("ca4")) {
				time.setText("15:30 - 17:30");
			}

			id.setBounds(20,0,60,25);
			name.setBounds(85,0,200,25);
			credits.setBounds(290,0,20,25);
			nameClass.setBounds(315,0,50,25);
			idTeacher.setBounds(370,0,60,25);
			nameTeacher.setBounds(425,0,150,25);
			day.setBounds(580,0,100,25);
			time.setBounds(685,0,100,25);
			Icon ic = new ImageIcon("image/Menu/Teacher/Student/checkDone.png");
			JButton btn = new JButton(ic);
			btn.setBounds(0,5,14,14);
			cell.add(id);
			cell.add(name);
			cell.add(credits);
			 cell.add(nameClass);
			cell.add(idTeacher);
			cell.add(nameTeacher);
			cell.add(day);
			cell.add(time);
			cell.add(btn);
			panel.add(cell);
			String tmpp = stdList.get(i).getPk().getIdSubject();
			btn.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					checkTime();
					Study.Pk nPk = new Study.Pk();
					nPk.setIdStudent(data.id);
					nPk.setIdSubject(tmpp);
					studyDao.deleteStudy(nPk);
					on();
					
				}
			});
		}
		
        frame.setContentPane(panel);
        frame.setVisible(true);
		
	}
	public studentRegisRegis(ActionListener obj) {

		setLayout(null);	add(backBtn);	prepareGUI();	backBtn.addActionListener(obj);	setBounds(0, 10, 495, 281);
		
	}
}
