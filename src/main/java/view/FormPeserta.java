package view;

import Model.peserta;
import dao.PesertaDAO;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.SwingUtilities;

public class FormPeserta extends JFrame {

    private final JTextField txtNim = new JTextField(20);
    private final JTextField txtNama = new JTextField(20);
    private final JRadioButton rbLaki = new JRadioButton("Laki-laki");
    private final JRadioButton rbPerempuan = new JRadioButton("Perempuan");
    private final ButtonGroup buttonGroupJenisKelamin = new ButtonGroup();
    private final JTextField txtProdi = new JTextField(20);
    private final JTextField txtNoHp = new JTextField(20);
    private final JTextField txtEmail = new JTextField(20);
    private final JButton btnSimpan = new JButton("Simpan");
    private final JButton btnReset = new JButton("Reset");

    public FormPeserta() {
        setTitle("Form Peserta - SIKURSUS");
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        buttonGroupJenisKelamin.add(rbLaki);
        buttonGroupJenisKelamin.add(rbPerempuan);

        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        GridBagConstraints c = new GridBagConstraints();
        c.insets = new Insets(6, 6, 6, 6);
        c.anchor = GridBagConstraints.WEST;

        JPanel panelJk = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelJk.add(rbLaki);
        panelJk.add(rbPerempuan);

        JPanel panelTombol = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 0));
        panelTombol.add(btnSimpan);
        panelTombol.add(btnReset);

        tambahBaris(panel, c, 0, "NIM", txtNim);
        tambahBaris(panel, c, 1, "Nama", txtNama);
        tambahBaris(panel, c, 2, "Jenis Kelamin", panelJk);
        tambahBaris(panel, c, 3, "Prodi", txtProdi);
        tambahBaris(panel, c, 4, "No. HP", txtNoHp);
        tambahBaris(panel, c, 5, "Email", txtEmail);
        tambahBaris(panel, c, 6, "", panelTombol);

        btnSimpan.addActionListener(e -> simpan());
        btnReset.addActionListener(e -> clearForm());

        setContentPane(panel);
        pack();
        setLocationRelativeTo(null);
    }

    private void tambahBaris(JPanel p, GridBagConstraints c, int baris,
                             String label, JComponent isi) {
        c.gridx = 0;
        c.gridy = baris;
        p.add(new JLabel(label), c);
        c.gridx = 1;
        p.add(isi, c);
    }

    private void clearForm() {
        txtNim.setText("");
        txtNama.setText("");
        buttonGroupJenisKelamin.clearSelection();
        txtProdi.setText("");
        txtNoHp.setText("");
        txtEmail.setText("");
        txtNim.requestFocus();
    }

    private void simpan() {
        String nim = txtNim.getText().trim();
        String nama = txtNama.getText().trim();
        String prodi = txtProdi.getText().trim();
        String noHp = txtNoHp.getText().trim();
        String email = txtEmail.getText().trim();

        String jenisKelamin = "";
        if (rbLaki.isSelected()) {
            jenisKelamin = "L";
        } else if (rbPerempuan.isSelected()) {
            jenisKelamin = "P";
        }

        if (nim.isEmpty() || nama.isEmpty() || prodi.isEmpty()
                || jenisKelamin.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "NIM, nama, jenis kelamin, dan prodi wajib diisi.",
                    "Validasi", JOptionPane.WARNING_MESSAGE);
            return;
        }

        // id = 0 karena id_peserta diisi otomatis oleh MySQL (AUTO_INCREMENT)
        peserta p = new peserta(0, nama, noHp, nim, prodi, jenisKelamin, email);
        PesertaDAO dao = new PesertaDAO();

        try {
            if (dao.insert(p)) {
                JOptionPane.showMessageDialog(this,
                        "Data peserta berhasil disimpan.");
                clearForm();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Data tidak tersimpan.", "Informasi",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (java.sql.SQLException e) {
            JOptionPane.showMessageDialog(this,
                    "Gagal menyimpan data:\n" + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
           e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new FormPeserta().setVisible(true));
    }
}