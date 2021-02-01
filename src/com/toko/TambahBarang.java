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

public class TambahBarang extends JFrame {

	private JPanel contentPane;
	private JTextField kodebarang;
	private JTextField namabarang;
	private JTextField stokbarang;
	private JTextField hargabarang;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public TambahBarang() {
		Connection koneksi = SQLConnector.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Tambah Barang");
		lblNewLabel.setFont(new Font("Lucida Grande", Font.PLAIN, 20));
		lblNewLabel.setBounds(26, 25, 181, 36);
		contentPane.add(lblNewLabel);
		
		kodebarang = new JTextField();
		kodebarang.setBounds(157, 89, 328, 26);
		contentPane.add(kodebarang);
		kodebarang.setColumns(10);
		
		namabarang = new JTextField();
		namabarang.setBounds(157, 149, 328, 26);
		contentPane.add(namabarang);
		namabarang.setColumns(10);
		
		stokbarang = new JTextField();
		stokbarang.setBounds(157, 214, 198, 26);
		contentPane.add(stokbarang);
		stokbarang.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Kode Barang");
		lblNewLabel_1.setBounds(26, 94, 104, 16);
		contentPane.add(lblNewLabel_1);
		
		JLabel lblNewLabel_2 = new JLabel("Nama Barang");
		lblNewLabel_2.setBounds(26, 154, 82, 16);
		contentPane.add(lblNewLabel_2);
		
		JLabel lblNewLabel_3 = new JLabel("Stok");
		lblNewLabel_3.setBounds(26, 219, 61, 16);
		contentPane.add(lblNewLabel_3);
		
		JLabel status = new JLabel("");
		status.setBounds(26, 325, 552, 16);
		contentPane.add(status);
		
		JButton btnNewButton = new JButton("Tambah");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String query = "insert into barang (kodebarang, namabarang, stok, harga_satuan) values(?, ?, ?, ?);";
					String nama = namabarang.getText();
					PreparedStatement pst = koneksi.prepareStatement(query);
					pst.setString(1, kodebarang.getText());
					pst.setString(2, namabarang.getText());
					pst.setString(3, stokbarang.getText());
					pst.setString(4, hargabarang.getText());
					pst.execute();
					status.setText("Barang "+nama+" telah dimasukan ");
					pst.close();
					
				} catch(Exception error) {
					JOptionPane.showMessageDialog(null, error);
				}
			}
		});
		btnNewButton.setBounds(182, 353, 117, 29);
		contentPane.add(btnNewButton);
		
		JButton btnNewButton_1 = new JButton("Kembali");
		btnNewButton_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				dispose();
				AdminActivity admin = new AdminActivity();
				admin.setVisible(true);
			}
		});
		btnNewButton_1.setBounds(26, 353, 117, 29);
		contentPane.add(btnNewButton_1);
		
		JLabel lblNewLabel_4 = new JLabel("Harga Satuan");
		lblNewLabel_4.setBounds(26, 279, 104, 16);
		contentPane.add(lblNewLabel_4);
		
		hargabarang = new JTextField();
		hargabarang.setBounds(157, 274, 198, 26);
		contentPane.add(hargabarang);
		hargabarang.setColumns(10);
		
		
	}

}
