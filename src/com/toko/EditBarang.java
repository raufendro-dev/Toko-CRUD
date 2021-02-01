package com.toko;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.awt.event.ActionEvent;

public class EditBarang extends JFrame {

	private JPanel contentPane;
	private JTextField namabarang;
	private JTextField stokbarang;
	private JTextField hargabarang;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public EditBarang() {
		Connection koneksi = SQLConnector.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Edit Barang");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(25, 35, 138, 25);
		contentPane.add(lblNewLabel);
		
		JLabel lblNewLabel_1 = new JLabel("Nama Barang");
		lblNewLabel_1.setBounds(25, 90, 97, 16);
		contentPane.add(lblNewLabel_1);
		
		namabarang = new JTextField();
		namabarang.setBounds(148, 85, 270, 26);
		contentPane.add(namabarang);
		namabarang.setColumns(10);
		
		JLabel lblNewLabel_2 = new JLabel("Stok");
		lblNewLabel_2.setBounds(25, 151, 61, 16);
		contentPane.add(lblNewLabel_2);
		
		stokbarang = new JTextField();
		stokbarang.setText("0");
		stokbarang.setBounds(148, 146, 130, 26);
		contentPane.add(stokbarang);
		stokbarang.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Harga Satuan");
		lblNewLabel_3.setBounds(25, 247, 97, 16);
		contentPane.add(lblNewLabel_3);
		
		hargabarang = new JTextField();
		hargabarang.setText("0");
		hargabarang.setBounds(148, 242, 130, 26);
		contentPane.add(hargabarang);
		hargabarang.setColumns(10);
		
		JLabel status = new JLabel("");
		status.setBounds(25, 351, 344, 16);
		contentPane.add(status);
		
		JButton btnNewButton = new JButton("+");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM barang where namabarang=?;";
					String nama = namabarang.getText();
					PreparedStatement pst = koneksi.prepareStatement(query);
					
					pst.setString(1, namabarang.getText());
					
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()) {
						
						int hargaawal=rs.getInt("harga_satuan");
						
						
						
						String hargastr=hargabarang.getText();
						int hargabaru=Integer.parseInt(hargastr);
						int hargaupdate=hargaawal+hargabaru;
						String hargaupdatestr=String.valueOf(hargaupdate);
						;
						String updatequery2 = "Update barang set harga_satuan=? where namabarang=?;";
						PreparedStatement pst3 = koneksi.prepareStatement(updatequery2);
						pst3.setLong(1, hargaupdate);
						pst3.setString(2, nama);
						pst3.execute();
						status.setText("Harga barang telah ditambah");
						pst3.close();
						
					}
					rs.close();
					pst.close();
					
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
				
			}
		});
		btnNewButton.setBounds(25, 280, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("-");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM barang where namabarang=?;";
					String nama = namabarang.getText();
					PreparedStatement pst = koneksi.prepareStatement(query);
					
					pst.setString(1, namabarang.getText());
					
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()) {
						
						int hargaawal=rs.getInt("harga_satuan");
						
						
						
						String hargastr=hargabarang.getText();
						int hargabaru=Integer.parseInt(hargastr);
						int hargaupdate=hargaawal+hargabaru;
						String hargaupdatestr=String.valueOf(hargaupdate);
						;
						String updatequery2 = "Update barang set harga_satuan=? where namabarang=?;";
						PreparedStatement pst3 = koneksi.prepareStatement(updatequery2);
						pst3.setLong(1, hargaupdate);
						pst3.setString(2, nama);
						pst3.execute();
						status.setText("Harga barang telah dikurangi");
						pst3.close();
						
					}
					rs.close();
					pst.close();
					
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
			}
		});
		btnNewButton_1.setBounds(183, 280, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JButton btnNewButton_2 = new JButton("Kembali");
		btnNewButton_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminActivity admin = new AdminActivity();
				admin.setVisible(true);
			}
		});
		btnNewButton_2.setBounds(25, 379, 117, 29);
		contentPane.add(btnNewButton_2);
		
		JButton btnNewButton_3 = new JButton("+");
		btnNewButton_3.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM barang where namabarang=?;";
					String nama = namabarang.getText();
					PreparedStatement pst = koneksi.prepareStatement(query);
					
					pst.setString(1, namabarang.getText());
					
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()) {
						int stok=rs.getInt("stok");
						
						String masuk = stokbarang.getText();
						int angka = Integer.parseInt(masuk);
						int hitung = stok+angka;
						
						
						
						String updatequery = "Update barang set stok=? where namabarang=?;";
						PreparedStatement pst2 = koneksi.prepareStatement(updatequery);
						pst2.setLong(1, hitung);
						pst2.setString(2, nama);
						pst2.execute();
						status.setText("Stok Barang telah ditambah");
						pst2.close();
						
						
					}
					rs.close();
					pst.close();
					
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
			}
		});
		btnNewButton_3.setBounds(25, 192, 117, 29);
		contentPane.add(btnNewButton_3);
		
		JButton btnNewButton_4 = new JButton("-");
		btnNewButton_4.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "SELECT * FROM barang where namabarang=?;";
					String nama = namabarang.getText();
					PreparedStatement pst = koneksi.prepareStatement(query);
					
					pst.setString(1, namabarang.getText());
					
					ResultSet rs=pst.executeQuery();
					
					while(rs.next()) {
						int stok=rs.getInt("stok");
						
						String masuk = stokbarang.getText();
						int angka = Integer.parseInt(masuk);
						int hitung = stok-angka;
						
						
						
						String updatequery = "Update barang set stok=? where namabarang=?;";
						PreparedStatement pst2 = koneksi.prepareStatement(updatequery);
						pst2.setLong(1, hitung);
						pst2.setString(2, nama);
						pst2.execute();
						status.setText("Stok Barang telah ditambah");
						pst2.close();
						
						
					}
					rs.close();
					pst.close();
					
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
			}
		});
		btnNewButton_4.setBounds(183, 192, 117, 29);
		contentPane.add(btnNewButton_4);
		
		
	}
}
