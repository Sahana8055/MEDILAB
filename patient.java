package labDB;


import java.awt.EventQueue;
import net.proteanit.sql.DbUtils;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
//import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

import javax.swing.JTable;
import javax.swing.JScrollPane;
//@SuppressWarnings("unused")
public class patient extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtpatientiformation;
	private JTextField  PidtextField;
	private JTextField  FnametextField;
	private JTextField  LnametextField;
	private JTextField  AddresstextField;
	private JTextField  GendertextField;
	private JTextField  BloodgrouptextField;
	private JTextField  DOBtextField;
	private JTextField  Ph_notextField;
	private JTable table3;
	private Connection con=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					patient frame = new patient();
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
	public patient() {
		
		con=DB.getConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100,1100, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtpatientiformation = new JTextField();
		txtpatientiformation.setText("PATIENT INFORMATION");
		txtpatientiformation.setHorizontalAlignment(SwingConstants.CENTER);
		txtpatientiformation.setForeground(Color.BLACK);
		txtpatientiformation.setFont(new Font("Arial Black", Font.BOLD, 15));
		txtpatientiformation.setEditable(false);
		txtpatientiformation.setColumns(10);
		txtpatientiformation.setBackground(Color.ORANGE);
		txtpatientiformation.setBounds(227, 11, 238, 25);
		contentPane.add(txtpatientiformation);
		
		 FnametextField = new JTextField();
		 FnametextField.setColumns(10);
		 FnametextField.setBounds(160, 120, 133, 25);
		contentPane.add(FnametextField);
		
		 LnametextField = new JTextField();
		 LnametextField.setColumns(10);
		 LnametextField.setBounds(160, 174, 133, 25);
		contentPane.add( LnametextField);
		
		AddresstextField = new JTextField();
		AddresstextField.setColumns(10);
		AddresstextField.setBounds(160, 228, 133, 25);
		contentPane.add(AddresstextField);
		
		
		GendertextField = new JTextField();
		GendertextField.setColumns(10);
		GendertextField.setBounds(160, 290, 133, 25);
		contentPane.add(GendertextField);
		
		
		BloodgrouptextField = new JTextField();
		BloodgrouptextField.setColumns(10);
		BloodgrouptextField.setBounds(160, 350, 133, 25);
		contentPane.add(BloodgrouptextField);
		
		
		DOBtextField = new JTextField();
		DOBtextField.setColumns(10);
		DOBtextField.setBounds(160, 415, 133, 25);
		contentPane.add(DOBtextField);
		
		

		Ph_notextField = new JTextField();
		Ph_notextField.setColumns(10);
		Ph_notextField.setBounds(160, 490, 133, 25);
		contentPane.add(Ph_notextField);
		
		
		
		
		
		JButton button = new JButton("INSERT");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				ResultSet id;
				
		
					
					String fname = FnametextField.getText();
					String lname = LnametextField.getText();
					String address=AddresstextField.getText();
					String gender=GendertextField.getText();
					String bloodgroup= BloodgrouptextField.getText();
					String dob=DOBtextField.getText();
					String phno=Ph_notextField.getText();

					     try {
					        Class.forName("oracle.jdbc.driver.OracleDriver");
					        Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
					        String sql1 = "SELECT MAX(P_ID) FROM PATIENT";
					        PreparedStatement pst1=con2.prepareStatement(sql1);
					        id= pst1.executeQuery();
					        String id1 = null;
					        if(id.next())
					        	id1 = id.getString(1);
					        if(id1==null) {
					        	id1="0";
					        }
					        
					        int id2 = Integer.parseInt(id1);
					        id2=id2+1;
					        if(fname.isEmpty()==false && lname.isEmpty()==false && address.isEmpty()==false && gender.isEmpty()==false && bloodgroup.isEmpty()==false && dob.isEmpty()==false && phno.isEmpty()==false)
					        {
					        	String sql = "insert into patient values('"+id2+"','"+fname+"','"+lname+"','"+address+"','"+gender+"','"+bloodgroup+"','"+dob+"','"+phno+"')";
					        PreparedStatement pst=con2.prepareStatement(sql);
					        pst.executeQuery();
					        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
					        }
					        else
					        {
						        JOptionPane.showMessageDialog(null,	"Data not inserted");

					        }
					    } catch(Exception e1)  {  System.out.println(e1);}
					
				}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(50, 600, 100, 30);
		contentPane.add(button);
		
		

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(300, 72,700, 300);
		contentPane.add(scrollPane);
		
		table3 = new JTable();
		scrollPane.setViewportView(table3);
		
		
		JButton button_2 = new JButton("VIEW");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String query="select * from patient order by p_id";
					PreparedStatement pst1=con.prepareStatement(query);
					ResultSet rs=pst1.executeQuery();
					table3.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBounds(180, 600, 100, 30);
		contentPane.add(button_2);
		
		
		
		
		JLabel lblFPatientName = new JLabel("First name");
		lblFPatientName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblFPatientName.setBounds(10, 122, 93, 14);
		contentPane.add(lblFPatientName);
		
		JLabel lblLPatientName = new JLabel("Last name");
		lblLPatientName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblLPatientName.setBounds(10, 172, 93, 14);
		contentPane.add(lblLPatientName);
		
		JLabel lblAddress = new JLabel("Address");
		lblAddress.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblAddress.setBounds(10, 226, 93, 14);
		contentPane.add(lblAddress);
		
		JLabel lblgender = new JLabel("Gender");
		lblgender.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblgender.setBounds(10, 290, 68, 14);
		contentPane.add(lblgender);
		
		
		JLabel lblbloodgroup = new JLabel("Blood Group");
		lblbloodgroup.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblbloodgroup.setBounds(10, 350, 100, 14);
		contentPane.add(lblbloodgroup);
		
		
		JLabel lbldob = new JLabel("DOB");
		lbldob.setFont(new Font("Tahoma", Font.BOLD, 12));
		lbldob.setBounds(10, 420, 68, 14);
		contentPane.add(lbldob);
		
		JLabel lblphno = new JLabel("Phone Number");
		lblphno.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblphno.setBounds(10, 490, 100, 14);
		contentPane.add(lblphno);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainpage m = new mainpage();
				m.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(315,600, 100, 30);
		contentPane.add(btnExit);
		
		
		
	}
}

