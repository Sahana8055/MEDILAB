package labDB;


import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

//@SuppressWarnings("serial")
public class hospital extends JFrame {

	
	private JPanel contentPane;
	private JTextField  h_idtextField;
	private JTextField  nametextField;
	private Connection con=null;
	private JTable table1;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					hospital frame = new hospital();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * @return
	 */
	public  hospital() {
		
		con=DB.getConnection();
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 757, 690);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JTextPane txtpnInformation = new JTextPane();
		txtpnInformation.setBackground(Color.ORANGE);
		txtpnInformation.setForeground(Color.BLACK);
		txtpnInformation.setEditable(false);
		txtpnInformation.setFont(new Font("Arial Black", Font.BOLD, 15));
		txtpnInformation.setText("HOSPITAL INFORMATION");
		txtpnInformation.setBounds(247, 11, 250, 25);
		contentPane.add(txtpnInformation);
		
		
		nametextField = new JTextField();
		nametextField.setBounds(150, 150, 150, 30);
		contentPane.add(nametextField);
		nametextField.setColumns(10);
		
		
		JButton btnNewButton = new JButton("INSERT");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				   ResultSet id;
				   String name = null;
				   name = nametextField.getText();

				     try {
				        Class.forName("oracle.jdbc.driver.OracleDriver");
				        Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe","TECHACAD","sahana");
				      
				        String sql1 = "SELECT MAX(HOSPITAL_ID) FROM HOSPITAL";
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
				        if (name.isEmpty()==false)
				        {
				        String sql = "insert into hospital values('"+id2+"','"+name+"')";
				       
				        PreparedStatement pst=con2.prepareStatement(sql);
				        pst.executeQuery();
				        JOptionPane.showMessageDialog(null,	"Data Inserted Successfully");
				        }
				        else
				        {
				        	 JOptionPane.showMessageDialog(null,"Hospital name not inserted");				        }
				       		       
				    } catch(Exception e1)  {  System.out.println(e1);}
				
				
			}
			
		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(50, 350, 100, 30);
		contentPane.add(btnNewButton);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(358, 110, 351, 200);
		contentPane.add(scrollPane);
		
		table1= new JTable();
		scrollPane.setViewportView(table1);
		
		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
				try
				{
					String query="select * from hospital ORDER BY HOSPITAL_ID";
					PreparedStatement pst1=con.prepareStatement(query);
					ResultSet rs=pst1.executeQuery();
					table1.setModel(DbUtils.resultSetToTableModel(rs));
				}
				catch(Exception e1)
				{
					e1.printStackTrace();
				}
			

			}
			  
			  
			  });
		btnView.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnView.setBounds(180, 350, 100, 30);
		contentPane.add(btnView);
		
		
		
		JLabel lblWname = new JLabel("HOSPITAL NAME");
		lblWname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblWname.setBounds(20, 158, 150, 14);
		contentPane.add(lblWname);
		
		
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








