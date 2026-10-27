package dao;

import Model.peserta;
import config.Database;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class PesertaDAO {

    public boolean insert(peserta p) throws SQLException {
        String sql = "INSERT INTO peserta "
                   + "(nim, nama, jenis_kelamin, prodi, no_hp, email) "
                   + "VALUES (?, ?, ?, ?, ?, ?)";

        try (Connection conn = Database.getConnection();
             PreparedStatement ps = conn.prepareStatement(sql)) {
            ps.setString(1, p.getNim());
            ps.setString(2, p.getNama());          // diwarisi dari orang
            ps.setString(3, p.getJenisKelamin());
            ps.setString(4, p.getProdi());
            ps.setString(5, p.getNoHp());          // diwarisi dari orang
            ps.setString(6, p.getEmail());
            return ps.executeUpdate() == 1;
        }
    }
}