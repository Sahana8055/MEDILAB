package labDB;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.*;
import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.border.EmptyBorder;

import net.proteanit.sql.DbUtils;

//@SuppressWarnings("serial")
public class Doctor extends JFrame {

	private JPanel contentPane;
	private JTextField hidtextField;
	private JTextField didtextField;
	private JTextField dnametextField;
	private JTextField phonetextField;
	private Connection con = null;
	String hname;
	String hid;
	private JTable table2;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Doctor frame = new Doctor();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 * 
	 * @return
	 */
	public Doctor() {

		con = DB.getConnection();

		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 758, 460);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);

		JTextPane txtpnInformation = new JTextPane();
		txtpnInformation.setBackground(Color.ORANGE);
		txtpnInformation.setForeground(Color.BLACK);
		txtpnInformation.setEditable(false);
		txtpnInformation.setFont(new Font("Arial Black", Font.BOLD, 15));
		txtpnInformation.setText("DOCTOR INFORMATION");
		txtpnInformation.setBounds(247, 11, 227, 28);
		contentPane.add(txtpnInformation);

		try {
			ResultSet rs;
			Class.forName("oracle.jdbc.driver.OracleDriver");
			Connection con21 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "TECHACAD", "sahana");

			String sql11 = "SELECT HNAME FROM HOSPITAL ORDER BY HNAME";
			PreparedStatement pst11 = con21.prepareStatement(sql11);
			rs = pst11.executeQuery();
			String str = null;
			String str1 = "SELECT HNAME";
			while (rs.next()) {
				str = rs.getString(1);
				str1 = str1 + "," + str;
			}
			String str2[];
			str2 = str1.split(",");
			JComboBox cb = new JComboBox(str2);
			cb.setBounds(115, 100, 121, 25);
			ActionListener actionListener = new ActionListener() {
				public void actionPerformed(ActionEvent actionEvent) {
					ItemSelectable is = (ItemSelectable) actionEvent.getSource();
					Object selected[] = is.getSelectedObjects();
					hname = (String) selected[0];
					try {
						Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "TECHACAD",
								"sahana");
						String sql2 = "SELECT HOSPITAL_ID FROM HOSPITAL WHERE HNAME = '" + hname + "'";
						PreparedStatement pst2 = con2.prepareStatement(sql2);
						ResultSet rs2;
						rs2 = pst2.executeQuery();
						if (rs2.next())
							hid = rs2.getString(1);
					} catch (Exception e) {
						System.out.println(e);
					}
				}
			};
			cb.addActionListener(actionListener);
			contentPane.add(cb);

		} catch (Exception e1) {
			System.out.println(e1);
		}
		dnametextField = new JTextField();
		dnametextField.setBounds(115, 150, 121, 25);
		contentPane.add(dnametextField);
		dnametextField.setColumns(10);

		phonetextField = new JTextField();
		phonetextField.setBounds(115, 201, 121, 25);
		contentPane.add(phonetextField);
		phonetextField.setColumns(10);

		JButton btnNewButton = new JButton("INSERT");
		btnNewButton.addActionListener(new ActionListener() {

			public void actionPerformed(ActionEvent e) {
				ResultSet id;
				String name = dnametextField.getText();
				String phone = phonetextField.getText();

				try {
					int id2 = 1;
					Class.forName("oracle.jdbc.driver.OracleDriver");
					Connection con2 = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "TECHACAD",
							"sahana");
					String sql1 = "SELECT MAX(DOC_ID) FROM DOCTOR";
					PreparedStatement pst1 = con2.prepareStatement(sql1);
					id = pst1.executeQuery();
					String id1 = null;
					if (id.next())
						id1 = id.getString(1);
					if (id1 == null) {
						id1 = "0";
					}
					id2 = Integer.parseInt(id1);
					id2 = id2 + 1;
					if (name.isEmpty() == false && phone.isEmpty() == false && hid.isEmpty() == false) {
						String sql = "insert into doctor values('" + hid + "','" + id2 + "','" + name + "','" + phone
								+ "')";
						PreparedStatement pst = con2.prepareStatement(sql);
						pst.executeQuery();
						JOptionPane.showMessageDialog(null, "Data Inserted Successfully");
					} else {
						JOptionPane.showMessageDialog(null, "Data not inserted");

					}

				} catch (Exception e1) {
					System.out.println(e1);
				}

			}

		});
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnNewButton.setBounds(50, 350, 100, 30);
		contentPane.add(btnNewButton);

		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(358, 110, 351, 200);
		contentPane.add(scrollPane);

		table2 = new JTable();
		scrollPane.setViewportView(table2);

		JButton btnView = new JButton("VIEW");
		btnView.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {

				try {
					String query = "select * from DOCTOR order by DOC_ID";
					PreparedStatement pst1 = con.prepareStatement(query);
					ResultSet rs = pst1.executeQuery();
					table2.setModel(DbUtils.resultSetToTableModel(rs));
				} catch (Exception e1) {
					e1.printStackTrace();
				}

			}

		});
		btnView.setFont(new Font("Tahoma", Font.BOLD, 11));
		btnView.setBounds(180, 350, 100, 30);
		contentPane.add(btnView);

		JLabel lblhid = new JLabel("Hospital Name");
		lblhid.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblhid.setBounds(10, 100, 200, 14);
		contentPane.add(lblhid);

		JLabel lblDoctorname = new JLabel("Name");
		lblDoctorname.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblDoctorname.setBounds(10, 152, 54, 14);
		contentPane.add(lblDoctorname);

		JLabel lblPhoneNo = new JLabel("PHONE NUMBER");
		lblPhoneNo.setFont(new Font("Tahoma", Font.BOLD, 12));
		lblPhoneNo.setBounds(10, 203, 200, 14);
		contentPane.add(lblPhoneNo);

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
