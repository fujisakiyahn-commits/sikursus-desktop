package dao;

import config.Database;
import model.Peserta;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class PesertaDAO {

    // 1. INSERT (Menyimpan data peserta baru)
    public boolean insert(Peserta p) throws SQLException {
        String sql = "INSERT INTO peserta (nim, nama, jenis_kelamin, prodi, no_hp, email) VALUES (?, ?, ?, ?, ?, ?)";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getProdi());
            ps.setString(5, p.getNoHp());
            ps.setString(6, p.getEmail());
            return ps.executeUpdate() > 0;
        }
    }

    // 2. GETALL / FINDALL (Menampilkan seluruh data ke JTable)
    public List<Peserta> getAll() throws SQLException {
        List<Peserta> list = new ArrayList<>();
        String sql = "SELECT * FROM peserta";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {
            while (rs.next()) {
                Peserta p = new Peserta(
                    rs.getString("nim"),
                    rs.getString("nama"),
                    rs.getString("jenis_kelamin"),
                    rs.getString("prodi"),
                    rs.getString("no_hp"),
                    rs.getString("email")
                );
                list.add(p);
            }
        }
        return list;
    }

    // 3. UPDATE (Mengubah data berdasarkan NIM)
    public boolean update(Peserta p) throws SQLException {
        String sql = "UPDATE peserta SET nama = ?, jenis_kelamin = ?, prodi = ?, no_hp = ?, email = ? WHERE nim = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNama());
            ps.setString(2, p.getJenisKelamin());
            ps.setString(3, p.getProdi());
            ps.setString(4, p.getNoHp());
            ps.setString(5, p.getEmail());
            ps.setString(6, p.getNim());
            return ps.executeUpdate() > 0;
        }
    }

    // 4. DELETE (Menghapus data berdasarkan NIM)
    public boolean delete(String nim) throws SQLException {
        String sql = "DELETE FROM peserta WHERE nim = ?";
        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, nim);
            return ps.executeUpdate() > 0;
        }
    }

    // 5. SEARCH (Mencari data berdasarkan NIM, Nama, atau Prodi)
    public List<Peserta> search(String keyword) throws SQLException {
        List<Peserta> list = new ArrayList<>();
        String sql = "SELECT * FROM peserta WHERE nim LIKE ? OR nama LIKE ? OR prodi LIKE ?";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            String pattern = "%" + keyword + "%";
            ps.setString(1, pattern);
            ps.setString(2, pattern);
            ps.setString(3, pattern);
            
            try (ResultSet rs = ps.executeQuery()) {
                while (rs.next()) {
                    Peserta p = new Peserta(
                        rs.getString("nim"),
                        rs.getString("nama"),
                        rs.getString("jenis_kelamin"),
                        rs.getString("prodi"),
                        rs.getString("no_hp"),
                        rs.getString("email")
                    );
                    list.add(p);
                }
            }
        }
        return list;
    }
}