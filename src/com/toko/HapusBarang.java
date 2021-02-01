package com.toko;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.awt.event.ActionEvent;

public class HapusBarang extends JFrame {

	private JPanel contentPane;
	private JTextField namabarang;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public HapusBarang() {
		Connection koneksi = SQLConnector.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Hapus Barang");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(32, 37, 144, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nama Barang");
		lblNewLabel_1.setBounds(32, 118, 99, 16);
		contentPane.add(lblNewLabel_1);
		
		namabarang = new JTextField();
		namabarang.setBounds(156, 113, 248, 26);
		contentPane.add(namabarang);
		namabarang.setColumns(10);
		
		JLabel status = new JLabel("");
		status.setBounds(32, 182, 372, 16);
		contentPane.add(status);
		
		JButton btnNewButton = new JButton("Hapus Barang");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "delete from barang where namabarang=?;";
					String nama = namabarang.getText();
					PreparedStatement pst = koneksi.prepareStatement(query);
					pst.setString(1, nama);
					pst.execute();
					status.setText("Barang "+nama+" telah dihapus");
					pst.close();
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
			}
		});
		btnNewButton.setBounds(32, 243, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kembali");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminActivity admin = new AdminActivity();
				admin.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(32, 310, 117, 29);
		contentPane.add(btnNewButton_1);
		
		
	}
}
