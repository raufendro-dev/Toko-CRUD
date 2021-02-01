package com.toko;

import java.awt.BorderLayout;
import java.sql.*;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import com.toko.SQLConnector;

import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Login extends JFrame {

	private JPanel contentPane;
	private JTextField username;
	private JTextField password;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Login frame = new Login();
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
	public Login() {
		Connection koneksi = SQLConnector.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 300, 250);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		username = new JTextField();
		username.setBounds(27, 47, 226, 26);
		contentPane.add(username);
		username.setColumns(10);
		
		password = new JTextField();
		password.setBounds(27, 105, 226, 26);
		contentPane.add(password);
		password.setColumns(10);
		
		JButton btnNewButton = new JButton("Login");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM users where username=? and password=?;";
					PreparedStatement pst = koneksi.prepareStatement(query);
					pst.setString(1, username.getText());
					pst.setString(2, password.getText());
					ResultSet rs=pst.executeQuery();
					int hit=0;
					while(rs.next()) {
						
						hit=hit+1;
						
						
					}
					rs.close();
					pst.close();
					if(hit==1) {
						dispose();
						AdminActivity admin = new AdminActivity();
						admin.setVisible(true);
						
					} else {
						System.out.print("Gagal Login");
						JOptionPane.showMessageDialog(null, "Username atau Password salah!");
					}
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
				
				
				
			}
		});
		btnNewButton.setBounds(27, 175, 117, 29);
		contentPane.add(btnNewButton);
		
		JLabel usernamelabel = new JLabel("Username");
		usernamelabel.setBounds(27, 19, 94, 16);
		contentPane.add(usernamelabel);
		
		JLabel passwordlabel = new JLabel("Password");
		passwordlabel.setBounds(27, 77, 61, 16);
		contentPane.add(passwordlabel);
	}
}
