package labDB;


import java.awt.EventQueue;


import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import net.proteanit.sql.DbUtils;
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

public class Test extends JFrame {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtTestInformation;
	private JTextField  tnametextField;
	private JTextField  minreadingtextField;
	private JTextField  maxreadingtextField;
	private JTable table4;
	private Connection con=null;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Test frame = new Test();
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
	public Test() {
		
		con=DB.getConnection();
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 426);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		txtTestInformation = new JTextField();
		txtTestInformation.setText("Test Information");
		txtTestInformation.setHorizontalAlignment(SwingConstants.CENTER);
		txtTestInformation.setForeground(Color.BLACK);
		txtTestInformation.setFont(new Font("Arial Black", Font.BOLD, 15));
		txtTestInformation.setEditable(false);
		txtTestInformation.setColumns(10);
		txtTestInformation.setBackground(Color.ORANGE);
		txtTestInformation.setBounds(227, 11, 238, 25);
		contentPane.add(txtTestInformation);
		
		tnametextField = new JTextField();
		tnametextField.setColumns(10);
		tnametextField.setBounds(160, 120, 133, 25);
		contentPane.add(tnametextField);
		
		minreadingtextField = new JTextField();
		minreadingtextField.setColumns(10);
		minreadingtextField.setBounds(160, 174, 133, 25);
		contentPane.add(minreadingtextField);
		
		maxreadingtextField = new JTextField();
		maxreadingtextField.setColumns(10);
		maxreadingtextField.setBounds(160, 228, 133, 25);
		contentPane.add(maxreadingtextField);
		
		
		
		JButton button = new JButton("INSERT");
		button.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				     ResultSet id;
					String tname = tnametextField.getText();
					String minreading = minreadingtextField.getText();
				    String maxreading=maxreadingtextField.getText();

					     try {
					        Class.forName("oracle.jdbc.driver.OracleDriver");
					        Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
					        String sql1 = "SELECT MAX(TEST_ID) FROM TEST";
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
					        if(tname.isEmpty()==false && minreading.isEmpty()==false && maxreading.isEmpty()==false)
					        {
					        String sql = "insert into test values('"+id2+"','"+tname+"','"+minreading+"','"+maxreading+"')";
					        PreparedStatement pst=con2.prepareStatement(sql);
					        pst.executeQuery();
					        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
					        }
					        else {
					        	JOptionPane.showMessageDialog(null,	"Data not inserted");
					        }
					     }
					       		       
					   catch(Exception e1)  {  System.out.println(e1);}
				}
		});
		button.setFont(new Font("Tahoma", Font.BOLD, 11));
		button.setBounds(50, 330, 100, 30);
		contentPane.add(button);
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(384, 72, 347, 216);
		contentPane.add(scrollPane);
		
		table4 = new JTable();
		scrollPane.setViewportView(table4);
		
		
		JButton button_2 = new JButton("VIEW");
		button_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String query="select * from test order by test_id";
					PreparedStatement pst1=con.prepareStatement(query);
					ResultSet rs=pst1.executeQuery();
					table4.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			}
		});
		button_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		button_2.setBounds(180, 330, 100, 30);
		contentPane.add(button_2);
		
		JLabel lblTestName = new JLabel("Test Name");
		lblTestName.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblTestName.setBounds(10, 122, 93, 14);
		contentPane.add(lblTestName);
		
		JLabel lblMinReading = new JLabel("Min Reading");
		lblMinReading.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMinReading.setBounds(10, 176, 93, 14);
		contentPane.add(lblMinReading);
		
		JLabel lblMaxReading = new JLabel("Max Reading");
		lblMaxReading.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblMaxReading.setBounds(10, 230, 93, 14);
		contentPane.add(lblMaxReading);
		
		JButton btnExit = new JButton("EXIT");
		btnExit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				mainpage m = new mainpage();
				m.setVisible(true);
			}
		});
		btnExit.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnExit.setBounds(315, 330, 100, 30);
		contentPane.add(btnExit);
		
		
		
	}
}

