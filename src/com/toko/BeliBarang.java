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
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class BeliBarang extends JFrame {

	private JPanel contentPane;
	private JTextField tanggal;
	private JTextField namabarang;
	private JTextField stokbeli;
	private JTextField hargabeli;

	/**
	 * Launch the application.
	 */
	
	

	/**
	 * Create the frame.
	 */
	public BeliBarang() {
		Connection koneksi = SQLConnector.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Beli Barang");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(19, 23, 114, 32);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nama Barang");
		lblNewLabel_1.setBounds(19, 85, 94, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Jumlah");
		lblNewLabel_2.setBounds(19, 164, 94, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Bayar");
		lblNewLabel_3.setBounds(19, 252, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel lblNewLabel_4 = new JLabel("Tanggal");
		lblNewLabel_4.setBounds(336, 34, 61, 16);
		contentPane.add(lblNewLabel_4);
		
		JLabel status = new JLabel("");
		status.setBounds(162, 307, 238, 29);
		contentPane.add(status);
		
		tanggal = new JTextField();
		tanggal.setBounds(409, 29, 171, 26);
		contentPane.add(tanggal);
		tanggal.setColumns(10);
		
		namabarang = new JTextField();
		namabarang.setBounds(130, 80, 197, 26);
		contentPane.add(namabarang);
		namabarang.setColumns(10);
		
		stokbeli = new JTextField();
		stokbeli.setBounds(130, 159, 130, 26);
		contentPane.add(stokbeli);
		stokbeli.setColumns(10);
		
		hargabeli = new JTextField();
		hargabeli.setBounds(130, 247, 130, 26);
		contentPane.add(hargabeli);
		hargabeli.setColumns(10);
		
		JButton btnNewButton = new JButton("Bayar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM barang where namabarang=?;";
					String nama = namabarang.getText();
					PreparedStatement pst = koneksi.prepareStatement(query);
					
					pst.setString(1, namabarang.getText());
					
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()) {
						int stok=rs.getInt("stok");
						int hargaawal=rs.getInt("harga_satuan");
						String masuk = stokbeli.getText();
						int angka = Integer.parseInt(masuk);
						int hitung = stok-angka;
						int beli=hargaawal*angka;
						String hargastr=hargabeli.getText();
						int hargabeli=Integer.parseInt(hargastr);
						
						
						
						
						if(angka<stok) {
							String updatequery = "Update barang set stok=? where namabarang=?;";
							PreparedStatement pst2 = koneksi.prepareStatement(updatequery);
							pst2.setLong(1, hitung);
							pst2.setString(2, nama);
							pst2.execute();
							pst2.close();
							if(hargabeli>beli) {
								String updatequery2 = "insert into pembelian (tanggal, namabarang, stokbeli, hargabeli) values (?, ?, ?, ?);";
								PreparedStatement pst3 = koneksi.prepareStatement(updatequery2);
								pst3.setString(1, tanggal.getText());
								pst3.setString(2, nama);
								pst3.setLong(3, angka);
								pst3.setLong(4, beli);
								pst3.execute();
								int kembalian = hargabeli - beli;
								status.setText("Kembalian = "+kembalian+" IDR");
								pst3.close();
							} else if(hargabeli==beli) {
								String updatequery2 = "insert into pembelian (tanggal, namabarang, stokbeli, hargabeli) values (?, ?, ?, ?);";
								PreparedStatement pst3 = koneksi.prepareStatement(updatequery2);
								pst3.setString(1, tanggal.getText());
								pst3.setString(2, nama);
								pst3.setLong(3, angka);
								pst3.setLong(4, beli);
								pst3.execute();
								status.setText("Uang Pas");
								pst3.close();
								
							} else {
								status.setText("Uang Kurang");
							}
							
						} else {
							status.setText("Stok kurang dari yang dibeli");
						}
						
						
						
					}
					rs.close();
					pst.close();
					
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
			}
		});
		btnNewButton.setBounds(19, 307, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kembali");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				KasirActivity kasir = new KasirActivity();
				kasir.setVisible(true);
			}
			
		});
		btnNewButton_1.setBounds(16, 397, 117, 29);
		contentPane.add(btnNewButton_1);
	}

}
