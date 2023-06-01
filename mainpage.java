package labDB;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.border.EmptyBorder;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Font;
import javax.swing.ImageIcon;
import java.awt.*;
import javax.swing.JTextField;
import java.awt.Color;
import javax.swing.SwingConstants;

public class mainpage extends JFrame {
	JLabel l1;	
	
	
	
	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtHospitalDatabase;

	/**
	 * Launch the application.
	 */
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					mainpage frame = new mainpage();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public mainpage() {
		setTitle("MEDILAB-Pathology Software/sahana");
		setSize(500,500);
		setLayout(new BorderLayout());
		setContentPane(new JLabel(new ImageIcon("img.jpg")));
		setLayout(new FlowLayout());
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(200, 150, 500, 400);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		
		JButton btnHospital = new JButton("Hospital Information");
		btnHospital.setFont(new Font("Tahoma", Font.ITALIC, 13));
		
		btnHospital.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				hospital h = new hospital();
			    h.setVisible(true);
			}
		});
		btnHospital.setBounds(25, 100, 200, 50);
		btnHospital.setBackground(Color.PINK);
		contentPane.add(btnHospital);
		
		JButton btnDoctor = new JButton("Doctor");
		btnDoctor.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				Doctor d = new Doctor();
				d.setVisible(true);
			}
		});
		btnDoctor.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnDoctor.setBounds(25, 175, 200, 50);
		btnDoctor.setBackground(Color.PINK);
		contentPane.add(btnDoctor);
		
		JButton btnPatientInfo = new JButton("Patient Info");
		btnPatientInfo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				patient p = new patient();
				p.setVisible(true);
			}
		});
		btnPatientInfo.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnPatientInfo.setBounds(250, 100, 200, 50);
		btnPatientInfo.setBackground(Color.PINK);
		contentPane.add(btnPatientInfo);
		
		JButton btnTest = new JButton("Test");
		btnTest.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				Test t = new Test();
				t.setVisible(true);
			}
		});
		btnTest.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnTest.setBounds(250, 175, 200, 50);
		btnTest.setBackground(Color.PINK);
		contentPane.add(btnTest);
		
		JButton btnReport = new JButton("Report");
		btnReport.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				 Report r = new Report();
				 r.setVisible(true);
				
			}
		});
		btnReport.setFont(new Font("Tahoma", Font.ITALIC, 13));
		btnReport.setBounds(145, 250, 200, 50);
		btnReport.setBackground(Color.PINK);
		contentPane.add(btnReport);
		

		txtHospitalDatabase = new JTextField();
		txtHospitalDatabase.setText("MEDILAB");
		txtHospitalDatabase.setHorizontalAlignment(SwingConstants.CENTER);
		txtHospitalDatabase.setForeground(Color.BLUE);
		
		txtHospitalDatabase.setFont(new Font("ELEPHANT", Font.BOLD, 17));
		txtHospitalDatabase.setEditable(false);
		txtHospitalDatabase.setColumns(10);
		txtHospitalDatabase.setBackground(Color.YELLOW);
		txtHospitalDatabase.setBounds(75, 27, 350, 42);
		contentPane.add(txtHospitalDatabase);
	}
}


