package com.toko;

import java.awt.BorderLayout;
import com.toko.SQLConnector;

import net.proteanit.sql.DbUtils;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;
import javax.swing.JScrollPane;
import java.sql.*;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
public class AdminActivity extends JFrame {

	private JPanel contentPane;
	private JTable table;
	private JButton tambahbarang;
	private JButton editstok;
	private JButton hapusbarang;
	private JButton btnNewButton;
	private JButton btnNewButton_1;

	/**
	 * Launch the application.
	 */
	

	/**
	 * Create the frame.
	 */
	public AdminActivity() {
		Connection koneksi = SQLConnector.dbconnector();
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 640, 480);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Admin");
		lblNewLabel.setBounds(19, 19, 61, 16);
		contentPane.add(lblNewLabel);
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(19, 113, 601, 305);
		contentPane.add(scrollPane);
		
		table = new JTable();
		scrollPane.setViewportView(table);
		try {
			String query = "SELECT * FROM barang ORDER BY kodebarang ASC;";
			PreparedStatement pst = koneksi.prepareStatement(query);
			ResultSet rs=pst.executeQuery();
			table.setModel(DbUtils.resultSetToTableModel(rs));
			rs.close();
			pst.close();
			
			tambahbarang = new JButton("Tambah Barang");
			tambahbarang.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					TambahBarang tambah = new TambahBarang();
					tambah.setVisible(true);
				}
			});
			tambahbarang.setBounds(19, 55, 140, 29);
			contentPane.add(tambahbarang);
			
			editstok = new JButton("Edit Barang");
			editstok.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					EditBarang edit = new EditBarang();
					edit.setVisible(true);
				}
			});
			editstok.setBounds(171, 55, 117, 29);
			contentPane.add(editstok);
			
			hapusbarang = new JButton("Hapus Barang");
			hapusbarang.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					HapusBarang hapus = new HapusBarang();
					hapus.setVisible(true);
				}
			});
			hapusbarang.setBounds(300, 55, 117, 29);
			contentPane.add(hapusbarang);
			
			btnNewButton = new JButton("Keluar");
			btnNewButton.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					Login login = new Login();
					login.setVisible(true);
				}
			});
			btnNewButton.setBounds(71, 14, 117, 29);
			contentPane.add(btnNewButton);
			
			btnNewButton_1 = new JButton("Data Pembelian");
			btnNewButton_1.addActionListener(new ActionListener() {
				public void actionPerformed(ActionEvent e) {
					dispose();
					DataPembelian data = new DataPembelian();
					data.setVisible(true);
					
				}
			});
			btnNewButton_1.setBounds(185, 14, 148, 29);
			contentPane.add(btnNewButton_1);
			
		} catch(Exception error) {
			JOptionPane.showMessageDialog(null, error);
	}
}
}
