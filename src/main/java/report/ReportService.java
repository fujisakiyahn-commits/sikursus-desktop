package report;

import config.Database;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.Date;
import java.util.HashMap;
import java.util.Map;
import javax.swing.JOptionPane;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.view.JasperViewer;

public class ReportService {

    // Lokasi file .jasper di src/main/resources/report/
    private static final String FILE_PESERTA     = "/report/laporan_peserta.jasper";
    private static final String FILE_PENDAFTARAN = "/report/laporan_pendaftaran.jasper";
    private static final String FILE_PEMBAYARAN  = "/report/laporan_pembayaran.jasper";

    // Cetak 1 pendaftaran berdasarkan ID (dipakai dari form Pendaftaran)
    public static void cetakPendaftaran(int idPendaftaran) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PENDAFTARAN", idPendaftaran);
        tampilkanReport(FILE_PENDAFTARAN, params, "Data pendaftaran tidak ditemukan.");
    }

    // Cetak laporan pembayaran berdasarkan rentang tanggal
    public static void cetakPembayaran(Date tanggalAwal, Date tanggalAkhir) {
        Map<String, Object> params = new HashMap<>();
        params.put("P_TANGGAL_AWAL", tanggalAwal);
        params.put("P_TANGGAL_AKHIR", tanggalAkhir);
        tampilkanReport(FILE_PEMBAYARAN, params, "Tidak ada transaksi pembayaran pada rentang tanggal tersebut.");
    }

    // Menu Laporan > Laporan Peserta (dibuat static agar bisa dipanggil langsung)
    public static void tampilkanLaporanPeserta() {
        tampilkanReport(FILE_PESERTA, new HashMap<>(), "Data peserta belum ada.");
    }

    // Menu Laporan > Laporan Pendaftaran (tanpa filter ID = semua data)
    public static void tampilkanLaporanPendaftaran() {
        Map<String, Object> params = new HashMap<>();
        params.put("P_ID_PENDAFTARAN", null);
        tampilkanReport(FILE_PENDAFTARAN, params, "Data pendaftaran belum ada.");
    }

    // Menu Laporan > Laporan Pembayaran (Versi tanpa parameter jika dibuka dari menu utama)
    public static void tampilkanLaporanPembayaran() {
        tampilkanReport(FILE_PEMBAYARAN, new HashMap<>(), "Data pembayaran belum ada.");
    }

    // Method bersama: isi report dengan koneksi database lalu tampilkan di JasperViewer
    private static void tampilkanReport(String resourcePath, Map<String, Object> params, String pesanKosong) {
        try (Connection conn = Database.getConnection();
             InputStream reportStream = ReportService.class.getResourceAsStream(resourcePath)) {

            if (reportStream == null) {
                throw new IllegalStateException("File " + resourcePath + " tidak ditemukan di folder resource.");
            }

            JasperPrint print = JasperFillManager.fillReport(reportStream, params, conn);
            if (print.getPages().isEmpty()) {
                JOptionPane.showMessageDialog(null, pesanKosong);
                return;
            }
            JasperViewer.viewReport(print, false);
        } catch (Exception ex) {
            ex.printStackTrace();
            JOptionPane.showMessageDialog(null, "Gagal menampilkan laporan: " + ex.getMessage());
        }
    }
}