package com.toko;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;

public class KasirActivity extends JFrame {

	private JPanel contentPane;
	private JTextField namabarang;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public KasirActivity() {
		Connection koneksi = SQLConnector.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Kasir");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(20, 25, 70, 25);
		contentPane.add(lblNewLabel);
		
		JButton btnNewButton = new JButton("Keluar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				Login login = new Login();
				login.setVisible(true);
			}
		});
		btnNewButton.setBounds(119, 25, 117, 29);
		contentPane.add(btnNewButton);
		
		namabarang = new JTextField();
		namabarang.setBounds(151, 151, 270, 26);
		contentPane.add(namabarang);
		namabarang.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Cek Harga");
		lblNewLabel_1.setBounds(35, 156, 88, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel status = new JLabel("");
		status.setBounds(151, 185, 351, 16);
		contentPane.add(status);
		
		JButton btnNewButton_1 = new JButton("Cek Harga");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM barang where namabarang=?;";
					String nama = namabarang.getText();
					PreparedStatement pst = koneksi.prepareStatement(query);
					
					pst.setString(1, namabarang.getText());
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()) {
						int angka = rs.getInt("harga_satuan");
						String harga = String.valueOf(angka);
						status.setText(harga+" IDR");
					}
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
			}
		});
		btnNewButton_1.setBounds(20, 184, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Beli Barang");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				BeliBarang beli = new BeliBarang();
				beli.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(20, 80, 117, 29);
		contentPane.add(btnNewButton_2);
		
		
	}

}
