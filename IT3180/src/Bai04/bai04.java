package Bai04;

import java.awt.Color;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JTextField;
import javax.swing.table.DefaultTableModel;
import javax.swing.table.TableCellRenderer;
import javax.swing.JFrame;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTable;

public class bai04 {
	private static JFrame frm_main;
	private static JPanel pal_username;
	private static JLabel lbl_username;
	private static JPanel pal_password;
	private static JTextField txt_username;
	private static JLabel lbl_password;
	private static JTextField txt_password;
	private static JPanel pal_btn_login;
	private static JButton btn_login;
	private static JPanel pal_login;
	
	private static JTable tab_info;
	private static JPanel pal_tab_info;
	
	private static Connection connection;
	private static Statement statement;
	
	public static void main(String[] args) {
		
		try {
			connection = DriverManager.getConnection("jdbc:sqlite:bai04.db");
			statement = connection.createStatement();
			
			statement.executeUpdate("drop table if exists bai04");
			statement.executeUpdate("create table bai04 (username TEXT NOT NULL, password TEXT NOT NULL, name TEXT NOT NULL, age INT NOT NULL, PRIMARY KEY(username))");
			statement.executeUpdate("insert into bai04 values('taikhoan01', 'abcdef', 'Nguyen Van A', 39)");
			statement.executeUpdate("insert into bai04 values('admin', 'admin', 'Tran Van B', 45)");
			statement.executeUpdate("insert into bai04 values('taikhoan02', '123456', 'Do Thi C', 22)");
			statement.executeUpdate("insert into bai04 values('user001', 'test', 'Dang Tran D', 27)");
			statement.executeUpdate("insert into bai04 values('zzzz', 'vabc', 'Vo Thi T', 16)");
			statement.executeUpdate("insert into bai04 values('it3180', '1803', 'Alex', 29)");
			statement.executeUpdate("insert into bai04 values('xxxx', 'yyyy', 'Do S', 21)");
			// salt+hash >>> text
			String username = "taikhoan01";
			ResultSet rs = statement.executeQuery("select * from bai04 where username='" + username +"'");
			//dont inject please
			while (rs.next()) {
				// read the result set
				System.out.println("username = " + rs.getString("username"));
				System.out.println("password = " + rs.getString("password"));
			}
			
			frm_main = new JFrame("main");
			frm_main.setLayout(null);
			lbl_username = new JLabel("username: ");
			lbl_password = new JLabel("password: ");
			txt_username = new JTextField(16);
			txt_password = new JTextField(16);
			btn_login = new JButton("login");
			pal_login = new JPanel();
			pal_username = new JPanel();
			pal_password = new JPanel();
			pal_btn_login = new JPanel();
			pal_username.add(lbl_username);
			pal_username.add(txt_username);
			pal_password.add(lbl_password);
			pal_password.add(txt_password);
			pal_btn_login.add(btn_login);
			pal_login.setBounds(160, 90, 320, 180);
			pal_login.setLayout(new BoxLayout(pal_login, BoxLayout.Y_AXIS));
			pal_login.add(pal_username);
			pal_login.add(pal_password);
			pal_login.add(pal_btn_login);
			
			tab_info = new JTable(new DefaultTableModel() {{
				addColumn("username");
				addColumn("name");
				addColumn("age");
				}
				public boolean isCellEditable(int row, int column) {
					return false;
				}
			}) {
				public Component prepareRenderer(TableCellRenderer renderer, int row, int column) {
					Component c = super.prepareRenderer(renderer, row, column);
					if (row == 0) {
						c.setBackground(Color.DARK_GRAY);
						c.setForeground(Color.WHITE);
					}
					else {
						c.setForeground(Color.BLACK);
						if (!isRowSelected(row))
							c.setBackground(row % 2 == 1 ? getBackground() : Color.LIGHT_GRAY);
					}
					
			        return c;
			    }
			};
			
			pal_tab_info = new JPanel();
			pal_tab_info.setBounds(45, 30, 640-90, 360);
			pal_tab_info.setLayout(new BoxLayout(pal_tab_info, BoxLayout.Y_AXIS));
			pal_tab_info.add(tab_info);
			
			pal_tab_info.setVisible(false);
			frm_main.add(pal_tab_info);
			frm_main.add(pal_login);
			
			btn_login.addActionListener(new ActionListener() {
				@Override
				public void actionPerformed(ActionEvent e) {
					String username = txt_username.getText();
					String password = txt_password.getText();
					try {
						boolean success = true;
						if (connection.isClosed()) {
							
						}
						ResultSet rs = statement.executeQuery("select password from bai04 where username='" + username +"'");
						if (!rs.next()) {
							success = false;
						}
						else {
							success = password.equals(rs.getString("password"));
						}
						
						if (success) {
							DefaultTableModel dtm = (DefaultTableModel)tab_info.getModel();
							dtm.setRowCount(0);
							dtm.addRow(new Object[] {"username", "name", "age"});
							
							rs = statement.executeQuery("select username,name,age from bai04");
							while (rs.next()) {
								String usname = rs.getString("username");
								String name = rs.getString("name");
								int age = rs.getInt("age");
								dtm.addRow(new Object[] {usname, name, ""+age});
							}
							
							pal_login.setVisible(false);
							pal_tab_info.setVisible(true);
							frm_main.repaint();
						}
						else {
							JOptionPane.showMessageDialog(null, "Sai thong tin dang nhap", "Warning!", JOptionPane.WARNING_MESSAGE);
						}
					}
					catch (SQLException ee) {
						ee.printStackTrace(System.err);
						JOptionPane.showMessageDialog(null, "Loi", "Error!", JOptionPane.ERROR_MESSAGE);
					}
				}
			});
			
			
			frm_main.setSize(640,360);
			frm_main.setVisible(true);
			frm_main.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		} catch (SQLException e) {
			// if the error message is "out of memory",
			// it probably means no database file is found
			e.printStackTrace(System.err);
		}
	}
}
