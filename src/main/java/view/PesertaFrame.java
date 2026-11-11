package view;

import config.Database;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.*;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;

public class PesertaFrame extends JFrame {
    private JTextField txtNim, txtNama, txtProdi, txtNoHp, txtEmail, txtKodeAkses, txtPencarian;
    private JRadioButton rbLaki, rbPerempuan;
    private ButtonGroup bgJK;
    private JComboBox<String> cmbKursus;
    private JCheckBox cbModul, cbSertifikat, cbRekaman;
    private JTextArea txtRingkasan;
    private JButton btnSimpan, btnUbah, btnHapus, btnReset, btnCetakSemua, btnCari;
    private JTable tablePeserta;
    private DefaultTableModel tableModel;

    public PesertaFrame() {
        initComponents();
        setLocationRelativeTo(null);
        loadDataTabel(""); // Memuat data dari database saat form dibuka
    }

    private void initComponents() {
        setTitle("SIKURSUS Desktop - Master Data Peserta");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Wajib agar aman dengan WindowManager Pertemuan 11
        setSize(750, 780);
        setLayout(new BorderLayout(10, 10));

        // Header Title Banner
        JPanel panelHeaderBanner = new JPanel(new FlowLayout(FlowLayout.CENTER));
        panelHeaderBanner.setBackground(new Color(128, 0, 64)); 
        JLabel lblTitle = new JLabel("Master Data Peserta");
        lblTitle.setForeground(Color.WHITE);
        lblTitle.setFont(new Font("Segoe UI", Font.BOLD, 18));
        panelHeaderBanner.add(lblTitle);
        add(panelHeaderBanner, BorderLayout.NORTH);

        // Main Container Form & Table
        JPanel panelMain = new JPanel(new BorderLayout(10, 10));
        panelMain.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        // Form Input Panel
        JPanel panelForm = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(4, 8, 4, 8);
        gbc.fill = GridBagConstraints.HORIZONTAL;

        int row = 0;

        // NIM
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Nim"), gbc);
        txtNim = new JTextField(30);
        gbc.gridx = 1; panelForm.add(txtNim, gbc);

        // Nama Peserta
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Nama Peserta"), gbc);
        txtNama = new JTextField(30);
        gbc.gridx = 1; panelForm.add(txtNama, gbc);

        // Jenis Kelamin
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Jenis Kelamin"), gbc);
        JPanel panelJK = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        rbLaki = new JRadioButton("Laki-Laki");
        rbPerempuan = new JRadioButton("Perempuan");
        bgJK = new ButtonGroup();
        bgJK.add(rbLaki);
        bgJK.add(rbPerempuan);
        panelJK.add(rbLaki);
        panelJK.add(Box.createHorizontalStrut(15));
        panelJK.add(rbPerempuan);
        gbc.gridx = 1; panelForm.add(panelJK, gbc);

        // Program Studi
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Program Studi"), gbc);
        txtProdi = new JTextField(30);
        gbc.gridx = 1; panelForm.add(txtProdi, gbc);

        // Nomor Hp
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Nomor Hp"), gbc);
        txtNoHp = new JTextField(30);
        gbc.gridx = 1; panelForm.add(txtNoHp, gbc);

        // Email
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Email"), gbc);
        txtEmail = new JTextField(30);
        gbc.gridx = 1; panelForm.add(txtEmail, gbc);

        // Kode Akses
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Kode Akses"), gbc);
        txtKodeAkses = new JTextField(30);
        gbc.gridx = 1; panelForm.add(txtKodeAkses, gbc);

        // Kursus Combobox
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Kursus"), gbc);
        cmbKursus = new JComboBox<>(new String[]{"-Pilih Kursus-", "Web Programming", "UI/UX Design", "Java Desktop"});
        gbc.gridx = 1; panelForm.add(cmbKursus, gbc);

        // Fasilitas Checkbox
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Fasilitas"), gbc);
        JPanel panelFasilitas = new JPanel(new FlowLayout(FlowLayout.LEFT, 0, 0));
        cbModul = new JCheckBox("Modul Cetak");
        cbSertifikat = new JCheckBox("Sertifikat");
        cbRekaman = new JCheckBox("Akses Rekaman");
        panelFasilitas.add(cbModul);
        panelFasilitas.add(Box.createHorizontalStrut(10));
        panelFasilitas.add(cbSertifikat);
        panelFasilitas.add(Box.createHorizontalStrut(10));
        panelFasilitas.add(cbRekaman);
        gbc.gridx = 1; panelForm.add(panelFasilitas, gbc);

        // Ringkasan
        row++;
        gbc.gridx = 0; gbc.gridy = row;
        panelForm.add(new JLabel("Ringkasan"), gbc);
        txtRingkasan = new JTextArea(3, 30);
        txtRingkasan.setLineWrap(true);
        JScrollPane scrollRingkasan = new JScrollPane(txtRingkasan);
        gbc.gridx = 1; panelForm.add(scrollRingkasan, gbc);

        // Action Buttons Panel
        row++;
        JPanel panelButton = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        btnSimpan = new JButton("Simpan");
        btnUbah = new JButton("Ubah");
        btnHapus = new JButton("Hapus");
        btnReset = new JButton("Reset");
        btnCetakSemua = new JButton("Cetak Semua");

        panelButton.add(btnSimpan);
        panelButton.add(btnUbah);
        panelButton.add(btnHapus);
        panelButton.add(btnReset);
        panelButton.add(btnCetakSemua);

        gbc.gridx = 0; gbc.gridy = row; gbc.gridwidth = 2;
        panelForm.add(panelButton, gbc);

        panelMain.add(panelForm, BorderLayout.NORTH);

        // Table & Search Panel (Data Peserta)
        JPanel panelTableContainer = new JPanel(new BorderLayout(5, 5));
        panelTableContainer.setBorder(BorderFactory.createTitledBorder(
                BorderFactory.createLineBorder(new Color(128, 0, 64)), 
                "Data Peserta", 
                javax.swing.border.TitledBorder.DEFAULT_JUSTIFICATION, 
                javax.swing.border.TitledBorder.DEFAULT_POSITION, 
                new Font("Segoe UI", Font.BOLD, 12), 
                new Color(128, 0, 64)
        ));

        JPanel panelSearch = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        panelSearch.add(new JLabel("Pencarian"));
        txtPencarian = new JTextField(25);
        btnCari = new JButton("Cari");
        panelSearch.add(txtPencarian);
        panelSearch.add(btnCari);
        panelTableContainer.add(panelSearch, BorderLayout.NORTH);

        // Inisialisasi JTable dengan DefaultTableModel
        String[] columns = {"NIM", "Nama", "JK", "Prodi", "No HP", "Email"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Tabel tidak bisa diedit langsung secara manual
            }
        };
        tablePeserta = new JTable(tableModel);
        JScrollPane scrollTable = new JScrollPane(tablePeserta);
        panelTableContainer.add(scrollTable, BorderLayout.CENTER);

        panelMain.add(panelTableContainer, BorderLayout.CENTER);
        add(panelMain, BorderLayout.CENTER);

        // ================= EVENT LISTENERS =================
        btnSimpan.addActionListener(e -> prosesSimpan());
        btnUbah.addActionListener(e -> prosesUbah());
        btnHapus.addActionListener(e -> prosesHapus());
        btnReset.addActionListener(e -> resetForm());
        btnCari.addActionListener(e -> loadDataTabel(txtPencarian.getText().trim()));
        btnCetakSemua.addActionListener(e -> JOptionPane.showMessageDialog(this, "Fitur Cetak Semua diaktifkan pada pertemuan laporan."));

        // Event Klik Baris Tabel untuk Mengisi Form
        tablePeserta.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                int rowIdx = tablePeserta.getSelectedRow();
                if (rowIdx != -1) {
                    txtNim.setText(tableModel.getValueAt(rowIdx, 0).toString());
                    txtNim.setEnabled(false); // NIM Primary Key tidak boleh diubah saat mode Edit
                    txtNama.setText(tableModel.getValueAt(rowIdx, 1).toString());
                    
                    String jk = tableModel.getValueAt(rowIdx, 2).toString();
                    if ("L".equalsIgnoreCase(jk) || "Laki-Laki".equalsIgnoreCase(jk)) {
                        rbLaki.setSelected(true);
                    } else {
                        rbPerempuan.setSelected(true);
                    }
                    
                    txtProdi.setText(tableModel.getValueAt(rowIdx, 3).toString());
                    txtNoHp.setText(tableModel.getValueAt(rowIdx, 4).toString());
                    txtEmail.setText(tableModel.getValueAt(rowIdx, 5).toString());
                }
            }
        });
    }

    // Method untuk Load Data dari Database ke JTable
    private void loadDataTabel(String keyword) {
        tableModel.setRowCount(0); // Kosongkan tabel dulu
        String sql = "SELECT * FROM peserta WHERE nim LIKE ? OR nama LIKE ?";
        
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, "%" + keyword + "%");
            ps.setString(2, "%" + keyword + "%");
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Object[] row = {
                        rs.getString("nim"),
                        rs.getString("nama"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("prodi"),
                        rs.getString("no_hp"),
                        rs.getString("email")
                    };
                    tableModel.addRow(row);
                }
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal memuat data dari database: " + ex.getMessage(), "Database Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method Proses Simpan Data ke Database
    private void prosesSimpan() {
        if (txtNim.getText().trim().isEmpty() || txtNama.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "NIM dan Nama Peserta wajib diisi!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "INSERT INTO peserta (nim, nama, jenis_kelamin, prodi, no_hp, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txtNim.getText().trim());
            ps.setString(2, txtNama.getText().trim());
            ps.setString(3, rbLaki.isSelected() ? "L" : "P");
            ps.setString(4, txtProdi.getText().trim());
            ps.setString(5, txtNoHp.getText().trim());
            ps.setString(6, txtEmail.getText().trim());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data peserta berhasil disimpan!");
            loadDataTabel("");
            resetForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal menyimpan data (NIM mungkin sudah terdaftar): " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method Proses Ubah Data
    private void prosesUbah() {
        if (txtNim.isEnabled()) {
            JOptionPane.showMessageDialog(this, "Pilih data pada tabel terlebih dahulu untuk mengubah!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        String sql = "UPDATE peserta SET nama=?, jenis_kelamin=?, prodi=?, no_hp=?, email=? WHERE nim=?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, txtNama.getText().trim());
            ps.setString(2, rbLaki.isSelected() ? "L" : "P");
            ps.setString(3, txtProdi.getText().trim());
            ps.setString(4, txtNoHp.getText().trim());
            ps.setString(5, txtEmail.getText().trim());
            ps.setString(6, txtNim.getText().trim());
            
            ps.executeUpdate();
            JOptionPane.showMessageDialog(this, "Data peserta berhasil diubah!");
            loadDataTabel("");
            resetForm();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Gagal mengubah data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Method Proses Hapus Data
    private void prosesHapus() {
        if (txtNim.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(this, "Pilih data yang ingin dihapus!", "Peringatan", JOptionPane.WARNING_MESSAGE);
            return;
        }

        int confirm = JOptionPane.showConfirmDialog(this, "Apakah Anda yakin ingin menghapus data dengan NIM " + txtNim.getText() + "?", "Konfirmasi Hapus", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            String sql = "DELETE FROM peserta WHERE nim=?";
            try (Connection conn = Database.getConnection();
                 PreparedStatement ps = conn.prepareStatement(sql)) {
                ps.setString(1, txtNim.getText().trim());
                ps.executeUpdate();
                JOptionPane.showMessageDialog(this, "Data peserta berhasil dihapus!");
                loadDataTabel("");
                resetForm();
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Gagal menghapus data: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }

    // Method Reset Form
    private void resetForm() {
        txtNim.setText("");
        txtNim.setEnabled(true); // Aktifkan kembali NIM agar bisa diisi untuk data baru
        txtNama.setText("");
        bgJK.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtKodeAkses.setText("");
        cmbKursus.setSelectedIndex(0);
        cbModul.setSelected(false);
        cbSertifikat.setSelected(false);
        cbRekaman.setSelected(false);
        txtRingkasan.setText("");
        txtPencarian.setText("");
        tablePeserta.clearSelection();
    }
}