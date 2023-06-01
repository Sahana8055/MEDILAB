package labDB;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
import javax.swing.JTextField;
import java.awt.Color;
import java.awt.Font;
import java.awt.ItemSelectable;
import javax.swing.DefaultListModel;
import javax.swing.SwingConstants;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JComboBox;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.JScrollPane;

public class Report extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtReportInformation;
	private JTextField P_idtextField;
	private JTextField Test_idtextField;
	private JTextField Doc_idtextField;
	private JTextField datetextField;
	private JTextField ridtextField;
	private JTextField readingtextField;
	private JTable table5;
	String fname;
	String pid;
	String tname;
	String tid;
	String dname;
	String did;
	private Connection con=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Report frame = new Report();
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
	public Report() {
		
		con=DB.getConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1100, 500);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtReportInformation = new JTextField();
		txtReportInformation.setText("REPORT INFORMATION");
		txtReportInformation.setHorizontalAlignment(SwingConstants.CENTER);
		txtReportInformation.setForeground(Color.BLACK);
		txtReportInformation.setFont(new Font("Arial Black", Font.BOLD, 15));
		txtReportInformation.setEditable(false);
		txtReportInformation.setColumns(10);
		txtReportInformation.setBackground(Color.ORANGE);
		txtReportInformation.setBounds(227, 11, 238, 25);
		contentPane.add(txtReportInformation);
		
		
		try
		{
		ResultSet rs;
		Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con21 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
		
        String sql11 = "SELECT FNAME FROM PATIENT ORDER BY FNAME";
		PreparedStatement pst11=con21.prepareStatement(sql11);
	    rs= pst11.executeQuery();
	    String str=null;
	    String str1="SELECT NAME";
	    while(rs.next())
	    {
	    str = rs.getString(1);
	    str1 = str1+","+str;
	    }
	    String str2[];
	    str2= str1.split(",");
	    //@SuppressWarnings("unchecked")
		JComboBox cb = new JComboBox(str2);
	    cb.setBounds(115, 72, 121, 25);
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        ItemSelectable is = (ItemSelectable)actionEvent.getSource();
	        Object selected[] = is.getSelectedObjects();
	        fname =  (String)selected[0];
	        try {
	        	Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
	        	String sql2  = "SELECT P_ID FROM PATIENT WHERE FNAME = '"+fname+"'";
	    		PreparedStatement pst2=con2.prepareStatement(sql2);
	    	    ResultSet rs2;
	    	    rs2= pst2.executeQuery();
	    	    if(rs2.next())
	    	    pid = rs2.getString(1);
	        }
	        catch(Exception e)
	        {
	        System.out.println(e);
	        }
	        }
	    };
	    cb.addActionListener(actionListener);
	    contentPane.add(cb);

		}
		catch(Exception e1)  {  System.out.println(e1);}	
		try
		{
		ResultSet rs;
		Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con21 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
		
        String sql11 = "SELECT TEST_NAME FROM TEST ORDER BY TEST_NAME";
		PreparedStatement pst11=con21.prepareStatement(sql11);
	    rs= pst11.executeQuery();
	    String str=null;
	    String str1="SELECT NAME";
	    while(rs.next())
	    {
	    str = rs.getString(1);
	    str1 = str1+","+str;
	    }
	    String str2[];
	    str2= str1.split(",");
	    JComboBox cb = new JComboBox(str2);
	    cb.setBounds(115, 120, 121, 25);
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        ItemSelectable is = (ItemSelectable)actionEvent.getSource();
	        Object selected[] = is.getSelectedObjects();
	        tname =  (String)selected[0];
	        try {
	        	Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
	        	String sql2  = "SELECT TEST_ID FROM TEST WHERE TEST_NAME = '"+tname+"'";
	    		PreparedStatement pst2=con2.prepareStatement(sql2);
	    	    ResultSet rs2;
	    	    rs2= pst2.executeQuery();
	    	    if(rs2.next())
	    	    tid = rs2.getString(1);

	        }
	        catch(Exception e){
	        	System.out.println(e);
	        	}
	        }
	    };
	    cb.addActionListener(actionListener);
	    contentPane.add(cb);

		}
	    catch(Exception e1)  {  System.out.println(e1);}	
		
		try
		{
		ResultSet rs;
		Class.forName("oracle.jdbc.driver.OracleDriver");
        Connection con21 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
		
        String sql11 = "SELECT DOCTOR_NAME FROM DOCTOR ORDER BY DOCTOR_NAME";
		PreparedStatement pst11=con21.prepareStatement(sql11);
	    rs= pst11.executeQuery();
	    String str=null;
	    String str1="SELECT NAME";
	    while(rs.next())
	    {
	    str = rs.getString(1);
	    str1 = str1+","+str;
	    }
	    String str2[];
	    str2= str1.split(",");
	    JComboBox cb = new JComboBox(str2);
	    cb.setBounds(115, 174, 121, 25);
	    ActionListener actionListener = new ActionListener() {
	        public void actionPerformed(ActionEvent actionEvent) {
	        ItemSelectable is = (ItemSelectable)actionEvent.getSource();
	        Object selected[] = is.getSelectedObjects();
	        dname =  (String)selected[0];
	        try {
	        	Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
	        	String sql2  = "SELECT DOC_ID FROM DOCTOR WHERE DOCTOR_NAME = '"+dname+"'";
	    		PreparedStatement pst2=con2.prepareStatement(sql2);
	    	    ResultSet rs2;
	    	    rs2= pst2.executeQuery();
	    	    if(rs2.next())
	    	    did = rs2.getString(1);

	        }
	        catch(Exception e)
	        {
	        System.out.println(e);
	        }
	        }
	    };
	    cb.addActionListener(actionListener);
	    contentPane.add(cb);
		}
		catch(Exception e1)  {  System.out.println(e1);}	
		 
		 datetextField = new JTextField();
		 datetextField.setColumns(10);
		 datetextField.setBounds(115, 228, 121, 25);
		 contentPane.add(datetextField);
		
		readingtextField = new JTextField();
		readingtextField.setColumns(10);
		readingtextField.setBounds(115, 282, 121, 25);
		contentPane.add(readingtextField);
		
		JButton button = new JButton("INSERT");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				       ResultSet id;
					   String date = datetextField.getText();
					   String reading = readingtextField.getText();
					   

					     try {
					        Class.forName("oracle.jdbc.driver.OracleDriver");
					        Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
					        String sql1 = "SELECT MAX(R_ID) FROM REPORT";
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
					        if(pid.isEmpty()==false && tid.isEmpty()==false && did.isEmpty()==false && date.isEmpty()==false && reading.isEmpty()==false)
					        {
					        String sql = "insert into report values('"+pid+"','"+tid+"','"+did+"','"+date+"','"+id2+"','"+reading+"')";
					        PreparedStatement pst=con2.prepareStatement(sql);
					        pst.executeQuery();
					        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
					        }
					        else {
					        	JOptionPane.showMessageDialog(null,	"Data not inserted");
					        }
					     } catch(Exception e1)  {  System.out.println(e1);}
					
				}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(50, 350, 100, 30);
		contentPane.add(button);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 72, 600, 216);
		contentPane.add(scrollPane);
		
		table5 = new JTable();
		scrollPane.setViewportView(table5);
		
		
		JButton button_2 = new JButton("VIEW");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try 
				{
					String query="select r_id,fname,DOCTOR_NAME,test_name,reading from report r,patient p,test t,doctor d where r.p_id=p.p_id AND r.test_id=t.test_id AND r.doc_id=d.doc_id order by r_id";
					PreparedStatement pst1=con.prepareStatement(query);
					ResultSet rs=pst1.executeQuery();
					table5.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e1) 
				{
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBounds(180, 350, 100, 30);
		contentPane.add(button_2);
		
		JLabel label = new JLabel("Patient Name");
		label.setFont(new Font("Tahoma", Font.BOLD, 12));
		label.setBounds(10, 72, 90, 14);
		contentPane.add(label);
		
		JLabel labeltestid = new JLabel("Test Name");
		labeltestid.setFont(new Font("Tahoma", Font.BOLD, 12));
		labeltestid.setBounds(10, 120, 80, 14);
		contentPane.add(labeltestid);
		
		JLabel labeldid = new JLabel("Doctor Name");
		labeldid.setFont(new Font("Tahoma", Font.BOLD, 12));
		labeldid.setBounds(10, 180, 80, 14);
		contentPane.add(labeldid);
		
		JLabel labeldate = new JLabel("Date");
		labeldate.setFont(new Font("Tahoma", Font.BOLD, 12));
		labeldate.setBounds(10, 230, 80, 14);
		contentPane.add(labeldate);
		
		JLabel lblReading = new JLabel("Reading");
		lblReading.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblReading.setBounds(10, 285, 93, 14);
		contentPane.add(lblReading);
		
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainpage m = new mainpage();
				m.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(315, 350, 100, 30);
		contentPane.add(btnExit);
		
		
		
	}
}
