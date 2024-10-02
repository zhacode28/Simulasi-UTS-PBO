/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package SimulasiUTS;

/**
 *
 * @author zahidahhanumalzahra
 */


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.GroupLayout;
import java.sql.ResultSet;
import javax.swing.table.DefaultTableModel;


public class TokoBuku extends javax.swing.JFrame {

    private JTextField jTextFieldISBN;
    private JTextField jTextFieldJudulBuku;
    private JTextField jTextFieldTahunTerbit;
    private JTextField jTextFieldPenerbit;
    private JButton jButtonSimpan;


    public TokoBuku() {
        initComponents();
    }

    private void initComponents() {
        jTextFieldISBN = new JTextField();
        jTextFieldJudulBuku = new JTextField();
        jTextFieldTahunTerbit = new JTextField();
        jTextFieldPenerbit = new JTextField();
        jButtonSimpan = new JButton("Simpan");

        jButtonSimpan.addActionListener(evt -> {
            String isbn = jTextFieldISBN.getText();
            String judulBuku = jTextFieldJudulBuku.getText();
            String tahunTerbit = jTextFieldTahunTerbit.getText();
            String penerbit = jTextFieldPenerbit.getText();
            simpanDataBuku(isbn, judulBuku, tahunTerbit, penerbit);
        });

        // Menambahkan komponen ke layout
        JPanel panel = new JPanel();
        GroupLayout layout = new GroupLayout(panel);
        panel.setLayout(layout);
        layout.setAutoCreateGaps(true);
        layout.setAutoCreateContainerGaps(true);

        layout.setHorizontalGroup(
            layout.createSequentialGroup()
                .addGroup(layout.createParallelGroup(GroupLayout.Alignment.LEADING)
                    .addComponent(jTextFieldISBN)
                    .addComponent(jTextFieldJudulBuku)
                    .addComponent(jTextFieldTahunTerbit)
                    .addComponent(jTextFieldPenerbit)
                    .addComponent(jButtonSimpan))
        );

        layout.setVerticalGroup(
            layout.createSequentialGroup()
                .addComponent(jTextFieldISBN)
                .addComponent(jTextFieldJudulBuku)
                .addComponent(jTextFieldTahunTerbit)
                .addComponent(jTextFieldPenerbit)
                .addComponent(jButtonSimpan)
        );

        add(panel);
        pack();
    }


    private void simpanDataBuku(String isbn, String judulBuku, String tahunTerbit, String penerbit) {
        Connection conn = null;
        PreparedStatement ps = null;

        try {
            String url = "jdbc:postgresql://localhost:5432/TokoBuku"; // Ganti dengan nama database TokoBuku
            String user = "postgres"; // Ganti dengan username PostgreSQL
            String password = "197300"; // Ganti dengan password PostgreSQL
            conn = DriverManager.getConnection(url, user, password);

            String sql = "INSERT INTO Buku (ISBN, JudulBuku, TahunTerbit, Penerbit) VALUES (?, ?, ?, ?)";
            ps = conn.prepareStatement(sql);
            ps.setString(1, isbn);
            ps.setString(2, judulBuku);
            ps.setString(3, tahunTerbit);
            ps.setString(4, penerbit);
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data berhasil ditambahkan!");

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Terjadi kesalahan: " + e.getMessage());
        } finally {
            try {
                if (ps != null) ps.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public static void main(String args[]) {
        java.awt.EventQueue.invokeLater(() -> new TokoBuku().setVisible(true));
    }
}
