package a;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import java.awt.BorderLayout;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import javax.swing.JTextField;
import javax.swing.JLabel;
import java.awt.GridBagLayout;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import javax.swing.JPasswordField;
import javax.swing.JButton;
import javax.swing.JSlider;
import javax.swing.JEditorPane;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import java.awt.Canvas;
import java.awt.GridLayout;
import java.awt.CardLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class B {

	private JFrame frame;
	private JTextField usernameTextField;
	private JPasswordField passwordField;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					B window = new B();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public B() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 640, 480);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(new BorderLayout(0, 0));
		
		JPanel panel = new JPanel();
		panel.setLayout(new FlowLayout(FlowLayout.CENTER, 5, 5));
		
		JPanel ImagePanel = new JPanel();
		panel.add(ImagePanel);
		
		JEditorPane editorPane = new JEditorPane();
		GroupLayout gl_ImagePanel = new GroupLayout(ImagePanel);
		gl_ImagePanel.setHorizontalGroup(
			gl_ImagePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_ImagePanel.createSequentialGroup()
					.addComponent(editorPane, GroupLayout.PREFERRED_SIZE, 336, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		gl_ImagePanel.setVerticalGroup(
			gl_ImagePanel.createParallelGroup(Alignment.LEADING)
				.addComponent(editorPane, GroupLayout.DEFAULT_SIZE, 438, Short.MAX_VALUE)
		);
		ImagePanel.setLayout(gl_ImagePanel);
		
		JPanel LoginPanel = new JPanel();
		panel.add(LoginPanel);
		GridBagLayout gbl_LoginPanel = new GridBagLayout();
		gbl_LoginPanel.columnWidths = new int[]{51, 96, 0};
		gbl_LoginPanel.rowHeights = new int[]{20, 0, 0, 0};
		gbl_LoginPanel.columnWeights = new double[]{0.0, 1.0, Double.MIN_VALUE};
		gbl_LoginPanel.rowWeights = new double[]{1.0, 0.0, 0.0, Double.MIN_VALUE};
		LoginPanel.setLayout(gbl_LoginPanel);
		
		JLabel lblUsernameLabel = new JLabel("UserName");
		GridBagConstraints gbc_lblUsernameLabel = new GridBagConstraints();
		gbc_lblUsernameLabel.anchor = GridBagConstraints.WEST;
		gbc_lblUsernameLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblUsernameLabel.gridx = 0;
		gbc_lblUsernameLabel.gridy = 0;
		LoginPanel.add(lblUsernameLabel, gbc_lblUsernameLabel);
		
		usernameTextField = new JTextField();
		GridBagConstraints gbc_usernameTextField = new GridBagConstraints();
		gbc_usernameTextField.insets = new Insets(0, 0, 5, 0);
		gbc_usernameTextField.anchor = GridBagConstraints.NORTHWEST;
		gbc_usernameTextField.gridx = 1;
		gbc_usernameTextField.gridy = 0;
		LoginPanel.add(usernameTextField, gbc_usernameTextField);
		usernameTextField.setColumns(10);
		
		JLabel lblPasswordLabel = new JLabel("Password");
		GridBagConstraints gbc_lblPasswordLabel = new GridBagConstraints();
		gbc_lblPasswordLabel.anchor = GridBagConstraints.EAST;
		gbc_lblPasswordLabel.insets = new Insets(0, 0, 5, 5);
		gbc_lblPasswordLabel.gridx = 0;
		gbc_lblPasswordLabel.gridy = 1;
		LoginPanel.add(lblPasswordLabel, gbc_lblPasswordLabel);
		
		passwordField = new JPasswordField();
		GridBagConstraints gbc_passwordField = new GridBagConstraints();
		gbc_passwordField.insets = new Insets(0, 0, 5, 0);
		gbc_passwordField.fill = GridBagConstraints.HORIZONTAL;
		gbc_passwordField.gridx = 1;
		gbc_passwordField.gridy = 1;
		LoginPanel.add(passwordField, gbc_passwordField);
		
		JButton btnLoginButton = new JButton("Login");
		btnLoginButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				System.out.println("Username: " + usernameTextField.getText());
				System.out.println("Password: " + new String(passwordField.getPassword()));
			}
		});
		GridBagConstraints gbc_btnLoginButton = new GridBagConstraints();
		gbc_btnLoginButton.gridx = 1;
		gbc_btnLoginButton.gridy = 2;
		LoginPanel.add(btnLoginButton, gbc_btnLoginButton);
		frame.getContentPane().add(panel, BorderLayout.CENTER);
	}
}
